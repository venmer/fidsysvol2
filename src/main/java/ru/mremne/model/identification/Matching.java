package ru.mremne.model.identification;

/**
 * autor:maksim
 * date: 27.05.15
 * time: 18:37.
 */
public class Matching {
    private double expectedLevel;
    private double actualLevel;
    private float matching;

    public Matching(float expectedLevel, float actualLevel) {
        this.expectedLevel = expectedLevel;
        this.actualLevel = actualLevel;
        this.matching = (actualLevel / expectedLevel) * 100;
    }

    public boolean isOrigin() {
        if (expectedLevel != 0)
            return (Math.abs(expectedLevel - actualLevel) <= actualLevel);
        else return false;
    }

    public double getExpectedLevel() {
        return expectedLevel;
    }

    public void setExpectedLevel(int expectedLevel) {
        this.expectedLevel = expectedLevel;
    }

    public double getActualLevel() {
        return actualLevel;
    }

    public void setActualLevel(int actualLevel) {
        this.actualLevel = actualLevel;
    }

    public float getMatching() {
        return matching;
    }

    public void setMatching(float matching) {
        this.matching = matching;
    }
}
