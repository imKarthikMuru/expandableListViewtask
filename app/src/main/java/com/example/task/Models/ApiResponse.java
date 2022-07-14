package com.example.task.Models;

import com.example.task.Utils.constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName(constants.status)
    String status;

    @SerializedName(constants.status_code)
    int status_code;

    @SerializedName(constants.store_menu)
    List<StoreMenu> list;

    public ApiResponse(String status, int status_code, List<StoreMenu> list) {
        this.status = status;
        this.status_code = status_code;
        this.list = list;
    }

    public ApiResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public List<StoreMenu> getList() {
        return list;
    }

    public void setList(List<StoreMenu> list) {
        this.list = list;
    }
}
