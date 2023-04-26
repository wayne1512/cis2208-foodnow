package com.example.foodnow.ui.discover;

import android.content.Context;

import androidx.annotation.NonNull;
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
    private String searchString = null;

    public DiscoverViewModel() {
        items = new MutableLiveData<>();
    }

    public MutableLiveData<List<Restaurant>> getItems(Context ctx,LocationHelper locationHelper) {
        List<Restaurant> l = new ArrayList<>();


        JsonReader.readRestaurants(ctx,locationHelper).onSuccessTask(new SuccessContinuation<Restaurant[], Void>() {
            @NonNull
            @Override
            public Task<Void> then(Restaurant[] restaurants) throws Exception {
                List<Restaurant> l = new ArrayList<>(Arrays.asList(restaurants));
                if (searchString != null)
                    //filter out the items that don't match the search
                    l.removeIf(restaurant -> !restaurant.name.toLowerCase().contains(searchString.toLowerCase()));

                items.setValue(l);
                //noinspection ConstantConditions
                return null;
            }
        });
        return items;
    }

    public MutableLiveData<List<Restaurant>> getItems() {
        return items;
    }

    public void setItems(MutableLiveData<List<Restaurant>> items) {
        this.items = items;
    }


    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}