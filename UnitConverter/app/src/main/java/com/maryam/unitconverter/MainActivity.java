package com.maryam.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ed_cm;
    private TextView tv_result;
    private Button btn_meter;
    float finalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_cm = findViewById(R.id.ed_cm);
        btn_meter = findViewById(R.id.btn_meter);
        tv_result = findViewById(R.id.tv_result);


        String value= ed_cm.getText().toString();
        try{
            finalValue=Integer.parseInt(value);
        } catch(NumberFormatException ex){ // handle your exception
            Log.d("TAG", "onCreate: "+ex);
        }


        btn_meter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= ed_cm.getText().toString();
                try{
                    finalValue=Float.parseFloat(value);
                } catch(NumberFormatException ex){
                    Log.d("TAG", "onCreate: "+ex);
                }
                float result = finalValue /100;
                tv_result.setVisibility(View.VISIBLE);
                tv_result.setText(String.valueOf(result)+"m");

            }
        });







    }
}