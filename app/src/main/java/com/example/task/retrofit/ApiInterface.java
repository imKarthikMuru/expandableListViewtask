package com.example.task.retrofit;

import com.example.task.Models.ApiResponse;
import com.example.task.Utils.constants;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(constants.base_url +constants.api_call)
    Observable<ApiResponse> getStores();


}
