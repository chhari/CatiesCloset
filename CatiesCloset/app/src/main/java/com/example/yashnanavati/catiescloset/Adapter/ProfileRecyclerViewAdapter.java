package com.example.yashnanavati.catiescloset.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yashnanavati.catiescloset.DonationStats.DonationStatsActivity;
import com.example.yashnanavati.catiescloset.Model.Donations;
import com.example.yashnanavati.catiescloset.R;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.util.List;

/**
 * Created by Game of Threads
 */

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder> {

    List<Object> contents;
    private final OnItemClickListener listener;
    Context context;
    private int countItems = 0;

    //Declaring the cards that are used for User Profile UI
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    static final int TYPE_NEW = 2;
    static final int TYPE_COUNT = 3;

    //Constructor of the Class
    public ProfileRecyclerViewAdapter(List<Object> contents, FragmentActivity activity, OnItemClickListener listener) {
        this.contents = contents;
        this.listener = listener;
        this.context = activity;
    }

    //Defining which card to use at what time
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            case 1:
                return TYPE_NEW;
            case 2:
                return TYPE_CELL;
            case 3:
                return TYPE_COUNT;
            default:
                return TYPE_COUNT;
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Object item);
    }

    //Using ViewHolder to describe the buttons for sharing info on Facebook and Twitter and other social media, and imageviews for packages (complete and incomplete)
    static class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnShareFb;
        private Button btnShareTw;
        ShareDialog shareDialog;
        private ImageView imgComplete;
        private ImageView imgIncomplete;
        private TextView help_count;


        public ViewHolder(final View itemView) {
            super(itemView);
            final String sharedString = "I donated packages to Catie's Closet!";
            shareDialog = new ShareDialog((Activity) itemView.getContext());
            //Using Facebook API to provide sharing possibility to the user on its page on Facebook
            btnShareFb = (Button) itemView.findViewById(R.id.btnShareFb);
            if (btnShareFb != null)
                btnShareFb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Facebook Clicked", Toast.LENGTH_SHORT).show();

                        ShareLinkContent content = new ShareLinkContent.Builder()
                                .setContentUrl(Uri.parse("http://catiescloset.org"))
                                .setQuote(sharedString)
                                .build();
                        shareDialog.show(content);

                    }
                });
            //Using Twitter API to provide the possibility to share on user's Twitter page
            btnShareTw = (Button) itemView.findViewById(R.id.btnShareTw);
            if (btnShareTw != null)
                btnShareTw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Twitter Clicked", Toast.LENGTH_SHORT).show();
                        TweetComposer.Builder builder = new TweetComposer.Builder(v.getContext())
                                .text(sharedString);
                        builder.show();
                    }
                });
            //Using intents, provide the possbility to share in other social media
            Button btnShare = (Button) itemView.findViewById(R.id.btnShare);
            if (btnShare != null)
                btnShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, sharedString);
                        sendIntent.setType("text/plain");
                        itemView.getContext().startActivity(sendIntent);
                    }
                });
            //Describing the action performed after clicking the image of complete package.
            //Using intent to call DonationStatsActivity
            imgComplete = (ImageView) itemView.findViewById(R.id.imgComplete);
            if (imgComplete != null)
                imgComplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(itemView.getContext(), DonationStatsActivity.class);
                        i.putExtra("status", true);
                        itemView.getContext().startActivity(i);
                    }
                });

            //Describing the action performed after clicking the image of incomplete package.
            //Using intent to call DonationStatsActivity
            imgIncomplete = (ImageView) itemView.findViewById(R.id.imgIncomplete);
            if (imgIncomplete != null)
                imgIncomplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(itemView.getContext(), DonationStatsActivity.class);
                        i.putExtra("status", false);
                        itemView.getContext().startActivity(i);
                    }
                });


            help_count = (TextView) itemView.findViewById(R.id.help_count);

        }


        public void bind(final Object item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }


    //Returns the size of the contents
    @Override
    public int getItemCount() {
        return contents.size();
    }


    //Inflating the layout for each specific card on a User Profile page
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        switch (viewType) {
            //The card with the badges
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_badges, parent, false);

                return new ViewHolder(view) {
                };
            }
            //The card with the packages
            case TYPE_NEW: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_packages, parent, false);
                return new ViewHolder(view) {
                };
            }
            //The card with the sharing
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_share, parent, false);
                return new ViewHolder(view) {
                };
            }
            //The card with the number of children helped
            case TYPE_COUNT: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_count, parent, false);
                return new ViewHolder(view) {
                };
            }
        }
        return null;
    }

    //Counts the number of items the user donated taking the records from the database
    //Also counts the number of children helped that is displayed on the last card on the User Profile page
    public int getDataFromDb(final ViewHolder holder) {
        FirebaseDatabase.getInstance().getReference().child("donations").child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".", "|"))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (final DataSnapshot postDataSnapshot : dataSnapshot.getChildren()) {
                                Donations d = postDataSnapshot.getValue(Donations.class);
                                if (null != d) {
                                    if (d.getPkg().isDelivered()) {
                                        countItems += d.getPkg().getNoOfItems();
                                        Log.d("***** COUNT EACH", String.valueOf(d.getPkg().getNoOfItems()));
                                    }
                                }
                            }
                            holder.help_count.setText(String.valueOf(countItems/2));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        Log.d("***** COUNT ITEMS", String.valueOf(countItems));
        return countItems;
    }



    //According to the position, gives the order of cards that should be shown on the page.
    //Binds viewholder to that position.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_NEW:
                break;
            case TYPE_CELL:
                break;
            case TYPE_COUNT:
                getDataFromDb(holder);
                break;
        }
    }


}
