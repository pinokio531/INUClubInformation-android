package com.ourincheon.app_center.network;

import com.ourincheon.app_center.model.club_info;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by SanJuku on 2018-02-22.
 */

public interface NetworkService {
    @FormUrlEncoded
    @POST("/user/login")
    Call<String> getLogin(@Field("id") String id,
                          @Field("pw") String pw);

    @GET("/club/category/{category}")
    Call< List<club_info>> getClubInfo(@Query("idx_category") club_info idx_category);



}
