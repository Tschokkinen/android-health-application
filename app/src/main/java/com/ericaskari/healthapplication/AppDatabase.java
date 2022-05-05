package com.ericaskari.healthapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Room Database Access point
 * @author Mohammad Askari (Eric)
 */
@Database(
        entities = {User.class, Medicine.class, PainLog.class},
        version = 1
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract MedicineDao medicineDao();

    public abstract PainLogDao painLogDao();


    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app-db").allowMainThreadQueries().build();
    }
}