package com.example.app;

public class CardInfo {
    private final int id;
    private String title;
    private String priority;
    private String status;
    private String deadline;
    private String category;
    private String description;

    public CardInfo(int id, String title, String priority, String status, String deadline, String category,  String description) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.deadline = deadline;
        this.category = category;
        this.description = description;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getPriority() {

        return priority;
    }

    public void setPriority(String priority) {

        this.priority = priority;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getDeadline() {

        return deadline;
    }

    public void setDeadline(String deadline) {

        this.deadline = deadline;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

}
