package com.kyb3r.asistem.exercise;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
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

import androidx.appcompat.app.AlertDialog;

import com.kyb3r.asistem.R;

import java.util.Arrays;
import java.util.List;

public class ExerciseClass {
    private Context context;
    public static int nextFragment;
    public static int correct;

    public ExerciseClass(Context context) {
        this.context = context;
    }

    public void closeButton() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Are you sure about that?");
        alertDialogBuilder.setMessage("All progress in this lesson will be lost.");

        // Close activity if press QUIT button.
        alertDialogBuilder.setPositiveButton("QUIT", (dialog, which) -> ((Activity) context).finish());

        // Close dialog if press CANCEL button.
        alertDialogBuilder.setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private int progressBarNumber = 0;
    public void checkAnswer() {
        MediaPlayer correctPlayer = MediaPlayer.create(context, R.raw.correct);
        MediaPlayer incorrectPlayer = MediaPlayer.create(context, R.raw.incorrect);
        ProgressBar progressBar = ((Activity) context).findViewById(R.id.progress_bar);

        // First check if the answer not is -1 (to not execute this the first time the button is pressed in the first exercise).
        if (correct != -1) {
            // Later check if the answer is correct or incorrect and do the correspond thin.
            if (correct == 1) {
                // start sound
                correctPlayer.start();
                // delay for release resource (4 seconds)
                //new Handler().postDelayed(() -> { if (correctPlayer != null) correctPlayer.release(); }, 4000);

                // Progress bar
                progressBarNumber += 10;
                progressBar.setProgress(progressBarNumber);

                // Message
                Toast.makeText(context, "Correct :)", Toast.LENGTH_SHORT).show();
            } else {
                // start sound
                incorrectPlayer.start();
                // delay for release resource (4 seconds)
                //new Handler().postDelayed(() -> { if (correctPlayer != null) incorrectPlayer.release(); }, 4000);

                // Message
                Toast.makeText(context, "Incorrect!, retry", Toast.LENGTH_SHORT).show();
            }
            correct = -1;
        }
    }
    private void changeLayout(int layoutId) {
        FrameLayout frameLayout = ((Activity) context).findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        ((Activity) context).getLayoutInflater().inflate(layoutId, frameLayout, true);
    }

    public void setupRead() {
        changeLayout(R.layout.exercise_read);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText("Okay");
        // Modify fragment
        TextView text = ((Activity) context).findViewById(R.id.text);
        text.setText(((Activity) context).getString(R.string.courseFracturesRead));

        nextFragment = 0;   // put next fragmentExercise (0)
    }

