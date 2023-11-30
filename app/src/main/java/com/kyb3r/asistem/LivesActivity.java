package com.kyb3r.asistem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LivesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lives);
        // Close button
        ImageButton closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> finish());
        DatabaseHelper db = new DatabaseHelper(this);

        // Get lives number
        final int[] livesNumber = {db.getLivesCount()};

        // Set number
        TextView lives = findViewById(R.id.lives);
        lives.setText(String.valueOf(livesNumber[0]));

        // Set number lives images
        ImageView heart1 = findViewById(R.id.heart1), heart2 = findViewById(R.id.heart2), heart3 = findViewById(R.id.heart3), heart4 = findViewById(R.id.heart4), heart5 = findViewById(R.id.heart5);
        ImageView[] hearts = new ImageView[]{ heart1, heart2, heart3, heart4, heart5 };
        for (int i = 0; i < livesNumber[0]; i++) { hearts[i].setImageResource(R.drawable.logo_duetone); }

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
                db.addOneLive();
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
            db.addOneLive();
            // reload activity (for refill heart)
            finish();
            startActivity(getIntent());
        });


    }
}