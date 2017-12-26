package com.example.yashnanavati.catiescloset.DonationModule;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yashnanavati.catiescloset.Fragments.ProfileRecyclerViewFragment;
import com.example.yashnanavati.catiescloset.Fragments.ScrollFragment;
import com.example.yashnanavati.catiescloset.Fragments.StatisticsFragment;
import com.example.yashnanavati.catiescloset.Login.MainActivity;
import com.example.yashnanavati.catiescloset.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Game of Threads
 */

public class UserProfileActivity extends DrawerActivity {


    private static UserProfileActivity _this = null;


    //Changes the default username to the one that user inputs on the Settings page in the User Profile
    public void setUsername(){

        TextView username = (TextView) _this.findViewById(R.id.txtUsername);

        String name = (getPreferences(Context.MODE_PRIVATE).getString("username",null));


        if (username != null) {

            if (name != null) {

                username.setText("Hello, "+name);

            }
        }

    }


    //Calling the Drawer layout that is the page shown when the user clicks on the hamburger.
    public void changePage(int page){
        mViewPager.getViewPager().setCurrentItem(page);
        DrawerLayout mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.closeDrawer((View)findViewById(R.id.left_drawer));

    }


    //The function is currently obsolete
    @TargetApi(16)
    public void exit(){
        this.finishAffinity();
    }


    //Changes picture for the given path or if there is no path, then it changes to the deafult picture
    public void changePicture(String path){

        if(path.equals("")){
            path = getPreferences(Context.MODE_PRIVATE).getString("profilePic","");
            if(path.equals("")) {
                path = "http://gazettereview.com/wp-content/uploads/2016/03/facebook-avatar.jpg";
            }
        }

        final String myPath = path;

        //Showing colors when the pages are switched at the place of the user's picture
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                myPath);
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                myPath);
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                myPath);
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
    }

    //Gets the picture from the user's media and changes the picture on the User Profile page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if ( resultCode == Activity.RESULT_OK && null != data && requestCode==1) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = "file://"+cursor.getString(columnIndex);
            cursor.close();

            SharedPreferences shared = getPreferences(Context.MODE_PRIVATE);
            shared.edit().putString("profilePic", picturePath).commit();
            changePicture();


        }


    }


    public void changePicture(){
        changePicture("");
    }

    @BindView(R.id.matViewPager)
    MaterialViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setTitle("");
        ButterKnife.bind(this);

        _this = this;

        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {


            //Calling a specific fragment given the position.
            //These fragments correspond to pages of the User Profile, which are Profile, Settings, Statistics
            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return ProfileRecyclerViewFragment.newInstance();
                    case 1:
                        return ScrollFragment.newInstance();
                    case 2:
                        return StatisticsFragment.newInstance();
                    default:
                        return ProfileRecyclerViewFragment.newInstance();
                }
            }

            //Specifies the number of the tabbed pages on the User Profile
            @Override
            public int getCount() {
                return 3;
            }


            //Specifies the page title for each tabbed page
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Profile";
                    case 1:
                        return "Edit Profile";
                    case 2:
                        return "Statistics";
                }
                return "";
            }
        });

        changePicture();

        //Limits the number of tabs you have
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        TextView username = (TextView) findViewById(R.id.txtUsername);

        setUsername();

        if (username != null) {


            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    //Toast.makeText(getApplicationContext(), "Thank you for donating!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnEditProfile = (Button) findViewById(R.id.btnEditProfile);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(1);
            }
        });

        Button btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

}
