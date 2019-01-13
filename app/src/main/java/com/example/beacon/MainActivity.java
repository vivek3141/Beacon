package com.example.beacon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button distress;
    private Button contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        distress = findViewById(R.id.distressButton);
//        distress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, scenarios.class);
//                startActivity(i);
//            }
//        });


        contacts = findViewById(R.id.contactsButton);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, scenarios.class);
                startActivity(intent);
            }
        });
    }
}
