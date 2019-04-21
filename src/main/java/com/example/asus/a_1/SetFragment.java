package com.example.asus.a_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;

public class SetFragment extends Fragment {

    private static final String TAG = "SetFragment";

    public TextView set;
//    public Button createDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        set = (TextView) view.findViewById(R.id.set);
//        createDatabase = (Button) view.findViewById(R.id.create_database);
//        createDatabase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LitePal.getDatabase();
//            }
//        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
}
