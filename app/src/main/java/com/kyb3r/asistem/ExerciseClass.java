package com.kyb3r.asistem;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;
import java.util.List;

public class ExerciseClass {
    private final String courseName;
    private final Context context;
    public static int correct, nextFragment;
    private DatabaseHelper livesDatabaseHelper;
    private int currentLives;
    private boolean finish;

    public ExerciseClass(String courseName, Context context ) {
        this.courseName = courseName;
        this.context = context;
        this.livesDatabaseHelper = new DatabaseHelper(context);
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

    private void decreaseLives() {
        currentLives = livesDatabaseHelper.getLivesCount();
        // Decrease live
        currentLives--;
        // Update lives count in database
        livesDatabaseHelper.setLivesCount(currentLives);


        // Write lives number
        TextView textLives = ((Activity) context).findViewById(R.id.lives);
        textLives.setText(String.valueOf(currentLives));

        if (currentLives <= 0) {
            ((Activity) context).setContentView(R.layout.leasson);

            // Start sound
            MediaPlayer failLeasson = MediaPlayer.create(context, R.raw.lesson_fail);
            failLeasson.start();
        }
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

                if (finish) {
                    int progress = livesDatabaseHelper.getCourseProgress(courseName);
                    int newProgress = progress + 1;
                    livesDatabaseHelper.setCourseProgress(courseName, newProgress);

                    ((Activity) context).setContentView(R.layout.leasson);
                    finish = false;
                    return;
                }
            } else {
                // start sound
                incorrectPlayer.start();
                decreaseLives(); // Restar una vida
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

        currentLives = livesDatabaseHelper.getLivesCount();
        TextView textLives = ((Activity) context).findViewById(R.id.lives);
        textLives.setText(String.valueOf(currentLives));
    }

    // Exercises setup methods.
    public void setupRead(String readText, int correctFragment) {
        changeLayout(R.layout.exercise_read);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText(R.string.exerciseOkay);

        // Modify fragment
        TextView text = ((Activity) context).findViewById(R.id.text);
        text.setText(readText);

        nextFragment = correctFragment;
        correct = -1;
    }

    public void setupVideo(int videoFile, int correctFragment) {
        changeLayout(R.layout.exercise_video);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText(R.string.exerciseOkay);

        VideoView video =  ((Activity) context).findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + videoFile);
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(context);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

        // Automatically start video
        video.start();

        nextFragment = correctFragment;
        correct = -1;
    }

