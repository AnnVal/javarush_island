package com.javarush.island;

public class Main {
    public static void main(String[] args) {
        Island island = new Island();
        Dialog dialog=new Dialog(island);
        dialog.start();
    }

}
