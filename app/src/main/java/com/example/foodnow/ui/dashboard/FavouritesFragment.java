package com.example.foodnow.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodnow.LocationHelper;
import com.example.foodnow.MainActivityViewModel;
import com.example.foodnow.R;
import com.example.foodnow.Restaurant;
import com.example.foodnow.databinding.FragmentFavouritesBinding;
import com.example.foodnow.ui.RestaurantCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    private FavouritesViewModel favouritesViewModel;
    private RestaurantCardAdapter adapter;
    private FragmentFavouritesBinding binding;
    private RecyclerView recyclerView;
    private final List<Restaurant> items = new ArrayList<>();
    View noResFoundView;

    private LocationHelper locationHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        locationHelper = new LocationHelper(getActivity());

        favouritesViewModel =
                new ViewModelProvider(this).get(FavouritesViewModel.class);


        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.restaurants_list);

        noResFoundView = root.findViewById(R.id.no_res_found);


        MainActivityViewModel activityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        activityViewModel.getSearchQueryLiveData().observe(getViewLifecycleOwner(), query -> {
            favouritesViewModel.setSearchString(query);
            fetchItems();
        });

        activityViewModel.getFilterLiveQuery().observe(getViewLifecycleOwner(), filter -> {
            favouritesViewModel.setFilter(filter);
            fetchItems();
        });


        setUpRecyclerView();
        fetchItems();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void fetchItems() {

        //if the items change, update the recycler view
        favouritesViewModel.getItems(getContext(), locationHelper).observe(getViewLifecycleOwner(),
                this::updateItemsList);
    }

    private void setUpRecyclerView() {
        //init the adapter
        adapter = new RestaurantCardAdapter(items);

        //set the adapter
        recyclerView.setAdapter(adapter);

        //set the layout manager
        recyclerView.setLayoutManager(new
                LinearLayoutManager(recyclerView.getContext()));
    }

    private void updateItemsList(List<Restaurant> newItems) {
        //clear previous items
        items.clear();
        //update the recycler view
        items.addAll(newItems);

        //show no res founds if needed
        if (newItems.size() == 0)
            noResFoundView.setVisibility(View.VISIBLE);
        else
            noResFoundView.setVisibility(View.GONE);


        //notify the adapter that the dataset has changed, so the recycler view can renderer to reflect the changed data
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        fetchItems();
    }
}