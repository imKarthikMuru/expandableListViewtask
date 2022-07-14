package com.example.task.Models;

import com.example.task.Utils.constants;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName(constants.id)
    int id;

    @SerializedName(constants.name)
    String name;

    @SerializedName(constants.isChecked)
    boolean isChecked;

    public Menu() {
    }

    public Menu(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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
}
