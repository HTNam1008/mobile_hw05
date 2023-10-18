package com.example.week05_inclass;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements MainCallBacks {
    FragmentTransaction ft;
    FragmentList fragmentList;
    FragmentInfo fragmentInfo;

    public boolean isDisable=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft=getSupportFragmentManager().beginTransaction();
        fragmentList=FragmentList.newInstance("list");
        ft.replace(R.id.main_holder_list,fragmentList);
        ft.commit();

        ft=getSupportFragmentManager().beginTransaction();
        fragmentInfo=FragmentInfo.newInstance("info");
        ft.replace(R.id.main_holder_info,fragmentInfo);
        ft.commit();
    }
    @Override
    public void onMsgFromFragToMain(String sender, String id, String fullName, String classId, String point) {
        if (sender.equals("list-frag-first")) {
            try {
                isDisable=true;
                fragmentInfo.onMsgFromMainToFragment(id, "Họ tên: "+fullName,"Lớp: "+classId, "Điểm trung bình: "+point);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }

        }
        else if (sender.equals("list-frag")) {

            try {
                isDisable=false;
                fragmentInfo.onMsgFromMainToFragment(id, "Họ tên: "+fullName,"Lớp: "+classId, "Điểm trung bình: "+point);
//                fragmentInfo.btnPrevious.setEnabled(true);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }

    }

    public void onMsgFromFragToMain(String sender, int position) {
        if(sender.equals("btnFirst"))
        {
            try{
                fragmentList.onMsgFromMainToFragment(position);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }

        if (sender.equals("btnPre")){
            try{
                fragmentList.onMsgFromMainToFragment("previous");
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
    }
}