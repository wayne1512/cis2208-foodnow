package com.example.foodnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.foodnow.ui.DetailsFoodTypeItemCardAdapter;
import com.example.foodnow.ui.FilterFoodTypeItemCardAdapter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FiltersActivity extends AppCompatActivity {

    Set<String> requiredFoodTypes = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        RecyclerView recyclerView = findViewById(R.id.filter_foodtype_list);
        //get the list of foodtypes and pass them to the recycler view
        RecyclerView.Adapter<FilterFoodTypeItemCardAdapter.ViewHolder> adapter = new FilterFoodTypeItemCardAdapter(Arrays.asList(Restaurant.foodTypeDrawableMap.keySet().toArray(new String[0])),requiredFoodTypes);

        //set the adapter
        recyclerView.setAdapter(adapter);

        //set the layout manager
        recyclerView.setLayoutManager(new
                LinearLayoutManager(recyclerView.getContext()));



        Button submitButton = findViewById(R.id.filter_submit_button);
        submitButton.setOnClickListener(v->{

            Filter f = new Filter();

            f.requiredFoodTypes = requiredFoodTypes;


            Intent data = new Intent();
            data.putExtra("filter",f);
            setResult(RESULT_OK,data);
            finish();
        });
    }
}