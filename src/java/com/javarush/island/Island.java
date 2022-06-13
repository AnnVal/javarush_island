package com.javarush.island;

import com.javarush.island.guidebook.InfoTables;
import com.javarush.island.items.animals.Animal;
import com.javarush.island.items.animals.carnivores.*;
import com.javarush.island.items.animals.herbivores.*;
import com.javarush.island.items.plants.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    private static final int FIELD_HEIGHT = 20;
    private static final int FIELD_WIDTH = 100;
    private Cell[][] field = new Cell[FIELD_HEIGHT][FIELD_WIDTH];
    static List<Class<? extends Animal>> possibleInhabitants = new ArrayList<>();

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
                int plantAmount = ThreadLocalRandom.current().nextInt(InfoTables.getPlantsMaxAmountOnCell(Plant.class));
                for (int k = 0; k < plantAmount; k++) {
                    field[i][j].getPlantsOnCell().add(new Plant(j, i,field));
                }
                for (Class<? extends Animal> clazz : possibleInhabitants) {
                    try {
                        int amount = ThreadLocalRandom.current().nextInt(InfoTables.getMaxAmountOnCell(clazz));
                        Constructor constructor = clazz.getConstructor(int.class, int.class, Cell[][].class);
                        for (int k = 0; k < amount; k++) {
                            field[i][j].getAnimalsOnCell().add((Animal) constructor.newInstance(j, i,field));
                        }
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        System.out.println("Some problem during creating island ");
                    }
                }
            }
        }
    }



}
