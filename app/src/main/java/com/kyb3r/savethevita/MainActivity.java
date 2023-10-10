package com.kyb3r.savethevita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setItemActiveIndicatorColor(getColorStateList(R.color.pink));  // set bottonNavigationBar selection indicator color
        toolBar = findViewById(R.id.toolBar);
        toolBar.setTitle(R.string.home);
        setSupportActionBar(toolBar);

        // Set the default home fragment
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutMain, new HomeFragment()).commit();

        // Set up the listener for BottonNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menuHome) {
                toolBar.setTitle(R.string.home);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMain, new HomeFragment()).commit();
                return true;
            } else if (item.getItemId() == R.id.menuCourses) {
                toolBar.setTitle(R.string.courses);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMain, new CoursesFragment()).commit();
                return true;
            } else if (item.getItemId() == R.id.menuSaved) {
                toolBar.setTitle(R.string.saved);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMain, new SavedFragment()).commit();
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Set up the options for toolBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAbout) {
            Intent AboutActivity = new Intent(this, AboutActivity.class);
            startActivity(AboutActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}