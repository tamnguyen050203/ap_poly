package com.example.myfpl.helpers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myfpl.data.room.TestDAO;
import com.example.myfpl.models.Test;

import kotlin.jvm.Volatile;

@Database(version = 1, entities = {Test.class})
abstract public class AppDatabaseHelper extends RoomDatabase {
    @Volatile
    private AppDatabaseHelper instance;

    public synchronized AppDatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabaseHelper.class, "MyFPLDB").build();
        }
        return instance;
    }

    public abstract TestDAO testDAO();
}
