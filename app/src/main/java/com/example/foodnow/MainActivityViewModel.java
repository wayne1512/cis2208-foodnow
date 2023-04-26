package com.example.foodnow;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private final MutableLiveData<String> _searchQuery = new MutableLiveData<>("");


    public String getSearchQuery() {
        return _searchQuery.getValue();
    }

    public void setSearchQuery(String searchQuery) {
        this._searchQuery.setValue(searchQuery);
    }

    public MutableLiveData<String> getSearchQueryLiveData() {
        return _searchQuery;
    }
}
