package ru.mremne.model.identification;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.*;

/**
 * autor:maksim
 * date: 08.05.15
 * time: 20:58.
 */
public class FidUtils {
    private static final Logger LOG =Logger.getLogger(FidUtils.class);
    public static Double[] getAngleValue(List<Point> points){
        LOG.info("total points size: "+points.size());
        List<Vector> vectors=new ArrayList<>();
        double x;
        double y;
        for(int i=0;i<points.size();i++){
            for(int j=0;j<points.size();j++){
                if(i!=j) {
                    x = points.get(j).getX() - points.get(i).getX();
                    y = points.get(j).getY() - points.get(i).getY();
                    vectors.add(new Vector(x, y));
                }
            }
        }
        LOG.info("total vectors count: "+vectors.size());
        Set<Double> angles=new HashSet<>();
        double d;
        Vector vectorA;
        Vector vectorB;
        int tmpcount=0;
        int tmpVectorCount=0;
        for(int i=0;i<vectors.size();i++) {
            for (int j = 0; j < vectors.size(); j++) {
                vectorA=vectors.get(i);
                vectorB=vectors.get(j);
                if(i!=j && vectorA.compareTo(vectorB)==0){
                    d = (vectorA.getX() * vectorB.getX() + vectorA.getX() * vectorB.getX()) /
                            (sqrt(pow(vectorA.getX(),2) * pow(vectorA.getY(),2))
                                    * sqrt(pow(vectorB.getX(),2) + pow(vectorB.getY(),2)));
                    angles.add(roundDouble(180 * (acos(d) / PI),1));
                    LOG.info("angle: "+roundDouble(180 * (acos(d) / PI),1));
                    tmpcount++;
                }else{
                    LOG.info("vectorA{"+vectorA.getX()+";"+vectorA.getY()+"] == vectorB["+vectorB.getX()+";"+vectorB.getY()+"]");
                    tmpVectorCount++;
                }
            }
        }
        LOG.info("in count: "+tmpcount);
        LOG.info("vectors == count: "+tmpVectorCount);
        Double[] resultAngles= angles.toArray(new Double[angles.size()]);
        Arrays.sort(resultAngles);
        LOG.info("total angles size: "+resultAngles.length);
        return resultAngles;
    }
    public static int extractMaxLevel(String originalStr)
    {
        Pattern pattern = Pattern.compile("level=(\\w+?)}");
        Matcher matcher = pattern.matcher(originalStr);
        int level=0;
        while (matcher.find()) {
            if(Integer.parseInt(matcher.group(1))>level){
                level=Integer.parseInt(matcher.group(1));
                System.out.println("Current level: "+level);
            }
        }
        return level;
    }
    private static void checkVectors(List<Vector> vectors){
        for(int i=0;i<vectors.size();i++){
            for(int j=0;j<vectors.size();j++){
                if(i!= j && vectors.get(i).compareTo(vectors.get(j))==1){
                    vectors.remove(j);
                }
            }
        }

    }
    public static double roundDouble(double d, int n) {
        return round(d * pow(10, (double) n)) / pow(10, (double) n);
    }
}
