package com.kyb3r.savethevita;

public class CoursesList {
    public String title, description;
    private final int imageResource;
    public CoursesList(String title, String description, int imageResource) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }
    public String getTitle() {
        return title;
    }

    /*public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

    public String getDescription() {
        return description;
    }
}
