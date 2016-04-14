package com.abshake.messcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static float total = 4250;
    static float spent = 0;
    static SharedPreferences sharp;
    final String MyPref = "myfile";
    EditText ed;
    TextView sp,rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = (EditText) findViewById(R.id.adder);
        sp = (TextView) findViewById(R.id.spent_money);
        rem = (TextView) findViewById(R.id.remain_money);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharp = getSharedPreferences(MyPref,Context.MODE_PRIVATE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        spent = sharp.getFloat("Value",0);
        total = sharp.getFloat("total", 4250);

        rem.setText(String.valueOf(total-spent));
        sp.setText(String.valueOf(spent));
        fab.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences.Editor editor = sharp.edit();
            spent = 0;
            sp.setText(String.valueOf(spent));
            rem.setText(String.valueOf(total - spent));
            editor.putFloat("Value", spent);
            editor.commit();
            return true;
        } else if (id == R.id.set_value) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (ed.getText().toString().matches("") || ((total - spent < 0))) {
            Snackbar.make(v, "Enter a Value", Snackbar.LENGTH_LONG).show();
        } else {
            SharedPreferences.Editor editor = sharp.edit();
            float add = Float.valueOf(ed.getText().toString());
            spent += add;
            sp.setText(String.valueOf(spent));
            rem.setText(String.valueOf(total - spent));
            editor.putFloat("Value", spent);
            editor.commit();
            ed.setText("");
        }

    }
}
