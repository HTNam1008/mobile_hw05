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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInfo#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentInfo extends Fragment implements FragmentCallBacks {
    MainActivity main;
    TextView txtId;
    TextView txtName;
    TextView txtClass;
    TextView txtPoint;
    Button btnFirst;

    public Button btnPrevious;

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
        // Inflate the layout for this fragment

        View layout_info = inflater.inflate(R.layout.fragment_info,null);
        txtId=(TextView) layout_info.findViewById(R.id.txtId);
        txtName=(TextView) layout_info.findViewById(R.id.txtName);
        txtClass=(TextView) layout_info.findViewById(R.id.txtClass);
        txtPoint=(TextView) layout_info.findViewById(R.id.txtPoint);
        btnFirst=(Button) layout_info.findViewById(R.id.btnFirst);
        btnPrevious=(Button) layout_info.findViewById(R.id.btnPre);
        btnPrevious.setEnabled(false);

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                main.onMsgFromFragToMain("btnFirst",0);

            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("btnPre",0);
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
    public void onMsgFromMainToFragment(String id, String fullName, String classId, String point) {
        txtId.setText(id);
        txtName.setText(fullName);
        txtClass.setText(classId);
        txtPoint.setText(point);
        if (main.isDisable){
            btnPrevious.setEnabled(false);
        }
        else{
            btnPrevious.setEnabled(true);
        }

    }
}