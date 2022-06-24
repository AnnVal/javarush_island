package com.javarush.island.guidebook;

import com.javarush.island.items.animals.Animal;
import com.javarush.island.items.animals.carnivores.*;
import com.javarush.island.items.animals.herbivores.*;
import com.javarush.island.items.plants.Plant;

import java.util.*;

public class InfoTables {
    private static Map<String, AnimalsCharacteristics> AnimalsCharacteristicsTable = new HashMap<>();
    private static Map<String, PlantsCharacteristics> PlantsCharacteristicsTable = new HashMap<>();
    private static Map<String, Map<String, Integer>> FoodTable = new HashMap<>();
    private static List<Class<? extends Animal>> possibleInhabitants = new ArrayList<>();

    public static double getWeight(Class<? extends Animal> animal) {
        return AnimalsCharacteristicsTable.get(animal.getSimpleName()).WEIGHT;
    }

    public static int getMaxAmountOnCell(Class<? extends Animal> animal) {
        return AnimalsCharacteristicsTable.get(animal.getSimpleName()).MAX_AMOUNT_ON_CELL;
    }

    public static int getMaxSpeed(Class<? extends Animal> animal) {
        return AnimalsCharacteristicsTable.get(animal.getSimpleName()).MAX_SPEED;
    }

    public static double getFoodAmountTillSaturation(Class<? extends Animal> animal) {
        return AnimalsCharacteristicsTable.get(animal.getSimpleName()).FOOD_AMOUNT_TILL_SATURATION;
    }

    public static int getProbabilityOfEating(Animal animalWhoEats, Animal animalWhoIsEaten) {
        return FoodTable.get(animalWhoEats.getClass().getSimpleName()).get(animalWhoIsEaten.getClass().getSimpleName());
    }

    public static Set<String> getFoodSet(Animal animal) {
        return FoodTable.get(animal.getClass().getSimpleName()).keySet();
    }

    public static double getPlantsWeight(Class<? extends Plant> plant) {
        return PlantsCharacteristicsTable.get(plant.getSimpleName()).WEIGHT;
    }

    public static int getPlantsMaxAmountOnCell(Class<? extends Plant>plant) {
        return PlantsCharacteristicsTable.get(plant.getSimpleName()).MAX_AMOUNT_ON_CELL;
    }

    public static Set<String> getAnimalNames(){
        return AnimalsCharacteristicsTable.keySet();
    }

    public static List<Class<? extends Animal>> getPossibleInhabitants(){
        return possibleInhabitants;
    }


    static {
        AnimalsCharacteristicsTable.put("Bear", new AnimalsCharacteristics(500, 5, 2, 80));
        AnimalsCharacteristicsTable.put("Boa", new AnimalsCharacteristics(15, 30, 1, 3));
        AnimalsCharacteristicsTable.put("Eagle", new AnimalsCharacteristics(6, 20, 3, 1));
        AnimalsCharacteristicsTable.put("Fox", new AnimalsCharacteristics(8, 30, 2, 2));
        AnimalsCharacteristicsTable.put("Wolf", new AnimalsCharacteristics(50, 30, 3, 8));
        AnimalsCharacteristicsTable.put("Boar", new AnimalsCharacteristics(400, 50, 2, 50));
        AnimalsCharacteristicsTable.put("Buffalo", new AnimalsCharacteristics(700, 10, 3, 100));
        AnimalsCharacteristicsTable.put("Caterpillar", new AnimalsCharacteristics(0.01, 1000, 0, 0));
        AnimalsCharacteristicsTable.put("Deer", new AnimalsCharacteristics(300, 20, 4, 50));
        AnimalsCharacteristicsTable.put("Duck", new AnimalsCharacteristics(1, 200, 4, 0.15));
        AnimalsCharacteristicsTable.put("Goat", new AnimalsCharacteristics(60, 140, 3, 10));
        AnimalsCharacteristicsTable.put("Horse", new AnimalsCharacteristics(400, 20, 4, 60));
        AnimalsCharacteristicsTable.put("Mouse", new AnimalsCharacteristics(0.05, 500, 1, 0.01));
        AnimalsCharacteristicsTable.put("Rabbit", new AnimalsCharacteristics(2, 150, 2, 0.45));
        AnimalsCharacteristicsTable.put("Sheep", new AnimalsCharacteristics(70, 140, 3, 15));
    }

