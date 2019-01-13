package com.example.beacon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Call_scenarios extends AppCompatActivity {

    private Button bullyButton;
    private Button abuseButton;
    private Button kidnapButton;
    private Button shootingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenarios);

        bullyButton = findViewById(R.id.optionBully);
        abuseButton = findViewById(R.id.optionAbuse);
        kidnapButton = findViewById(R.id.optionKidnap);
        shootingButton = findViewById(R.id.optionShooting);

        bullyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(Call_scenarios.this, Call.class);
                int1.putExtra("type", "bully");
                startActivity(int1);

            }
        });
        kidnapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(Call_scenarios.this, Call.class);
                int2.putExtra("type", "kidnap");
                startActivity(int2);

            }
        });
        abuseButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(Call_scenarios.this, Call.class);
                int2.putExtra("type", "abuse");
                startActivity(int2);
            }
        }));
        shootingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3 = new Intent(Call_scenarios.this, Call.class);
                int3.putExtra("type", "shooting");
                startActivity(int3);
            }
        });
    }
}
