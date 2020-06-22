package com.example.no_fucking_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    //region 변수 생성
    Spinner Temp;
    Spinner Water;
    Spinner Prepar;

    String[] NTemp = new String[] {"44ºC", "41ºC", "38ºC", "25ºC", "15ºC"};
    String[] NWater = new String[] {"70%", "60%", "40%", "20%"};
    String[] NPrepars = new String[] {"O", "X"};

//    String[] Temps = new String[]{"온도", "44도(운동 후 피로할때)", "41도(저혈압 / 다이어트)", "38도(평범함)", "25도(시원함)", "15도(차가움)"};
//    String[] Waters = new String[]{"수위", "70%(온몸)", "60%(온몸)", "40%(반신욕)", "20%(족욕)"};
//    String[] Prepars= new String[]{"입욕제 여부", "O", "X"};



    int[] Acc = new int[]{0, 0, 0};

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        btn_Listener();

        Spinner_Adapt();

    }
    //region 버튼 리스너 생성
    void btn_Listener()
    {
        findViewById(R.id.btnCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                intent.putExtra("Spinners", Acc);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    //endregion

    //region Spinner 설정
    void Spinner_Adapt() {
        String[] Temps = new String[]{"온도", "44도(운동 후 피로할때)", "41도(저혈압 / 다이어트)", "38도(평범함)", "25도(시원함)", "15도(차가움)"};
        String[] Waters = new String[]{"수위", "70%(온몸)", "60%(온몸)", "40%(반신욕)", "20%(족욕)"};
        String[] Prepars= new String[]{"입욕제 여부", "O", "X"};

        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0)
                {


                    if (parent == Temp) { Acc[0] = position-1;
                        Toast.makeText(getApplicationContext(), position-1 + "번" + NTemp[Acc[0]] + " 선택됨" ,Toast.LENGTH_SHORT).show(); //선택된 온도 토스트 띄우기
                    }
                    if (parent == Water) { Acc[1] = position-1;
                        Toast.makeText(getApplicationContext(), position-1 + "번" + NWater[Acc[1]] + " 선택됨" ,Toast.LENGTH_SHORT).show(); //선택된 온도 토스트 띄우기
                    }
                    if (parent == Prepar) { Acc[2] = position-1;
                        Toast.makeText(getApplicationContext(), position-1 + "번" + NPrepars[Acc[2]] + " 선택됨" ,Toast.LENGTH_SHORT).show(); //선택된 온도 토스트 띄우기
                    }
                    // 대충 DB에 저장하는 코드 쓰라는 뜻
                    //
                    //
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };

        Temp = findViewById(R.id.spinner_temp);
        ArrayAdapter<String> adapter_Temp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Temps);
        adapter_Temp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Temp.setAdapter(adapter_Temp);
        Temp.setPrompt("온도 선택");
        Temp.setOnItemSelectedListener(onItemSelectedListener);

        Water = findViewById(R.id.spinner_water);
        ArrayAdapter<String> adapter_Water = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, Waters);
        adapter_Water.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Water.setAdapter(adapter_Water);
        Water.setPrompt("수위 선택");
        Water.setOnItemSelectedListener(onItemSelectedListener);

        Prepar = findViewById(R.id.spinner_prepar);
        ArrayAdapter<String> adapter_Prepar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, Prepars);
        adapter_Prepar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Prepar.setAdapter(adapter_Prepar);
        Prepar.setPrompt("입욕제 여부");
        Prepar.setOnItemSelectedListener(onItemSelectedListener);
    }
    //endregion
}
