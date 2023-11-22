package com.kyb3r.asistem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyb3r.asistem.course.FracturesCourseActivity;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    List<CoursesList> elements;
    RecyclerView recyclerCourses;

    public CoursesFragment() {
        // Required empty public constructor
    }

    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerCourses = (RecyclerView) view.findViewById(R.id.coursesRecyclerView);
        recyclerCourses.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        elements = new ArrayList<>();
        elements.add(new CoursesList(getString(R.string.course1Title), getString(R.string.course1Description), R.drawable.banner_course_fractures));
        elements.add(new CoursesList(getString(R.string.course2Title), getString(R.string.course2Description), R.drawable.banner_course_suicide));

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
            switch (elements.indexOf(coursesList)) {
                case 0:
                    Intent intent = new Intent(getContext(), FracturesCourseActivity.class);
                    startActivity(intent);
                    break;
            }
        });

        return view;
    }
}