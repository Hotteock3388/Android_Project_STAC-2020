package com.example.newtry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sdsmdg.harjot.crollerTest.Croller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class NewReserveActivity extends AppCompatActivity {

    DatePicker datePicker;
    String reserveDay = "";
    Croller croller;
    TimePicker timePicker;
    Button btn_Repeat, btn_Reserve;
    Boolean repeat = true;
    Boolean[] isDaySelected = {true, true, true, true, true, false, false};
    Button[] btnDays = new Button[7];
    int[] btnIds = {R.id.btn_Day1, R.id.btn_Day2, R.id.btn_Day3
            , R.id.btn_Day4, R.id.btn_Day5, R.id.btn_Day6, R.id.btn_Day7};
    TextView text_ReservingDay, text_leftTime, selectTemper;
    Calendar cal = Calendar.getInstance();
    SwitchCompat sw_Prepar;
    public static int Count = 0;
    int btn_Count = 0;
    //String[] DataSaveInput = new String[4];
        //Boolean 요일 / 날짜 , String 날짜 / (Boolean[7] 요일) , String 시간 , String 온도 , Boolean 입욕제
        //        True/False
    //Object[] DatasSave = new Object[5];

    TextView textView_TimeCheck;
    Button btnCheck;


    String tempYear;
    String tempMonth= "";
    String tempDay = "";

    String saveDataString = "";
    Boolean save_IsDayOfWeek = true;
    String save_Day = "";
    String save_Date = "";
    String save_Time = "";
    String save_Temperature = "";
    String save_Prepar = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reserve);

        timePicker = findViewById(R.id.timePicker);
        croller = findViewById(R.id.croller);
        text_ReservingDay = findViewById(R.id.text_ReservingDay);
        text_leftTime = findViewById(R.id.text_LeftTime);
        selectTemper = findViewById(R.id.select_Temper);
        btn_Repeat = findViewById(R.id.btn_Repeat);
        sw_Prepar = findViewById(R.id.switch_Prepar);
        textView_TimeCheck = findViewById(R.id.textView_TimeCheck);
        //btnCheck = findViewById(R.id.btn_Check);
        btn_Reserve = findViewById(R.id.btn_Reserve);


        setText_leftTime();

        //resetCount();
        //updateCountData();

        setBtnDays();

        setCroller();

        setTimePicker();

        //checkPrepar();





