package com.woowahan.intern.internproject.business;

/**
 * Created by user on 2015. 6. 7..
 */
public class Business {
    private int Status;
    private int Id;
    private String Name;
    private float aScore;
    private int reCount;
    private String Distance;

    public Business(int status, int id, String name, float aScore, int reCount, String distance) {
        Status = status;
        Id = id;
        Name = name;
        this.aScore = aScore;
        this.reCount = reCount;
        Distance = distance;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getaScore() {
        return aScore;
    }

    public void setaScore(float aScore) {
        this.aScore = aScore;
    }

    public int getReCount() {
        return reCount;
    }

    public void setReCount(int reCount) {
        this.reCount = reCount;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }
}
