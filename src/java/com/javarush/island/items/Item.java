package com.javarush.island.items;

import com.javarush.island.Cell;
import com.javarush.island.Island;
import com.javarush.island.usefullThings.StatisticsCounter;

import java.util.concurrent.locks.ReentrantLock;

public abstract class Item {
    public static  Cell[][] field = Island.getField();
    public static StatisticsCounter statisticsCounter = new StatisticsCounter();
    protected int x;
    protected int y;
    public ReentrantLock lock;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
        lock = new ReentrantLock();
    }
}
