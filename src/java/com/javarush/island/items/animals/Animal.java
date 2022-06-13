package com.javarush.island.items.animals;

import com.javarush.island.Cell;
import com.javarush.island.guidebook.InfoTables;
import com.javarush.island.items.Item;
import com.javarush.island.items.animals.carnivores.Boa;
import com.javarush.island.items.animals.herbivores.Deer;
import com.javarush.island.items.animals.herbivores.Mouse;
import com.javarush.island.usefullThings.Direction;

import java.util.concurrent.ThreadLocalRandom;


public abstract class Animal extends Item {
   private double saturation;
   private Direction direction;


   public Animal (int x, int y, Cell[][] field){
      super(x,y,field);
      direction = Direction.RIGHT;
      saturation = ThreadLocalRandom.current().nextDouble(InfoTables.getFoodAmountTillSaturation(this.getClass()));

   }





   public static void main(String[] args) {



   }
}
