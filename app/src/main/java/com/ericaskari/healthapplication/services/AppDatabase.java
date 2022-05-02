package com.ericaskari.healthapplication.services;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ericaskari.healthapplication.daos.MedicineDao;
import com.ericaskari.healthapplication.daos.PainLogDao;
import com.ericaskari.healthapplication.daos.UserDao;
import com.ericaskari.healthapplication.models.Medicine;
import com.ericaskari.healthapplication.models.PainLog;
import com.ericaskari.healthapplication.models.User;

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