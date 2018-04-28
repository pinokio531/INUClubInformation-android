package com.ourincheon.app_center;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 성민 on 2018-04-05.
 */

public class DefaultRestClient<T> {

    private T service;
    private String baseUrl = "http://13.124.254.99:3303/";

    public T getClient(Class<? extends  T> type){
        if (service == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();

                    Request request = original.newBuilder().header("ex-hader", "sample")
                            .method(original.method(), original.body()).build();

                    return chain.proceed(request);
                }
            }).build();

            Retrofit client = new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            service = client.create(type);
        }
        return service;
    }
}
