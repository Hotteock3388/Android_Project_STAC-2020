package com.example.no_fucking_fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountActivity extends AppCompatActivity {

    public int Count = 0;

    String[] NTemp = new String[] {"44ºC", "41ºC", "38ºC", "25ºC", "15ºC"};
    String[] NWater = new String[] {"70%", "60%", "40%", "20%"};
    String[] NPrepars = new String[] {"O", "X"};

    String[][] ArrOfSettings = new String[][]{{"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"},
            {"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"},{"X","0","0","0"}};
    int[] Ids = new int[]{R.id.Setting0, R.id.Setting1, R.id.Setting2, R.id.Setting3, R.id.Setting4, R.id.Setting5, R.id.Setting6, R.id.Setting7, R.id.Setting8, R.id.Setting9 };

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        editText = findViewById(R.id.removeText);
        navigation_addListener("Account");
        remove_addListener();

//        int t = '2' - 48;
//        Toast.makeText(getApplicationContext(), Integer.toString(t) , Toast.LENGTH_SHORT).show();



        actUpdate();
    }

    private void remove_addListener() {

        findViewById(R.id.btnRemove).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(String.valueOf(editText.getText()));
                removeSettingDatas(index);
            }
        });
}

    void addListener(){
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivityForResult(intent, 0);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       // Toast.makeText(this, Integer.toString(resultCode), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "넘어옴", Toast.LENGTH_SHORT).show();

        if(requestCode == 0 && resultCode == RESULT_OK)
        {

            int[] NLArr = data.getIntArrayExtra("Spinners");
            String Temp = "";

            Temp += ArrOfSettings[Count][0] = "O";
            for(int i = 1; i < 4; i++) {
                ArrOfSettings[Count][i] = Integer.toString(NLArr[i-1]);
                Temp += ArrOfSettings[Count][i];
            }
            setDatasStr(Temp, true);
            actUpdate();
            Count++;

        }
        else ;
    }


public void showSettings() {
    //FragmentAccount fragmentAccount = new FragmentAccount();
    //for (int i = 0; i < Count; i++) {
    //if (ArrOfSettings[i][0] == "O") {
    String str = getDatasStr();
   // String str = "";
    int i = 0;
//    boolean End = true;
//    if(str.charAt(i) == 'O') End = false;
    //if (str != "") {
        while (getDatasStr().length() > i++) {
            int D1 = Character.getNumericValue(str.charAt(i++));
            int D2 = Character.getNumericValue(str.charAt(i++));
            int D3 = Character.getNumericValue(str.charAt(i++));

//
            //FragmentAccount fragmentAccount = new FragmentAccount();
            TextView Temp = new TextView(this);
            TextView Water = new TextView(this);
            TextView Prepar = new TextView(this);


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            //params.gravity = Gravity.CENTER;


            //Temp.setText(NTemp[Integer.parseInt(String.valueOf(ArrOfSettings[i][1]))]);
            Temp.setTextSize(40);
            Temp.setLayoutParams(params);
            Temp.setGravity(Gravity.CENTER);


            //Water.setText(NWater[Integer.parseInt(String.valueOf(ArrOfSettings[i][2]))]);
            Water.setTextSize(40);
            Water.setLayoutParams(params);
            Water.setGravity(Gravity.CENTER);

            //Prepar.setText(NPrepars[Integer.parseInt(String.valueOf(ArrOfSettings[i][3]))]);
            Prepar.setTextSize(40);
            Prepar.setLayoutParams(params);
            Prepar.setGravity(Gravity.CENTER);

            LinearLayout newLayout = new LinearLayout(this);

            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 120, getResources().getDisplayMetrics());
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);

            newLayout.setLayoutParams(param);
            newLayout.setBackgroundResource(R.drawable.border);

            Temp.setText(NTemp[D1]);
            Water.setText(NWater[D2]);
            Prepar.setText(NPrepars[D3]);

            newLayout.addView(Temp);
            newLayout.addView(Water);
            newLayout.addView(Prepar);

            //newLayout.removeView(newLayout);

            //newLayout.setLayoutParams(param);
            final LinearLayout linearLayout = findViewById(R.id.bigLayout);
            linearLayout.addView(newLayout);

            addSettingListener(newLayout);
            newLayout.setId(Ids[Count]);
