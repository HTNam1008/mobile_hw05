package com.example.week05_inclass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FragmentInfo extends Fragment implements FragmentCallBacks {
    MainActivity main;
    TextView txtId;
    TextView txtName;
    TextView txtClass;
    TextView txtPoint;
    Button btnFirst;


    Button btnPrevious;
    Button btnLast;
    Button btnNext;

    public static FragmentInfo newInstance(String arg) {
        FragmentInfo fragment = new FragmentInfo();
        Bundle args = new Bundle();
        args.putString("info", arg);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MainCallBacks)) {
            throw new IllegalStateException( "Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout_info = inflater.inflate(R.layout.fragment_info,null);
        txtId=(TextView) layout_info.findViewById(R.id.txtId);
        txtName=(TextView) layout_info.findViewById(R.id.txtName);
        txtClass=(TextView) layout_info.findViewById(R.id.txtClass);
        txtPoint=(TextView) layout_info.findViewById(R.id.txtPoint);

        btnFirst=(Button) layout_info.findViewById(R.id.btnFirst);

        btnPrevious=(Button) layout_info.findViewById(R.id.btnPre);
        btnPrevious.setEnabled(false);

        btnPrevious.setAlpha(0.5f);

        btnLast = (Button) layout_info.findViewById(R.id.btnLast);

        btnNext=(Button) layout_info.findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setAlpha(0.5f);


        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("btnFirst");
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("btnPre");
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("btnNext");
            }
        });
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                main.onMsgFromFragToMain("btnLast");
            }
        });


        try {
            Bundle arguments = getArguments();
        }
        catch (Exception e) {
            Log.e("RED BUNDLE ERROR – ", "" + e.getMessage());
        }
        return layout_info;
    }

    @Override
    public void onMsgFromMainToFragment(String id, String fullName, String classId, String point,int position ) {
        txtId.setText(id);
        txtName.setText(fullName);
        txtClass.setText(classId);
        txtPoint.setText(point);

        if (position==0) {
            btnFirst.setEnabled(false);
            btnFirst.setAlpha(0.5f);

            btnPrevious.setEnabled(false);
            btnPrevious.setAlpha(0.5f);

            btnLast.setEnabled(true);
            btnLast.setAlpha(1);

            btnNext.setEnabled(true);
            btnNext.setAlpha(1);

        }
        else if (position==main.fragmentList.id.length-1) {
            btnLast.setEnabled(false);
            btnLast.setAlpha(0.5f);

            btnNext.setEnabled(false);
            btnNext.setAlpha(0.5f);

            btnFirst.setEnabled(true);
            btnFirst.setAlpha(1);

            btnPrevious.setEnabled(true);
            btnPrevious.setAlpha(1);
        }
        else {
            btnFirst.setEnabled(true);
            btnFirst.setAlpha(1);

            btnPrevious.setEnabled(true);
            btnPrevious.setAlpha(1);

            btnLast.setEnabled(true);
            btnLast.setAlpha(1);

            btnNext.setEnabled(true);
            btnNext.setAlpha(1);

        }
    }

    @Override
    public void onMsgFromMainToFragment(String Sender) {

    }
}