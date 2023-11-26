package com.kyb3r.asistem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LivesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lives);

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
        timeCounter.setText("00:00:00");
    }
}