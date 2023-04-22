package com.example.foodnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodnow.backend.DbHelper;
import com.example.foodnow.data.images.ImageReader;
import com.example.foodnow.ui.DetailsFoodTypeItemCardAdapter;

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

        TextView distanceView = findViewById(R.id.details_distance_text);
        distanceView.setText(Util.formatDistance(restaurant.distanceTo));

        Button favouriteButton = findViewById(R.id.details_favouriteButton);
        favouriteButton.setOnClickListener((View view)->{
            if (restaurant.isFav){
                restaurant.isFav = false;
                DbHelper dbHelper = new DbHelper(this);
                dbHelper.unsetFavourite(restaurant);
            } else {
                restaurant.isFav = true;
                DbHelper dbHelper = new DbHelper(this);
                dbHelper.setFavourite(restaurant);
            }

            //set text/icon
            updateFavButton(restaurant);
        });

        //set text and icon
        updateFavButton(restaurant);

        RecyclerView recyclerView = findViewById(R.id.details_foodtype_list);
        RecyclerView.Adapter<DetailsFoodTypeItemCardAdapter.ViewHolder> adapter = new DetailsFoodTypeItemCardAdapter(Arrays.asList(restaurant.foodTypes));

        //set the adapter
        recyclerView.setAdapter(adapter);

        //set the layout manager
        recyclerView.setLayoutManager(new
                LinearLayoutManager(recyclerView.getContext()));


    }

    private void updateFavButton(Restaurant restaurant){
        Button favouriteButton = findViewById(R.id.details_favouriteButton);


        int textResource;
        int iconResource;

        if (restaurant.isFav){
            textResource = R.string.button_remove_from_favourites;
            iconResource = R.drawable.ic_star_24dp;
        } else {
            textResource = R.string.button_add_to_favourites;
            iconResource = R.drawable.ic_star_outline_24dp;
        }


        Drawable icon = ContextCompat.getDrawable(this,iconResource);
        int iconSize = Util.convertDPtoPX(24,this.getResources());
        icon.setBounds(0,0,iconSize,iconSize);

        favouriteButton.setText(textResource);
        favouriteButton.setCompoundDrawablesRelative(null,null, icon,null);

    }
}