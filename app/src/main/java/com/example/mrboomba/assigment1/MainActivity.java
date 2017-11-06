package com.example.mrboomba.assigment1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText ed1,ed2,ed3,ed4,ed5;
    private Button button,set;
    private Spinner sp;
    private int age,choice;
    private DateFormat fmtDateAndTime;
    private Calendar myCalendar;
    private RadioButton b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.edit1);
        ed2 = (EditText) findViewById(R.id.edit2);
        ed3 = (EditText) findViewById(R.id.edit3);
        ed4 = (EditText) findViewById(R.id.edit4);
        ed5 = (EditText) findViewById(R.id.edit5);
        button = (Button) findViewById(R.id.submit);
        sp = (Spinner) findViewById(R.id.spin);
        set = (Button) findViewById(R.id.set);
        fmtDateAndTime = DateFormat.getDateTimeInstance();
        myCalendar = Calendar.getInstance();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.postname,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,d,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice == 0){
                    String filename = "cs355.txt";
                    FileOutputStream outputStream;
                    try{
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        outputStream = openFileOutput(filename,MODE_PRIVATE);
                        outputStream.write(ed1.getText().toString().getBytes());
                        outputStream.write("#".getBytes());
                        outputStream.write(ed2.getText().toString().getBytes());
                        outputStream.write("#".getBytes());
                        outputStream.write((""+age).getBytes());
                        outputStream.write("#".getBytes());
                        outputStream.write(ed4.getText().toString().getBytes());
                        outputStream.write("#".getBytes());
                        outputStream.write(ed5.getText().toString().getBytes());
                        outputStream.close();
                        intent.putExtra("choice",0);
                        startActivity(intent);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,"Please Check Input",Toast.LENGTH_SHORT).show();
                        Log.d("mrboomba",e.toString());
                    }
                }
                else if(choice == 1){
                    try {
                        SharedPreferences sharedPreferences = getSharedPreferences("boomba",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        editor.putString("name", ed1.getText().toString());
                        editor.putString("lastname", ed2.getText().toString());
                        editor.putInt("age", age);
                        editor.putString("e-mail", ed4.getText().toString());
                        editor.putString("phoneNo.", ed5.getText().toString());
                        editor.commit();
                        intent.putExtra("choice",1);
                        startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,"Please Check Input",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR,year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLable();
        }
    };

    private void updateLable(){
        ed3.setText(fmtDateAndTime.format(myCalendar.getTime()));
        Calendar tmp = Calendar.getInstance();
        age = tmp.get(Calendar.YEAR) - myCalendar.get(Calendar.YEAR);

        if (tmp.get(Calendar.DAY_OF_YEAR) < myCalendar.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
    }
    public void onRadioButtonClicked(View view){
        switch (view.getId()){
            case R.id.file:
                choice = 0;
                break;
            case R.id.obj:
                choice = 1;
                break;
        }
    }

}
