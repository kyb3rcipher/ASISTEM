package com.kyb3r.asistem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSettings);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Apparance
        ArrayAdapter<String> appearanceOptions = new ArrayAdapter<>(this, R.layout.dropdown_item, getResources().getStringArray(R.array.appareance));
        AutoCompleteTextView appearanceautoCompleteTextView = findViewById(R.id.appearanceTextView);
        appearanceautoCompleteTextView.setAdapter(appearanceOptions);
        appearanceautoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    break;
                case 1:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case 2:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
            }

            finish();
            startActivity(getIntent());
        });

        // Language
        String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, languages);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    setLanguage("en");
                    break;
                case 1:
                    setLanguage("es");
                    break;
                case 2:
                    setLanguage("de");
                case 3:
                    setLanguage("ru");
                    break;
            }
        });
    }

    private void setLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration(getResources().getConfiguration());
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Reload Activity
        finish();
        startActivity(getIntent());
    }

}
