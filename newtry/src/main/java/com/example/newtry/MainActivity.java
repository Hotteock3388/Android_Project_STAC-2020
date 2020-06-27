package com.example.newtry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.github.clans.fab.FloatingActionButton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;
    private final int MAX_SIZE = 20;

    Button btn_Reserve;
    Button btn_ShowStatus;
    Button btn_Main;
    TextView text_Main;
    FloatingActionButton fab_newReserve, fab_showPreviousSettings;

    ListView listView;

    ArrayList<String> monthList = getPreviousSettingDatasArrayList("month");
    ArrayList<String> dayList = getPreviousSettingDatasArrayList("day");
    ArrayList<String> temperList = getPreviousSettingDatasArrayList("temper");
    ArrayList<String> preparList = getPreviousSettingDatasArrayList("prepar");


    Boolean[] isDayOfWeek = new Boolean[MAX_SIZE];
    Boolean[][] day = new Boolean[MAX_SIZE][7];
    String[] date = new String[MAX_SIZE];
    String[] time = new String[MAX_SIZE];
    String[] temperature = new String[MAX_SIZE];
    Boolean[] prepar = new Boolean[MAX_SIZE];

    int Count_Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setDatasStr("Count", "", true);
        if(getDatasStr("Count") == ""){
            setDatasStr("Count", "0", true);
        }
        setDatasStr("ReserveDataList", "", true);

        getReserveDataList();

        listViewSetting();

        fabSetting();

    }

    @Override
    protected void onResume() {
        super.onResume();

        getReserveDataList();
        listViewSetting();

    }

    public void initReserveDataList(){

    }

    private void listViewSetting() {
        ArrayList<HashMap<String, String>> preSettingDatas = new ArrayList<>();
        listView = findViewById(R.id.listView_PreviousSettings);

        //setDatasStr("Count", "0", false);

        // ListView에 표시할 데이터들을 합쳐 하나의 ArrayList 만들기
        for(int i = 0; i < Count_Save; i++){
            HashMap<String, String> hashMap = new HashMap<String, String>();
            if(isDayOfWeek[i]){
                hashMap.put("IsDayOfWeek","O");
                for(int j = 0; j < 7; j++) {
                    if(day[i][j])
                        hashMap.put("Day"+(j+1), "O");
                    else
                        hashMap.put("Day"+(j+1), "X"); }
                hashMap.put("Time",time[i]);
                hashMap.put("Temperature", temperature[i]);
                if(prepar[i])
                    hashMap.put("Prepar", "O"); else hashMap.put("Prepar", "X");
            }
            else {
                hashMap.put("IsDayOfWeek", "X");
                hashMap.put("Date", date[i]);
                hashMap.put("Time",time[i]);
                hashMap.put("Temperature", temperature[i]);
                if(prepar[i])
                    hashMap.put("Prepar", "O"); else hashMap.put("Prepar", "X");
            }

//            hashMap.put("Day", dayList.get(i));
//            hashMap.put("Temperature", temperList.get(i));
//            hashMap.put("Prepar", preparList.get(i));

            preSettingDatas.add(hashMap);
        }

        CustomAdapter adapter = new CustomAdapter(this,R.layout.listitem_show_reservestatus, preSettingDatas);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(this, ReserveActivity.class);
                //intent.put() <- HashMap 추가

                //startActivity(intent);
            }
        });
    }

    private ArrayList<String> getPreviousSettingDatasArrayList(String DataKey) {
        ArrayList<String> tempList = new ArrayList<String>();
        switch (DataKey){
            case "month":

                break;
            case "day":

                break;
            case "temper":

                break;
            case "preapr":

                break;

        }

        return tempList;
    }

    public void getReserveDataList(){
        //int Count , Boolean 요일 / 날짜  ,  String 날짜  ,  (Boolean[7] 요일)  ,  String 시간  ,  String 온도  ,  Boolean 입욕제



        String reserveDataList;
        int index = 0;
        Count_Save = Integer.parseInt(getDatasStr("Count"));
        reserveDataList = getDatasStr("ReserveDataList");
        int Size = reserveDataList.length();
        int Temp = 0;

        for(int i = 0; i < Size; i++){

            if(reserveDataList.charAt(i) == ','){
                Temp++;
                continue;
            }
            switch (Temp)
            {
                case 0: //  카운트(index)
                    index = Character.getNumericValue(reserveDataList.charAt(i));
                    break;

                case 1: //isDayOfWeek (요일반복인지 날짜인지)
                    if(reserveDataList.charAt(i) == 'O') {
                        isDayOfWeek[index] = true;
                    }
                    else {
                        isDayOfWeek[index] = false;
                    }
                    break;

                case 2:
                    if(isDayOfWeek[index]) {    //요일 반복
                        for (int j = 0; j < 7; j++) {
                            if (reserveDataList.charAt(i + j) == 'O')
                                day[index][j] = true;
                            else if (reserveDataList.charAt(i + j) == 'X')
                                day[index][j] = false;
                        }
                        i += 6;
                    }

                    else{          // 특정 날짜
                        //년도
                        date[index] = Character.toString(reserveDataList.charAt(i++)) + Character.toString(reserveDataList.charAt(i++))
                                + Character.toString(reserveDataList.charAt(i++)) + Character.toString(reserveDataList.charAt(i++)); i++;
                        //월
                        date[index] += Character.toString(reserveDataList.charAt(i++)) + Character.toString(reserveDataList.charAt(i++)); i++;
                        //일
                        date[index] += Character.toString(reserveDataList.charAt(i++)) + Character.toString(reserveDataList.charAt(i));
                    }
                    break;

                case 3:
                    time[index] = Character.toString(reserveDataList.charAt(i++)) + Character.toString(reserveDataList.charAt(i++)); i++;
                    time[index] += Character.toString(reserveDataList.charAt(i++)) + Character.toString(reserveDataList.charAt(i));
                    break;

                case 4:
                    temperature[index] = Character.toString(reserveDataList.charAt(i++)) + Character.toString(reserveDataList.charAt(i));
                    break;

                case 5:
                    if(reserveDataList.charAt(i) == 'O')
                        prepar[index] = true;
                    else
                        prepar[index] = false;

                    Temp = 0;
                    break;

            }


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

    private void fabSetting() {

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
    }



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