package com.example.tristan.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tristan.mvvmdemo.PersonAdapter;
import com.example.tristan.mvvmdemo.R;
import com.example.tristan.mvvmdemo.databinding.MainActivityBinding;
import com.example.tristan.mvvmdemo.model.Person;
import com.example.tristan.mvvmdemo.model.Person_;
import com.example.tristan.mvvmdemo.viewmodel.MainViewModel;

import java.util.List;

/**
 * Created by Tristan on 05.05.2017.
 */

public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {

    MainActivityBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mainViewModel = new MainViewModel(this,this);
        binding.setViewModel(mainViewModel);
        setSupportActionBar(binding.toolbar);
        setupRecyclerView(binding.recyclerView);
        mainViewModel.loadUser();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.destroy();
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        PersonAdapter adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onDataChanged(Person persons) {
        PersonAdapter adapter =
                (PersonAdapter) binding.recyclerView.getAdapter();
        adapter.setPersons(persons.getPersons());
        adapter.notifyDataSetChanged();
    }
}
