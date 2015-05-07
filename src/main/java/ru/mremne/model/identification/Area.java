package ru.mremne.model.identification;

import java.util.ArrayList;
import java.util.List;

/**
 * autor:maksim
 * date: 07.05.15
 * time: 18:41.
 */
public class Area {
    private Point comparablePoint;
    private List<Point> points;
    public Area(Point p){
        comparablePoint =p;
        points=new ArrayList<>();
        points.add(comparablePoint);
    }

    public Point getComparablePoint() {
        return comparablePoint;
    }

    public void setComparablePoint(Point comparablePoint) {
        this.comparablePoint = comparablePoint;
    }

    public Point getResultPoint() {
        Point resPoint;
        double x=0,y=0;
        int size=points.size();
        for(Point p:points){
            x+=p.getX();
            y+=p.getY();
        }
        resPoint=new Point(x/size,y/size);
        return resPoint;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }
}
