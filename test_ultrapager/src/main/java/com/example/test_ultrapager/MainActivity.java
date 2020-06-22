package com.example.test_ultrapager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.tmall.ultraviewpager.UltraViewPager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UltraViewPager ultraViewPager = findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);

            //initialize UltraPagerAdapter，and add child view to UltraViewPager

        PagerAdapter adapter = new UltraPagerAdapter(false);
        ultraViewPager.setAdapter(adapter);

        //region Indicator
            //initialize built-in indicator
//        ultraViewPager.initIndicator();
//
//            //set style of indicators
//        ultraViewPager.getIndicator()
//                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
//                .setFocusColor(Color.GREEN)
//                .setNormalColor(Color.WHITE)
//                .setRadius((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
//            //set the alignment
//        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
//            //construct built-in indicator, and add it to  UltraViewPager
//        ultraViewPager.getIndicator().build();
        //endregion


            //set an infinite loop
            //enable auto-scroll mode
        ultraViewPager.setAutoScroll(2000);
        ultraViewPager.setInfiniteLoop(true);


    }
}
