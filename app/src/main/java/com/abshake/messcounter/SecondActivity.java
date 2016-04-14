package com.abshake.messcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText ed;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.limit);
        ed = (EditText) findViewById(R.id.eder);
        sh = getSharedPreferences("myfile", Context.MODE_PRIVATE);

    }

    public void runner(View v) {
        MainActivity.total = Float.valueOf(ed.getText().toString());
        SharedPreferences.Editor edits = sh.edit();
        edits.putFloat("Value", 0);
        edits.putFloat("total", MainActivity.total);
        edits.apply();
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivityForResult(intent, 0);

    }

}
