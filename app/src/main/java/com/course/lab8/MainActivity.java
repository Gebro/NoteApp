package com.course.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.course.lab8.DataBase.AppDataBase;
import com.course.lab8.DataBase.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.RV_notes);
        floatingActionButton =findViewById( R.id.floatingActionButton);

    }
    @Override
    protected void onResume() {
        super.onResume();
        AppDataBase mBase=AppDataBase.getInstance(this);
        Note[]arr =mBase.getNoteDao().selectAll();
        NotesAdapter notesAdapter=new NotesAdapter(arr);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(notesAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddNoteActivity.class));
            }
        });
    }

}
