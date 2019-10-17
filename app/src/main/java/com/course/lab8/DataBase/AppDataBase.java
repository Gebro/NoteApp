package com.course.lab8.DataBase;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract NoteDao getNoteDao();
	
	
    private static AppDataBase  appDataBase = null;

    public static AppDataBase getInstance(Context context){
        if (appDataBase==null){
			appDataBase =
                    Room.databaseBuilder(context, AppDataBase.class,"my_data_base")
                            .allowMainThreadQueries()
                            .build();
		}
        return appDataBase;
    }

}
