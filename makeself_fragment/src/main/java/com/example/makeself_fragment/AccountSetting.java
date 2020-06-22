package com.example.makeself_fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountSetting extends Fragment {

    boolean AC_needUpdate = false;

//    Spinner Temp;
//    Spinner Water;
//    Spinner Prepar;
    int[] Acc = new int[]{0, 0, 0};

//    String[] Temps = new String[]{"온도", "44도(운동 후 피로할때)", "41도(저혈압 / 다이어트)", "38도(평범함)", "25도(시원함)", "15도(차가움)"};
//    String[] Waters = new String[]{"수위", "70%(온몸)", "60%(온몸)", "40%(반신욕)", "20%(족욕)"};
//    String[] Prepars= new String[]{"입욕제 여부", "O", "X"};


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.account_setting, container, false);
        final MainActivity A = (MainActivity)getActivity();

        Button btnCan, btnSav;

        btnSav = v.findViewById(R.id.btnSave);
        btnCan = v.findViewById(R.id.btnCancle);



        //region 스피너 리스너 생성


//        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(position != 0)
//                {
//                   // Toast.makeText(A,parent.getItemAtPosition(position) + " 선택됨" ,Toast.LENGTH_SHORT).show(); //선택된 온도 토스트 띄우기
//
//                    if (parent == Temp) { Acc[0] = position-1; }
//                    if (parent == Water) { Acc[1] = position-1; }
//                    if (parent == Prepar) { Acc[2] = position-1; }
//                    // 대충 DB에 저장하는 코드 쓰라는 뜻
//                    //
//                    //
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) { }
//        };
//        //endregion
//
//        Temp = v.findViewById(R.id.spinner_temp);
//        ArrayAdapter<String> adapter_Temp = new ArrayAdapter<String>(A, android.R.layout.simple_spinner_dropdown_item, Temps);
//        adapter_Temp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Temp.setAdapter(adapter_Temp);
//        Temp.setPrompt("온도 선택");
//        Temp.setOnItemSelectedListener(onItemSelectedListener);
//
//        Water = v.findViewById(R.id.spinner_water);
//        ArrayAdapter<String> adapter_Water = new ArrayAdapter<String>(A, android.R.layout.simple_spinner_item, Waters);
//        adapter_Water.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Water.setAdapter(adapter_Water);
//        Water.setPrompt("수위 선택");
//        Water.setOnItemSelectedListener(onItemSelectedListener);
//
//        Prepar = v.findViewById(R.id.spinner_prepar);
//        ArrayAdapter<String> adapter_Prepar = new ArrayAdapter<String>(A, android.R.layout.simple_spinner_item, Prepars);
//        adapter_Prepar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Prepar.setAdapter(adapter_Prepar);
//        Prepar.setPrompt("입욕제 여부");
//        Prepar.setOnItemSelectedListener(onItemSelectedListener);
//
//
//        btnCan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                A.onFragmentChanged(0);
//            }
//        });
//
//        btnSav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent savIn = getActivity().getIntent();
//                    int Count = savIn.getIntExtra("Count", 0);
//                    //FragmentAccount fragmentAccount = new FragmentAccount();
//
//                    Intent savOut = new Intent(getActivity().getApplicationContext(), MainActivity.class);
//
////                    fragmentAccount.ArrOfSettings[Count][0] = "O";
////                    fragmentAccount.ArrOfSettings[Count][1] = Integer.toString(Acc[0]);
////                    fragmentAccount.ArrOfSettings[Count][2] = Integer.toString(Acc[1]);
////                    fragmentAccount.ArrOfSettings[Count][3] = Integer.toString(Acc[2]);
//
//                    AC_needUpdate = true;
//                    Count++;
//                    savOut.putExtra("Settings", Acc);
//                    savOut.putExtra("Update", AC_needUpdate);
//                    savOut.putExtra("Count", Count);
//
//                    getActivity().setResult(getActivity().RESULT_OK,savOut);
//                    //A.onFragmentChanged(0);
//                    getActivity().finish();
////                    String str = fragmentAccount.ArrOfSettings[2][0] + fragmentAccount.ArrOfSettings[2][1] + fragmentAccount.ArrOfSettings[2][2] + fragmentAccount.ArrOfSettings[2][3];
////                    Toast.makeText(getActivity(),Integer.toString(Count),Toast.LENGTH_SHORT).show();
////                    Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
////
////                    //fragmentAccount.actUpdate();
////                    Count++;
//            }
//        });
        return v;
    }
    public int getSetData1(){
        return Acc[0];
    }
    public int getSetData2(){
        return Acc[1];
    }
    public int getSetData3(){
        return Acc[2];
    }
}
