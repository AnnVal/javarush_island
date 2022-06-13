package com.javarush.island.usefullThings;

public enum Direction {
    RIGHT(1,0),
    LEFT(-1,0),
    UP(0,-1),
    DOWN(0,1);
    public int dx;
    public int dy;

     Direction(int dx,int dy){
        this.dx=dx;
        this.dy=dy;
    }


}
