package com.maryam.notebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maryam.notebook.adapter.NoteAdapter;
import com.maryam.notebook.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton add;
    private NoteViewModel noteViewModel;
    private RecyclerView rv;

    private NoteAdapter noteAdapter;
    ArrayList<Note> arrayList;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add_note);
        rv = findViewById(R.id.rv_notes);
        noteAdapter = new NoteAdapter();
        arrayList = new ArrayList<>();

        noteViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DataInsert.class);
                i.putExtra("type","addMode");
                startActivityForResult(i,1);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(noteAdapter);

        noteViewModel.getAllData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction==ItemTouchHelper.RIGHT){
                noteViewModel.deleteData(noteAdapter.getNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();}
                else{
                    Intent i = new Intent(MainActivity.this,DataInsert.class);
                    i.putExtra("type","update");
                    i.putExtra("title",noteAdapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    i.putExtra("desc",noteAdapter.getNote(viewHolder.getAdapterPosition()).getDesc());
                    i.putExtra("id",noteAdapter.getNote(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(i,2);
                }

            }
        }).attachToRecyclerView(rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1)
        {
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("desc");
            Note note = new Note(title,desc);

            try {
                noteViewModel.insertData(note);
                Log.d("TAG", "ccc onActivityResult: success"+title);

            }catch (Exception e){
                Log.d("TAG", "ccc onActivityResult: failed");
            }

        }
        else if( resultCode==2){
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("desc");
            Note note = new Note(title,desc);
            note.setId(data.getIntExtra("id",0));
            noteViewModel.updateData(note);
        }
    }
}