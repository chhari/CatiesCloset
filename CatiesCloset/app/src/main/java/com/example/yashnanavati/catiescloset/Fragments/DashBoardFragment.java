package com.example.yashnanavati.catiescloset.Fragments;

/**
 * Created by Game of Threads
 */


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yashnanavati.catiescloset.Adapter.ViewPagerAdapter;
import com.example.yashnanavati.catiescloset.R;

import java.util.Arrays;

import me.huseyinozer.TooltipIndicator;

public class DashBoardFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private Context context;
    private TooltipIndicator indicator;


    public DashBoardFragment() {
        // Required empty public constructor
    }

    public static DashBoardFragment newInstance(String param1, String param2) {
        DashBoardFragment fragment = new DashBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dash_board, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        indicator = (TooltipIndicator) view.findViewById(R.id.tooltip_indicator);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        context = getActivity();
        indicator.setupViewPager(viewPager);
        //indicator.setupViewPager(viewPager);

        indicator.setToolTipDrawables(Arrays.asList(
                ContextCompat.getDrawable(context, R.drawable.ind1),
                ContextCompat.getDrawable(context, R.drawable.ind2),
                ContextCompat.getDrawable(context, R.drawable.ind3),
                ContextCompat.getDrawable(context, R.drawable.ind4)

        ));

        return view;

    }

}
