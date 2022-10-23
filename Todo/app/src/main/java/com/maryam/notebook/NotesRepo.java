package com.maryam.notebook;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.maryam.notebook.database.NoteDao;
import com.maryam.notebook.database.NotesDataBase;
import com.maryam.notebook.model.Note;

import java.util.List;

public class NotesRepo {
    private NoteDao noteDao;
    private LiveData<List<Note>> notelist;

    public NotesRepo(Application application){
        NotesDataBase notesDataBase =NotesDataBase.getInstance(application);
        noteDao = notesDataBase.noteDao();
        notelist = noteDao.getAllNotes();
    }
    public void insertData(Note note){new InsertData(noteDao).execute(note);}
    public void updateData(Note note){new UpdateData(noteDao).execute(note);}
    public void deleteData(Note note){new DeleteData(noteDao).execute(note);}

    LiveData<List<Note>> getAllNotes (){
        return notelist;
    }
    private static class InsertData extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public InsertData(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insertNotes(notes[0]);
            return null;
        }
    }
    private static class DeleteData extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public DeleteData(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteNotes(notes[0]);
            return null;
        }
    }private static class UpdateData extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public UpdateData(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.updateNotes(notes[0]);
            return null;
        }
    }
}
