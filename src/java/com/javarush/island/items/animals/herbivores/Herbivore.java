package com.javarush.island.items.animals.herbivores;

import com.javarush.island.Cell;
import com.javarush.island.items.animals.Animal;
import com.javarush.island.usefullThings.StatisticsCounter;

public abstract class Herbivore extends Animal {
    public Herbivore(int x, int y, Cell[][] field, StatisticsCounter statisticsCounter) {
        super(x, y,field,statisticsCounter);
    }
}
