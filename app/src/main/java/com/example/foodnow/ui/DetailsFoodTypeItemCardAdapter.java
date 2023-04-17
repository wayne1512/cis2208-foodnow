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
import com.example.foodnow.data.images.ImageReader;

import java.util.ArrayList;
import java.util.List;

public class DetailsFoodTypeItemCardAdapter extends RecyclerView.Adapter<DetailsFoodTypeItemCardAdapter.ViewHolder> {
        private List<String> foodtypes = new ArrayList<>();
        public DetailsFoodTypeItemCardAdapter(List<String> foodtypes) {
            this.foodtypes = foodtypes;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
                viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.layout_detailed_foodtype_item, parent,
                    false);
            return new ViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position)
        {
            //get the required restaurant
            String item = foodtypes.get(position);


            ImageView imageView = holder.imageView;

            imageView.setImageResource(Restaurant.foodTypeDrawableMap.get(item));


            TextView textView = holder.textView;
            textView.setText(item);
        }

    @Override
        public int getItemCount() {
            return foodtypes.size();
        }
    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public TextView textView;

        public ViewHolder(final View itemView) {
            super(itemView);

            imageView = (ImageView)
                    itemView.findViewById(R.id.detailed_foodtype_rowitem_image);
            textView = (TextView)
                    itemView.findViewById(R.id.detailed_foodtype_rowitem_text);
        }
    }
}