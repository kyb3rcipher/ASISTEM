package com.kyb3r.asistem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbarSettings);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Appearance
        ArrayAdapter<String> appearanceOptions = new ArrayAdapter<>(this, R.layout.dropdown_item, getResources().getStringArray(R.array.appareance));
        AutoCompleteTextView appearanceautoCompleteTextView = findViewById(R.id.appearanceTextView);
        appearanceautoCompleteTextView.setAdapter(appearanceOptions);
        appearanceautoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            int apperancedMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

            switch (position) {
                case 0:
                    apperancedMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                    break;
                case 1:
                    apperancedMode = AppCompatDelegate.MODE_NIGHT_NO;
                    break;
                case 2:
                    apperancedMode = AppCompatDelegate.MODE_NIGHT_YES;
                    break;
            }

            // Set appearance
            AppCompatDelegate.setDefaultNightMode(apperancedMode);

            // Save config
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("appearance_mode", apperancedMode);
            editor.apply();

            // Reload activity
            finish();
            startActivity(getIntent());
        });


        // Language
        String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, languages);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            String language = "en";

            switch (position) {
                case 0:
                    language = "en";
                    break;
                case 1:
                    language = "es";
                    break;
                case 2:
                    language = "de";
                    break;
                case 3:
                    language = "ru";
                    break;
            }

            // Set language
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration config = new Configuration(getResources().getConfiguration());
            config.setLocale(locale);
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());

            // Save language preference
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("selected_language", language);
            editor.apply();

            // Reload Activity
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
