package com.example.lr9;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lr9.adapter.RecipeAdapter;
import com.example.lr9.data.Recipe;
import com.example.lr9.data.RecipeRepository;
import com.example.lr9.util.LocaleHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner spLang, spMeal, spCuisine;
    private RecyclerView rv;
    private RecipeAdapter adapter;

    private List<Recipe> all;
    private String currentLang; // "en"/"ru"/"es"

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(LocaleHelper.applySavedLanguage(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spLang = findViewById(R.id.spLang);
        spMeal = findViewById(R.id.spMeal);
        spCuisine = findViewById(R.id.spCuisine);
        rv = findViewById(R.id.rvRecipes);

        all = RecipeRepository.getAll();

        // язык: если сохранён — берём его; иначе берём язык устройства (ru/es/en)
        String saved = LocaleHelper.getSavedLanguage(this);
        String device = Locale.getDefault().getLanguage();
        currentLang = saved != null ? saved : normalizeLang(device);

        setupLanguageSpinner();
        setupFilterSpinners();

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter(new ArrayList<>(), currentLang, recipe -> {
            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            i.putExtra("recipe", recipe);
            i.putExtra("lang", currentLang);
            startActivity(i);
        });
        rv.setAdapter(adapter);

        applyFiltersAndUpdate();
    }

    private String normalizeLang(String lang) {
        if ("ru".equals(lang)) return "ru";
        if ("es".equals(lang)) return "es";
        return "en";
    }

    private void setupLanguageSpinner() {
        // UI названия можно тоже локализовать через ресурсы, но для простоты — фикс:
        String[] langs = {"English", "Русский", "Español"};

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, langs);
        spLang.setAdapter(ad);

        int pos = 0;
        if ("ru".equals(currentLang)) pos = 1;
        else if ("es".equals(currentLang)) pos = 2;
        spLang.setSelection(pos, false);

        spLang.setOnItemSelectedListener(new SimpleItem(position -> {
            String newLang = position == 1 ? "ru" : position == 2 ? "es" : "en";
            if (!newLang.equals(currentLang)) {
                currentLang = newLang;
                LocaleHelper.saveLanguage(MainActivity.this, newLang);
                // применяем локаль и пересоздаём экран
                recreate();
            }
        }));
    }

    private void setupFilterSpinners() {
        // Meal
        String[] meals = {
                getString(R.string.all),
                getString(R.string.breakfast),
                getString(R.string.lunch),
                getString(R.string.dinner)
        };
        spMeal.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, meals));

        // Cuisine
        String[] cuisines = {
                getString(R.string.all),
                "ITALIAN",
                "JAPANESE",
                "RUSSIAN",
                "AMERICAN"
        };
        spCuisine.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cuisines));

        spMeal.setOnItemSelectedListener(new SimpleItem(pos -> applyFiltersAndUpdate()));
        spCuisine.setOnItemSelectedListener(new SimpleItem(pos -> applyFiltersAndUpdate()));
    }

    private void applyFiltersAndUpdate() {
        int mealPos = spMeal.getSelectedItemPosition();
        int cuisinePos = spCuisine.getSelectedItemPosition();

        Recipe.MealType mealFilter = null;
        if (mealPos == 1) mealFilter = Recipe.MealType.BREAKFAST;
        else if (mealPos == 2) mealFilter = Recipe.MealType.LUNCH;
        else if (mealPos == 3) mealFilter = Recipe.MealType.DINNER;

        Recipe.Cuisine cuisineFilter = null;
        if (cuisinePos == 1) cuisineFilter = Recipe.Cuisine.ITALIAN;
        else if (cuisinePos == 2) cuisineFilter = Recipe.Cuisine.JAPANESE;
        else if (cuisinePos == 3) cuisineFilter = Recipe.Cuisine.RUSSIAN;
        else if (cuisinePos == 4) cuisineFilter = Recipe.Cuisine.AMERICAN;

        List<Recipe> filtered = new ArrayList<>();
        for (Recipe r : all) {
            boolean okMeal = (mealFilter == null) || r.mealType == mealFilter;
            boolean okCuisine = (cuisineFilter == null) || r.cuisine == cuisineFilter;
            if (okMeal && okCuisine) filtered.add(r);
        }
        adapter.update(filtered, currentLang);
    }
}