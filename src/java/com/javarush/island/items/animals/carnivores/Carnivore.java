package com.javarush.island.items.animals.carnivores;

import com.javarush.island.Cell;
import com.javarush.island.items.animals.Animal;
import com.javarush.island.usefullThings.StatisticsCounter;

public abstract class Carnivore extends Animal {
    public Carnivore(int x, int y, Cell[][] field, StatisticsCounter statisticsCounter) {

        super(x, y,field,statisticsCounter);
    }


}
