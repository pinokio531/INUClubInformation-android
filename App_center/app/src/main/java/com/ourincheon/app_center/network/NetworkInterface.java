package com.ourincheon.app_center.network;

import com.google.gson.JsonObject;
import com.ourincheon.app_center.model.ErrorMsgResult;
import com.ourincheon.app_center.model.LoginData;
import com.ourincheon.app_center.model.ModifyClubInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {
    @GET("club/search?")
    Call<ArrayList<JsonObject>> getInformation(@Query("keyword") String clubname);

    @GET("/club/info/{clubnum}")
    Call<ArrayList<JsonObject>> getDetailInformation(@Path("clubnum") int num);

    @POST("/club/info/{club}")
    Call<ErrorMsgResult> giveModifiedContents(@Path("club") String num2, @Body ModifyClubInfo modifyClubInfo);

    @POST("user/login")
    Call<String> getLoginInfo(@Body LoginData loginData);
}
