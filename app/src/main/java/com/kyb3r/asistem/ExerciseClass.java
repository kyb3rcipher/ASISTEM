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
    private DatabaseHelper db;
    private int currentBands;
    private boolean finish;

    public ExerciseClass(String courseName, Context context ) {
        this.courseName = courseName;
        this.context = context;
        this.db = new DatabaseHelper(context);
    }

    public void closeButton() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(R.string.exerciseAlert_title);
        alertDialogBuilder.setMessage(R.string.exerciseAlert_message);

        // Close activity if press QUIT button.
        alertDialogBuilder.setPositiveButton(R.string.exerciseAlert_quit, (dialog, which) -> ((Activity) context).finish());

        // Close dialog if press CANCEL button.
        alertDialogBuilder.setNegativeButton(R.string.exerciseAlert_cancel, (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void decreaseBands() {
        currentBands = db.getBandsCount();
        // Decrease band
        currentBands--;
        // Update band count in database
        db.setBandsCount(currentBands);


        // Write bands number
        TextView bandsText = ((Activity) context).findViewById(R.id.bands);
        bandsText.setText(String.valueOf(currentBands));

        if (currentBands <= 0) {
            setupLeasson(R.string.courseNoBands_title, R.string.courseNoBands_message);

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
                Toast.makeText(context, R.string.exerciseCorrect, Toast.LENGTH_SHORT).show();

                if (finish) {
                    int progress = db.getCourseProgress(courseName);
                    int newProgress = progress + 1;
                    db.setCourseProgress(courseName, newProgress);

                    ((Activity) context).setContentView(R.layout.leasson);
                    finish = false;
                    return;
                }
            } else {
                // start sound
                incorrectPlayer.start();
                decreaseBands(); // Rest a life
                // delay for release resource (4 seconds)
                //new Handler().postDelayed(() -> { if (correctPlayer != null) incorrectPlayer.release(); }, 4000);

                // Message
                Toast.makeText(context, R.string.exerciseIncorrect, Toast.LENGTH_SHORT).show();
            }
            correct = -1;
        }
    }

    private void changeLayout(int layoutId) {
        FrameLayout frameLayout = ((Activity) context).findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        ((Activity) context).getLayoutInflater().inflate(layoutId, frameLayout, true);

        currentBands = db.getBandsCount();
        TextView textLives = ((Activity) context).findViewById(R.id.bands);
        textLives.setText(String.valueOf(currentBands));
    }

    // Exercises setup methods.
    public void read(String readText, int correctFragment) {
        changeLayout(R.layout.exercise_read);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText(R.string.exerciseOkay);

        // Modify fragment
        TextView text = ((Activity) context).findViewById(R.id.text);
        text.setText(readText);

        nextFragment = correctFragment;
        correct = -1;
    }

    public void video(String videoName) {
        changeLayout(R.layout.exercise_video);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText(R.string.exerciseOkay);

        VideoView video =  ((Activity) context).findViewById(R.id.videoView);

        // Get the code of the current locale
        String localeCode = context.getResources().getConfiguration().locale.getLanguage();

        // Build the video file name based on the locale
        String videoFileName = videoName + "_" + localeCode;

        // Get the resource identifier for the video
        int videoFile = context.getResources().getIdentifier(videoFileName, "raw", context.getPackageName());

        if (videoFile == 0) {
            // If the video specific to the locale is not found, use the default one
            videoFile = context.getResources().getIdentifier(videoName, "raw", context.getPackageName());
        }

        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + videoFile);
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(context);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

        // Start the video automatically
        video.start();

        nextFragment = 0;
        correct = -1;
    }

    public void options(String instructionText, String option1Text, String option2Text, String option3Text, int correctOption, int correctFragment, int incorrectFragment, boolean last) {
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
                if (last) {
                    finish = true;
                    return;
                }
                nextFragment = correctFragment;
            } else {
                correct = 0;
                nextFragment = incorrectFragment;
            }
        });

        checkAnswer();
    }

    public void select(String instructionText, int image1, String text1, int image2, String text2, int image3, String text3, int image4, String text4, int correctOption, int correctFragment, int incorrectFragment, boolean last) {
        changeLayout(R.layout.exercise_select);
        Button button = ((Activity) context).findViewById(R.id.button);
        button.setText(R.string.exerciseCheck);

        TextView instruction = ((Activity) context).findViewById(R.id.instruction);
        instruction.setText(instructionText);

        TextView option1Text = ((Activity) context).findViewById(R.id.option1Text), option2Text = ((Activity) context).findViewById(R.id.option2Text), option3Text = ((Activity) context).findViewById(R.id.option3Text), option4Text = ((Activity) context).findViewById(R.id.option4Text);
        option1Text.setText(text1);
        option2Text.setText(text2);
        option3Text.setText(text3);
        option4Text.setText(text4);

        ImageButton[] imageOptions = new ImageButton[4];
        imageOptions[0] = ((Activity) context).findViewById(R.id.option1);
        imageOptions[1] = ((Activity) context).findViewById(R.id.option2);
        imageOptions[2] = ((Activity) context).findViewById(R.id.option3);
        imageOptions[3] = ((Activity) context).findViewById(R.id.option4);

        imageOptions[0].setImageResource(image1);
        imageOptions[1].setImageResource(image2);
        imageOptions[2].setImageResource(image3);
        imageOptions[3].setImageResource(image4);

        for (int i = 0; i < imageOptions.length; i++) {
            int finalI = i;
            imageOptions[i].setOnClickListener(view -> {
                if (finalI == correctOption - 1) {
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
        }

        checkAnswer();
    }

    public void write(String instructionText, List<String> correctAnswerList, int correctFragment, int incorrectFragment, boolean last) {
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

    public void tapPairs(String instructionText, String[] buttonPairsText, int[][] buttonPairs, int correctFragment, boolean last) {
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

        for (int i = 0; i < buttons.size(); i++) {
            Button buttonText = buttons.get(i);
            buttonText.setText(buttonPairsText[i]);
        }

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

    public void setupLeasson(int tittle, int message) {
        ((Activity) context).setContentView(R.layout.leasson);
        // Set close button
        ImageButton closeButton = ((Activity) context).findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() { @Override  public void onClick(View v) { ((Activity) context).finish(); }});

        // Set text
        TextView title = ((Activity) context).findViewById(R.id.title), text = ((Activity) context).findViewById(R.id.text);
        title.setText(tittle);
        text.setText(message);
    }

    public void setupFinishCourse() {
        setupLeasson(R.string.courseFinished_title, R.string.courseFinished_message);
    }
}