package com.ericaskari.healthapplication.services;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * This class is for converting Date to Unix time number format for saving in database
 * And will convert back when fetching it from database
 * @author Mohammad Askari (Eric)
 */
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}