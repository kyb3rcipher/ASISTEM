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
                                    "Estar solo todo el dia, dormir mucho, no jugar videojuegos",
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
                                    4, 3, false
                            );
                            break;
                        case 4:
                            exercises.options(
                                    "¿Qué diferencia hay entre la depresión clínica y simplemente sentirse deprimido?",
                                    "La depresión clínica es una respuesta temporal a situaciones como obtener malas calificaciones. En contraste, sentirse deprimido es una respuesta y afeccion medica que puede permsistir durante semanas",
                                    "La depresión clínica es una afección médica que persiste durante al menos dos semanas y afecta significativamente la capacidad de trabajar, jugar o amar. En contraste, sentirse deprimido puede ser una respuesta temporal a situaciones como obtener malas calificaciones",
                                    "La depresión clínica es una enfermedad que puede ser detectada por rayos X",
                                    R.id.option2,
                                    5, 4, false
                            );
                            break;
                        case 5:
                            exercises.options(
                                    "¿Cuáles son algunos tratamientos efectivos para la depresión?",
                                    "Terapia, Medicamentos, Tratamientos experimentales como la estimulación magnética transcraneal.",
                                    "Terapia, Comer mas, Salir con amigos",
                                    "Terapia, Salir de viaje, Tratamientos experimentales como las drogas",
                                    R.id.option1,
                                    6, 5, false
                            );
                            break;
                        case 6:
                            exercises.write(
                                    "Escribe ¿Cómo se describe la terapia electroconvulsiva y en qué casos se menciona que puede ser especialmente útil como tratamiento para la depresión?",
                                    Arrays.asList("terapia electroconvulsiva", "electroconvulsiva terapia", "electroconvulsiva"),
                                    7, 6, false
                            );
                            break;
                        case 7:
                            exercises.options(
                                    "Selecciona la respuesta correcta",
                                    "La depresion es una enfermedad que se cura con medicamentos",
                                    "La depresion aunque es una enfermedad no signifca que la persona tenga una debilidad",
                                    "La depresion es equivalente a ser una persona suicida",
                                    R.id.option2,
                                    8, 7, false
                            );
                            break;
                        case 8:
                            exercises.write(
                                    "Escribe el porcentaje de adultos que tienen depresion en Estados Unidos",
                                    Arrays.asList("10%", "10", "10porciento", "diez", "diezporciento"),
                                    9, 8, false
                            );
                            break;
                        case 9:
                            exercises.select(
                                    "Selecciona la persona que posiblemente tenga depresion",
                                    R.drawable.avatar1,
                                    "Se siente mal desde hace unos dias por que reprobo su examen",
                                    R.drawable.avatar2,
                                    "No ha querido salir en las ultimas 2 semanas por la muerte de su mascota",
                                    R.drawable.avatar3,
                                    "Se ha sentido culpable, tiene apatia y se siente generalmente mal desde hace 1 mes",
                                    R.drawable.avatar4,
                                    "No ha comido en todo el dia por que los dias lluviosos le ponen triste",
                                    3,
                                    10, 9, false
                            );
                            break;
                        case 10:
                            exercises.select(
                                    "Selecciona la respuesta correcta",
                                    R.drawable.llorar,
                                    "Llorar una vez es un indicio de depresion",
                                    R.drawable.genetica,
                                    "La depresion es 100% genetica",
                                    R.drawable.hablar,
                                    "Hablar sobre la depresion ayuda",
                                    R.drawable.definitiva,
                                    "La depresion ya tiene una cura definitiva",
                                    1,
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