    public void setupOptions(String instructionText, String option1Text, String option2Text, String option3Text, int correctOption, int correctFragment, int incorrectFragment, boolean last) {
        changeLayout(R.layout.exercise_options);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText(R.string.exerciseCheck);

        TextView instruction = ((Activity) context).findViewById(R.id.instruction);
        instruction.setText(instructionText);

        RadioButton option1 = ((Activity) context).findViewById(R.id.option1);
        RadioButton option2 = ((Activity) context).findViewById(R.id.option2);
        RadioButton option3 = ((Activity) context).findViewById(R.id.option3);

        option1.setText(option1Text);
        option2.setText(option2Text);
        option3.setText(option3Text);

        RadioGroup radioButton = ((Activity) context).findViewById(R.id.optionsGroup);
        radioButton.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == correctOption) {
                correct = 1;
                nextFragment = correctFragment;
                if (last) {
                    finish = true;
                }
            } else {
                correct = 0;
                nextFragment = incorrectFragment;
            }
        });

        checkAnswer();
    }

    public void setupSelect(String instructionText, int correctOption, int[] fragmentMappings, boolean last) {
        changeLayout(R.layout.exercise_select);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText(R.string.exerciseCheck);

        TextView instruction = ((Activity) context).findViewById(R.id.instruction);
        instruction.setText(instructionText);

        ImageButton[] imageOptions = new ImageButton[4];
        imageOptions[0] = ((Activity) context).findViewById(R.id.option1);
        imageOptions[1] = ((Activity) context).findViewById(R.id.option1);
        imageOptions[2] = ((Activity) context).findViewById(R.id.option3);
        imageOptions[3] = ((Activity) context).findViewById(R.id.option4);

        for (int i = 0; i < imageOptions.length; i++) {
            int finalI = i;
            imageOptions[i].setOnClickListener(view -> {
                if (finalI == correctOption) {
                    correct = 1;
                    if (last) {
                        finish = true;
                    }
                } else {
                    correct = 0;
                }
                nextFragment = fragmentMappings[finalI];
            });
        }

        checkAnswer();
    }

    public void setupWrite(String instructionText, List<String> correctAnswerList, int correctFragment, int incorrectFragment, boolean last) {
        changeLayout(R.layout.exercise_write_answer);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText(R.string.exerciseCheck);

        TextView instruction = ((Activity) context).findViewById(R.id.instruction);
        instruction.setText(instructionText);

        EditText answerEditText = ((Activity) context).findViewById(R.id.answer);
        answerEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String userAnswer = editable.toString().toLowerCase();

                // Check answer
                if (correctAnswerList.contains(userAnswer)) {
                    correct = 1;
                    nextFragment = correctFragment;
                    if (last) {
                        finish = true;
                    }
                } else {
                    correct = 0;
                    nextFragment = incorrectFragment;
                }
            }

        });

        checkAnswer();
    }

    public void setupTapPairs(String instructionText, int[][] buttonPairs, int correctFragment, boolean last) {
        changeLayout(R.layout.exercise_tap_pairs);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setVisibility(View.GONE);

        TextView instruction = ((Activity) context).findViewById(R.id.instruction);
        instruction.setText(instructionText);

        List<Button> buttons = new ArrayList<>();
        buttons.add(((Activity) context).findViewById(R.id.button1));
        buttons.add(((Activity) context).findViewById(R.id.button2));
        buttons.add(((Activity) context).findViewById(R.id.button3));
        buttons.add(((Activity) context).findViewById(R.id.button4));
        buttons.add(((Activity) context).findViewById(R.id.button5));
        buttons.add(((Activity) context).findViewById(R.id.button6));

        List<Integer> clickedButtons = new ArrayList<>();

        for (Button btn : buttons) {
            btn.setOnClickListener(v -> {
                int buttonIndex = buttons.indexOf(btn);

                if (clickedButtons.contains(buttonIndex)) {
                    // Button already clicked, do nothing
                    return;
                }

                clickedButtons.add(buttonIndex);

                if (clickedButtons.size() == 2) {
                    // Check if the clicked buttons form a pair
                    int button1 = clickedButtons.get(0);
                    int button2 = clickedButtons.get(1);

                    for (int[] pair : buttonPairs) {
                        if ((pair[0] == button1 && pair[1] == button2) ||
                                (pair[0] == button2 && pair[1] == button1)) {
                            // Disable the buttons in the pair
                            buttons.get(pair[0]).setEnabled(false);
                            buttons.get(pair[1]).setEnabled(false);
                        }
                    }

                    // Clear the list for the next pair
                    clickedButtons.clear();

                    // Check if all buttons are disabled
                    boolean allDisabled = true;
                    for (Button b : buttons) {
                        if (b.isEnabled()) {
                            allDisabled = false;
                            break;
                        }
                    }

                    // If all buttons are disabled, trigger the next action
                    if (allDisabled) {
                        correct = 1;
                        nextFragment = correctFragment;
                        if (last) {
                            finish = true;
                        }
                        button.performClick();
                    }
                }
            });
        }

        checkAnswer();
    }

    public void setupFinishCourse() {
        ((Activity) context).setContentView(R.layout.leasson);
        TextView title = ((Activity) context).findViewById(R.id.title), text = ((Activity) context).findViewById(R.id.text);

        title.setText("FINISH COURSE");
        text.setText("MENTIIIIIIIIIIIIIIIIIIIII");
    }

}