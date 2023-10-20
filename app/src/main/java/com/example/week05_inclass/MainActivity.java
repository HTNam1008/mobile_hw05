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
    public void onMsgFromFragToMain(String sender, String id, String fullName, String classId, String point, int position) {
        if (sender.equals("list-frag")) {
            try {
                fragmentInfo.onMsgFromMainToFragment(id, "Họ tên: "+fullName,"Lớp: "+classId, "Điểm trung bình: "+point,position);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
    }

    @Override
    public void onMsgFromFragToMain(String sender) {
        if(sender.equals("btnFirst")||sender.equals("btnPre")||sender.equals("btnNext")||sender.equals("btnLast"))
        {
            try{
                fragmentList.onMsgFromMainToFragment(sender);

            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
    }
}