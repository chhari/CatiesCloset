package com.example.yashnanavati.catiescloset.Adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.yashnanavati.catiescloset.R;
/**
 * Created by Game of Threads
 */


public class Page1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Fragment for each home page
        View view = inflater.inflate(R.layout.frag_two, container, false);
        final RelativeLayout rel = (RelativeLayout) view.findViewById(R.id.reId);
        Button button = (Button) view.findViewById(R.id.button);
        final VideoView vid=(VideoView)view.findViewById(R.id.video);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
