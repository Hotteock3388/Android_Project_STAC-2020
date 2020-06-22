package com.example.newtry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

//import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.github.clans.fab.FloatingActionButton;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;

//    private Animation fab_open, fab_close;
//    private Boolean isFabOpen = false;
//    private FloatingActionButton fab, fab1, fab2;

    Button btn_Reserve;
    Button btn_ShowStatus;
    FloatingActionButton fab_newReserve, fab_showPreviousSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Reserve = findViewById(R.id.btn_Reserve);
        btn_ShowStatus = findViewById(R.id.btn_showReserveStatus);

        fab_newReserve = findViewById(R.id.fab_newReserve);
        fab_showPreviousSettings = findViewById(R.id.fab_showPreviousSettings);

        fab_newReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewReserveActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity);
            }
        });

        fab_showPreviousSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PreviousSettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity);
            }
        });

        //fabSetting();




        btn_Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Reserve_NeworPre.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity);
            }
        });

        btn_ShowStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReserveStatus.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity);
            }
        });

    }

//    public void anim(){
//
//        if (isFabOpen) {
//            fab1.startAnimation(fab_close);
//            fab2.startAnimation(fab_close);
//            fab1.setClickable(false);
//            fab2.setClickable(false);
//            isFabOpen = false;
//        } else {
//            fab1.startAnimation(fab_open);
//            fab2.startAnimation(fab_open);
//            fab1.setClickable(true);
//            fab2.setClickable(true);
//            isFabOpen = true;
//        }
//    }
//
//    public void fabSetting(){
//        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
//        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
//
//        fab = findViewById(R.id.fab);
//        fab1 = findViewById(R.id.fab1);
//        fab2 = findViewById(R.id.fab2);
//
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = v.getId();
//                switch (id) {
//                    case R.id.fab:
//                        anim();
//                        Toast.makeText(MainActivity.this, "Floating Action Button", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.fab1:
//                        anim();
//                        Toast.makeText(MainActivity.this, "Button1", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.fab2:
//                        anim();
//                        Toast.makeText(MainActivity.this, "Button2", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        };
//
//        fab.setOnClickListener(listener);
//        fab1.setOnClickListener(listener);
//        fab2.setOnClickListener(listener);
//    }

    public void onBackPressed()
    {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누르면 꺼버린다.", Toast.LENGTH_SHORT).show();
        }
    }

}