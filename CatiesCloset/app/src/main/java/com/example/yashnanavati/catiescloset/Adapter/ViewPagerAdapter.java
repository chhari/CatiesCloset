package com.example.yashnanavati.catiescloset.Adapter;
/**
 * Created by Game of Threads
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Adapter for the home page presenting each fragment

    @Override
    public Fragment getItem(int position) {
        if(position==1){
            return new Page1Fragment();
        }
        if(position==2){
            return new Page2Fragment();
        }
        if(position==3){
            return new PageFragment();
        }

        return new Page3Fragment();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
