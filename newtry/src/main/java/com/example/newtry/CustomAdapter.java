package com.example.newtry;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;

class CustomAdapter extends BaseAdapter {

    Context context;
    int resource;
    ArrayList<HashMap<String, String>> settingList;

    public CustomAdapter(Context context, int resource,  ArrayList<HashMap<String, String>> settingList) {
        this.context = context;
        this.resource = resource;
        this.settingList = settingList;
    }

    @Override
    public int getCount() {
        return settingList.size();
    }

    @Override
    public Object getItem(int position) {
        return settingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 리스트뷰의 한 항목을 구현하는 뷰 반환
    // 화면에 항목이 보여질 때 호출됨.  화면에 보여지는 개수만큼 호출
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 재활용 가능한 View가 없다면 새로운 View 생성
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
        }

        Boolean isDayOfWeek;
        if(settingList.get(position).get("IsDayOfWeek") == "O")
            isDayOfWeek = true;
        else
            isDayOfWeek = false;

        Boolean days[] = new Boolean[7];
        int[] textIds_Days ={R.id.text_1, R.id.text_2, R.id.text_3, R.id.text_4, R.id.text_5, R.id.text_6, R.id.text_7};
        String[] text_Days = {"월", "화", "수", "목", "금", "토", "일"};

        String date = "";
        String time = "";
        int hourInt;
        int minuteInt;
        String hourString = "";
        String minuteString;
        int temper;
        Boolean prepar;


        //  요일
        if(isDayOfWeek){

            for(int i = 0; i < 7; i++){
                if(settingList.get(position).get("Day" + (i+1)) == "O")
                    days[i] = true;
                else
                    days[i] = false; }

        }
        //  날짜
        else {
            date = settingList.get(position).get("Date");
        }


        time = settingList.get(position).get("Time");
        hourInt = Integer.parseInt(Character.toString(time.charAt(0)) + Character.toString(time.charAt(1)));


        minuteInt = Integer.parseInt(Character.toString(time.charAt(2)) + Character.toString(time.charAt(3)));
        minuteString = Character.toString(time.charAt(2)) + Character.toString(time.charAt(3));
        temper = Integer.parseInt(settingList.get(position).get("Temperature"));


        if(settingList.get(position).get("Prepar") == "O")
            prepar = true;
        else
            prepar = false;



        //요일
        if(isDayOfWeek) {
            convertView.findViewById(R.id.text_reserveDateForm).setVisibility(View.GONE);

            if(days[0] == true &&days[1] == true &&days[2] == true &&days[3] == true &&days[4] == true && days[5] == false && days[6] == false) {
                convertView.findViewById(R.id.text_duringWeeks).setVisibility(View.VISIBLE);

                convertView.findViewById(R.id.text_endOfWeeks).setVisibility(View.GONE);
                for(int i = 0; i < 7; i++) convertView.findViewById(textIds_Days[i]).setVisibility(View.GONE);
            }
            else if(days[0] == false &&days[1] == false &&days[2] == false &&days[3] == false &&days[4] == false &&days[5] == true && days[6] == true) {
                convertView.findViewById(R.id.text_endOfWeeks).setVisibility(View.VISIBLE);

                convertView.findViewById(R.id.text_duringWeeks).setVisibility(View.GONE);
                for(int i = 0; i < 7; i++) convertView.findViewById(textIds_Days[i]).setVisibility(View.GONE);
            }
            else{
                convertView.findViewById(R.id.text_endOfWeeks).setVisibility(View.GONE);
                convertView.findViewById(R.id.text_duringWeeks).setVisibility(View.GONE);

                for(int i = 0; i < 7; i++){
                    TextView textView_Days = convertView.findViewById(textIds_Days[i]);
                    textView_Days.setVisibility(View.VISIBLE);
                    if(days[i]){
                        textView_Days.setText(text_Days[i]);
                        textView_Days.setTextColor(ContextCompat.getColor(convertView.getContext().getApplicationContext(), R.color.colorBlack));
                    }else{
                        textView_Days.setHint(text_Days[i]);
                        textView_Days.setTextColor(ContextCompat.getColor(convertView.getContext().getApplicationContext(), R.color.colorAppBackgroundGray));
                    }
                }
            }

        }
        else{
            for(int i = 0; i < 7; i++) convertView.findViewById(textIds_Days[i]).setVisibility(View.GONE);
            convertView.findViewById(R.id.text_duringWeeks).setVisibility(View.GONE);
            convertView.findViewById(R.id.text_endOfWeeks).setVisibility(View.GONE);

            TextView textView_Date = convertView.findViewById(R.id.text_reserveDateForm);
            textView_Date.setVisibility(View.VISIBLE);
            String reserveDate = "";

            reserveDate = Character.toString(date.charAt(0)) + Character.toString(date.charAt(1)) + Character.toString(date.charAt(2)) + Character.toString(date.charAt(3))
                    + "/" + Character.toString(date.charAt(5)) + "/" +Character.toString(date.charAt(6)) + Character.toString(date.charAt(7));
            textView_Date.setText(reserveDate);
        }




        //시간


        // 0 ~ 11 / 12 ~ 23
        // 오전 0 1 2 3 4 5 6 7 8 9 10 11
        if(hourInt <= 11) {
            convertView.findViewById(R.id.text_Morning).setVisibility(View.VISIBLE);
            hourString = Character.toString(time.charAt(0)) + Character.toString(time.charAt(1));


            convertView.findViewById(R.id.text_Afternoon).setVisibility(View.GONE);
        }
        // 오후
        else if (hourInt >= 12) { // 12 13 14 15 16 17 18 19 20 21 22 23
            convertView.findViewById(R.id.text_Afternoon).setVisibility(View.VISIBLE);
            if(hourInt == 12)
                hourString = Integer.toString(Integer.parseInt(Character.toString(time.charAt(0)) + Character.toString(time.charAt(1))));
            else if (hourInt >= 22)
                hourString = Integer.toString(Integer.parseInt(Character.toString(time.charAt(0)) + Character.toString(time.charAt(1))) - 12);
            else
                hourString = "0" + Integer.toString(Integer.parseInt(Character.toString(time.charAt(0)) + Character.toString(time.charAt(1))) - 12);
            convertView.findViewById(R.id.text_Morning).setVisibility(View.GONE);
        }


        TextView text_ReserveTime = convertView.findViewById(R.id.text_reservedTime);
        text_ReserveTime.setText(hourString + ":" + minuteString);
        //온도 설정
        TextView text_Temper = convertView.findViewById(R.id.text_Temperature);
        text_Temper.setText(temper + "℃");


        //입욕제
        TextView text_Prepar = convertView.findViewById(R.id.text_Preapr);
        if(prepar)
            text_Prepar.setTextColor(ContextCompat.getColor(convertView.getContext().getApplicationContext(), R.color.colorBlack));
        else
            text_Prepar.setTextColor(ContextCompat.getColor(convertView.getContext().getApplicationContext(), R.color.colorButtonGray));


        //반환할 View 객체 설정
        return convertView;
    }
}
