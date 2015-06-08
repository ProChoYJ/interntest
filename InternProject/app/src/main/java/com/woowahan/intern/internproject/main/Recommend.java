package com.woowahan.intern.internproject.main;

/**
 * Created by user on 2015. 6. 7..
 */
public class Recommend {
    private String Name;
    private int Id;

    public Recommend(String name, int id) {
        Name = name;
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
