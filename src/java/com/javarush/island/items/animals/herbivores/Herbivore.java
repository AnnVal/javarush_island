package com.javarush.island.items.animals.herbivores;

import com.javarush.island.Cell;
import com.javarush.island.items.animals.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(int x, int y, Cell[][] field) {
        super(x, y,field);
    }
}
