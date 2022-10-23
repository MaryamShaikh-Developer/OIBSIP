package com.maryam.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DataInsert extends AppCompatActivity {
    private EditText ed_title,ed_desc;
    Button submit_btn;
    private String title,desc;
    private TextView title_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_insert);

        ed_title = findViewById(R.id.ed_title);
        ed_desc = findViewById(R.id.ed_desc);
        submit_btn = findViewById(R.id.submit_btn);
        title_name = findViewById(R.id.type_title);

        String type = getIntent().getStringExtra("type");
        if(type.equals("update")){
            title_name.setText("Update");
            ed_title.setText(getIntent().getStringExtra("title"));
            ed_desc.setText(getIntent().getStringExtra("desc"));
            int id = getIntent().getIntExtra("id",0);

            submit_btn.setText("Update");

            ed_title.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    title = ed_title.getText().toString();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            ed_desc.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    desc = ed_desc.getText().toString();

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            submit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent();
                    i.putExtra("title",title);
                    i.putExtra("desc",desc);
                    i.putExtra("id",id);
                    setResult(2,i);
                    finish();
                }
            });
        }
        else{
            title_name.setText("Add Item");

            ed_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                title = ed_title.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                desc = ed_desc.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("title",title);
                i.putExtra("desc",desc);
                setResult(1,i);
                Log.d("TAG", "ccc onClick: "+title);
                Log.d("TAG", "ccc onClick: "+desc);
                finish();

            }
        });
    }}

}