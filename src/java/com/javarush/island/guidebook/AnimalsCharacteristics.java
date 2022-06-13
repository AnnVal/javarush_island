package com.javarush.island.guidebook;

public class AnimalsCharacteristics {
    public final double WEIGHT;
    public final int MAX_AMOUNT_ON_CELL;
    public final int MAX_SPEED;
    public final double FOOD_AMOUNT_TILL_SATURATION;

    public AnimalsCharacteristics(double weigth, int maxAmountOnCell, int maxSpeed, double foodAmountTillSaturation) {
        this.WEIGHT = weigth;
        this.MAX_AMOUNT_ON_CELL = maxAmountOnCell;
        this.MAX_SPEED = maxSpeed;
        this.FOOD_AMOUNT_TILL_SATURATION = foodAmountTillSaturation;
    }
}
