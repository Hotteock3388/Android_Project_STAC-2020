package com.example.test_ultrapager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Home_Viewpager_Adapter extends PagerAdapter {
    private boolean isMultiScr;
    private String title;
    public Home_Viewpager_Adapter(boolean isMultiScr, String title) {
        this.isMultiScr = isMultiScr;
        this.title = title;
    }
    @Override public int getCount() {
        return 3;
    }

    @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull @Override public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.home_viewpager_item, null);
//        ImageView viewpager_Image = (ImageView) linearLayout.findViewById(R.id.viewpager_Image);
        TextView viewpager_Title = (TextView) linearLayout.findViewById(R.id.viewpager_Title);
        viewpager_Title.setText(title);
        container.addView(linearLayout);
        return linearLayout;
    }

    @Override public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        LinearLayout view = (LinearLayout) object; container.removeView(view);
    }


}
