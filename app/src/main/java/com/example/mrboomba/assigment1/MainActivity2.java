package com.example.mrboomba.assigment1;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    private EditText ed1,ed2,ed3,ed4,ed5;
    private ImageView img;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        int age = intent.getIntExtra("age",21);
        ed1 = (EditText) findViewById(R.id.edit1);
        ed2 = (EditText) findViewById(R.id.edit2);
        ed3 = (EditText) findViewById(R.id.edit3);
        ed4 = (EditText) findViewById(R.id.edit4);
        ed5 = (EditText) findViewById(R.id.edit5);
        img = (ImageView) findViewById(R.id.image);
        ed1.setText(intent.getStringExtra("name"));
        ed2.setText(intent.getStringExtra("lastname"));
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
