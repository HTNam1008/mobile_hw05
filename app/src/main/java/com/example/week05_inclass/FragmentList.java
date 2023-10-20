package com.example.week05_inclass;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentList extends Fragment implements FragmentCallBacks{
    MainActivity main;
    Context context;
    String id[] = {"21120185", "21120182","21120176","21120099","21120076","21120075","21120074","21120073","21120072","21120071","21120070","21120069","21120068","21120067","21120066","21120065","21120064","21120063","21120062","21120061"};
    String fullName[] = {"Phạm Vân Anh Thư", "Phan Trí Nhân","Đinh Thị Thúy Hường","Hoàng Thành Nam","Nguyễn Thanh Huệ","Nguyễn Thị Hoa","Trần Kim Liên","Đỗ Ngọc Hà",
            "Nguyễn Thùy Chi","Trần Ngọc Diễm","Lê Thị Bích Hồng","Lê Như Thảo","Lý Thanh Ngân","Lê Nhật Nam","Lê Phương Thùy","Trần Tiểu Vy","Lê Hữu Nghĩa","Nguyễn Hoàng Mai","Lê Như Ý","Võ Ý Nhi"};
    String points[] = {"10", "9","9","9","10","6","7","8","9","10","5", "6","8","8","5","6","7","8","9","10"};
    Integer[] icons={R.drawable.actor_1,R.drawable.actor_2,R.drawable.actor_3,R.drawable.actor_4,R.drawable.actor_5,R.drawable.actor_6,R.drawable.actor_7,R.drawable.actor_8,R.drawable.actor_9,R.drawable.actor_10,R.drawable.actor_11,R.drawable.actor_12,R.drawable.actor_13,R.drawable.actor_14,R.drawable.actor_15,R.drawable.actor_16,R.drawable.actor_17,R.drawable.actor_18,R.drawable.actor_19,R.drawable.actor_20};

    String classID="21CTT2";
    TextView txtChoosen;
    ListView listView;
    int currentPosition=0;
    int indexBefore=0;
    public static FragmentList newInstance(String arg) {
        FragmentList fragment = new FragmentList();
        Bundle args = new Bundle();
        args.putString("list", arg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            context = getActivity();
            main = (MainActivity) getActivity();
        }
        catch (IllegalStateException e){
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout_list = (LinearLayout) inflater.inflate(R.layout.fragment_list, null);
        txtChoosen = (TextView) layout_list.findViewById(R.id.txtChoosen);
        listView = (ListView) layout_list.findViewById(R.id.listView);
        listView.setBackgroundColor(Color.parseColor("#ffccddff"));
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        CustomIconNameAdapter adapter = new CustomIconNameAdapter(context, R.layout.custom_row_icon_name, id, icons);
        listView.setAdapter(adapter);


        listView.setSelection(0);
        listView.smoothScrollToPosition(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {

                main.onMsgFromFragToMain("list-frag",id[position],fullName[position],classID,points[position],position);
                txtChoosen.setText("Mã số: "+id[position]);

                setBackgroundItem(position);
                indexBefore=position;

            }
        });
        return layout_list;
    }

    public void setBackgroundItem(int position) {
        listView.smoothScrollToPosition(position);
        CustomIconNameAdapter adapter=(CustomIconNameAdapter) listView.getAdapter();
        adapter.setSelectedPosition(position);
   }

    @Override
    public void onMsgFromMainToFragment(String sender){
        int position=0;
        if (sender.equals("btnFirst")) {
            position=0;
        }
        else if (sender.equals("btnPre")) {
            position=indexBefore-1;
        }
        else if (sender.equals("btnNext")) {
            position=indexBefore+1;
        }
        else if (sender.equals("btnLast")) {
            position=id.length-1;
        }

        txtChoosen.setText("Mã số: "+id[position]);

        setBackgroundItem(position);

        main.onMsgFromFragToMain("list-frag",id[position],fullName[position],classID,points[position],position);
        indexBefore=position;
    }

    @Override
    public void onMsgFromMainToFragment(String id, String fullName, String classId, String point,int position) {

    }
}