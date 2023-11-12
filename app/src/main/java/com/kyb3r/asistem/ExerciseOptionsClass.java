package com.kyb3r.asistem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ExerciseOptionsClass {
    private Context context;

    public ExerciseOptionsClass(Context context) {
        this.context = context;
        setupExerciseOptions();
    }

    public void setupExerciseOptions() {
        FrameLayout frameLayout = ((Activity) context).findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        LayoutInflater.from(context).inflate(R.layout.exercise_options, frameLayout, true);

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


//        RadioGroup radioButton = ((Activity) context).findViewById(R.id.optionsGroup);
//        radioButton.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId == R.id.option2) {
//                correct[0] = 1;
//                nextFragment = 1;
//            } else {
//                correct[0] = 0;
//                nextFragment = 0;
//            }
//        });
    }
}