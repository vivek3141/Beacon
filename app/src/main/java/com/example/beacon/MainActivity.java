package com.example.beacon;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;


public class MainActivity extends AppCompatActivity {


    private Button distress;
    private Button contacts;
    private final int REQUEST_CODE = 1;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contacts = findViewById(R.id.contactsButton);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, scenarios.class);
                startActivity(intent);
            }
        });
        distress = findViewById(R.id.distressButton);
        distress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Call_scenarios.class);
                startActivity(intent);
            }
        });

    }

    public void sendMessages(String message, String number) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("smsto:" + number, null, message, null, null);
    }


    public void makeCall(String number) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + number));
        try {
            startActivity(phoneIntent);
        }
        catch(SecurityException s){}
    }

}








