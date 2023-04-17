package com.example.foodnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodnow.data.images.ImageReader;
import com.example.foodnow.ui.DetailsFoodTypeItemCardAdapter;
import com.example.foodnow.ui.RestaurantCardAdapter;

import java.util.Arrays;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        
        //get the intent and extract the data
        Intent intent = getIntent();
        Restaurant restaurant = ((Restaurant) intent.getSerializableExtra("restaurant"));

        ImageView imageView = findViewById(R.id.details_image);
        imageView.setImageBitmap(ImageReader.getRestaurantBanner(imageView.getContext(), restaurant.id));


        TextView titleView = findViewById(R.id.details_title);
        titleView.setText(restaurant.name);
        TextView subtitleView = findViewById(R.id.details_subtitle);
        subtitleView.setText(restaurant.subtitle);


        RecyclerView recyclerView = findViewById(R.id.details_foodtype_list);
        RecyclerView.Adapter<DetailsFoodTypeItemCardAdapter.ViewHolder> adapter = new DetailsFoodTypeItemCardAdapter(Arrays.asList(restaurant.foodTypes));

        //set the adapter
        recyclerView.setAdapter(adapter);

        //set the layout manager
        recyclerView.setLayoutManager(new
                LinearLayoutManager(recyclerView.getContext()));
    }
}