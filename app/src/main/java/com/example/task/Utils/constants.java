package com.example.task.Utils;

import com.example.task.Models.Menu;
import com.example.task.Models.StoreMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface constants {
    String base_url="https://goferjek.trioangledemo.com/api/";
    String api_call="store/store_menu?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dvZmVyamVrLnRyaW9hbmdsZWRlbW8uY29tL2FwaS9zdG9yZS9sb2dpbiIsImlhdCI6MTY1Njc0NTI2MiwiZXhwIjoxNjU5MzczMjYyLCJuYmYiOjE2NTY3NDUyNjIsImp0aSI6InBCSDFSb2l6MFl0NTdOU1EiLCJzdWIiOjEwMDI5LCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.gzTV-01ouh-RAw9irm4JrW9mD6pmsZdFlQ38W6lrNyg";
    String status="status_message";
    String status_code="status_code";
    String store_menu="store_menu";
    String menu_category="menu_category";
    String id="id";
    String name="name";
    String isChecked="isChecked";

    public class Lists{
        public static List<StoreMenu> parentItems = new ArrayList<>();
        public static List<List<Menu>> childItems = new ArrayList<>();
    }

}
