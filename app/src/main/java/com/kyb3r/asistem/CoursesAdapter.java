package com.kyb3r.asistem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private final List<CoursesList> coursesList;

    public CoursesAdapter(List<CoursesList> coursesList) {
        this.coursesList = coursesList;
    }

    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, int position) {
        CoursesList course = coursesList.get(position);
        holder.setCourses(course);
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView courseImage;
        TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.course1Image);
            title = itemView.findViewById(R.id.course1Name);
            description = itemView.findViewById(R.id.course1Description);
        }

        public void setCourses(CoursesList course) {
            title.setText(course.getTitle());
            description.setText(course.getDescription());
            courseImage.setImageResource(course.getImageResource());
        }
    }
}