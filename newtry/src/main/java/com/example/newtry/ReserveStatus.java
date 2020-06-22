package com.example.newtry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ReserveStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_status);
    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity);

    }
}
