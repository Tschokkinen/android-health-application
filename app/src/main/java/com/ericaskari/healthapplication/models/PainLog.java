package com.ericaskari.healthapplication.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

/**
 * Database Model for Pain
 */
@Entity
public class PainLog {
    @NonNull
    @PrimaryKey
    public String id;

    @ColumnInfo()
    public String bodyPart;

    @ColumnInfo()
    public String description;

    @ColumnInfo()
    public Date createdAt;

    @ColumnInfo()
    public int painStrength;

    @ColumnInfo()
    public String medicineTaken;

    @ColumnInfo()
    public String tellAboutYourFeelings;

    public PainLog() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
    }

    public PainLog(Date createdAt, String bodyPart, String description, String medicineTaken, int painStrength, String tellAboutYourFeelings) {
        this.id = UUID.randomUUID().toString();
        this.createdAt = createdAt;
        this.bodyPart = bodyPart;
        this.description = description;
        this.medicineTaken = medicineTaken;
        this.painStrength = painStrength;
        this.tellAboutYourFeelings = tellAboutYourFeelings;
    }

    @Override
    public String toString() {
        return "PainLog{" +
                "id='" + id + '\'' +
                ", bodyPart='" + bodyPart + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt + '\'' +
                ", medicineTaken=" + medicineTaken + '\'' +
                ", painStrength=" + painStrength + '\'' +
                ", tellAboutYourFeelings=" + tellAboutYourFeelings +
                '}';
    }
}