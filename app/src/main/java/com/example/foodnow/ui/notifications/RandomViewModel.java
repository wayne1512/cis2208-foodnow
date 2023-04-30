package com.example.foodnow.ui.notifications;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodnow.Filter;
import com.example.foodnow.LocationHelper;
import com.example.foodnow.Restaurant;
import com.example.foodnow.data.json.JsonReader;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class RandomViewModel extends ViewModel {

    //list of restaurants
    private MutableLiveData<List<Restaurant>> items;
    private String searchString = null;
    private Filter filter = null;

    public RandomViewModel() {
        items = new MutableLiveData<>();
    }

    public MutableLiveData<List<Restaurant>> getItems(Context ctx, LocationHelper locationHelper) {


        JsonReader.readRestaurants(ctx, locationHelper).onSuccessTask(new SuccessContinuation<Restaurant[], Void>() {
            @NonNull
            @Override
            public Task<Void> then(Restaurant[] restaurants) {
                List<Restaurant> l = new ArrayList<>(Arrays.asList(restaurants));
                if (searchString != null)
                    //filter out the items that don't match the search
                    l.removeIf(restaurant -> !restaurant.name.toLowerCase().contains(searchString.toLowerCase()));

                if (filter != null) {
                    if (filter.requiredFoodTypes != null)
                        l.removeIf(restaurant -> !new HashSet<>(Arrays.asList(restaurant.foodTypes)).containsAll(filter.requiredFoodTypes));
                }

                //shuffle
                Collections.shuffle(l);

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

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}