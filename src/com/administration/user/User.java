package com.administration.user;

import java.util.Objects;

public class User {
    private static Long userCounter = 0L;
    private final Long userID;
    private String name;
    private String phoneNumber;

    public User(String name, String phoneNumber ){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userID = ++userCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name.toLowerCase(), user.name.toLowerCase()) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), phoneNumber);
    }

    @Override
    public String toString() {
        return
                "userID=" + userID +
                ", name= " + name +
                ", phoneNumber=" + phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getUserID() {
        return userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
