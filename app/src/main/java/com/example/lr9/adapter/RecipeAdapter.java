package com.example.lr9.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lr9.R;
import com.example.lr9.data.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.VH> {

    public interface OnRecipeClick {
        void onClick(Recipe recipe);
    }

    private List<Recipe> items;
    private String lang; // "en"/"ru"/"es"
    private final OnRecipeClick listener;

    public RecipeAdapter(List<Recipe> items, String lang, OnRecipeClick listener) {
        this.items = items;
        this.lang = lang;
        this.listener = listener;
    }

    public void update(List<Recipe> newItems, String lang) {
        this.items = newItems;
        this.lang = lang;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Recipe r = items.get(position);
        holder.title.setText(r.getTitle(lang));
        holder.meta.setText(r.mealType.name() + " • " + r.cuisine.name());
        holder.itemView.setOnClickListener(v -> listener.onClick(r));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView title, meta;
        VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            meta  = itemView.findViewById(R.id.tvMeta);
        }
    }
}