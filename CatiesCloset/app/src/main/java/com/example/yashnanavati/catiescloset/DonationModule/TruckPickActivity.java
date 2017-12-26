package com.example.yashnanavati.catiescloset.DonationModule;

/**
 * Created by Game of Threads
 */


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yashnanavati.catiescloset.R;

public class TruckPickActivity extends AppCompatActivity {

    private Button callbutton;
    // This activity functions when there are more than 10 boxes being sent by the user for donation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_pick);

        callbutton = (Button) findViewById(R.id.callbutton);
        //On click of the call us button the catie's closet phone number is dialed in but user is given option to cancel or make the call
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = "978-957-2200"; //CATIE'S CLOSET PHONE NO.
                Intent phoneCallMom = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNo));
                startActivity(phoneCallMom);

            }
        });

    }

}
