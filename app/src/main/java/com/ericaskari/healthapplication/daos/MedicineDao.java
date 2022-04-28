package com.ericaskari.healthapplication.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ericaskari.healthapplication.models.Medicine;

import java.util.List;

/**
 * Room Database Medicine Data Access Object
 */
@Dao
public interface MedicineDao {
    @Query("SELECT * FROM Medicine")
    List<Medicine> getAll();

    @Query("SELECT * FROM Medicine WHERE id IN (:userIds)")
    List<Medicine> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Medicine... medicines);

    @Delete
    void delete(Medicine medicine);
}