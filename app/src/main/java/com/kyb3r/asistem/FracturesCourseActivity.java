package com.kyb3r.asistem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class FracturesCourseActivity extends AppCompatActivity {

    private int currentFragment = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fractures_course);

        Button button = findViewById(R.id.button);

        // Set first Layout
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        getLayoutInflater().inflate(R.layout.exercise_read, frameLayout, true);
        button.setText("Okay");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                switch (currentFragment) {
                    case 0:
                        changeLayout(R.layout.exercise_options);
                        button.setText("Check");

                        TextView instruction = findViewById(R.id.instruction);
                        instruction.setText("UwU");

                        currentFragment++;
                    break;

                    case 1:
                        changeLayout(R.layout.exercise_select);
                        button.setText("Check");

                        currentFragment++;
                    break;

                    case 2:
                        changeLayout(R.layout.exercise_write_answer);
                        button.setText("Check");

                        currentFragment++;
                    break;
                }



            }
        });
    }

    private void changeLayout(int layoutId) {
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        getLayoutInflater().inflate(layoutId, frameLayout, true);
    }

}