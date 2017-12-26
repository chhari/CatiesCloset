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

public class DeliveryActivity extends AppCompatActivity {

    private Button callbutton;
    //this activity handles the delivery option of the user to the donation center
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        callbutton = (Button) findViewById(R.id.callbutton);
        //The call us button is used to dial the catie closet phone number for any questions
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = "978-957-2200"; //CATIE'S CLOSET PHONE NO.
                Intent phoneCallMom = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNo)); // It dials the number automatically for the user
                startActivity(phoneCallMom); //call activity begins in new screen

            }
        });
    }

}
