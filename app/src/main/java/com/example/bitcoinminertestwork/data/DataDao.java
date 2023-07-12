package com.example.bitcoinminertestwork.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM bitcoindata")
    LiveData<List<DataModel>> getAll();
    @Insert
    void insert(DataModel dataModels);

    @Update
    void update(DataModel dataModel);
}
