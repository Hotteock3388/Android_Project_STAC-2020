package com.example.newtry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Reserve_NeworPre extends AppCompatActivity {

    Button btn_Pre, btn_New;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve__new_or_pre);


        btn_Pre = findViewById(R.id.btn_PreSettings);
        btn_New = findViewById(R.id.btn_NewReserrve);
        btn_Pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PreviousSettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity);
            }
        });

        btn_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewReserveActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity);

    }
}
