package com.example.foodnow.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodnow.DetailsActivity;
import com.example.foodnow.R;
import com.example.foodnow.Restaurant;
import com.example.foodnow.Util;
import com.example.foodnow.backend.DbHelper;
import com.example.foodnow.data.images.ImageReader;

import java.util.ArrayList;
import java.util.List;

public class RestaurantCardAdapter extends RecyclerView.Adapter<RestaurantCardAdapter.ViewHolder> {
    private List<Restaurant> items = new ArrayList<>();

    public RestaurantCardAdapter(List<Restaurant> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.layout_restaurant_card, parent,
                false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the required restaurant
        Restaurant item = items.get(position);

        //set restaurant to be used on click
        holder.restaurant = item;

        ImageView imageView = holder.imageImageView;

        imageView.setImageBitmap(ImageReader.getRestaurantBanner(imageView.getContext(), item.id));


        //get 24dp to pixels to be the size of each food type icon
        int size = Util.convertDPtoPX(24, holder.foodTypeIconBar.getResources());

        //remove all food type icons
        holder.foodTypeIconBar.removeAllViews();
        if (item.foodTypes == null)
            item.foodTypes = new String[0];

        //create the icons that represent the food types sold by this restaurant
        for (String foodType : item.foodTypes) {

            //get the icon for the food type
            int imageResource = Restaurant.foodTypeDrawableMap.get(foodType);


            ImageView typeImageView = new ImageView(holder.foodTypeIconBar.getContext());
            typeImageView.setImageResource(imageResource);
            typeImageView.setMaxHeight(size);
            typeImageView.setMaxWidth(size);
            typeImageView.setPadding(0, 0, Util.convertDPtoPX(8, holder.foodTypeIconBar.getResources()), 0);
            typeImageView.requestLayout();


            holder.foodTypeIconBar.addView(typeImageView);
        }

        TextView nameTextView = holder.nameTextView;
        nameTextView.setText(item.name);

        TextView distanceTextView = holder.distanceTextView;
        distanceTextView.setText(Util.formatDistance(item.distanceTo));

        updateFavButton(holder, item);
    }

    private void updateFavButton(@NonNull ViewHolder holder, Restaurant restaurant) {
        ImageView favouriteButton = holder.favButton;


        int iconResource;


        //choose between the filled star and the hollow star depending if the restaurant
        //is in the fav list
        if (restaurant.isFav) {
            iconResource = R.drawable.ic_star_24dp;
        } else {
            iconResource = R.drawable.ic_star_outline_24dp;
        }

        favouriteButton.setImageResource(iconResource);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Restaurant restaurant;

        public ImageView imageImageView;
        public TextView nameTextView;
        public TextView distanceTextView;

        public ImageView favButton;


        public LinearLayout foodTypeIconBar;
        public Button detailsButton;

        public ViewHolder(final View itemView) {
            super(itemView);

            imageImageView = itemView.findViewById(R.id.restaurantCard_imageView);
            nameTextView = itemView.findViewById(R.id.restaurantCard_name);

            foodTypeIconBar = itemView.findViewById(R.id.restaurantCard_foodTypeIcons);

            detailsButton = itemView.findViewById(R.id.restaurantCard_detailsButton);

            distanceTextView = itemView.findViewById(R.id.restaurantCard_distanceText);

            favButton = itemView.findViewById(R.id.restaurantCard_fav);


            favButton.setOnClickListener(v -> {
                if (restaurant.isFav) {
                    restaurant.isFav = false;
                    DbHelper dbHelper = new DbHelper(favButton.getContext());
                    dbHelper.unsetFavourite(restaurant);
                } else {
                    restaurant.isFav = true;
                    DbHelper dbHelper = new DbHelper(favButton.getContext());
                    dbHelper.setFavourite(restaurant);
                }

                //set text/icon
                updateFavButton(this, restaurant);
            });

            detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClick(v);
                }
            });
        }

        public void itemOnClick(View v) {
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);

            intent.putExtra("restaurant", restaurant);

            v.getContext().startActivity(intent);
        }
    }
}