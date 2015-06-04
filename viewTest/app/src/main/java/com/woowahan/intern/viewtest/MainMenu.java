package com.woowahan.intern.viewtest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2015. 6. 4..
 */
public class MainMenu {


    @SerializedName("Id")
    int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
