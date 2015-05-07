package ru.mremne.model.identification;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * autor:maksim
 * date: 10.01.15
 * time: 1:42.
 */
public class ResultPoints {
    private static final Logger LOG =Logger.getLogger(ResultPoints.class);
    public static final int ERROR=60;
    private int x;
    private int y;

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


    public static SortedSet<Double> getAngleValue(List<Point> points){
      List<double[]> vectors=new ArrayList<>();
      for(int i=0;i<points.size()-1;i++){
          for(int j=1;j<points.size();j++){
              vectors.add(new double[]{points.get(i).getX() - points.get(j).getX(),
                                    points.get(i).getY() - points.get(j).getY()});
          }
      }
     SortedSet<Double> angles=new TreeSet<>();
     Double d;
     double[] vectorA=new double[2];
     double[] vectorB=new double[2];
     for(int i=0;i<vectors.size()-1;i++) {
         for (int j = 1; j < vectors.size(); j++) {
             vectorA[0] = vectors.get(i)[0];
             vectorA[1] = vectors.get(i)[1];
             vectorB[0] = vectors.get(j)[0];
             vectorB[1] = vectors.get(j)[1];
             d = (vectorA[0] * vectorB[0] + vectorA[1] * vectorB[1]) /
                     (Math.sqrt(vectorA[0] * vectorA[0] + vectorA[1] * vectorA[1])
                             * Math.sqrt(vectorB[0] * vectorB[0] + vectorB[1] * vectorB[1]));
              angles.add(roundDouble(180 * (Math.acos(d) / Math.PI),0));
         }
     }
     return angles;
 }
    public static int extractMaxLevel(String originalStr)
    {
        Pattern pattern = Pattern.compile("level=(\\w+?)}");
        Matcher matcher = pattern.matcher(originalStr);
        int l=0;
        while (matcher.find()) {
            if(Integer.parseInt(matcher.group(1))>l){
                l=Integer.parseInt(matcher.group(1));
            }
        }
        return l;
    }
    public static double roundDouble(double d, int n) {
        return java.lang.Math.round(d * java.lang.Math.pow(10, (double) n)) / java.lang.Math.pow(10, (double) n);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