    static {
        PlantsCharacteristicsTable.put("Plant", new PlantsCharacteristics(1, 200));
    }

    static {
        FoodTable.put("Bear", new HashMap<>());
        FoodTable.put("Boa", new HashMap<>());
        FoodTable.put("Eagle", new HashMap<>());
        FoodTable.put("Fox", new HashMap<>());
        FoodTable.put("Wolf", new HashMap<>());
        FoodTable.put("Boar", new HashMap<>());
        FoodTable.put("Buffalo", new HashMap<>());
        FoodTable.put("Caterpillar", new HashMap<>());
        FoodTable.put("Deer", new HashMap<>());
        FoodTable.put("Duck", new HashMap<>());
        FoodTable.put("Goat", new HashMap<>());
        FoodTable.put("Horse", new HashMap<>());
        FoodTable.put("Mouse", new HashMap<>());
        FoodTable.put("Rabbit", new HashMap<>());
        FoodTable.put("Sheep", new HashMap<>());

        FoodTable.get("Wolf").put("Horse", 10);
        FoodTable.get("Wolf").put("Deer", 15);
        FoodTable.get("Wolf").put("Rabbit", 60);
        FoodTable.get("Wolf").put("Mouse", 80);
        FoodTable.get("Wolf").put("Goat", 60);
        FoodTable.get("Wolf").put("Sheep", 70);
        FoodTable.get("Wolf").put("Boar", 15);
        FoodTable.get("Wolf").put("Buffalo", 10);
        FoodTable.get("Wolf").put("Duck", 40);

        FoodTable.get("Boa").put("Fox", 15);
        FoodTable.get("Boa").put("Rabbit", 20);
        FoodTable.get("Boa").put("Mouse", 40);
        FoodTable.get("Boa").put("Duck", 10);

        FoodTable.get("Fox").put("Rabbit", 70);
        FoodTable.get("Fox").put("Mouse", 90);
        FoodTable.get("Fox").put("Duck", 60);
        FoodTable.get("Fox").put("Caterpillar", 40);

        FoodTable.get("Bear").put("Boa", 80);
        FoodTable.get("Bear").put("Horse", 40);
        FoodTable.get("Bear").put("Deer", 80);
        FoodTable.get("Bear").put("Rabbit", 80);
        FoodTable.get("Bear").put("Mouse", 90);
        FoodTable.get("Bear").put("Goat", 70);
        FoodTable.get("Bear").put("Sheep", 70);
        FoodTable.get("Bear").put("Boar", 50);
        FoodTable.get("Bear").put("Buffalo", 20);
        FoodTable.get("Bear").put("Duck", 10);

        FoodTable.get("Eagle").put("Fox", 10);
        FoodTable.get("Eagle").put("Rabbit", 90);
        FoodTable.get("Eagle").put("Mouse", 90);
        FoodTable.get("Eagle").put("Duck", 80);

        FoodTable.get("Boar").put("Mouse", 50);
        FoodTable.get("Boar").put("Caterpillar", 90);

        FoodTable.get("Mouse").put("Caterpillar", 90);

        FoodTable.get("Duck").put("Caterpillar", 90);

    }

    static {
        possibleInhabitants.add(Bear.class);
        possibleInhabitants.add(Boa.class);
        possibleInhabitants.add(Eagle.class);
        possibleInhabitants.add(Fox.class);
        possibleInhabitants.add(Wolf.class);
        possibleInhabitants.add(Boar.class);
        possibleInhabitants.add(Buffalo.class);
        possibleInhabitants.add(Caterpillar.class);
        possibleInhabitants.add(Deer.class);
        possibleInhabitants.add(Duck.class);
        possibleInhabitants.add(Goat.class);
        possibleInhabitants.add(Horse.class);
        possibleInhabitants.add(Mouse.class);
        possibleInhabitants.add(Rabbit.class);
        possibleInhabitants.add(Sheep.class);

    }
}
