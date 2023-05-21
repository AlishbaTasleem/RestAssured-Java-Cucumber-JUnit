package org.example.framework.models;

public class ActivityModel {
    private Integer id;
    private String title;
    private String dueDate;
    private boolean completed;

    // Constructors, getters, and setters

    // Constructors
    public ActivityModel() {
    }
    public ActivityModel(Integer id, String title, String dueDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
