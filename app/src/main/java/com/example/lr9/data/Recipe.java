package com.example.lr9.data;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    public enum MealType { BREAKFAST, LUNCH, DINNER }
    public enum Cuisine { ITALIAN, JAPANESE, RUSSIAN, AMERICAN }

    public final String id;
    public final MealType mealType;
    public final Cuisine cuisine;

    // Локализованное содержимое рецепта
    public final String titleRu, titleEn, titleEs;
    public final List<String> ingredientsRu, ingredientsEn, ingredientsEs;
    public final List<String> stepsRu, stepsEn, stepsEs;

    // Озвучка (может быть null)
    public final Integer voiceRu;
    public final Integer voiceEn;
    public final Integer voiceEs;

    // Видео (может быть null)
    public final Integer videoRes;

    public Recipe(
            String id,
            MealType mealType,
            Cuisine cuisine,
            String titleRu, String titleEn, String titleEs,
            List<String> ingredientsRu, List<String> ingredientsEn, List<String> ingredientsEs,
            List<String> stepsRu, List<String> stepsEn, List<String> stepsEs,
            Integer voiceRu, Integer voiceEn, Integer voiceEs,
            Integer videoRes
    ) {
        this.id = id;
        this.mealType = mealType;
        this.cuisine = cuisine;

        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.titleEs = titleEs;

        this.ingredientsRu = ingredientsRu;
        this.ingredientsEn = ingredientsEn;
        this.ingredientsEs = ingredientsEs;

        this.stepsRu = stepsRu;
        this.stepsEn = stepsEn;
        this.stepsEs = stepsEs;

        this.voiceRu = voiceRu;
        this.voiceEn = voiceEn;
        this.voiceEs = voiceEs;

        this.videoRes = videoRes;
    }

    public String getTitle(String lang) {
        if ("ru".equals(lang)) return titleRu;
        if ("es".equals(lang)) return titleEs;
        return titleEn;
    }

    public List<String> getIngredients(String lang) {
        if ("ru".equals(lang)) return ingredientsRu;
        if ("es".equals(lang)) return ingredientsEs;
        return ingredientsEn;
    }

    public List<String> getSteps(String lang) {
        if ("ru".equals(lang)) return stepsRu;
        if ("es".equals(lang)) return stepsEs;
        return stepsEn;
    }

    public Integer getVoice(String lang) {
        if ("ru".equals(lang)) return voiceRu;
        if ("es".equals(lang)) return voiceEs;
        return voiceEn;
    }
}