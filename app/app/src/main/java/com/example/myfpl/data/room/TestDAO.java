package com.example.myfpl.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myfpl.models.Test;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface TestDAO {
    @Query("SELECT * FROM test")
    public Flowable<List<Test>> getListTest();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertTestModel(Test test);

    @Delete
    public Completable deleteTestModel(Test test);
}
