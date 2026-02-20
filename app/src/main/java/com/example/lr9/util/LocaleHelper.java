package com.example.lr9.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LocaleHelper {
    private static final String PREFS = "settings";
    private static final String KEY_LANG = "lang"; // "en", "ru", "es"

    public static Context applySavedLanguage(Context context) {
        String lang = getSavedLanguage(context);
        return setLocale(context, lang);
    }

    public static String getSavedLanguage(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        // null => язык устройства
        return sp.getString(KEY_LANG, null);
    }

    public static void saveLanguage(Context context, String langOrNull) {
        SharedPreferences sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit().putString(KEY_LANG, langOrNull).apply();
    }

    public static Context setLocale(Context context, String langOrNull) {
        Locale locale;
        if (langOrNull == null) {
            locale = Locale.getDefault(); // язык устройства
        } else {
            locale = new Locale(langOrNull);
        }
        Locale.setDefault(locale);

        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.setLocale(locale);

        return context.createConfigurationContext(config);
    }
}