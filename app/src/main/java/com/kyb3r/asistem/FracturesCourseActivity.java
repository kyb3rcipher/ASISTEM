package com.kyb3r.asistem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class FracturesCourseActivity extends AppCompatActivity {

    private int nextFragment;
    private int[] progrssBarNumber = {0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fractures_course);

        // Add closeButton function to close (the left X)
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> onBackPressed());

        // Initialize elements
        ProgressBar progressBar = findViewById(R.id.progress_bar);
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

                    ImageButton imageOption1 = findViewById(R.id.option1), imageOption2 = findViewById(R.id.option2), imageOption3 = findViewById(R.id.option3), imageOption4 = findViewById(R.id.option4);
                    imageOption1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            correct[0] = 0;
                            nextFragment = 1;
                        }
                    });
                    imageOption2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            correct[0] = 0;
                            nextFragment = 1;
                        }
                    });
                    imageOption3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            correct[0] = 0;
                            nextFragment = 1;
                        }
                    });
                    imageOption4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            correct[0] = 1;
                            nextFragment = 2;
                        }
                    });
                break;

                case 2:
                    changeLayout(R.layout.exercise_write_answer);
                    button.setText("Check");

                    EditText answerEditText = findViewById(R.id.answer);
                    answerEditText.addTextChangedListener(new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                        @Override
                        public void afterTextChanged(Editable editable) {
                            String userAnswer = editable.toString().toLowerCase();
                            List<String> correctAnswerList = Arrays.asList("uwu", "owo", "xd");

                            // Check answer
                            if (correctAnswerList.contains(userAnswer)) {
                                correct[0] = 1;
                                nextFragment = 1;   // tmp I HOPE, I HOPE, I HOPE...
                            } else {
                                correct[0] = 0;
                                nextFragment = 2;
                            }
                        }

                    });
                break;
            }

            // First check if the answer not is -1 (to not execute this the first time the button is pressed in the first exercise).
            if (correct[0] != -1) {
                // Later check if the answer is correct or incorrect and do the correspond thin.
                if (correct[0] == 1) {
                    // start sound
                    correctPlayer.start();
                    // delay for release resource (4 seconds)
                    //new Handler().postDelayed(() -> { if (correctPlayer != null) correctPlayer.release(); }, 4000);

                    // Progress bar
                    progrssBarNumber[0] += 10;
                    progressBar.setProgress(progrssBarNumber[0]);

                    // Message
                    Toast.makeText(getApplicationContext(), "Correct :)", Toast.LENGTH_SHORT).show();
                } else {
                    // start sound
                    incorrectPlayer.start();
                    // delay for release resource (4 seconds)
                    //new Handler().postDelayed(() -> { if (correctPlayer != null) incorrectPlayer.release(); }, 4000);

                    // Message
                    Toast.makeText(getApplicationContext(), "Incorrect!, retry", Toast.LENGTH_SHORT).show();
                }
                correct[0] = -1;
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