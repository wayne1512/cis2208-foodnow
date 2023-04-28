package com.example.foodnow;

import androidx.lifecycle.MutableLiveData;

import java.io.Serializable;
import java.util.Set;

public class Filter implements Serializable {
    public Set<String> requiredFoodTypes = null;

    //used Float instead of float to allow for nulls (filter not set)
    public Float minDistance= null;
    public Float maxDistance= null;


}
