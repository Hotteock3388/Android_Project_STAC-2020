package com.example.test_ultrapager;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by mikeafc on 15/11/26.
 */
public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;
    public int nowPosition;

    public UltraPagerAdapter(boolean isMultiScr) {
        this.isMultiScr = isMultiScr;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        nowPosition = position;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child, null);
        ConstraintLayout constraintLayout = (ConstraintLayout)LayoutInflater.from(container.getContext()).inflate(R.layout.layout_01, null);;
        new LinearLayout(container.getContext());
        TextView textView = linearLayout.findViewById(R.id.pager_textview);
        textView.setText(position + "");
        linearLayout.setId(R.id.item_id);
        switch (position) {
            case 0:
                linearLayout.setBackgroundColor(Color.parseColor("#2196F3"));
                break;
            case 1:
                //linearLayout.setBackgroundColor(Color.parseColor("#673AB7"));
                constraintLayout = (ConstraintLayout)LayoutInflater.from(container.getContext()).inflate(R.layout.layout_01, null);
                break;
            case 2:
                //linearLayout.setBackgroundColor(Color.parseColor("#009688"));
                constraintLayout = (ConstraintLayout)LayoutInflater.from(container.getContext()).inflate(R.layout.layout_02, null);
                break;
            case 3:
                //linearLayout.setBackgroundColor(Color.parseColor("#607D8B"));
                constraintLayout = (ConstraintLayout)LayoutInflater.from(container.getContext()).inflate(R.layout.layout_03, null);
                break;
            case 4:
                //linearLayout.setBackgroundColor(Color.parseColor("#F44336"));
                constraintLayout = (ConstraintLayout)LayoutInflater.from(container.getContext()).inflate(R.layout.layout_04, null);
                break;
            case 5:
                constraintLayout = (ConstraintLayout)LayoutInflater.from(container.getContext()).inflate(R.layout.layout_05, null);
                break;
        }

        if(position == 0) {
            container.addView(linearLayout);
            return linearLayout;
        }
        else
        {
            container.addView(constraintLayout);
            return constraintLayout;
        }
//        linearLayout.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, container.getContext().getResources().getDisplayMetrics());
//        linearLayout.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, container.getContext().getResources().getDisplayMetrics());

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(position == 0) {
            LinearLayout view = (LinearLayout) object;
            container.removeView(view);
        }
        else{
            ConstraintLayout view = (ConstraintLayout) object;
            container.removeView(view);
        }
    }
}