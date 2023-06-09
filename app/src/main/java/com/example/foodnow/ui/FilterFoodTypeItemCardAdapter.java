package com.example.foodnow.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodnow.R;
import com.example.foodnow.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilterFoodTypeItemCardAdapter extends RecyclerView.Adapter<FilterFoodTypeItemCardAdapter.ViewHolder> {
    private List<String> foodtypes = new ArrayList<>();
    private final Set<String> requiredFoodTypes;

    public FilterFoodTypeItemCardAdapter(List<String> foodtypes, Set<String> requiredFoodTypes) {
        this.foodtypes = foodtypes;
        this.requiredFoodTypes = requiredFoodTypes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.layout_filter_foodtype_item, parent,
                false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the required restaurant
        String item = foodtypes.get(position);

        holder.foodType = item;
        ImageView imageView = holder.imageView;

        imageView.setImageResource(Restaurant.foodTypeDrawableMap.get(item));

        TextView textView = holder.textView;
        textView.setText(item);

        holder.requiredSwitch.setChecked(requiredFoodTypes.contains(item));
    }

    @Override
    public int getItemCount() {
        return foodtypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public String foodType;

        public ImageView imageView;
        public TextView textView;

        public CheckBox requiredSwitch;


        public ViewHolder(final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.filter_foodtype_rowitem_image);
            textView = itemView.findViewById(R.id.filter_foodtype_rowitem_text);

            requiredSwitch = itemView.findViewById(R.id.filter_foodtype_rowitem_switch);

            requiredSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked)
                        //remove from required list
                        requiredFoodTypes.remove(foodType);
                    else
                        //add to required list
                        requiredFoodTypes.add(foodType);
                }
            });
        }
    }
}