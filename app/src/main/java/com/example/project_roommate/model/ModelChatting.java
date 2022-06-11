package com.example.project_roommate.model;

public class ModelChatting {

    String message;
    String name;
    String time;

    public ModelChatting() {}

    public ModelChatting(String n, String m, String t) {
        this.name = n;
        this.message = m;
        this.time = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
