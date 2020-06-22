package com.example.makeself_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentAccount fragmentAccount = new FragmentAccount();
    private FragmentReserve fragmentReserve = new FragmentReserve();
    private FragmentOption fragmentOption = new FragmentOption();
    private AccountSetting accountSetting = new AccountSetting();
    public FragmentActivity FgActivity = fragmentAccount.getActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentAccount).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    //fragment 교체
    public void onFragmentChanged(int index) {
        if (index == 0) {
            //fragment manager 를 이용하여 현재의 fragment를 교체
            //begin transaction를 이용하여 롤백할 수 있도록한다
            //commit를 호출하여 실행
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentAccount).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, accountSetting).commit();
        }
    }

    public void RR() {
        fragmentAccount.actUpdate();
    }


   public int getNewSet_Data1(){
        return accountSetting.getSetData1();
    }
    public int getNewSet_Data2(){
        return accountSetting.getSetData2();
    }
    public int getNewSet_Data3(){
        return accountSetting.getSetData3();
    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(item.getItemId())
            {
                case R.id.AccountMenu:
                    transaction.replace(R.id.frameLayout, fragmentAccount).commitAllowingStateLoss();
                    //fragmentAccount.showSettings();

                    break;
                case R.id.ReserveMenu:
                    transaction.replace(R.id.frameLayout, fragmentReserve).commitAllowingStateLoss();
                    break;
                case R.id.optionMenu:
                    transaction.replace(R.id.frameLayout, fragmentOption).commitAllowingStateLoss();
                    break;
            }
            return true;
        }

    }

}
