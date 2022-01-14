package com.example.nguyenhafood.API;

import com.example.nguyenhafood.Service.CheckMomoService;
import com.example.nguyenhafood.Service.MomoService;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICLinetTest {
    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://hr.softwareviet.com/api/momopayment/")
                .build();
        return retrofit;
    }
    public static MomoService momoService(){
        MomoService momoService = getRetrofit().create(MomoService.class);
        return  momoService;
    }
    public static CheckMomoService checkMomoService(){
        CheckMomoService checkMomoService = getRetrofit().create(CheckMomoService.class);
        return checkMomoService;
    }
}
