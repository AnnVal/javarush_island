package com.javarush.island.items.plants;


import com.javarush.island.Cell;
import com.javarush.island.items.Item;
import com.javarush.island.usefullThings.StatisticsCounter;

public class Plant extends Item {
    public Plant(int x, int y, Cell[][] field, StatisticsCounter statisticsCounter ){

        super(x, y,field ,statisticsCounter );
    }
}
