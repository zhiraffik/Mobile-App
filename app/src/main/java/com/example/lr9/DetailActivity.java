package com.example.lr9;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lr9.data.Recipe;
import com.example.lr9.util.LocaleHelper;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(LocaleHelper.applySavedLanguage(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        String lang = getIntent().getStringExtra("lang");
        if (lang == null) lang = "en";

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvMeta = findViewById(R.id.tvMeta);
        TextView tvIngredients = findViewById(R.id.tvIngredients);
        TextView tvSteps = findViewById(R.id.tvSteps);

        if (recipe == null) return;

        tvTitle.setText(recipe.getTitle(lang));
        tvMeta.setText(recipe.mealType.name() + " • " + recipe.cuisine.name());

        tvIngredients.setText(joinLines(recipe.getIngredients(lang)));
        tvSteps.setText(numbered(recipe.getSteps(lang)));
    }

    private String joinLines(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (String s : lines) sb.append("• ").append(s).append("\n");
        return sb.toString().trim();
    }

    private String numbered(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            sb.append(i + 1).append(". ").append(lines.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}