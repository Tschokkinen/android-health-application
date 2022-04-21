package com.ericaskari.healthapplication.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity
public class User {
    @NonNull
    @PrimaryKey
    public String id;

    @ColumnInfo()
    public String firstName;

    @ColumnInfo()
    public String lastName;

    @ColumnInfo()
    public Date birthDate;

    @ColumnInfo()
    public int height;

    @ColumnInfo()
    public int weight;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String firstName, String lastName, Date birthDate, int height, int weight) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", weight=" + weight +
                '}';
    }
}