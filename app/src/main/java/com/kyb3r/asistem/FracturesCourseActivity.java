package com.kyb3r.asistem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FracturesCourseActivity extends AppCompatActivity {

    private int nextFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fractures_course);

        Button button = findViewById(R.id.button);

        // Set first Layout
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        getLayoutInflater().inflate(R.layout.exercise_read, frameLayout, true);
        button.setText("Okay");
        TextView text = frameLayout.findViewById(R.id.text);
        text.setText(getString(R.string.courseFracturesRead));
        nextFragment = 0;

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
                        nextFragment = (checkedId == R.id.option2 && checkedId == group.getCheckedRadioButtonId()) ? 1 : 0;
                    });
                    /*radioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if (checkedId == R.id.option2) {
                                if (group.getCheckedRadioButtonId() == R.id.option2) {
                                    nextFragment = 1;
                                }
                            } else if (checkedId == R.id.option1 || checkedId == R.id.option3) {
                                if (group.getCheckedRadioButtonId() == R.id.option1 || group.getCheckedRadioButtonId() == R.id.option3) {
                                    nextFragment = 0;
                                }
                            }
                        }
                    });*/
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

        });
    }

    private void changeLayout(int layoutId) {
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        getLayoutInflater().inflate(layoutId, frameLayout, true);
    }

}