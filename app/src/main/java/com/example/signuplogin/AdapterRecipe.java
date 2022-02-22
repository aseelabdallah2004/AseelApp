package com.example.signuplogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.ViewHolder> {

    private List<Recipe> mData;
    private LayoutInflater mInflater;
    private Context context;

    private final AdapterRecipe.ItemClickListener mClickListener = new ItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            // get recipe data
            Recipe rec = mData.get(position);
            // upload recipe data
            // goto details activity
            Intent i = new Intent(context, RecDetailsActivity.class);
            i.putExtra("rec", rec);
            context.startActivity(i);
        }
    };

    // data is passed into the constructor
    AdapterRecipe(Context context, List<Recipe> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public AdapterRecipe.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_recipe, parent, false);
        return new AdapterRecipe.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(AdapterRecipe.ViewHolder holder, int position) {
        Recipe rec = mData.get(position);
        holder.tvName.setText(rec.getName());
        Picasso.get().load(rec.getPhoto()).into(holder.ivPhoto);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrol    led off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        ImageView ivPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameRecRow);
            ivPhoto = itemView.findViewById(R.id.ivPhotoAddRec);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Recipe getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    /*
    void setClickListener(AdapterRestaurant.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }*/

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
