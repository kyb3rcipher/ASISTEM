package com.kyb3r.asistem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EmergencyNumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_numbers);
        // Close button
        ImageButton closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() { @Override  public void onClick(View v) { finish(); }});

        Button buttonCallEmergency = findViewById(R.id.buttonCallEmergency);
        buttonCallEmergency.setOnClickListener(view -> {
            // Call 911
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:911"));
            startActivity(intent);
        });
    }
}