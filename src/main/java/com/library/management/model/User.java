package com.library.management.model;

import java.time.LocalDate;

public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;
    private LocalDate membershipDate;
    private String membershipStatus;

    // Constructors
    public User() {}

    public User(String name, String email, String phone, LocalDate membershipDate) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipDate = membershipDate;
        this.membershipStatus = "ACTIVE";
    }

    public User(int userId, String name, String email, String phone, LocalDate membershipDate, String membershipStatus) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipDate = membershipDate;
        this.membershipStatus = membershipStatus;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(String membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", membershipDate=" + membershipDate +
                ", membershipStatus='" + membershipStatus + '\'' +
                '}';
    }
}