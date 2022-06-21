package com.javarush.island.items.animals.herbivores;

import com.javarush.island.Cell;
import com.javarush.island.usefullThings.StatisticsCounter;

public class Boar extends Herbivore{
    public Boar(int x, int y, Cell[][] field, StatisticsCounter statisticsCounter) {
        super(x, y,field,statisticsCounter);
    }
}
