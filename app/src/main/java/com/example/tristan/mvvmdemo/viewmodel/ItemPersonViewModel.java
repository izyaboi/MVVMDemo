package com.example.tristan.mvvmdemo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.example.tristan.mvvmdemo.model.Person;
import com.example.tristan.mvvmdemo.model.Person_;
import com.squareup.picasso.Picasso;

/**
 * Created by Tristan on 05.05.2017.
 */

public class ItemPersonViewModel extends BaseObservable  implements ViewModel {


    private Person_ person_;
    private Context context;

    public ItemPersonViewModel(Context context, Person_ person_) {
        this.context = context;
        this.person_ = person_;
    }

    public String getFirstName() {
        return person_.firstname;
    }

    public String getLastName(){
        return person_.lastname;
    }

    public String getImageUrl() {
        return person_.image;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @Override
    public void destroy() {
        //no op
    }

    public void setPerson(Person_ person_) {
        this.person_ = person_;
        notifyChange();
    }
}
