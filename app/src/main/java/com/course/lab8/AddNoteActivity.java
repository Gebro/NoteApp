package com.course.lab8;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.course.lab8.DataBase.AppDataBase;
import com.course.lab8.DataBase.Note;

import java.time.Year;
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    EditText title;
    EditText desc;
    TextView time;
    TextView date;
    Button save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        getSupportActionBar().hide();
        title=findViewById(R.id.ET_title);
        desc=findViewById(R.id.ET_desc);
        date=findViewById(R.id.TV_date_value);
        time=findViewById(R.id.TV_time_value);
        save =findViewById(R.id.btn_save);
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(AddNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        c.set(Calendar.YEAR,i);
                        c.set(Calendar.MONTH,i1);
                        c.set(Calendar.DAY_OF_MONTH,i2);
                        date.setText(i+"-"+ (i1+1)+"-"+i2);                   }
                }, year, month, day).show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(AddNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        c.set(Calendar.HOUR,i);
                        c.set(Calendar.MINUTE,i1);
                       time.setText(i+":"+ i1);
                    }
                }, c.get(Calendar.HOUR), c.get(Calendar.MINUTE),true).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    AppDataBase appDataBase= AppDataBase.getInstance(AddNoteActivity.this);
                    Note note=new Note();
                    note.title=title.getText().toString();
                    note.description=desc.getText().toString();
                    note.date=c.getTime();
                    appDataBase.getNoteDao().insertNote(note);
                    finish();
                }
            }
        });


    }
    boolean validate(){
        if(title.getText().toString().trim().length()==0){
            title.setError("please add Title");
            return false;

        }
        if(desc.getText().toString().trim().length()==0){
            title.setError("please add description");
            return false;

        }
           return true;
    }
}
