package com.example.tristan.mvvmdemo.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.example.tristan.mvvmdemo.App;
import com.example.tristan.mvvmdemo.R;
import com.example.tristan.mvvmdemo.model.ApiService;
import com.example.tristan.mvvmdemo.model.Person;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by Tristan on 05.05.2017.
 */

public class MainViewModel implements ViewModel {

    private static final String TAG = "MainViewModel";

    public ObservableInt infoMessageVisibility;
    public ObservableInt progressVisibility;
    public ObservableInt recyclerViewVisibility;


    public ObservableField<String> infoMessage;

    private Context context;
    private Subscription subscription;
    private Person persons;
    private DataListener dataListener;

    public MainViewModel(Context context,DataListener dataListener ) {
        this.context = context;
        this.dataListener = dataListener;
        infoMessageVisibility = new ObservableInt(View.VISIBLE);
        progressVisibility = new ObservableInt(View.INVISIBLE);
        recyclerViewVisibility = new ObservableInt(View.INVISIBLE);
        infoMessage = new ObservableField<>(context.getString(R.string.default_info_message));
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    public void loadUser() {
        progressVisibility.set(View.VISIBLE);
        recyclerViewVisibility.set(View.INVISIBLE);
        infoMessageVisibility.set(View.INVISIBLE);

        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        App application = App.get(context);
        ApiService apiService = application.getApiService();
        subscription = apiService.getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<Person>() {
                    @Override
                    public void onCompleted() {
                        if (dataListener != null) dataListener.onDataChanged(persons);
                        progressVisibility.set(View.INVISIBLE);
                        if (!persons.getPersons().isEmpty()) {
                            recyclerViewVisibility.set(View.VISIBLE);
                        } else {
                            infoMessage.set(context.getString(R.string.text_empty_persons));
                            infoMessageVisibility.set(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e(TAG, "Error loading persons ", error);
                        progressVisibility.set(View.INVISIBLE);
                        infoMessage.set(context.getString(R.string.error_loading_person));
                        infoMessageVisibility.set(View.VISIBLE);
                    }

                    @Override
                    public void onNext(Person persons) {
                        Log.i(TAG, "Person loaded " + persons);
                        MainViewModel.this.persons = persons;
                    }
                });
    }

    public interface DataListener {
        void onDataChanged(Person persons);
    }
    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        subscription = null;
        context = null;
    }
}
