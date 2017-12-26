package com.example.yashnanavati.catiescloset.Adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yashnanavati.catiescloset.R;

/**
 *
 * Created by Game of Threads
 */


// Home Page Adapter that handles the home page fragments
public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {


    private Context context;

    public HomePageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.images.setBackgroundResource(R.drawable.home_image);
        //runEnterAnimation(holder.itemView, position);
        Log.d("Animation","I am in animation");
        holder.images.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable homeAnimation = (AnimationDrawable) holder.images.getBackground();
                homeAnimation.start();
            }
        });

    }

    private void runEnterAnimation(View view, int position) {
        view.animate()
                .rotation(360)
                .setDuration(2000)
                .start();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        ViewHolder(View itemView) {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.images);
        }

    }

}
