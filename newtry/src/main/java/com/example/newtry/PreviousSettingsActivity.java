package com.example.newtry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PreviousSettingsActivity extends AppCompatActivity {
    private final int MAX_SIZE = 50;
    Boolean[] isDayOfWeek = new Boolean[MAX_SIZE];
    Boolean[][] day = new Boolean[MAX_SIZE][7];
    String[] date = new String[MAX_SIZE];
    String[] time = new String[MAX_SIZE];
    String[] temperature = new String[MAX_SIZE];
    Boolean[] prepar = new Boolean[MAX_SIZE];

    int Count_Save;

    ListView listView;
    ArrayList<HashMap<String, String>> preSettingDatas = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_settings);

        listView = findViewById(R.id.listView_PreviousSettings);

        getHistory_Reserve();

        listViewSetting();

    }
    public void startReserveWithPreviousData(int position){
        String previousReserveData = "";

        String save_Day = "";
//        String save_Time = "";
//        String save_Temperature = "";
//        String save_Prepar = "";

        if(isDayOfWeek[position] == true)
        {
            for(int i = 0; i < 7; i++){
                if(day[position][i] == true)
                    save_Day += "O";
                else
                    save_Day += "X";
            }
        }

        //요일인지 + 요일
        if(isDayOfWeek[position] == true)
        {
            previousReserveData += "O,";   //Boolean 요일
            previousReserveData += save_Day + ",";           //String 요일
        }
        //날짜인지 + 날짜
        else{
            previousReserveData += "X,";   //Boolean 날짜
            previousReserveData += date[position] + ",";           //String 날짜
        }
        previousReserveData += time[position] +",";           //String 시간
        previousReserveData += temperature[position] + ",";   //String 온도
        if(prepar[position])
            previousReserveData += "O";              //String 입욕제 OX
        else
            previousReserveData += "X";

        Log.d("Test", previousReserveData);

        Intent intent = new Intent(this, NewReserveActivity.class);
        intent.putExtra("Data", previousReserveData);
        startActivity(intent);
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
//            hashMap.put("Temperature", temperList.get(i));\
//            hashMap.put("Prepar", preparList.get(i));

            preSettingDatas.add(hashMap);
        }

        CustomAdapter adapter = new CustomAdapter(this,R.layout.listitem_show_reservestatus, preSettingDatas);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(PreviousSettingsActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
                startReserveWithPreviousData(position);
            }
        });
    }

    public void getHistory_Reserve(){
        //int Count , Boolean 요일 / 날짜  ,  String 날짜  ,  (Boolean[7] 요일)  ,  String 시간  ,  String 온도  ,  Boolean 입욕제

        String history_Reserve;
        int index = 0;
        Count_Save = Integer.parseInt(getDatasStr("Count"));
        history_Reserve = getDatasStr("History_Reserve");
        int Size = history_Reserve.length();
        int Temp = 0;

        for(int i = 0; i < Size; i++){

            if(history_Reserve.charAt(i) == ','){
                Temp++;
                continue;
            }
            switch (Temp)
            {
                case 0: //  카운트(index)
                    index = Character.getNumericValue(history_Reserve.charAt(i));
                    break;

                case 1: //isDayOfWeek (요일반복인지 날짜인지)
                    if(history_Reserve.charAt(i) == 'O') {
                        isDayOfWeek[index] = true;
                    }
                    else {
                        isDayOfWeek[index] = false;
                    }
                    break;

                case 2:
                    if(isDayOfWeek[index]) {    //요일 반복
                        for (int j = 0; j < 7; j++) {
                            if (history_Reserve.charAt(i + j) == 'O')
                                day[index][j] = true;
                            else if (history_Reserve.charAt(i + j) == 'X')
                                day[index][j] = false;
                        }
                        i += 6;
                    }

                    else{          // 특정 날짜
                        //년도
                        date[index] = Character.toString(history_Reserve.charAt(i++)) + Character.toString(history_Reserve.charAt(i++))
                                + Character.toString(history_Reserve.charAt(i++)) + Character.toString(history_Reserve.charAt(i++)); i++;
                        //월
                        date[index] += Character.toString(history_Reserve.charAt(i++)) + Character.toString(history_Reserve.charAt(i++)); i++;
                        //일
                        date[index] += Character.toString(history_Reserve.charAt(i++)) + Character.toString(history_Reserve.charAt(i));
                    }
                    break;

                case 3:
                    time[index] = Character.toString(history_Reserve.charAt(i++)) + Character.toString(history_Reserve.charAt(i++)); i++;
                    time[index] += Character.toString(history_Reserve.charAt(i++)) + Character.toString(history_Reserve.charAt(i));
                    break;

                case 4:
                    temperature[index] = Character.toString(history_Reserve.charAt(i++)) + Character.toString(history_Reserve.charAt(i));
                    break;

                case 5:
                    if(history_Reserve.charAt(i) == 'O')
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity);

    }
}
