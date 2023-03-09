package com.example.locationtest;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    Button prevPageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        prevPageButton = (Button) findViewById(R.id.prev_page_button);

        prevPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Go to activity 1");
                openActivity1();
            }
        });


    }

    public void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}