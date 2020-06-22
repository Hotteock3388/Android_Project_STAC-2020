package com.example.newtry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewReserveActivity extends AppCompatActivity {

    TimePicker timePicker;
    Button btn_Repeat;
    Boolean repeat = true;
    Boolean[] isDaySelected = {true, true, true, true, true, false, false};
    Button[] btnDays = new Button[7];
    int[] btnIds = {R.id.btn_Day1, R.id.btn_Day2, R.id.btn_Day3
            , R.id.btn_Day4, R.id.btn_Day5, R.id.btn_Day6, R.id.btn_Day7};
    TextView text_ReservingDay, text_leftTime;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reserve);


        timePicker = findViewById(R.id.timePicker);
        btn_Repeat = findViewById(R.id.btn_Repeat);
        text_ReservingDay = findViewById(R.id.text_ReservingDay);
        text_leftTime = findViewById(R.id.text_LeftTime);

        setBtnDays();

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setText_leftTime();
            }
        });

        btn_Repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat) {
                    createDatePicker();
                } else {
                    btn_Repeat.setText("반복 사용");
                    btn_Repeat.setBackgroundResource(R.drawable.btn_corner_blue);
                    text_ReservingDay.setVisibility(View.GONE);
                    for (int i = 0; i < 7; i++) {
                        btnDays[i].setVisibility(View.VISIBLE);
                    }
                    repeat = true;
                }
            }


        });
    }

    private void createDatePicker() {
        LayoutInflater inflater = getLayoutInflater();
        View myView = inflater.inflate(R.layout.alertdatepickerform, null);
        DatePicker datePicker = findViewById(R.id.datePicker);
        //datePicker.get
        AlertDialog.Builder dlg = new AlertDialog.Builder(NewReserveActivity.this);

        dlg.setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn_Repeat.setText("반복 사용안함");
                        btn_Repeat.setBackgroundResource(R.drawable.btn_corner_gray);
                        for(int i = 0; i < 7; i++){ btnDays[i].setVisibility(View.GONE); }
                        text_ReservingDay.setVisibility(View.VISIBLE);
                        repeat = false;
                    }
                })
                .setNegativeButton("취소", null)
                .setView(myView)
                .show();


    }

    public void setText_leftTime(){
        long leftTimeMinutes = 0;
        String leftTime = "";

        int nowHour = cal.get(Calendar.HOUR);
        int nowMinute = cal.get(Calendar.MINUTE);

        long leftDay = 0;
        long leftHour;
        long leftMinute;

        if (repeat) {
            int dow = cal.get(Calendar.DAY_OF_WEEK);
            int i = --dow;
            for (int j = 0; j < 7; j++) {
                if (isDaySelected[i] == true) {
                    leftDay = j;
                    //reserveMilliSecond += (leftDay * 86400);
                    leftTimeMinutes += leftDay * 60 * 24;
                    break;
                }
                else {
                    if (i == 6) i = -1;
                    i++;
                }

            }
        } else {

        }

        leftTimeMinutes +=( timePicker.getHour() - nowHour) * 60;
        leftTimeMinutes += timePicker.getMinute() - nowMinute;

        leftDay = leftTimeMinutes / (60*24);
        leftTimeMinutes %= 60*24;

        leftHour = leftTimeMinutes / 60;
        leftTimeMinutes %= 60;

        leftMinute = leftTimeMinutes;
        leftTime += leftDay + "일" + leftHour + "시간" + leftMinute + "분";


        leftTime += "후에 목욕을 시작합니다.";


        text_leftTime.setText(leftTime);
    }

    public void setBtnDays(){

        View.OnClickListener btnDaysSelectListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedDay = 0;
                for(int i = 0; i < 7; i++){
                    if(v.getId() == btnIds[i])
                        clickedDay = i;
                }

                btnDaysChange(clickedDay);
            }
        };

        for(int i = 0; i < 7; i++){
            btnDays[i] = findViewById(btnIds[i]);
            btnDays[i].setOnClickListener(btnDaysSelectListener);
        }
    }



public void btnDaysChange(int clickedDay){
    if(isDaySelected[clickedDay] == true)
    {
        btnDays[clickedDay].setBackgroundResource(R.drawable.btn_unselected_day);
        btnDays[clickedDay].setTextColor(getResources().getColor(R.color.colorButtonGray));
        isDaySelected[clickedDay] = false;
    }
    else{
        btnDays[clickedDay].setBackgroundResource(R.drawable.btn_selected_day);
        btnDays[clickedDay].setTextColor(getResources().getColor(R.color.colorBlack));
        isDaySelected[clickedDay] = true;
    }
    setText_leftTime();
}

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity);
    }
}
