package com.example.task.Models;

import com.example.task.Utils.constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreMenu {

   @SerializedName(constants.id)
    int id;

   @SerializedName(constants.name)
    String name;

   @SerializedName(constants.menu_category)
    List<Menu> list;

   @SerializedName(constants.isChecked)
   boolean isChecked;

    public StoreMenu() {
    }

    public StoreMenu(int id, String name, List<Menu> list) {
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }
}
