package com.example.yashnanavati.catiescloset.Adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashnanavati.catiescloset.Model.PackageDataModel;
import com.example.yashnanavati.catiescloset.R;

import java.util.ArrayList;

/**
 * Created by Game of Threads
 */

public class DonationStatsAdapter extends ArrayAdapter<PackageDataModel> implements View.OnClickListener {
    private ArrayList<PackageDataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        ImageView info;
    }

    // Declare data and context for Donation Statistics
    public DonationStatsAdapter(ArrayList<PackageDataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    // Inflates the data model when on click donation statistics
    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        PackageDataModel dataModel=(PackageDataModel)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Location: " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    // Get the view based on the position, from which convertView, in which view group
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PackageDataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;


        // Declare view if it is the first time being called
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name_one);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.version_number);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        // Declare the view when it is not the first time being called by converting it to view
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        // Generates animation to illustrate donation statistic models
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        // Binding view holder to donation statistics
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtType.setText(dataModel.getType());
        viewHolder.txtVersion.setText(dataModel.getVersion_number());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
