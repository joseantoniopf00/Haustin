package com.haustinsl.haustin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PropertyPagerAdapter extends RecyclerView.Adapter<PropertyPagerAdapter.PropertyViewHolder> {

    private List<PropertyItem> propertyItems;

    public PropertyPagerAdapter() {
        propertyItems = new ArrayList<>();
        // Datos de ejemplo
        propertyItems.add(new PropertyItem("1.200$/mes", "Madrid", "3 hab y 80m"));
        propertyItems.add(new PropertyItem("950$/mes", "Barcelona", "2 hab y 65m"));
        propertyItems.add(new PropertyItem("1.500$/mes", "Valencia", "4 hab y 95m"));
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_property_card, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        PropertyItem item = propertyItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return propertyItems.size();
    }

    static class PropertyViewHolder extends RecyclerView.ViewHolder {
        private TextView priceTextView, cityTextView, roomsTextView;

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            roomsTextView = itemView.findViewById(R.id.roomsTextView);
        }

        public void bind(PropertyItem item) {
            priceTextView.setText(item.getPrice());
            cityTextView.setText(item.getCity());
            roomsTextView.setText(item.getRooms());
        }
    }
}