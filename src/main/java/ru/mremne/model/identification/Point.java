package ru.mremne.model.identification;

/**
 * autor:maksim
 * date: 07.05.15
 * time: 18:39.
 */
public class Point {
    private double x;
    private double y;
    public Point(){}
    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: "+x+"y: "+y;
    }
}
