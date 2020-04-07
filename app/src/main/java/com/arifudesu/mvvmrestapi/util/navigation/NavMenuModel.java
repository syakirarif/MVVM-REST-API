package com.arifudesu.mvvmrestapi.util.navigation;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miki on 7/8/17.
 */

public class NavMenuModel {
    public String menuTitle;
    public int menuIconDrawable;
    public List<SubMenuModel> subMenu;
    public int fragment;

    public NavMenuModel(String menuTitle, int menuIconDrawable, int fragment) {
        this.menuTitle = menuTitle;
        this.menuIconDrawable = menuIconDrawable;
        this.fragment = fragment;
        this.subMenu = new ArrayList<>();
    }

    public NavMenuModel(String menuTitle, int menuIconDrawable, ArrayList<SubMenuModel> subMenu) {
        this.menuTitle = menuTitle;
        this.menuIconDrawable = menuIconDrawable;
        this.subMenu = new ArrayList<>();
        this.subMenu.addAll(subMenu);
    }

    public static class SubMenuModel {
        public String subMenuTitle;
        public int fragment;

        public SubMenuModel(String subMenuTitle, int fragment) {
            this.subMenuTitle = subMenuTitle;
            this.fragment = fragment;
        }
    }
}
