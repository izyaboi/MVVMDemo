package com.example.tristan.mvvmdemo.model;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Tristan on 04.05.2017.
 */

public interface ApiService {


    @GET("10pvr7")
    Observable<Person> getUser();

    class Factory {
        public static ApiService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.myjson.com/bins/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}
