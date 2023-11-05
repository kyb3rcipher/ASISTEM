package com.kyb3r.asistem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FracturesCourseActivity extends AppCompatActivity {

    private int nextFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fractures_course);

        // Add closeButton function to close (the left X)
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> onBackPressed());

        // Initialize elements
        Button button = findViewById(R.id.button);
        MediaPlayer correctPlayer = MediaPlayer.create(this, R.raw.correct);
        MediaPlayer incorrectPlayer = MediaPlayer.create(this, R.raw.incorrect);

        // Set first fragmentExercise (read)
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        getLayoutInflater().inflate(R.layout.exercise_read, frameLayout, true);
        button.setText("Okay");
        // Modify fragment
        TextView text = frameLayout.findViewById(R.id.text);
        text.setText(getString(R.string.courseFracturesRead));

        nextFragment = 0;   // put next fragmentExercise (0)

        // Exercise fragments switcher
        // Start correct exercise variable (-1/null) - (0 - correct, 1 - incorrect)
        final int[] correct = {-1};
        // Listener for nextButton switch the fragment to change.
        button.setOnClickListener(v -> {
            switch (nextFragment) {
                case 0:
                    changeLayout(R.layout.exercise_options);
                    button.setText("Check");

                    TextView instruction = findViewById(R.id.instruction);
                    instruction.setText("What are fractures?");


                    RadioButton option1 = findViewById(R.id.option1), option2 = findViewById(R.id.option2), option3 = findViewById(R.id.option3);

                    option1.setText("NADIE SABE...");
                    option2.setText("They are breaks in the bones and can vary in severity");
                    option3.setText("Bad Bunny");

                    RadioGroup radioButton = findViewById(R.id.optionsGroup);
                    /*radioButton.setOnCheckedChangeListener((group, checkedId) -> {
                        correct[0] = (checkedId == R.id.option2 && checkedId == group.getCheckedRadioButtonId()) ? 1 : 0;
                        nextFragment = (checkedId == R.id.option2 && checkedId == group.getCheckedRadioButtonId()) ? 1 : 0;
                    });*/
                    radioButton.setOnCheckedChangeListener((group, checkedId) -> {
                        if (checkedId == R.id.option2) {
                            correct[0] = 1;
                            nextFragment = 1;
                        } else {
                            correct[0] = 0;
                            nextFragment = 0;
                        }
                    });
                break;

                case 1:
                    changeLayout(R.layout.exercise_select);
                    button.setText("Check");

                    nextFragment++;
                break;

                case 2:
                    changeLayout(R.layout.exercise_write_answer);
                    button.setText("Check");

                    nextFragment++;
                break;
            }

            // First check if the answer not is -1 (to not execute this the first time the button is pressed in the first exercise).
            if (correct[0] != -1) {
                // Later check if the answer is correct or incorrect and do the correspond thin.
                if (correct[0] == 1) {
                    // start sound
                    correctPlayer.start();
                    // delay for release resource (4 seconds)
                    new Handler().postDelayed(() -> { if (correctPlayer != null) correctPlayer.release(); }, 4000);

                    Toast.makeText(getApplicationContext(), "Correct :)", Toast.LENGTH_SHORT).show();
                } else {
                    // start sound
                    incorrectPlayer.start();
                    // delay for release resource (4 seconds)
                    new Handler().postDelayed(() -> { if (correctPlayer != null) incorrectPlayer.release(); }, 4000);

                    Toast.makeText(getApplicationContext(), "Incorrect!, retry", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Are you sure about that?");
        alertDialogBuilder.setMessage("All progress in this lesson will be lost.");

        // Close activity if press QUIT button.
        alertDialogBuilder.setPositiveButton("QUIT", (dialog, which) -> finish());

        // Close dialog if press CANCEL button.
        alertDialogBuilder.setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void changeLayout(int layoutId) {
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        getLayoutInflater().inflate(layoutId, frameLayout, true);
    }

}