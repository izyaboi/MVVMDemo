package com.example.tristan.mvvmdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tristan on 04.05.2017.
 */

public class Person_ implements Parcelable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("image")
    @Expose
    public String image;
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.firstname);
        dest.writeString(this.lastname);
        dest.writeString(this.image);
    }

    public Person_() {
    }

    protected Person_(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Person_> CREATOR = new Parcelable.Creator<Person_>() {
        @Override
        public Person_ createFromParcel(Parcel source) {
            return new Person_(source);
        }

        @Override
        public Person_[] newArray(int size) {
            return new Person_[size];
        }
    };
}
