package com.javarush.island;

import com.javarush.island.items.animals.Animal;
import com.javarush.island.items.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int x;
    private int y;
    private List<Animal> animalsOnCell;
    private List<Plant> plantsOnCell;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        animalsOnCell = new ArrayList<>();
        plantsOnCell = new ArrayList<>();
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



}
