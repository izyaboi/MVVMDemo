package com.example.tristan.mvvmdemo;

import android.app.Application;
import android.content.Context;

import com.example.tristan.mvvmdemo.model.ApiService;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Tristan on 04.05.2017.
 */

public class DemoApplication extends Application {

    private ApiService apiService;
    private Scheduler defaultSubscribeScheduler;

    public static DemoApplication get(Context context) {
        return (DemoApplication) context.getApplicationContext();
    }

    public ApiService getApiService() {
        if (apiService == null) {
            apiService = ApiService.Factory.create();
        }
        return apiService;
    }

    //For setting mocks during testing
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    //User to change scheduler from tests
    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }
}
