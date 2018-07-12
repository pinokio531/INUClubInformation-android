package com.ourincheon.app_center.network;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkInterface {
    @GET("club")
    Call<ArrayList<JsonObject>> getInformation();
}
