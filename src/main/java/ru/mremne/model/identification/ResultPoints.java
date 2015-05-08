package ru.mremne.model.identification;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * autor:maksim
 * date: 10.01.15
 * time: 1:42.
 */
public class ResultPoints {
    private static final Logger LOG =Logger.getLogger(ResultPoints.class);
    public static final int ERROR=60;

    private List<Area> pointList=new ArrayList<>();
    public ResultPoints() {

    }
    public void putInResultPoints(int x,int y){
        boolean check=true;
        if(!pointList.isEmpty()){
        for(int i=0;i<pointList.size();i++){
            if((pointList.get(i).getComparablePoint().getX()>x-ERROR)
                    &&(pointList.get(i).getComparablePoint().getX()<x+ERROR)
               && (pointList.get(i).getComparablePoint().getY()>y-ERROR)&&(pointList.get(i).getComparablePoint().getY()<y+ERROR)){
                LOG.error("equals error: [" + pointList.get(i).getComparablePoint().getX() + ":" + pointList.get(i).getComparablePoint().getY() + "]");
                pointList.get(i).getPoints().add(new Point(x,y));
                check=false;
            }
        }
            if(check){
                pointList.add(new Area(new Point(x,y)));
            }
        }else{
            pointList.add(new Area(new Point(x,y)));
        }
    }
    public List<Area> getPointList() {
        return pointList;
    }

}
