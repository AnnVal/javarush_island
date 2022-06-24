package com.javarush.island.items.animals.carnivores;

import com.javarush.island.Cell;
import com.javarush.island.usefullThings.StatisticsCounter;

public class Eagle extends Carnivore{
    public Eagle(int x, int y, Cell[][] field, StatisticsCounter statisticsCounter) {
        super(x, y,field,statisticsCounter);
    }
}
