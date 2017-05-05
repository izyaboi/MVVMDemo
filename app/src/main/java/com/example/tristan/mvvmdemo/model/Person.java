package com.example.tristan.mvvmdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tristan on 04.05.2017.
 */

public class Person implements Parcelable {

    @SerializedName("persons")
    @Expose
    public List<Person_> persons = null;

    public List<Person_> getPersons() {
        return persons;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.persons);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.persons = in.createTypedArrayList(Person_.CREATOR);
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
