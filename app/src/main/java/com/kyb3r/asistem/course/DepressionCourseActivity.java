package com.kyb3r.asistem.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.kyb3r.asistem.DatabaseHelper;
import com.kyb3r.asistem.ExerciseClass;
import com.kyb3r.asistem.R;

import java.util.Arrays;

public class DepressionCourseActivity extends AppCompatActivity {

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
                exercises.video(R.raw.depression_es);
                button.setOnClickListener(v -> {
                    switch (ExerciseClass.nextFragment) {
                        case 0:
                            exercises.options(
                                    "Selecciona sintomas de la depresion:",
                                    "Dolor de cabeza, Llanto, Cambio en la manera de vestirse",
                                    "Apatía, Perdida de interés, Cambios en el apetito, Sentirse inultil/culpable",
                                    "HOLA",
                                    R.id.option2,
                                    1, 0, false
                            );
                            break;
                        case 1:
                            exercises.write(
                                    "Escribe un neurotransmisor que en menor medida genere la depresion",
                                    Arrays.asList("serotonina", "dopamina", "norepinefrina"),
                                    2, 1, false
                            );
                            break;
                        case 2:
                            exercises.select(
                                    "Selecciona un cambio que se produce en el cerebro de una persona con depresion",
                                    R.drawable.pequeno,
                                    "Lobulos/hipocampo mas pequeño",
                                    R.drawable.cerebro_blancoo,
                                    "Cerebro vacio/blanco",
                                    R.drawable.visionrayosx,
                                    "Vision rayos X",
                                    R.drawable.grande,
                                    "Lobulos frontales e hipocampo mas grandes",
                                    1,
                                    3, 2, false
                            );
                            break;
                        case 3:
                            exercises.options(
                                    "Cuanto tarda aproximadamente una persona con una enfermedad mental/depresion en pedir ayuda:",
                                    "12 meses",
                                    "6 meses",
                                    "10 años",
                                    R.id.option3,
                                    4, 3, true
                            );
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        case 9:
                            break;
                        case 10:
                            break;
                        case 11:
                            break;
                        case 12:
                            break;
                        case 13:
                            break;
                        case 14:
                            break;
                        case 15:
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