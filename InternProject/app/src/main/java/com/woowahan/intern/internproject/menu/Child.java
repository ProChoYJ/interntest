package com.woowahan.intern.internproject.menu;

/**
 * Created by user on 2015. 6. 8..
 */
public class Child {
    private String menuName;
    private int price;

    public Child(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
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
