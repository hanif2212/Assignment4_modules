package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.roomdatabase.Database;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void start(View view){
        database=new Database(this);
        
    }
    public void stop(View view){

    }
}
