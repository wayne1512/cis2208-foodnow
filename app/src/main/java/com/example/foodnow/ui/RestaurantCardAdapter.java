package com.example.foodnow.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.StackView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodnow.R;
import com.example.foodnow.Restaurant;

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
        public void onBindViewHolder(@NonNull ViewHolder holder, int position)
        {
            //get the required restaurant
            Restaurant item = items.get(position);

            ImageView imageView = holder.imageImageView;
            byte[] decodedString = Base64.decode(item.image, Base64.DEFAULT);
            Bitmap decodedBytes = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedBytes);


            //get 40dp to pixels to be the size of each food type icon
            int size = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, holder.foodTypeIconBar.getResources().getDisplayMetrics());


            for (String foodType : item.foodTypes) {

                int imageResource = Restaurant.foodTypeDrawableMap.get(foodType);


                ImageView typeImageView = new ImageView(holder.foodTypeIconBar.getContext());
                typeImageView.setImageResource(imageResource);
                typeImageView.setMaxHeight(size);
                typeImageView.setMaxWidth(size);
                typeImageView.requestLayout();


                holder.foodTypeIconBar.addView(typeImageView);
            }

            TextView nameTextView = holder.nameTextView;
            nameTextView.setText(item.name);
        }
        @Override
        public int getItemCount() {
            return items.size();
        }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageImageView;
        public TextView nameTextView;

        public LinearLayout foodTypeIconBar;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageImageView = (ImageView)
                    itemView.findViewById(R.id.restaurantCard_imageView);
            nameTextView = (TextView)
                    itemView.findViewById(R.id.restaurantCard_name);

            foodTypeIconBar = (LinearLayout) itemView.findViewById(R.id.restaurantCard_foodTypeIcons);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClick();
                }
            });
        }

        public void itemOnClick() {
            System.out.println("click");
        }
    }
}