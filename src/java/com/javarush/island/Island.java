package com.javarush.island;

import com.javarush.island.guidebook.InfoTables;
import com.javarush.island.items.Item;
import com.javarush.island.items.animals.Animal;
import com.javarush.island.items.animals.carnivores.*;
import com.javarush.island.items.animals.herbivores.*;
import com.javarush.island.items.plants.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Island {
    private static final int FIELD_HEIGHT = 20;
    private static final int FIELD_WIDTH = 20;
    private static Cell[][] field = new Cell[FIELD_HEIGHT][FIELD_WIDTH];
    public static List<Class<? extends Animal>> possibleInhabitants = new ArrayList<>();


    static {
        possibleInhabitants.add(Bear.class);
        possibleInhabitants.add(Boa.class);
        possibleInhabitants.add(Eagle.class);
        possibleInhabitants.add(Fox.class);
        possibleInhabitants.add(Wolf.class);
        possibleInhabitants.add(Boar.class);
        possibleInhabitants.add(Buffalo.class);
        possibleInhabitants.add(Caterpillar.class);
        possibleInhabitants.add(Deer.class);
        possibleInhabitants.add(Duck.class);
        possibleInhabitants.add(Goat.class);
        possibleInhabitants.add(Horse.class);
        possibleInhabitants.add(Mouse.class);
        possibleInhabitants.add(Rabbit.class);
        possibleInhabitants.add(Sheep.class);

    }

    public Island() {
        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                field[i][j] = new Cell(j, i);
                growPlants(i, j);
                initializeAnimals(i, j);
            }
        }
    }

    private void growPlants(int i, int j) {
        int plantAmount = ThreadLocalRandom.current().nextInt(InfoTables.getPlantsMaxAmountOnCell(Plant.class));
        for (int k = 0; k < plantAmount; k++) {
            field[i][j].getPlantsOnCell().add(new Plant(j, i));
        }
    }

    private void initializeAnimals(int i, int j) {
        for (Class<? extends Animal> clazz : possibleInhabitants) {
            try {
                int amount = ThreadLocalRandom.current().nextInt(InfoTables.getMaxAmountOnCell(clazz));
                Constructor constructor = clazz.getConstructor(int.class, int.class);
                for (int k = 0; k < amount; k++) {
                    field[i][j].getAnimalsOnCell().add((Animal) constructor.newInstance(j, i));
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                System.out.println("Some problem during creating island and new animal " + clazz.getSimpleName() + " by reflection" + ex.getMessage() + " " + ex.getCause());
            }
        }
    }

    public static Cell[][] getField() {
        return field;
    }

    public static int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    public static int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public static void main(String[] args) {
        System.out.println(new Date());
        Island island = new Island();

        Runnable plantGrowingTask = () -> {
            for (int i = 0; i < FIELD_HEIGHT; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++)
                    island.growPlants(i, j);
            }
        };
        Runnable animalsActionTask = () -> {
            for (int i = 0; i < FIELD_HEIGHT; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    for (Animal animal : island.getField()[i][j].getAnimalsOnCell())
                        animal.performAction();
                }
            }
        };

        Runnable statisticsTask = () -> {
            System.out.println("______________");
            System.out.println("have born: " + Item.statisticsCounter.birthCounter.get());
            System.out.println("died: " + Item.statisticsCounter.deathCounter.get());
            System.out.println("eaten plants " + Item.statisticsCounter.eatenPlantsCounter.get());
            System.out.println("animals on 1,1 " + island.getField()[1][1].getAnimalsOnCell().size());
            System.out.println("plants on 1,1 " + island.getField()[1][1].getPlantsOnCell().size());
            Item.statisticsCounter.clear();
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(plantGrowingTask, 0, 60, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(animalsActionTask, 10, 60, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(statisticsTask, 20, 60, TimeUnit.SECONDS);
        try {
            Thread.sleep(200000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception in maim while sleeping");
        }
        scheduledExecutorService.shutdown();


       /* for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++){
                for (Animal animal: island.getField()[i][j].getAnimalsOnCell()
                ) {
                    //if(animal.lock.tryLock())
                    animal.performAction();
                   // System.out.println("-");
                }
            }
        }

        System.out.println("______________");
        System.out.println("have born: " + Item.statisticsCounter.birthCounter.get());
        System.out.println("died: " + Item.statisticsCounter.deathCounter.get());
        System.out.println("eaten plants " + Item.statisticsCounter.eatenPlantsCounter.get());
        System.out.println("animals on 1,1 "+ island.getField()[1][1].getAnimalsOnCell().size());
        System.out.println("plants on 1,1"+ island.getField()[1][1].getPlantsOnCell().size());
        */
        System.out.println(new Date());

    }
}
