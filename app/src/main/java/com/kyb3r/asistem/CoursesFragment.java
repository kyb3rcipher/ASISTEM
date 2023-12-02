package com.kyb3r.asistem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kyb3r.asistem.course.DepressionCourseActivity;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    List<CoursesList> elements;
    RecyclerView recyclerCourses;

    public CoursesFragment() {
        // Required empty public constructor
    }

    public static CoursesFragment newInstance() {
        return new CoursesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerCourses = (RecyclerView) view.findViewById(R.id.coursesRecyclerView);
        recyclerCourses.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        elements = new ArrayList<>();
        elements.add(new CoursesList(getString(R.string.course1Title), getString(R.string.course1Description), R.drawable.banner_course_depression));
        //elements.add(new CoursesList(getString(R.string.course2Title), getString(R.string.course2Description), R.drawable.banner_course_fractures));

        DatabaseHelper db = new DatabaseHelper(getContext());

        for (CoursesList course : elements) {
            // If the course not exists add (to avoid duplicate rows)
            if (!db.isCourseExists(course.getTitle())) {
                db.addCourse(course.getTitle(), 0);
            }
        }

        CoursesAdapter adapter = new CoursesAdapter(elements);
        recyclerCourses.setAdapter(adapter);

        // Cards clicks
        adapter.setOnItemClickListener(coursesList -> {
            Intent course = null;

            switch (elements.indexOf(coursesList)) {
                case 0:
                    course = new Intent(getContext(), DepressionCourseActivity.class);
                    break;
            }

            // If you don't have bands no start course
            if (db.getBandsCount() > 0) {
                startActivity(course);
            } else {
                Toast.makeText(getContext(), R.string.courseNoBands_title, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}