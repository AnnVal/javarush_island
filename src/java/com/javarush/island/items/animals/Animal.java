package com.javarush.island.items.animals;

import com.javarush.island.items.Item;
import com.javarush.island.items.animals.carnivores.*;
import com.javarush.island.items.animals.herbivores.*;
import guidebook.InfoTables;


public class Animal extends Item {




   public static void main(String[] args) {
      Animal deer = new Deer();
      Item mouse = new Mouse();
      Boa boa = new Boa();
      System.out.println(InfoTables.AnimalsCharacteristicsTable.get(boa.getClass().getSimpleName()).WEIGHT);
      System.out.println(mouse.getClass().getSimpleName());
   }
}
