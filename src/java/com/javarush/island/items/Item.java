package com.javarush.island.items;

import com.javarush.island.Cell;
import com.javarush.island.Island;

public abstract class Item {
    static  Cell[][] field;
    int x;
    int y;

    public Item(int x, int y, Cell[][] field) {
        this.x = x;
        this.y = y;
        this.field = field;
    }
}
