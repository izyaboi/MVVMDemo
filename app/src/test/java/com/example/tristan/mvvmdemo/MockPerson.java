package com.example.tristan.mvvmdemo;

import com.example.tristan.mvvmdemo.model.Person_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tristan on 06.05.2017.
 */

public class MockPerson {

    public static List<Person_> newListOfPerson(int numRepos) {
        List<Person_> repositories = new ArrayList<>(numRepos);
        for (int i = 0; i < numRepos; i++) {
            repositories.add(newPerson_("firstName " + i, "lastName" + 1));
        }
        return repositories;
    }

    public static Person_ newPerson_(String firstName, String lastName) {

        Person_ person_ = new Person_();
        person_.firstname = firstName;
        person_.lastname = lastName;
        return person_;
    }

}