    public void setupOptions() {
        changeLayout(R.layout.exercise_options);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText("Check");

        TextView instruction = ((Activity) context).findViewById(R.id.instruction);
        instruction.setText("What are fractures?");

        RadioButton option1 = ((Activity) context).findViewById(R.id.option1);
        RadioButton option2 = ((Activity) context).findViewById(R.id.option2);
        RadioButton option3 = ((Activity) context).findViewById(R.id.option3);

        option1.setText("NADIE SABE...");
        option2.setText("They are breaks in the bones and can vary in severity");
        option3.setText("Bad Bunny");


        RadioGroup radioButton = ((Activity) context).findViewById(R.id.optionsGroup);
        radioButton.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.option2) {
                correct = 1;
                nextFragment = 1;
            } else {
                correct = 0;
                nextFragment = 0;
            }
        });

        checkAnswer();
    }

    public void setupSelect() {
        changeLayout(R.layout.exercise_select);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText("Check");

        ImageButton imageOption1 = ((Activity) context).findViewById(R.id.option1), imageOption2 = ((Activity) context).findViewById(R.id.option2), imageOption3 = ((Activity) context).findViewById(R.id.option3), imageOption4 = ((Activity) context).findViewById(R.id.option4);
        imageOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = 0;
                nextFragment = 1;
            }
        });
        imageOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = 0;
                nextFragment = 1;
            }
        });
        imageOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = 0;
                nextFragment = 1;
            }
        });
        imageOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = 1;
                nextFragment = 2;
            }
        });

        checkAnswer();
    }

    public void setupWrite() {
        changeLayout(R.layout.exercise_write_answer);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText("Check");

        EditText answerEditText = ((Activity) context).findViewById(R.id.answer);
        answerEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String userAnswer = editable.toString().toLowerCase();
                List<String> correctAnswerList = Arrays.asList("uwu", "owo", "xd");

                // Check answer
                if (correctAnswerList.contains(userAnswer)) {
                    correct = 1;
                    nextFragment = 3;
                } else {
                    correct = 0;
                    nextFragment = 2;
                }
            }

        });

        checkAnswer();
    }

    public void setupTapPairs() {
        changeLayout(R.layout.exercise_tap_pairs);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setVisibility(View.GONE);

        Button button1 = ((Activity) context).findViewById(R.id.button1), button2 = ((Activity) context).findViewById(R.id.button2), button3 = ((Activity) context).findViewById(R.id.button3),
                button4 = ((Activity) context).findViewById(R.id.button4), button5 = ((Activity) context).findViewById(R.id.button5), button6 = ((Activity) context).findViewById(R.id.button6);


        boolean[] buttonClicked = new boolean[6];
        boolean[] buttonCorrect = new boolean[3];
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked[0] = true;

                if (buttonClicked[1]) {
                    buttonCorrect[0] = true;
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                } else {
                    //buttonClicked[0] = false;
                    buttonClicked[1] = false;
                    buttonClicked[2] = false;
                    buttonClicked[3] = false;
                    buttonClicked[4] = false;
                    buttonClicked[5] = false;
                }

                if (buttonCorrect[0] && buttonCorrect[1] && buttonCorrect[2]) {
                    correct = 1;
                    nextFragment = 0;  // TEMPORAL, I HOPE I HOPE I HOPE
                    button.performClick();  // Simulate click main button
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked[1] = true;

                if (buttonClicked[0]) {
                    buttonCorrect[0] = true;
                    button1.setEnabled(false);
                    button2.setEnabled(false);

                } else {
                    buttonClicked[0] = false;
                    //buttonClicked[1] = false;
                    buttonClicked[2] = false;
                    buttonClicked[3] = false;
                    buttonClicked[4] = false;
                    buttonClicked[5] = false;
                }

                if (buttonCorrect[0] && buttonCorrect[1] && buttonCorrect[2]) {
                    correct = 1;
                    nextFragment = 0;  // TEMPORAL, I HOPE I HOPE I HOPE
                    button.performClick();  // Simulate click main button
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked[2] = true;

                if (buttonClicked[3]) {
                    buttonCorrect[1] = true;
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                } else {
                    buttonClicked[0] = false;
                    buttonClicked[1] = false;
                    //buttonClicked[2] = false;
                    buttonClicked[3] = false;
                    buttonClicked[4] = false;
                    buttonClicked[5] = false;
                }


                if (buttonCorrect[0] && buttonCorrect[1] && buttonCorrect[2]) {
                    correct = 1;
                    nextFragment = 0;  // TEMPORAL, I HOPE I HOPE I HOPE
                    button.performClick();  // Simulate click main button
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked[3] = true;

                if (buttonClicked[2]) {
                    buttonCorrect[1] = true;
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                } else {
                    buttonClicked[0] = false;
                    buttonClicked[1] = false;
                    buttonClicked[2] = false;
                    //buttonClicked[3] = false;
                    buttonClicked[4] = false;
                    buttonClicked[5] = false;
                }

                if (buttonCorrect[0] && buttonCorrect[1] && buttonCorrect[2]) {
                    correct = 1;
                    nextFragment = 0;  // TEMPORAL, I HOPE I HOPE I HOPE
                    button.performClick();  // Simulate click main button
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked[4] = true;

                if (buttonClicked[5]) {
                    buttonCorrect[2] = true;
                    button5.setEnabled(false);
                    button6.setEnabled(false);

                } else {
                    buttonClicked[0] = false;
                    buttonClicked[1] = false;
                    buttonClicked[2] = false;
                    buttonClicked[3] = false;
                    //buttonClicked[4] = false;
                    buttonClicked[5] = false;
                }

                if (buttonCorrect[0] && buttonCorrect[1] && buttonCorrect[2]) {
                    correct = 1;
                    nextFragment = 0;  // TEMPORAL, I HOPE I HOPE I HOPE
                    button.performClick();  // Simulate click main button
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked[5] = true;

                if (buttonClicked[4]) {
                    buttonCorrect[2] = true;
                    button5.setEnabled(false);
                    button6.setEnabled(false);

                } else {
                    buttonClicked[0] = false;
                    buttonClicked[1] = false;
                    buttonClicked[2] = false;
                    buttonClicked[3] = false;
                    buttonClicked[4] = false;
                    //buttonClicked[5] = false;
                }

                if (buttonCorrect[0] && buttonCorrect[1] && buttonCorrect[2]) {
                    correct = 1;
                    nextFragment = 0;  // TEMPORAL, I HOPE I HOPE I HOPE
                    button.performClick();  // Simulate click main button
                }
            }
        });

        checkAnswer();
    }
}