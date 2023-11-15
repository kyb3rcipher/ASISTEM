package com.kyb3r.asistem;

import com.kyb3r.asistem.exercise.ExerciseClass;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;


public class FracturesCourseActivity extends AppCompatActivity {
    ExerciseClass exercises = new ExerciseClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fractures_course);

        // Add closeButton function to close (the left X)
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> exercises.closeButton());

        // Set first fragmentExercise (read)
        exercises.setupRead();

        // Exercise fragments switcher
        // Start correct exercise variable (-1/null) - (0 - correct, 1 - incorrect)
        ExerciseClass.correct = -1;
        // Listener for nextButton switch the fragment to change.
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            switch (ExerciseClass.nextFragment) {
                case 0: exercises.setupOptions(); break;
                case 1: exercises.setupSelect(); break;
                case 2: exercises.setupWrite(); break;
                case 3: exercises.setupTapPairs(); break;
            }
        });
    }

}