package com.javarush.island.usefullThings;

import com.javarush.island.guidebook.InfoTables;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsCounter {
    public AtomicInteger deathCounter;
    public AtomicInteger birthCounter;
    public AtomicInteger eatenPlantsCounter;
    public Map<String, AtomicInteger> bornAnimals = new HashMap<>();
    public Map<String, AtomicInteger> deadAnimals = new HashMap<>();
    public Map<String, AtomicInteger> eatenAnimals = new HashMap<>();

    public StatisticsCounter() {
        deathCounter = new AtomicInteger(0);
        birthCounter = new AtomicInteger(0);
        eatenPlantsCounter = new AtomicInteger(0);

        for (String animalName : InfoTables.getAnimalNames()) {
            this.bornAnimals.put(animalName, new AtomicInteger(0));
            this.deadAnimals.put(animalName, new AtomicInteger(0));
            this.eatenAnimals.put(animalName, new AtomicInteger(0));
        }

    }

    public void clear() {
        deathCounter.set(0);
        birthCounter.set(0);
        eatenPlantsCounter.set(0);

        for (String key : bornAnimals.keySet())
            bornAnimals.get(key).set(0);

        for (String key : deadAnimals.keySet())
            bornAnimals.get(key).set(0);

        for (String key : eatenAnimals.keySet())
            bornAnimals.get(key).set(0);

    }
}
