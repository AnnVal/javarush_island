package com.javarush.island.usefullThings;

import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsCounter {
    public AtomicInteger deathCounter = new AtomicInteger(0);
    public AtomicInteger birthCounter = new AtomicInteger(0);
    public AtomicInteger eatenPlantsCounter = new AtomicInteger(0);

    public void clear()
    {
        deathCounter.set(0);
        birthCounter.set(0);
        eatenPlantsCounter.set(0);

    }
}
