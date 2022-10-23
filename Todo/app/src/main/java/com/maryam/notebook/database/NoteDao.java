package com.maryam.notebook.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.maryam.notebook.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    final static String TABLE_NAME = "note";

    @Query("SELECT * FROM "+TABLE_NAME)
    LiveData<List<Note>> getAllNotes ();

    @Insert
    void insertNotes(Note note);

    @Update
    void updateNotes(Note note);

    @Delete
    void deleteNotes(Note note);


}
