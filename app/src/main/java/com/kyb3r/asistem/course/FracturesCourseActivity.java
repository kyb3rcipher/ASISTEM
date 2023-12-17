package com.kyb3r.asistem.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.kyb3r.asistem.DatabaseHelper;
import com.kyb3r.asistem.ExerciseClass;
import com.kyb3r.asistem.R;

import java.util.Arrays;

public class FracturesCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ExerciseClass exercises = new ExerciseClass(getString(R.string.course1Title), this);
        // Add closeButton function to close (the left X)
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> exercises.closeButton());

        DatabaseHelper db = new DatabaseHelper(this);
        int progress = db.getCourseProgress(getString(R.string.course1Title));
        Button button = findViewById(R.id.button);
        // Exercise steps progress switcher
        switch (progress) {
            case 0:
                exercises.video("depression");
                button.setOnClickListener(v -> {
                    switch (ExerciseClass.nextFragment) {
                        case 0:
                            exercises.options(
                                    getString(R.string.courseFractures_step1_case0_instruction),
                                    getString(R.string.courseFractures_step1_case0_option1),
                                    getString(R.string.courseFractures_step1_case0_option2),
                                    getString(R.string.courseFractures_step1_case0_option3),
                                    R.id.option2,
                                    1, 0, false
                            );
                            break;
                        case 1:
                            exercises.write(
                                    getString(R.string.courseFractures_step1_case1_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step1_case1_word1),
                                            getString(R.string.courseFractures_step1_case1_word2),
                                            getString(R.string.courseFractures_step1_case1_word3)
                                    ),
                                    2, 1, false
                            );
                            break;
                        case 2:
                            exercises.select(
                                    getString(R.string.courseFractures_step1_case2_instruction),
                                    R.drawable.pequeno,
                                    getString(R.string.courseFractures_step1_case2_text1),
                                    R.drawable.cerebro_blancoo,
                                    getString(R.string.courseFractures_step1_case2_text2),
                                    R.drawable.visionrayosx,
                                    getString(R.string.courseFractures_step1_case2_text3),
                                    R.drawable.grande,
                                    getString(R.string.courseFractures_step1_case2_text4),
                                    1,
                                    3, 2, false
                            );
                            break;
                        case 3:
                            exercises.options(
                                    getString(R.string.courseFractures_step1_case3_instruction),
                                    getString(R.string.courseFractures_step1_case3_option1),
                                    getString(R.string.courseFractures_step1_case3_option2),
                                    getString(R.string.courseFractures_step1_case3_option3),
                                    R.id.option3,
                                    4, 3, false
                            );
                            break;
                        case 4:
                            exercises.options(
                                    getString(R.string.courseFractures_step1_case4_instruction),
                                    getString(R.string.courseFractures_step1_case4_option1),
                                    getString(R.string.courseFractures_step1_case4_option2),
                                    getString(R.string.courseFractures_step1_case4_option3),
                                    R.id.option2,
                                    5, 4, false
                            );
                            break;
                        case 5:
                            exercises.options(
                                    getString(R.string.courseFractures_step1_case5_instruction),
                                    getString(R.string.courseFractures_step1_case5_text1),
                                    getString(R.string.courseFractures_step1_case5_text2),
                                    getString(R.string.courseFractures_step1_case5_text3),
                                    R.id.option1,
                                    6, 5, false
                            );
                            break;
                        case 6:
                            exercises.write(
                                    getString(R.string.courseFractures_step1_case6_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step1_case6_word1),
                                            getString(R.string.courseFractures_step1_case6_word2),
                                            getString(R.string.courseFractures_step1_case6_word3)
                                    ),
                                    7, 6, false
                            );
                            break;
                        case 7:
                            exercises.options(
                                    getString(R.string.courseFractures_step1_case7_instruction),
                                    getString(R.string.courseFractures_step1_case7_option1),
                                    getString(R.string.courseFractures_step1_case7_option2),
                                    getString(R.string.courseFractures_step1_case7_option3),
                                    R.id.option2,
                                    8, 7, false
                            );
                            break;
                        case 8:
                            exercises.write(
                                    getString(R.string.courseFractures_step1_case8_instruction),
                                    Arrays.asList(getString(R.string.courseFractures_step1_case8_word1), getString(R.string.courseFractures_step1_case8_word2), getString(R.string.courseFractures_step1_case8_word3)),
                                    9, 8, false
                            );
                            break;
                        case 9:
                            exercises.select(
                                    getString(R.string.courseFractures_step1_case9_instruction),
                                    R.drawable.avatar1,
                                    getString(R.string.courseFractures_step1_case9_text1),
                                    R.drawable.avatar2,
                                    getString(R.string.courseFractures_step1_case9_text2),
                                    R.drawable.avatar3,
                                    getString(R.string.courseFractures_step1_case9_text3),
                                    R.drawable.avatar4,
                                    getString(R.string.courseFractures_step1_case9_text4),
                                    3,
                                    10, 9, false
                            );
                            break;
                        case 10:
                            exercises.select(
                                    getString(R.string.courseFractures_step1_case10_instruction),
                                    R.drawable.llorar,
                                    getString(R.string.courseFractures_step1_case10_text1),
                                    R.drawable.genetica,
                                    getString(R.string.courseFractures_step1_case10_text2),
                                    R.drawable.hablar,
                                    getString(R.string.courseFractures_step1_case10_text3),
                                    R.drawable.definitiva,
                                    getString(R.string.courseFractures_step1_case10_text4),
                                    3,
                                    10, 10, true
                            );
                            break;
                    }
                });
                break;

            case 1:
                exercises.read(getString(R.string.courseFractures_step2_readText), 0);
                button.setOnClickListener(v -> {
                    switch (ExerciseClass.nextFragment) {
                        case 0:
                            exercises.options(
                                    getString(R.string.courseFractures_step2_case0_instruction),
                                    getString(R.string.courseFractures_step2_case0_option1),
                                    getString(R.string.courseFractures_step2_case0_option2),
                                    getString(R.string.courseFractures_step2_case0_option3),
                                    R.id.option2,
                                    1, 0, false
                            );
                            break;
                        case 1:
                            exercises.options(
                                    getString(R.string.courseFractures_step2_case1_instruction),
                                    getString(R.string.courseFractures_step2_case1_option1),
                                    getString(R.string.courseFractures_step2_case1_option2),
                                    getString(R.string.courseFractures_step2_case1_option3),
                                    R.id.option1,
                                    2, 1, false
                            );
                            break;
                        case 2:
                            exercises.write(
                                    getString(R.string.courseFractures_step2_case2_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step2_case2_word1),
                                            getString(R.string.courseFractures_step2_case2_word2)
                                    ),
                                    3, 2, false
                            );
                            break;
                        case 3:
                            exercises.select(
                                    getString(R.string.courseFractures_step2_case3_instruction),
                                    R.drawable.avatar1,
                                    getString(R.string.courseFractures_step2_case3_option1),
                                    R.drawable.brain,
                                    getString(R.string.courseFractures_step2_case3_option2),
                                    R.drawable.tristeza,
                                    getString(R.string.courseFractures_step2_case3_option3),
                                    R.drawable.fuerza,
                                    getString(R.string.courseFractures_step2_case3_option4),
                                    2,
                                    4, 3, false
                            );
                            break;
                        case 4:
                            exercises.select(
                                    getString(R.string.courseFractures_step2_case4_instruction),
                                    R.drawable.emotions,
                                    getString(R.string.courseFractures_step2_case4_word1),
                                    R.drawable.warrior,
                                    getString(R.string.courseFractures_step2_case4_word2),
                                    R.drawable.ninguno,
                                    getString(R.string.courseFractures_step2_case4_word3),
                                    R.drawable.friends,
                                    getString(R.string.courseFractures_step2_case4_word4),
                                    4,
                                    5, 4, false
                            );
                            break;
                        case 5:
                            exercises.options(
                                    getString(R.string.courseFractures_step2_case5_instruction),
                                    getString(R.string.courseFractures_step2_case5_option1),
                                    getString(R.string.courseFractures_step2_case5_option2),
                                    getString(R.string.courseFractures_step2_case5_option3),
                                    R.id.option3,
                                    6, 5, false
                            );
                            break;
                        case 6:
                            exercises.options(
                                    getString(R.string.courseFractures_step2_case6_instruction),
                                    getString(R.string.courseFractures_step2_case6_option1),
                                    getString(R.string.courseFractures_step2_case6_option2),
                                    getString(R.string.courseFractures_step2_case6_option3),
                                    R.id.option2,
                                    7, 6, false
                            );
                            break;
                        case 7:
                            exercises.write(
                                    getString(R.string.courseFractures_step2_case7_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step2_case7_option1),
                                            getString(R.string.courseFractures_step2_case7_option2),
                                            getString(R.string.courseFractures_step2_case7_option3)
                                    ),
                                    8, 7, false
                            );
                            break;
                        case 8:
                            exercises.write(
                                    getString(R.string.courseFractures_step2_case8_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step2_case8_option1),
                                            getString(R.string.courseFractures_step2_case8_option2),
                                            getString(R.string.courseFractures_step2_case8_option3)
                                    ),
                                    9, 8, false
                            );
                            break;
                        case 9:
                            exercises.options(
                                    getString(R.string.courseFractures_step2_case9_instruction),
                                    getString(R.string.courseFractures_step2_case9_option1),
                                    getString(R.string.courseFractures_step2_case9_option2),
                                    getString(R.string.courseFractures_step2_case9_option3),
                                    R.id.option1,
                                    10, 9, false
                            );
                            break;
                        case 10:
                            exercises.write(
                                    getString(R.string.courseFractures_step2_case10_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step2_case10_word1),
                                            getString(R.string.courseFractures_step2_case10_word2),
                                            getString(R.string.courseFractures_step2_case10_word3),
                                            getString(R.string.courseFractures_step2_case10_word4)
                                    ),
                                    10, 10, true
                            );
                            break;
                    }
                });
                break;

            case 2:
                exercises.read(getString(R.string.courseFractures_step3_readText), 0);
                button.setOnClickListener(v -> {
                    switch (ExerciseClass.nextFragment) {
                        case 0:
                            exercises.options(
                                    getString(R.string.courseFractures_step3_case0_instruction),
                                    getString(R.string.courseFractures_step3_case0_option1),
                                    getString(R.string.courseFractures_step3_case0_option2),
                                    getString(R.string.courseFractures_step3_case0_option3),
                                    R.id.option2,
                                    1, 0, false
                            );
                            break;
                        case 1:
                            exercises.write(
                                    getString(R.string.courseFractures_step3_case1_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step3_case1_option1),
                                            getString(R.string.courseFractures_step3_case1_option2),
                                            getString(R.string.courseFractures_step3_case1_option3)
                                    ),
                                    2, 1, false
                            );
                            break;
                        case 2:
                            exercises.write(
                                    getString(R.string.courseFractures_step3_case2_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step3_case2_word1),
                                            getString(R.string.courseFractures_step3_case2_word2)
                                    ),
                                    3, 2, false
                            );
                            break;
                        case 3:
                            exercises.options(
                                    getString(R.string.courseFractures_step3_case3_instruction),
                                    getString(R.string.courseFractures_step3_case3_option1),
                                    getString(R.string.courseFractures_step3_case3_option2),
                                    getString(R.string.courseFractures_step3_case3_option3),
                                    R.id.option1,
                                    4, 3, false
                            );
                            break;
                        case 4:
                            exercises.options(
                                    getString(R.string.courseFractures_step3_case4_instruction),
                                    getString(R.string.courseFractures_step3_case4_word1),
                                    getString(R.string.courseFractures_step3_case4_word2),
                                    getString(R.string.courseFractures_step3_case4_word3),
                                    R.id.option1,
                                    5, 4, false
                            );
                            break;
                        case 5:
                            exercises.options(
                                    getString(R.string.courseFractures_step3_case5_instruction),
                                    getString(R.string.courseFractures_step3_case5_option1),
                                    getString(R.string.courseFractures_step3_case5_option2),
                                    getString(R.string.courseFractures_step3_case5_option3),
                                    R.id.option2,
                                    6, 5, false
                            );
                            break;
                        case 6:
                            exercises.options(
                                    getString(R.string.courseFractures_step3_case6_instruction),
                                    getString(R.string.courseFractures_step3_case6_option1),
                                    getString(R.string.courseFractures_step3_case6_option2),
                                    getString(R.string.courseFractures_step3_case6_option3),
                                    R.id.option2,
                                    7, 6, false
                            );
                            break;
                        case 7:
                            exercises.write(
                                    getString(R.string.courseFractures_step3_case7_instruction),
                                    Arrays.asList(getString(R.string.courseFractures_step3_case7_option1), getString(R.string.courseFractures_step3_case7_option2), getString(R.string.courseFractures_step3_case7_option3)),
                                    8, 7, false
                            );
                            break;
                        case 8:
                            exercises.write(
                                    getString(R.string.courseFractures_step3_case8_instruction),
                                    Arrays.asList(
                                            getString(R.string.courseFractures_step3_case8_option1),
                                            getString(R.string.courseFractures_step3_case8_option2),
                                            getString(R.string.courseFractures_step3_case8_option3)
                                    ),
                                    9, 8, false
                            );
                            break;
                        case 9:
                            exercises.options(
                                    getString(R.string.courseFractures_step3_case9_instruction),
                                    getString(R.string.courseFractures_step3_case9_option1),
                                    getString(R.string.courseFractures_step3_case9_option2),
                                    getString(R.string.courseFractures_step3_case9_option3),
                                    R.id.option2,
                                    10, 9, false
                            );
                            break;
                        case 10:
                            exercises.select(
                                    getString(R.string.courseFractures_step3_case10_instruction),
                                    R.drawable.low,
                                    getString(R.string.courseFractures_step3_case10_word1),
                                    R.drawable.lack,
                                    getString(R.string.courseFractures_step3_case10_word2),
                                    R.drawable.grades,
                                    getString(R.string.courseFractures_step3_case10_word3),
                                    R.drawable.feelings,
                                    getString(R.string.courseFractures_step3_case10_word4),
                                    2,
                                    10, 10, true
                            );
                            break;
                    }
                });
                break;

            default:
                exercises.setupFinishCourse();
                break;
        }
    }
}