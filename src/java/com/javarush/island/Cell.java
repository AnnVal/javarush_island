package com.javarush.island;

import com.javarush.island.items.animals.Animal;
import com.javarush.island.items.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {
    private int x;
    private int y;
    private List<Animal> animalsOnCell;
    private List<Plant> plantsOnCell;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        animalsOnCell = new CopyOnWriteArrayList<>();
        plantsOnCell = new CopyOnWriteArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public List<Animal> getAnimalsOnCell() {
        return animalsOnCell;
    }


    public List<Plant> getPlantsOnCell() {
        return plantsOnCell;
    }

    public int getAnimalCount(Animal animal) {
        Class clazz = animal.getClass();


        return getAnimalCountByClass(clazz);
    }

    public int getAnimalCountByClass(Class<? extends Animal> clazz) {
        int counter = 0;
        for (Animal an : animalsOnCell) {
            if (an.getClass().equals(clazz))
                counter++;
        }
        return counter;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y + "\n" +
                "animalsOnCell:" + "\n" + animalsOnCellAsString() +
                "Plant " + plantsOnCell.size() +
                '}';
    }

    private String animalsOnCellAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Class<? extends Animal> clazz : Island.possibleInhabitants) {
            stringBuilder.append(clazz.getSimpleName() + " " + getAnimalCountByClass(clazz) + "\n");
        }
        return stringBuilder.toString();
    }
}
