package com.ericaskari.healthapplication.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ericaskari.healthapplication.enums.RepeatTypeEnum;

import java.time.DayOfWeek;
import java.util.Date;

/**
 * Database Model for Medicine
 */
@Entity
public class Medicine {
    @NonNull
    @PrimaryKey
    public String id;

    @ColumnInfo()
    public String name;

    @ColumnInfo()
    public String expirationDate;

    @ColumnInfo()
    public RepeatTypeEnum repeatType;

    @ColumnInfo()
    public DayOfWeek weekDay;

    //  between zero and 23
    @ColumnInfo()
    public int timeOfDay;
}