package com.example.tristan.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.tristan.mvvmdemo.databinding.ItemPersonBinding;
import com.example.tristan.mvvmdemo.model.Person_;
import com.example.tristan.mvvmdemo.viewmodel.ItemPersonViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tristan on 05.05.2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person_> persons;

    public PersonAdapter() {
        this.persons = Collections.emptyList();
    }

    public PersonAdapter(List<Person_> persons ) {
        this.persons = persons;
    }

    public void setPersons(List<Person_> persons) {
        this.persons = persons;
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPersonBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_person,
                parent,
                false);
        return new PersonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.bindPerson(persons.get(position));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{
        final ItemPersonBinding binding;

        public PersonViewHolder(ItemPersonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindPerson(Person_ person_) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemPersonViewModel(itemView.getContext(), person_));
            } else {
                binding.getViewModel().setPerson(person_);
            }
            binding.executePendingBindings();
        }
    }
}
