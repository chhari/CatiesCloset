package com.example.yashnanavati.catiescloset.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yashnanavati.catiescloset.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Game of Threads
 */
public class StatisticsFragment extends Fragment {



    //Constructor of the class
    public static StatisticsFragment newInstance() {

        return new StatisticsFragment();
    }

    //Using MPAndroidChart API to create a graph
    //Initializing variables needed for API
    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistics, container, false);
        chart = (BarChart) v.findViewById(R.id.chart1);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        //Creating the graph using methods
        AddValuesToBARENTRY();

        AddValuesToBarEntryLabels();

        Bardataset = new BarDataSet(BARENTRY, "Number of Children Served");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        //Setting the color system for the graph
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);

        chart.animateY(3000);
        chart.setDescription("");

        return v;
    }


    //Setting up the bars
    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(4000f, 0));
        BARENTRY.add(new BarEntry(6000f, 1));
        BARENTRY.add(new BarEntry(13000f, 2));
        BARENTRY.add(new BarEntry(16000f, 3));
        BARENTRY.add(new BarEntry(24500f, 4));

    }

    //Adding labels to the graph
    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add("2012");
        BarEntryLabels.add("2013");
        BarEntryLabels.add("2014");
        BarEntryLabels.add("2015");
        BarEntryLabels.add("2016");

    }


}
