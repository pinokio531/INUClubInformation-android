package com.ourincheon.app_center.application;

import android.app.Application;

import com.ourincheon.app_center.network.NetworkInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController extends Application{
    static final String URL = "http://inuclub.us.to:3303/";
    //inuclub.us.to:3303/
    //13.124.254.99:3303/

    private static NetworkController ourInstance = new NetworkController();
    public static NetworkController getInstance(){
        return ourInstance;
    }
    private NetworkController(){

    }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);

    public NetworkInterface getNetworkInterface(){
        return networkInterface;
    }
}
