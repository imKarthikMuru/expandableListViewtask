package com.example.task.viewModels;

import androidx.lifecycle.LiveData;

import com.example.task.Models.StoreMenu;
import com.example.task.repos.NetWorkRepo;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {

    NetWorkRepo repo=new NetWorkRepo();

    public LiveData<List<StoreMenu>> getStoreMenuList(){
        return repo.getStoreMenus();
    }

}
