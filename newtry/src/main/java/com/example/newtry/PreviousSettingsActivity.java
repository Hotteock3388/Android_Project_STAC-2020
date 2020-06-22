package com.example.newtry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class PreviousSettingsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String, String>> preSettingDatas = new ArrayList<>();

    ArrayList<String> monthList = getPreviousSettingDatasArrayList("month");
    ArrayList<String> dayList = getPreviousSettingDatasArrayList("day");
    ArrayList<String> temperList = getPreviousSettingDatasArrayList("temper");
    ArrayList<String> preparList = getPreviousSettingDatasArrayList("prepar");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_settings);

        listView = findViewById(R.id.listView_PreviousSettings);

        // ListView에 표시할 데이터들을 합쳐 하나의 ArrayList 만들기
        for(int i = 0; i < monthList.size(); i++){
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("month", monthList.get(i));
            hashMap.put("day", dayList.get(i));
            hashMap.put("temper", temperList.get(i));
            hashMap.put("prepar", preparList.get(i));

            preSettingDatas.add(hashMap);
        }

        CustomAdapter adapter = new CustomAdapter(this,R.layout.presettingsform, preSettingDatas);

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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity);

    }
}
