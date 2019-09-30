package com.example.myfirstapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myfirstapplication.dao.UserDao;
import com.example.myfirstapplication.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao UserDao();
}