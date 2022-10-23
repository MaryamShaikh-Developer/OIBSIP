package com.maryam.notebook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.maryam.notebook.model.Note;

@Database(entities = Note.class,version = 1)
public abstract class NotesDataBase extends RoomDatabase {

    private static NotesDataBase mInstance;
    public abstract NoteDao noteDao();
    public static synchronized NotesDataBase getInstance(Context context){
        if (mInstance == null){
           mInstance = Room.databaseBuilder(context.getApplicationContext(),
                   NotesDataBase.class,"my_todos").
                   allowMainThreadQueries().
                   build();
        }
        return mInstance;
    }
}
