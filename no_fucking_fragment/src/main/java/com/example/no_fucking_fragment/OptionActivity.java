package com.example.no_fucking_fragment;

import android.content.Intent;
import android.content.PeriodicSync;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.sdsmdg.harjot.crollerTest.Croller;

public class OptionActivity extends AppCompatActivity {
    CircleProgressBar circleProgressBar;
    Button btnPlus, btnMinus;
    public static int per = 50;
    Croller croller;
    TextView selectTemper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        croller = findViewById(R.id.croller);
        selectTemper = findViewById(R.id.select_Temper);
        navigation_addListener("Option");
        //seekBar.getResources().

        Drawable d = getResources().getDrawable(R.drawable.point);

        croller.setOnProgressChangedListener(new Croller.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress) {
                int Temp = progress +13;

                if(Temp == 14) {
                    selectTemper.setText("가장 낮은 온도");
                    croller.setBackCircleColor(Color.parseColor("#FF3F51B5"));
                }
                else if (Temp == 45) {
                    selectTemper.setText("가장 높은 온도");
                    croller.setBackCircleColor(Color.parseColor("#FFFF0000"));
                }
                else if (Temp == 44) {
                    selectTemper.setText("운동 후 근육풀기 \n44ºC");
                    croller.setBackCircleColor(Color.parseColor("#FFF44336"));
                }
                else if (Temp == 41) {
                    selectTemper.setText("저혈압 / 다이어트 \n41ºC");
                    croller.setBackCircleColor(Color.parseColor("#FFF15321"));
                }

                else if (Temp == 38) {
                    selectTemper.setText("따뜻한 온도\n38ºC");
                    croller.setBackCircleColor(Color.parseColor("#FFFF9800"));
                }
                else if (Temp == 25) {
                    selectTemper.setText("시원한 온도\n25ºC");
                    croller.setBackCircleColor(Color.parseColor("#FFA2CBEC"));
                }
                else if (Temp == 15) {
                    selectTemper.setText("차가운 온도 \n15ºC");
                    croller.setBackCircleColor(Color.parseColor("#FF2196F3"));
                }
                else
                    selectTemper.setText(Integer.toString(Temp) + "ºC");
                //44도(운동 후 피로할때)", "41도(저혈압 / 다이어트)", "38도(평범함)", "25도(시원함)", "15도(차가움)"
            }
        });




        //region 1

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (croller.getProgress() != 32)
                    croller.setProgress(croller.getProgress() + 1);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (croller.getProgress() != 1)
                croller.setProgress(croller.getProgress() - 1);
            }
        });
        //endregion


    }


    void navigation_addListener(String Where)
    {
        if(Where != "Account") {
            findViewById(R.id.imageView1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(Where != "Main") {
            findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if(Where != "Option") {
            findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), OptionActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

}
