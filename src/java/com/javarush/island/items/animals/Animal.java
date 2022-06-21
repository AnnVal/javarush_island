package com.javarush.island.items.animals;

import com.javarush.island.Cell;
import com.javarush.island.Island;
import com.javarush.island.guidebook.InfoTables;
import com.javarush.island.items.Item;
import com.javarush.island.items.animals.herbivores.Herbivore;
import com.javarush.island.items.plants.Plant;
import com.javarush.island.usefullThings.Direction;
import com.javarush.island.usefullThings.StatisticsCounter;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.List;


public abstract class Animal extends Item {


    private double saturation;
    private Direction direction;


    public Animal(int x, int y, Cell[][] field, StatisticsCounter statisticsCounter) {
        super(x, y, field, statisticsCounter);
        direction = Direction.RIGHT;
        saturation = InfoTables.getFoodAmountTillSaturation(this.getClass()) == 0 ? 0 :
                ThreadLocalRandom.current().nextDouble(InfoTables.getFoodAmountTillSaturation(this.getClass()));

    }

    public void performAction() {
        getHungry();
        if (isAnimalHungry())
            eat();
        else if (ThreadLocalRandom.current().nextBoolean())
            move();
        else
            reproduce();
        checkDeathFromHunger();

    }

    private void reproduce() {
        if (isReadyForReproducing()) {
            try {
                Constructor constructor = this.getClass().getConstructor(int.class, int.class,Cell[][].class,StatisticsCounter.class);
                Animal newborn = (Animal) constructor.newInstance(x, y,field,statisticsCounter);
                field[y][x].getAnimalsOnCell().add(newborn);
                statisticsCounter.birthCounter.getAndIncrement();
                statisticsCounter.bornAnimals.get(this.getClass().getSimpleName()).incrementAndGet();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                System.out.println("problems in reproducing by reflection");
            }
        }
    }

    private boolean isReadyForReproducing() {
        return !isAnimalHungry() && isCellFreeForThisAnimal(x, y) && field[y][x].getAnimalCount(this) >= 2;
    }

    private void move() {
        if (canMove()) {
            int numberOfSteps = getRandomNumberOfSteps();
            for (int i = 1; i <= numberOfSteps; i++) {
                if (checkMovementPossibility(1))
                    relocate(1);
                else if (changeDirection())
                    relocate(1);
            }
        }
    }

    private int getRandomNumberOfSteps() {
        return ThreadLocalRandom.current().nextInt(1, InfoTables.getMaxSpeed(this.getClass()) + 1);
    }

    private void relocate(int numberOfSteps) {
        field[y][x].getAnimalsOnCell().remove(this);
        field[y + direction.dy * numberOfSteps][x + direction.dx * numberOfSteps].getAnimalsOnCell().add(this);

    }

    private void eat() {
        List<Animal> possibleFood = field[y][x].getAnimalsOnCell().stream()
                .filter(x -> InfoTables.getFoodSet(this).contains(x.getClass().getSimpleName()))
                .collect(Collectors.toList());
        if (possibleFood.size() > 0) {
            Animal animalToBeEaten = possibleFood.get(ThreadLocalRandom.current().nextInt(0, possibleFood.size()));
            int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 100);
            if (boundedRandomValue <= InfoTables.getProbabilityOfEating(this, animalToBeEaten)) {
                double amountForSaturation = getAmountForSaturation();
                double foodAmount = InfoTables.getWeight(animalToBeEaten.getClass());
                changeSaturation(amountForSaturation, foodAmount);
                field[y][x].getAnimalsOnCell().remove(animalToBeEaten);
                statisticsCounter.eatenAnimals.get(animalToBeEaten.getClass().getSimpleName()).incrementAndGet();

            }
            statisticsCounter.deathCounter.getAndIncrement();

        }
        if (this instanceof Herbivore) {
            if (!field[y][x].getPlantsOnCell().isEmpty()) {
                Plant plantToEat = field[y][x].getPlantsOnCell().get(0);
                double amountForSaturation = getAmountForSaturation();
                double foodAmount = InfoTables.getPlantsWeight(plantToEat.getClass());
                changeSaturation(amountForSaturation, foodAmount);
                field[y][x].getPlantsOnCell().remove(plantToEat);
                statisticsCounter.eatenPlantsCounter.getAndIncrement();


            }
        }
    }

    private void checkDeathFromHunger() {
        if (saturation <= 0) {
            field[y][x].getAnimalsOnCell().remove(this);
            statisticsCounter.deathCounter.getAndIncrement();
            statisticsCounter.deadAnimals.get(this.getClass().getSimpleName()).incrementAndGet();
        }
    }

    private void changeSaturation(double amountForSaturation, double foodAmount) {
        this.saturation = foodAmount >= amountForSaturation ? InfoTables.getFoodAmountTillSaturation(this.getClass()) :
                this.saturation + foodAmount;
    }

    private double getAmountForSaturation() {
        double amountForSaturation = InfoTables.getFoodAmountTillSaturation(this.getClass()) - this.saturation;
        return amountForSaturation;
    }

    private boolean changeDirection() {
        int initialDirectionNumber = direction.ordinal();
        for (int i = 1; i < 4; i++) {
            direction = Direction.values()[(initialDirectionNumber + i) % 4];
            if (checkMovementPossibility(1))
                return true;
        }
        direction = Direction.values()[initialDirectionNumber];
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    private boolean canMove() {
        int stepsNumber = InfoTables.getMaxSpeed(this.getClass());
        return stepsNumber > 0;
    }

    private boolean checkMovementPossibility(int stepsNumber) {
        int newX = this.x + this.direction.dx * stepsNumber;
        int newY = this.y + this.direction.dy * stepsNumber;
        boolean canMove = false;
        canMove = (newX >= 0 && newX < field[0].length) && (newY >= 0 && newY < field.length);
        if (canMove)
            canMove &= isCellFreeForThisAnimal(newX, newY);

        return canMove;

    }

    private boolean isCellFreeForThisAnimal(int newX, int newY) {
        return field[newY][newX].getAnimalCount(this) < InfoTables.getMaxAmountOnCell(this.getClass());
    }

    private boolean isAnimalHungry() {
        if (this.saturation < 0.5 * InfoTables.getFoodAmountTillSaturation(this.getClass()))
            return true;
        else
            return false;
    }

    private void getHungry() {
        saturation -= 0.25 * InfoTables.getFoodAmountTillSaturation(this.getClass());
    }


}

