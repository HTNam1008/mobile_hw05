package com.example.week05_inclass;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentList extends Fragment {
    MainActivity main;
    Context context;
    String id[] = {"21120099", "21120098","21120097","21120096","21120095","21120094","21120093","21120092","21120091","21120090","21120089","21120088","21120087","21120086","21120085","21120084","21120083","21120082","21120081","21120080"};
    String fullName[] = {"Nguyễn A", "Nguyễn B","Nguyễn C","Nguyễn D","Nguyễn E","Nguyễn F","Nguyễn G","Nguyễn H","Nguyễn I","Nguyễn J","Lê A","Lê B","Lê C","Lê D","Lê E","Lê F","Lê G","Lê H","Lê I","Lê J"};
    String points[] = {"1", "2","3","4","5","6","7","8","9","10","1", "2","3","4","5","6","7","8","9","10"};
    Integer[] icons={R.drawable.actor_1,R.drawable.actor_2,R.drawable.actor_3,R.drawable.actor_4,R.drawable.actor_5,R.drawable.actor_6,R.drawable.actor_7,R.drawable.actor_9,R.drawable.actor_10,R.drawable.actor_2,R.drawable.actor_1,R.drawable.actor_2,R.drawable.actor_3,R.drawable.actor_4,R.drawable.actor_5,R.drawable.actor_6,R.drawable.actor_7,R.drawable.actor_9,R.drawable.actor_10,R.drawable.actor_2};

    String classID="21CTT2";

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout layout_list = (LinearLayout) inflater.inflate(R.layout.fragment_list, null);
        final TextView txtBlue = (TextView) layout_list.findViewById(R.id.txtChoosen);
        ListView listView = (ListView) layout_list.findViewById(R.id.listView);

        listView.setBackgroundColor(Color.parseColor("#ffccddff"));

        CustomIconNameAdapter adapter = new CustomIconNameAdapter(context, R.layout.custom_row_icon_name, id, icons);
        listView.setAdapter(adapter);

        listView.setSelection(0); listView.smoothScrollToPosition(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                main.onMsgFromFragToMain("list-frag",id[position],fullName[position],classID,points[position]);
                txtBlue.setText("Mã số: "+id[position]);
            }
        });

        return layout_list;
    }

}