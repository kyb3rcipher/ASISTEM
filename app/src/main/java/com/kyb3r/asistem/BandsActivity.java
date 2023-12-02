package com.kyb3r.asistem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BandsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bands);
        // Close button
        ImageButton closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> finish());
        DatabaseHelper db = new DatabaseHelper(this);

        // Get lives number
        final int[] bandsNumber = {db.getBandsCount()};

        // Set number
        TextView bandsText = findViewById(R.id.bands);
        bandsText.setText(String.valueOf(bandsNumber[0]));

        // Set number lives images
        ImageView band1 = findViewById(R.id.band1), band2 = findViewById(R.id.band2), band3 = findViewById(R.id.band3), band4 = findViewById(R.id.band4), band5 = findViewById(R.id.band5);
        ImageView[] bands = new ImageView[]{ band1, band2, band3, band4, band5 };
        for (int i = 0; i < bandsNumber[0]; i++) { bands[i].setImageResource(R.drawable.ic_band); }

        // Set counter for new life
        CountDownTimer countDownTimer;
        TextView timeCounter = findViewById(R.id.timeCounter);
        countDownTimer = new CountDownTimer(7200000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the text to remain time
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                seconds = seconds % 60;
                long hours = minutes / 60;
                minutes = minutes % 60;
                String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                timeCounter.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                db.addOneBand();
                // reload activity (for refill heart)
                finish();
                startActivity(getIntent());
                timeCounter.setText("Time's up!");
            }
        };
        countDownTimer.start();  // automatically start counter

        // Set temporal button for manual add life
        ImageButton buttonAddLife = findViewById(R.id.buttonAddLifee);
        buttonAddLife.setOnClickListener(v -> {
            db.addOneBand();
            // reload activity (for refill heart)
            finish();
            startActivity(getIntent());
        });


    }
}