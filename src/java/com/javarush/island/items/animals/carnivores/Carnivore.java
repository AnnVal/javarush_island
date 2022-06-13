package com.javarush.island.items.animals.carnivores;

import com.javarush.island.Cell;
import com.javarush.island.items.animals.Animal;

public abstract class Carnivore extends Animal {
    public Carnivore(int x, int y, Cell[][] field) {

        super(x, y, field);
    }


}
