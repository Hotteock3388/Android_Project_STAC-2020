package com.example.newtry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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



        //반환할 View 객체 설정
        return convertView;
    }
}
