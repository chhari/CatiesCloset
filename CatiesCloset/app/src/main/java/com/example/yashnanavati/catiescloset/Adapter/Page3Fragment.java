package com.example.yashnanavati.catiescloset.Adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yashnanavati.catiescloset.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;
/**
 * Created by Game of Threads
 */


public class Page3Fragment extends Fragment {

    //Fragment for each home page

    //Ticker is for a count down showing the number of students
    private TickerView ticker1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_four, container, false);
        ticker1 = view.findViewById(R.id.ticker1);
        ticker1.setCharacterList(TickerUtils.getDefaultNumberList());
        ticker1.setText("50000");
        return view;
    }


}