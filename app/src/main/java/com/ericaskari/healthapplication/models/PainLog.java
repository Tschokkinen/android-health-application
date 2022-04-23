package com.ericaskari.healthapplication.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

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

    public PainLog() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
    }

    public PainLog(String bodyPart, String description) {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
        this.bodyPart = bodyPart;
        this.description = description;
    }

    @Override
    public String toString() {
        return "PainLog{" +
                "id='" + id + '\'' +
                ", bodyPart='" + bodyPart + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}