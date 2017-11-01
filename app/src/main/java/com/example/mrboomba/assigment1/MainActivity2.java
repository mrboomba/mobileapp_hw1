package com.example.mrboomba.assigment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity2 extends AppCompatActivity {

    private EditText ed1,ed2,ed3,ed4,ed5;
    private ImageView img;
    private String name,lastname,email,phone;
    private int age;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ed1 = (EditText) findViewById(R.id.edit1);
        ed2 = (EditText) findViewById(R.id.edit2);
        ed3 = (EditText) findViewById(R.id.edit3);
        ed4 = (EditText) findViewById(R.id.edit4);
        ed5 = (EditText) findViewById(R.id.edit5);
        img = (ImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        int choice = intent.getIntExtra("choice",0);
        if(choice==0){
            String filename = "cs355.txt";
            String inputString;
            try{
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(filename)));
                StringBuffer stringBuffer = new StringBuffer();
                while ((inputString = inputReader.readLine())!=null){
                    stringBuffer.append(inputString+"#");
                }
                String tmp = stringBuffer.toString();
                String[] output = tmp.split("#");
                name = output[0];
                lastname = output[1];
                age = Integer.valueOf(output[2]);
                email = output[3];
                phone = output[4];
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            SharedPreferences sharedPreferences = getSharedPreferences("boomba",MODE_PRIVATE);
            name = sharedPreferences.getString("name","error");
            lastname = sharedPreferences.getString("lastname","error");
            age = sharedPreferences.getInt("age",0);
            email = sharedPreferences.getString("e-mail","error");
            phone = sharedPreferences.getString("phoneNo.","error");
        }

        ed1.setText(name);
        ed2.setText(lastname);
        ed3.setText(age+"");
        ed4.setText(intent.getStringExtra("e-mail"));
        ed5.setText(intent.getStringExtra("phoneNo."));
        if(age>=0 && age<=15){
            img.setImageDrawable(getDrawable(R.drawable.baby));
        }
        else if(age>=16 && age<=25){
            img.setImageDrawable(getDrawable(R.drawable.teen));
        }
        else if(age>=26 && age<=60){
            img.setImageDrawable(getDrawable(R.drawable.adult));
        }
        else{
            img.setImageDrawable(getDrawable(R.drawable.old));

        }



    }
}
