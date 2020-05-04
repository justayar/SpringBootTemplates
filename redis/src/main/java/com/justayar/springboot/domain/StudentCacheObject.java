package com.justayar.springboot.domain;

public class StudentCacheObject {

    private String name;
    private String email;
    private String major;
    private double gpa;
    private boolean hasScholar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean isHasScholar() {
        return hasScholar;
    }

    public void setHasScholar(boolean hasScholar) {
        this.hasScholar = hasScholar;
    }
}
