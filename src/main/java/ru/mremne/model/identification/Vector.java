package ru.mremne.model.identification;

/**
 * autor:maksim
 * date: 08.05.15
 * time: 19:41.
 */
public class Vector implements Comparable<Vector> {
    private double x;
    private double y;
    public Vector(){}
    public Vector(double x,double y){
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
    public int compareTo(Vector o) {
        if(o.getX()!=0 || o.getY()!=0) {
            if ((x / o.getX() == -1 && y / o.getY() == -1) || (x / o.getX() == 1 && y / o.getY() == 1)) {
                return 1;
            } else return 0;
        }return 1;
    }
}
