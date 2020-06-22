package com.example.no_fucking_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    int[] Selected = new int[3];
    int selectedPosition;
    String Settings[][] = new String[10][4];
    int sHour, sMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation_addListener("Main");
        Spinner_Adapt();

        add_resultListener();

    }

    void add_resultListener()
    {
        findViewById(R.id.btn_reserve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int nHour, nMinute;

                TimePicker timePicker = findViewById(R.id.timePicker);

                //region TimePicker 시간 가져오기
                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        sHour = hourOfDay;
                        sMinute = minute;
                    }
                });
                //endregion

                nHour = cal.get(Calendar.HOUR_OF_DAY);
                nMinute = cal.get(Calendar.MINUTE);

                Toast.makeText(getApplicationContext(), (nHour) + " + " + (nMinute) ,Toast.LENGTH_SHORT).show();


//                Toast.makeText(getApplicationContext(), "온도 : " + Settings[selectedPosition][1] +
//                        "수위 : " + Settings[selectedPosition][2] + "입욕제 : " + Settings[selectedPosition][3] , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getDatas(){
        String str = "";
        try(InputStreamReader inputStreamReader = new InputStreamReader(openFileInput("Data.txt"))) {
            int temp;
            while((temp = inputStreamReader.read()) != -1){
                str += (char)temp;
            }
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }
        return str;
    }

    void Spinner_Adapt() {
//        String[] Temps = new String[]{"온도", "44도(운동 후 피로할때)", "41도(저혈압 / 다이어트)", "38도(평범함)", "25도(시원함)", "15도(차가움)"};
//        String[] Waters = new String[]{"수위", "70%(온몸)", "60%(온몸)", "40%(반신욕)", "20%(족욕)"};
//        String[] Prepars= new String[]{"입욕제 여부", "O", "X"};
        Spinner Load = findViewById(R.id.loadSetting);

        String[] NTemp = new String[] {"44ºC", "41ºC", "38ºC", "25ºC", "15ºC"};
        String[] NWater = new String[] {"70%", "60%", "40%", "20%"};
        String[] NPrepars = new String[] {"O", "X"};
        String Setting;
        Setting = getDatas();
        String[] Show = new String[Setting.length()/4];


        if(Setting.length() != 0 ) {

            for(int i=0; i < Setting.length() / 4; i++) {
                Setting = getDatas();
                Settings[i][0] = "O";
                Settings[i][1] = NTemp[Character.getNumericValue(Setting.charAt(i*4 + 1))];      //1 5 9
                Settings[i][2] = NWater[Character.getNumericValue(Setting.charAt(i*4 + 2))];      //2 6 10
                Settings[i][3] = NPrepars[Character.getNumericValue(Setting.charAt(i*4 + 3))];      //3 7 11\
                Show[i] = Settings[i][1] + "    " +  Settings[i][2] + "    " + Settings[i][3];

            }
        }


        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                // 대충 DB에 저장하는 코드 쓰라는 뜻
                //
                //
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { selectedPosition = 0; }
        };


        ArrayAdapter<String> adapter_Load = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Show);
        adapter_Load.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Load.setAdapter(adapter_Load);
        Load.setPrompt("설정 선택");
        Load.setOnItemSelectedListener(onItemSelectedListener);
    }

    void navigation_addListener(String Where)
    {
        if(Where != "Account") {
            findViewById(R.id.imageView1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(Where != "Main") {
            findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(Where != "Option") {
            findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, OptionActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}