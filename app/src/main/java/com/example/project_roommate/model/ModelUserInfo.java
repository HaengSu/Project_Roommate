package com.example.project_roommate.model;

public class ModelUserInfo {
    String name;
    String age;
    String sleepTime;
    String hopePay;

    public ModelUserInfo(String name, String age, String sleepTime, String hopePay) {
        this.name = name;
        this.age = age;
        this.sleepTime = sleepTime;
        this.hopePay = hopePay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getHopePay() {
        return hopePay;
    }

    public void setHopePay(String hopePay) {
        this.hopePay = hopePay;
    }
}
