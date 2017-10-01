package com.example.mrboomba.assigment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText ed1,ed2,ed3,ed4,ed5;
    private Button button;

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("name",ed1.getText().toString());
                intent.putExtra("lastname",ed2.getText().toString());
                intent.putExtra("age",Integer.parseInt(ed3.getText().toString()));
                intent.putExtra("e-mail",ed4.getText().toString());
                intent.putExtra("phoneNo.",ed5.getText().toString());
                startActivity(intent);
            }
        });
    }
}
