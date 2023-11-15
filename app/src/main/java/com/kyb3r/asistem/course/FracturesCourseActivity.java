package com.kyb3r.asistem.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.kyb3r.asistem.ExerciseClass;
import com.kyb3r.asistem.R;

import java.util.Arrays;


public class FracturesCourseActivity extends AppCompatActivity {
    ExerciseClass exercises = new ExerciseClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Add closeButton function to close (the left X)
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> exercises.closeButton());

        // Set first fragmentExercise (read)
        exercises.setupRead("Okay", getString(R.string.courseFracturesRead), 0);

        // Exercise fragments switcher
        // Listener for nextButton switch the fragment to change.
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            switch (ExerciseClass.nextFragment) {
                case 0:
                    exercises.setupOptions("Check", "Best song?", "NADIE SABE", "MONACO", "FINA", R.id.option1, 1, 0);
                break;

                case 1:
                    int[] fragmentMappings = {1, 1, 1, 2};
                    exercises.setupSelect("PERRO NEGRO", "Check", 3, fragmentMappings);
                break;

                case 2:
                    exercises.setupWrite("CONAN GRAY", "Check", Arrays.asList("uwu", "owo", "xd"), 3, 2);
                break;

                case 3:
                    int[][] buttonPairs = {{0, 1}, {2, 3}, {4, 5}};
                    exercises.setupTapPairs("DJ PASTOR", buttonPairs, 1);
                break;
            }
        });
    }

}