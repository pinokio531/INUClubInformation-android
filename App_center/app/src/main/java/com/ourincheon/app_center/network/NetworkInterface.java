package com.ourincheon.app_center.network;

import com.google.gson.JsonObject;
import com.ourincheon.app_center.model.LoginData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkInterface {
    @GET("club/search?")
    Call<ArrayList<JsonObject>> getInformation(@Query("keyword") String clubname);

    @POST("user/login")
    Call<String> getLoginInfo(@Body LoginData loginData);
}
