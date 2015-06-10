package com.woowahan.intern.internproject.main;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2015. 6. 7..
 */
public class Recommend {

    @SerializedName("Name")
    private String Name;

    @SerializedName("Img")
    private String Img;

    @SerializedName("Store_Category_Code")
    private int category;

    public Recommend(String name, String img, int category) {
        Name = name;
        Img = img;
        this.category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
