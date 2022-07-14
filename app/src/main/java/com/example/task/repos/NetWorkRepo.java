package com.example.task.repos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.task.Models.ApiResponse;
import com.example.task.Models.StoreMenu;
import com.example.task.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NetWorkRepo {

    public static final String TAG = NetWorkRepo.class.getSimpleName();

    public static MutableLiveData<List<StoreMenu>> storeMenus;

    public LiveData<List<StoreMenu>> getStoreMenus() {
        if (storeMenus == null) {
            storeMenus = new MutableLiveData();
        }
        getStoreMenuList();
        return storeMenus;
    }

    private void getStoreMenuList() {

        Observable<ApiResponse> call = RetrofitClient
                .getInstance()
                .getApiInterface()
                .getStores();

        call.observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiresponse -> {
                            if (apiresponse.getList() != null) {
                                if (apiresponse.getList().size() != 0) {
                                    storeMenus.postValue(apiresponse.getList());
                                }
                            }
                        },
                        onError -> {
                            storeMenus.postValue(null);
                            Log.e(TAG, "getStoreMenuList: :::::::");
                            onError.printStackTrace();
                        });

    }


}
