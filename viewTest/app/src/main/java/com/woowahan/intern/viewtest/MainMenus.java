package com.woowahan.intern.viewtest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2015. 6. 4..
 */
public class MainMenus {

    @SerializedName("List")
    private List<MainMenu> mMainMenuList;

    public List<MainMenu> getMainMenuList() {
        return mMainMenuList;
    }
}
