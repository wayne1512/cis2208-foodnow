package com.example.foodnow.ui.discover;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodnow.Restaurant;
import com.example.foodnow.data.json.JsonReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscoverViewModel extends ViewModel {

    //list of restaurants
    private MutableLiveData<List<Restaurant>> items;

    public DiscoverViewModel() {
        items = new MutableLiveData<>();
    }

    public MutableLiveData<List<Restaurant>> getItems(Context ctx) {
        List<Restaurant> l = Arrays.asList(
                JsonReader.readRestaurants(ctx)
        );

        items.setValue(l);
        return items;
    }
}