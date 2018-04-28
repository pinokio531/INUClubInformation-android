package com.ourincheon.app_center.application;

import android.app.Application;

import com.ourincheon.app_center.network.NetworkService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SanJuku on 2018-02-22.
 */

public class ApplicationController extends Application {

    private static ApplicationController instance;

    private static String baseUrl = "http://13.124.254.99:3303/";

    private NetworkService networkService;

    public static ApplicationController getInstance() {
        return instance;
    }

    public NetworkService getNetworkService() {
        return networkService;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;

        buildService();
    }

    public void buildService() {


        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);
    }
}