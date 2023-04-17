package com.example.foodnow.ui.discover;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodnow.LocationHelper;
import com.example.foodnow.Restaurant;
import com.example.foodnow.data.json.JsonReader;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscoverViewModel extends ViewModel {

    //list of restaurants
    private MutableLiveData<List<Restaurant>> items;

    public DiscoverViewModel() {
        items = new MutableLiveData<>();
    }

    public MutableLiveData<List<Restaurant>> getItems(Context ctx,LocationHelper locationHelper) {
        List<Restaurant> l = new ArrayList<>();


        JsonReader.readRestaurants(ctx,locationHelper).onSuccessTask(new SuccessContinuation<Restaurant[], Void>() {
            @NonNull
            @Override
            public Task<Void> then(Restaurant[] restaurants) throws Exception {
                items.setValue(Arrays.asList(restaurants));
                return null;
            }
        });
        return items;
    }
}