package com.javarush.island;

import com.javarush.island.guidebook.InfoTables;
import com.javarush.island.items.Item;
import com.javarush.island.items.animals.Animal;
import com.javarush.island.items.plants.Plant;
import com.javarush.island.usefullThings.StatisticsCounter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Island {
    private static final int FIELD_HEIGHT = 20;
    private static final int FIELD_WIDTH = 20;
    private Cell[][] field;
    private StatisticsCounter statisticsCounter;


    public Island() {
        field = new Cell[FIELD_HEIGHT][FIELD_WIDTH];
        statisticsCounter = new StatisticsCounter();
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
            field[i][j].getPlantsOnCell().add(new Plant(j, i, this.field, this.statisticsCounter));
        }
    }

    private void initializeAnimals(int i, int j) {
        for (Class<? extends Animal> clazz : InfoTables.getPossibleInhabitants()) {
            try {
                int amount = ThreadLocalRandom.current().nextInt(InfoTables.getMaxAmountOnCell(clazz));
                Constructor constructor = clazz.getConstructor(int.class, int.class, Cell[][].class, StatisticsCounter.class);
                for (int k = 0; k < amount; k++) {
                    field[i][j].getAnimalsOnCell().add((Animal) constructor.newInstance(j, i, this.field, this.statisticsCounter));
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                System.out.println("Some problem during creating island and new animal " + clazz.getSimpleName() + " by reflection" + ex.getMessage() + " " + ex.getCause());
            }
        }
    }

    public Cell[][] getField() {
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
                    for (Animal animal : island.field[i][j].getAnimalsOnCell())
                        animal.performAction();
                }
            }
        };

        Runnable statisticsTask = () -> {
            System.out.println("______________");
            System.out.println("have born: " + island.statisticsCounter.birthCounter.get());
            System.out.println("died: " + island.statisticsCounter.deathCounter.get());
            System.out.println("eaten plants " + island.statisticsCounter.eatenPlantsCounter.get());
            System.out.println("born animals: " + island.statisticsCounter.bornAnimals);
            System.out.println("eaten animals: " + island.statisticsCounter.eatenAnimals);
            System.out.println("died animals: " + island.statisticsCounter.deadAnimals);
            island.statisticsCounter.clear();
        };

       /* ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(plantGrowingTask, 0, 60, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(animalsActionTask, 10, 60, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(statisticsTask, 20, 60, TimeUnit.SECONDS);
        try {
            Thread.sleep(200000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception in maim while sleeping");
        }
        scheduledExecutorService.shutdown();
*/

        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                for (Animal animal : island.field[i][j].getAnimalsOnCell()) {
                    animal.performAction();
                }
            }
        }

        System.out.println("______________");
        System.out.println("have born: " + island.statisticsCounter.birthCounter.get());
        System.out.println("died: " + island.statisticsCounter.deathCounter.get());
        System.out.println("eaten plants " + island.statisticsCounter.eatenPlantsCounter.get());
        System.out.println("born animals: " + island.statisticsCounter.bornAnimals);
        System.out.println("eaten animals: " + island.statisticsCounter.eatenAnimals);
        System.out.println("died animals: " + island.statisticsCounter.deadAnimals);
        System.out.println(new Date());

    }
}
