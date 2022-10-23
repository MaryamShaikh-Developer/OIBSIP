package com.maryam.notebook.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.maryam.notebook.NoteViewModel;
import com.maryam.notebook.R;
import com.maryam.notebook.model.Note;

import javax.security.auth.callback.Callback;

public class NoteAdapter extends ListAdapter<Note,NoteAdapter.ViewHolder> {

    public NoteAdapter (){
        super(CALLBACK);
    }
    private  static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDesc().equals(newItem.getDesc());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item,parent,false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_desc.setText(note.getDesc());
    }
    public Note getNote(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title =itemView.findViewById(R.id.tv_title);
            tv_desc =itemView.findViewById(R.id.tv_desc);
        }
    }
}
