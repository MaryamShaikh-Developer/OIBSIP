package com.maryam.notebook;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.maryam.notebook.model.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NotesRepo notesRepo;
    private LiveData<List<Note>> noteList;
    public NoteViewModel(@NonNull Application application) {
        super(application);

        notesRepo = new NotesRepo(application);
        noteList = notesRepo.getAllNotes();
    }
    public void insertData(Note note){
        notesRepo.insertData(note);
    }
    public void deleteData(Note note){
        notesRepo.deleteData(note);
    }
    public void updateData(Note note){
        notesRepo.updateData(note);
    }
    public LiveData<List<Note>> getAllData(){
        return noteList;
    }
}
