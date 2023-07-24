package com.example.demo_1.persistance.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.demo_1.data.model.TestApiResponseModel;

// persistence/dao/ResponseDao.java
@Dao
public interface ResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResponse(TestApiResponseModel response);

    @Query("SELECT * FROM ResponseModel")
    LiveData<TestApiResponseModel> getResponse();
}
