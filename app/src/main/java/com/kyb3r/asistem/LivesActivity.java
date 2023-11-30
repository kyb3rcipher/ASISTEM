package com.kyb3r.asistem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LivesActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lives);

        // Close button
        ImageButton closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() { @Override  public void onClick(View v) { finish(); }});

        DatabaseHelper db = new DatabaseHelper(this);
        int livesNumber = db.getLivesCount();

        TextView lives = findViewById(R.id.lives);
        lives.setText(String.valueOf(livesNumber));

        ImageView heart1 = findViewById(R.id.heart1), heart2 = findViewById(R.id.heart2), heart3 = findViewById(R.id.heart3), heart4 = findViewById(R.id.heart4), heart5 = findViewById(R.id.heart5);

        ImageView[] hearts = new ImageView[]{ heart1, heart2, heart3, heart4, heart5 };
        for (int i = 0; i < livesNumber; i++) {
            hearts[i].setImageResource(R.drawable.logo_duetone);
        }

        ImageButton buttonAddLife = findViewById(R.id.buttonAddLifee);
        DatabaseHelper livesDatabaseHelper = new DatabaseHelper(this);;
        buttonAddLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentLives = livesDatabaseHelper.getLivesCount();

                if (currentLives < 5) {
                    currentLives++;
                    livesDatabaseHelper.setLivesCount(currentLives);
                    // reload activity (for refull heart)
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        TextView timeCounter = findViewById(R.id.timeCounter);
        countDownTimer = new CountDownTimer(7200000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Actualiza el TextView con el tiempo restante
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
                int currentLives = livesDatabaseHelper.getLivesCount();
                if (currentLives < 5) {
                    currentLives++;
                    livesDatabaseHelper.setLivesCount(currentLives);
                    // reload activity (for refull heart)
                    finish();
                    startActivity(getIntent());
                }
                timeCounter.setText("Time's up!");
            }
        };

        countDownTimer.start();
    }
}