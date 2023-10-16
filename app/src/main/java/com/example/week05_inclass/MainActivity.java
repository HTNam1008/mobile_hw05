package com.example.week05_inclass;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;


public class MainActivity extends FragmentActivity implements MainCallBacks {
    FragmentTransaction ft;
    FragmentList fragmentList;
    FragmentInfo fragmentInfo;


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
        if (sender.equals("list-frag")) {
            try {
                fragmentInfo.onMsgFromMainToFragment(id, "Họ tên: "+fullName,"Lớp: "+classId, "Điểm trung bình: "+point);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
    }
}