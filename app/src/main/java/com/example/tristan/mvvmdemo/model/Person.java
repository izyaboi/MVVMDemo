package com.example.tristan.mvvmdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tristan on 04.05.2017.
 */

public class Person {

    @SerializedName("persons")
    @Expose
    private List<Person_> persons = null;

    public List<Person_> getPersons() {
        return persons;
    }

    public void setPersons(List<Person_> persons) {
        this.persons = persons;
    }
}