//            newLayout.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int index = 0;
//                    for(int i = 0; i < Count; i++) {
//                        if (Ids[i] == v.getId())
//                            index = i;
//                    }
//                    char[] str =  getDatasArr();
//                    for(int i = index*4 + 5; i < Count*4; i++){
//                        str[i-4] = str[i];
//                        str[i] = '';
//                    }
//                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileStreamPath("Data.txt"), false)))
//                    {
//                        String temp = "";
//                        for(int i = 0; i < Count*4 - 5; i++){
//                            temp += str[i];
//                        }
//                        bufferedWriter.write(temp);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    actUpdate();
//                    // 0123 4567 891011
//                    return false;
//                }
//            });
            //AccountSetting accountSetting = new AccountSetting();
            // } else {
            // String str = ArrOfSettings[2][0] + ArrOfSettings[2][1] + ArrOfSettings[2][2] + ArrOfSettings[2][3];
            //Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show(); //선택된 온도 토스트 띄우기
            //}
        }
        //Toast.makeText(this, "추가하기 버튼 생성완료", Toast.LENGTH_SHORT).show();
    if(Count != 9)
        makeAddLinear();
}

    //region 추가하기 Layout 만들기
    void makeAddLinear(){
        LinearLayout add = new LinearLayout(this);
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 120, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        add.setLayoutParams(param);
        add.setBackgroundResource(R.drawable.border);
        //add.setGravity(Gravity.CENTER);

        TextView addText = new TextView(this);
        addText.setText("추가하기");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        addText.setLayoutParams(params);
        addText.setTextSize(50);
        addText.setGravity(Gravity.CENTER);
        add.addView(addText);
        add.setId(R.id.add);
        LinearLayout linearLayout = findViewById(R.id.bigLayout);
        linearLayout.addView(add);
        addListener();
    }
    //endregion

void removeSettingDatas(int index){
        // 1234 5678 9012 3456 7890
        //   0    1    2   3    4
        char[] Temp = getDatasArr();
        int length = getDatasStr().length();
        String t = "";
//        //설정이 1개밖에 없을때
//        if(Temp.length == 4)
//        {
//
//        }
        //마지막 설정을 삭제할때
        for(int i = 0; i < length; i++){
            //0123 4567 8901
            if(i / 4 == index) // 1
                continue;
            else
                t += Temp[i];
        }
    Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();
    setDatasStr(t, false);
    actUpdate();
}

    // region String 형으로 설정들 받기
    public String getDatasStr() {
        String str = "";
        try (InputStreamReader inputStreamReader = new InputStreamReader(openFileInput("Data.txt"))) {
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
    public void setDatasStr(String str, boolean append){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileStreamPath("Data.txt"), append)))
        {
            bufferedWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region String배열로 데이터 받기
    public char[] getDatasArr(){
        char[] chars = new char[getDatasStr().length()];
        try(InputStreamReader inputStreamReader = new InputStreamReader(openFileInput("Data.txt"))) {
            int temp;
            int i = 0;
            while((temp = inputStreamReader.read()) != -1){
                chars[i] = (char) temp;
                i++;
            }
            //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }
        return chars;
    }
    //endregion

    //region String배열로 설정들 저장하기
    public void setDatasArr(String[] Arr){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileStreamPath("Data.txt"), true)))
        {
            String str = "";
            for(int i = 0; i < Arr.length; i++)
                str += Arr[i];

            bufferedWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    public void actUpdate()
    {
        LinearLayout linearLayout = findViewById(R.id.bigLayout);
        linearLayout.removeAllViews();
        showSettings();
    }

    void addSettingListener(final LinearLayout layout)
    {
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                LinearLayout linearLayout = new LinearLayout(getApplicationContext());
//                linearLayout.removeView(layout);
                return false;
            }
        });
    }

    //region 하단 네비게이션 바 설정
    void navigation_addListener(String Where)
    {
        if(Where != "Account") {
            findViewById(R.id.imageView1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                    startActivity(intent);
                    showSettings();
                }
            });
        }

        if(Where != "Main") {
            findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
        }

        if(Where != "Option") {
            findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), OptionActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    //endregion
}