//        btnCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveReserveDatas();
//            }
//        });

        btn_Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReserveDatas();
                finish();
                Toast.makeText(getApplicationContext(), text_leftTime.getText(), Toast.LENGTH_SHORT).show();
            }
        });



        //region 반복 예약 버튼 리스너 설정
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
        //endregion

    }

    private void saveReserveDatas() {
        saveDataString = "";
        save_Day = "";
        save_Time = "";
        save_Temperature = "";
        save_Prepar = "";

        //Boolean 요일 / 날짜 , String 날짜 / (Boolean[7] 요일) , String 시간 , String 온도 , Boolean 입욕제


        //Boolean save_IsDayOfWeek
        save_IsDayOfWeek = repeat;


        //String save_Day
        if(save_IsDayOfWeek == true)
        {
            for(int i = 0; i < 7; i++){
                if(isDaySelected[i] == true)
                    save_Day += "O";
                else
                    save_Day += "X";
            }
        }

            //String save_Date
//        else if (save_IsDayOfWeek == false){
//            save_Date = text_ReservingDay.getText().toString();
//        }

            //String save_time
        //save_Time = timePicker.getHour() + ":" + timePicker.getMinute();
        save_Time = getReserveTime_TimePicker();

            //String save_Temperature
        save_Temperature = Integer.toString(croller.getProgress() + 13);

            //String save_Preapr
        if (sw_Prepar.isChecked() == false) {
            save_Prepar = "X";
        }
        else{
            save_Prepar = "O";
        }




        //Boolean 요일 / 날짜 , String 날짜 / (Boolean[7] 요일) , String 시간 , String 온도 , Boolean 입욕제

        Count = updateCountData();
        saveDataString += Count + ",";

        if(save_IsDayOfWeek == true)
        {
            saveDataString += "O,";   //Boolean 요일
            saveDataString += save_Day + ",";           //String 요일
        }
        else{
            saveDataString += "X,";   //Boolean 날짜
            saveDataString += save_Date + ",";           //String 날짜
        }

        saveDataString += save_Time +",";           //String 시간
        saveDataString += save_Temperature + ",";   //String 온도
        saveDataString += save_Prepar;              //String 입욕제 OX


        setDatasStr("ReserveDataList", saveDataString, true);
        textView_TimeCheck.setText(saveDataString);


        Count++;
        setDatasStr("Count", Integer.toString(Count), false);
        updateCountData();
        
        //saveDataString = "";

    }

    private String getReserveTime_TimePicker() {
        String tempHour = "";
        String tempMinute = "";

        if(timePicker.getHour() < 10){
            tempHour = "0" + timePicker.getHour();
        }
        else
            tempHour = Integer.toString(timePicker.getHour());

        if(timePicker.getMinute() < 10){
            tempMinute = "0" + timePicker.getMinute();
        }
        else
            tempMinute = Integer.toString(timePicker.getMinute());

        return tempHour + ":" + tempMinute;
    }

    public int updateCountData(){
        //    Count = Integer.parseInt(getDatasStr("Count"));
        return Integer.parseInt(getDatasStr("Count"));
    }

    public void resetCount(){
        Count = 0;
        setDatasStr("Count", Integer.toString(Count), false);
    }


    private void checkPrepar() {
         if(sw_Prepar.isChecked())
         {
             save_Prepar = "O";
         }else{
             save_Prepar = "X";
         }

    }

    private void setTimePicker() {
        timePicker.setMinute(cal.get(Calendar.MINUTE) + 1);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setText_leftTime();
            }
        });

    }

    private void setCroller() {

        croller.setOnProgressChangedListener(new Croller.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress) {
                int Temp = progress +13;
                save_Temperature = Integer.toString(Temp);
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

    }

    private void createDatePicker() {
        LayoutInflater inflater = getLayoutInflater();
        final View myView = inflater.inflate(R.layout.alertdatepickerform, null);
        datePicker = myView.findViewById(R.id.formDatePicker);
        reserveDay = "";

        //myView.
        //datePicker.get
        AlertDialog.Builder dlg = new AlertDialog.Builder(NewReserveActivity.this);
        dlg.setCancelable(false)
                .setView(myView)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn_Repeat.setText("반복 사용안함");
                        btn_Repeat.setBackgroundResource(R.drawable.btn_corner_gray);
                        for(int i = 0; i < 7; i++){ btnDays[i].setVisibility(View.GONE); }
                        text_ReservingDay.setVisibility(View.VISIBLE);
                        repeat = false;
                        reserveDay += datePicker.getYear() + "년 ";
                        reserveDay += (datePicker.getMonth() +1)+ "월 ";
                        reserveDay += datePicker.getDayOfMonth() + "일 목욕예약";
                        text_ReservingDay.setText(reserveDay);

                        if(datePicker.getMonth()+1 < 10) {
                            tempMonth += "0" + (datePicker.getMonth()+1);
                        }
                        else {
                            int Temp;
                            Temp = (datePicker.getMonth());
                            tempMonth = Integer.toString(++Temp);
                        }
                        if(datePicker.getDayOfMonth() < 10){
                            tempDay += "0" + datePicker.getDayOfMonth();
                        }
                        else
                            tempDay = Integer.toString(datePicker.getDayOfMonth());


                        save_Date = datePicker.getYear() + "." + tempMonth + "." + tempDay;
                        tempMonth = "";
                        tempDay = "";
                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }

    public void setText_leftTime(){
        long leftTimeMinutes = 0;
        String leftTime = "";

        Calendar cal = Calendar.getInstance();

        Boolean selectPast = false;

        int nowHour = cal.get(Calendar.HOUR_OF_DAY);
        int nowMinute = cal.get(Calendar.MINUTE);



        int DOW = 0;
        long leftDay = 0;
        long leftHour;
        long leftMinute;

        String text_LeftHour = "";
        String text_leftMinute = "";

        if (repeat) {
            switch (cal.get(Calendar.DAY_OF_WEEK))
            {
                case 1: DOW = 6;break;
                case 2: DOW = 0;break;
                case 3: DOW = 1;break;
                case 4: DOW = 2;break;
                case 5: DOW = 3;break;
                case 6: DOW = 4;break;
                case 7: DOW = 5;break;
            }
            leftDay = getLeftDay(DOW);

        } else {
            text_ReservingDay.setText(reserveDay);
        }



        nowHour = cal.get(Calendar.HOUR_OF_DAY);
        nowMinute = cal.get(Calendar.MINUTE);

        leftTimeMinutes += leftDay * 60 * 24;
        leftTimeMinutes +=( timePicker.getHour() - nowHour) * 60;
        Log.d("getHour", Integer.toString(timePicker.getHour()));
        Log.d("nowHour", Integer.toString(nowHour));
        leftTimeMinutes += timePicker.getMinute() - nowMinute;



        if(leftTimeMinutes <= 0) {
            btn_Count = 0;
            for(int i = 0; i < 7; i++) if(isDaySelected[i] == true) btn_Count++;


            // 반복 버튼이 하나도 안켜져있을때
            // **** 추후 내용 추가바람 ****
            if(btn_Count == 0){
                leftDay += 1;
            }
            // **** 추후 내용 추가바람 ****
            if(btn_Count == 1)
                leftDay = 7;

            else if(btn_Count > 1)
                leftDay += (getLeftDay(DOW + 1) + 1);

            leftTimeMinutes += leftDay * 60 * 24;
        }


        leftDay = leftTimeMinutes / (60*24);
        leftTimeMinutes %= 60*24;

        leftHour = leftTimeMinutes / 60;
        leftTimeMinutes %= 60;
        text_LeftHour = Long.toString(leftHour);

        leftMinute = leftTimeMinutes;
        text_leftMinute = Long.toString(leftMinute);


        if(leftHour < 10)
            text_LeftHour = "0" + leftHour;
        if(leftMinute < 10)
            text_leftMinute = "0" + leftMinute;


        leftTime += leftDay + "일" + text_LeftHour + "시간" + text_leftMinute + "분";


        leftTime += "후에 목욕을 시작합니다.";


        text_leftTime.setText(leftTime);
    }

    private int getLeftDay(int DOW) {
        int leftDay = 0;
        int i = DOW;
        btn_Count = 0;
        for (int j = 0; j < 7; j++) {
            if (isDaySelected[i] == true) {
                leftDay = j;
                break;
            }
            else {
                if (i == 6) i = -1;
                i++;
            }
        }
        return leftDay;
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

    public String getDatasStr(String FileName) {
        String str = "";
        try (InputStreamReader inputStreamReader = new InputStreamReader(openFileInput(FileName + ".txt"))) {
            int temp;
            while ((temp = inputStreamReader.read()) != -1) {
                str += (char) temp;
            }
            //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    //endregion

    //region String형으로 설정들 저장하기
    public void setDatasStr(String FileName, String str, boolean append){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileStreamPath(FileName + ".txt"), append)))
        {
            bufferedWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region String배열로 데이터 받기
    public char[] getDatasArr(String FileName){
        char[] chars = new char[getDatasStr(FileName).length()];
        try(InputStreamReader inputStreamReader = new InputStreamReader(openFileInput(FileName + ".txt"))) {
            int temp;
            int i = 0;
            while((temp = inputStreamReader.read()) != -1){
                chars[i] = (char) temp;
                i++;
            }
            //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }
        return chars;
    }
    //endregion

    //region String배열로 설정들 저장하기
    public void setDatasArr(String[] Arr){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileStreamPath("Data.txt"), true)))
        {
            String str = "";
            for(int i = 0; i < Arr.length; i++)
                str += Arr[i];

            bufferedWriter.write(str);
            //bufferedWriter.wr
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

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
