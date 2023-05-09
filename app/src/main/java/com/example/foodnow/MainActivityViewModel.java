package com.example.foodnow;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    //the search query - used to search the name of the restaurant
    private final MutableLiveData<String> _searchQuery = new MutableLiveData<>("");
    //the filters as specified by the user in the filters activity.
    private final MutableLiveData<Filter> _filter = new MutableLiveData<>(null);


    public String getSearchQuery() {
        return _searchQuery.getValue();
    }

    public void setSearchQuery(String searchQuery) {
        this._searchQuery.setValue(searchQuery);
    }

    public MutableLiveData<String> getSearchQueryLiveData() {
        return _searchQuery;
    }

    public void setFilter(Filter value) {
        _filter.setValue(value);
    }

    @Nullable
    public Filter getFilter() {
        return _filter.getValue();
    }

    public MutableLiveData<Filter> getFilterLiveQuery() {
        return _filter;
    }
}
