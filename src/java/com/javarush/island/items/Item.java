package com.javarush.island.items;

import com.javarush.island.Cell;
import com.javarush.island.Island;
import com.javarush.island.guidebook.InfoTables;
import com.javarush.island.usefullThings.StatisticsCounter;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Item {
    protected  Cell[][] field ;
    protected  StatisticsCounter statisticsCounter ;

    protected int x;
    protected int y;



    public Item(int x, int y, Cell[][] field, StatisticsCounter statisticsCounter) {
        this.x = x;
        this.y = y;
        this.field = field;
        this.statisticsCounter=statisticsCounter;

    }


}
