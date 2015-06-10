package com.woowahan.intern.internproject.menu;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2015. 6. 8..
 */
public class Child {

    @SerializedName("Group_Id")
    private int Group_Id;

    @SerializedName("Name")
    private String menuName;

    @SerializedName("Price")
    private int price;

    public Child(int Group_Id,String menuName, int price) {
        this.Group_Id = Group_Id;
        this.menuName = menuName;
        this.price = price;
    }

    public int getGroup_Id() {
        return Group_Id;
    }

    public void setGroup_Id(int group_Id) {
        Group_Id = group_Id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
