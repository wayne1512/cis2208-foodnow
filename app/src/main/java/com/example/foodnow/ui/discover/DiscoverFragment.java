package com.example.foodnow.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodnow.R;
import com.example.foodnow.Restaurant;
import com.example.foodnow.databinding.FragmentDiscoverBinding;
import com.example.foodnow.ui.RestaurantCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment {

    private DiscoverViewModel discoverViewModel;
    private RestaurantCardAdapter adapter;
    private FragmentDiscoverBinding binding;
    private RecyclerView recyclerView;
    private List<Restaurant> items = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        discoverViewModel =
                new ViewModelProvider(this).get(DiscoverViewModel.class);

        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.restaurants_list);

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
        discoverViewModel.getItems().observe(getViewLifecycleOwner(),
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
        //update the recycler view
        items.addAll(newItems);
        //notify the adapter that the dataset has changed, so the recycler view can renderer to reflect the changed data
        adapter.notifyDataSetChanged();
    }
}