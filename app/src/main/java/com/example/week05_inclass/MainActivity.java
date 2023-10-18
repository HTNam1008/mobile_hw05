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

    public boolean isPreDisable=false;


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
    //Main nhận thông tin từ FragList và gửi cho FragInfo
    public void onMsgFromFragToMain(String sender, String id, String fullName, String classId, String point) {
        if (sender.equals("list-frag-first")) {
            try {
                isPreDisable=true;
                fragmentInfo.onMsgFromMainToFragment(id, "Họ tên: "+fullName,"Lớp: "+classId, "Điểm trung bình: "+point);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }

        }
        else if (sender.equals("list-frag")) {

            try {
                isPreDisable=false;
                fragmentInfo.onMsgFromMainToFragment(id, "Họ tên: "+fullName,"Lớp: "+classId, "Điểm trung bình: "+point);
//                fragmentInfo.btnPrevious.setEnabled(true);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }

    }

    //Main nhận thông tin từ FragInfo và gửi cho FragList
    public void onMsgFromFragToMain(String sender, int position) {
        if(sender.equals("btnFirst") || sender.equals("btnLast"))
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