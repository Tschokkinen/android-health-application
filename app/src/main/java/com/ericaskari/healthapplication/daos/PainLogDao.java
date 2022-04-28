package com.ericaskari.healthapplication.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ericaskari.healthapplication.models.PainLog;

import java.util.List;

/**
 * Room Database PainLog Data Access Object
 */
@Dao
public interface PainLogDao {
    @Query("SELECT * FROM PainLog")
    List<PainLog> getAll();

    @Query("SELECT * FROM PainLog WHERE id IN (:ids)")
    List<PainLog> loadAllByIds(int[] ids);

    @Insert
    void insertAll(PainLog... items);

    @Delete
    void delete(PainLog item);
}