package com.course.lab8.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {
    @Insert
    void insertNote(Note ob);

    @Insert
    void addAll(Note...notes);
	
    @Delete
    void removeNote(Note note);
	
    @Update
    void updateNote(Note note);

    @Query("Select * from note ")
    Note[] selectAll();

}
