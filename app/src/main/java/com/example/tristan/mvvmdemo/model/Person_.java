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
    private Integer id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
