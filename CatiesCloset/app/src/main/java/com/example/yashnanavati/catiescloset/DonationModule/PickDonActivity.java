package com.example.yashnanavati.catiescloset.DonationModule;

/**
 * Created by Game of Threads
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yashnanavati.catiescloset.Model.USPSLabel;
import com.example.yashnanavati.catiescloset.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PickDonActivity extends AppCompatActivity {

    protected EditText nameText;

    protected EditText emailText;

    protected EditText addressText;

    protected EditText cityZipText;

    protected EditText bag1Text;

    protected EditText bag2Text;

    protected EditText bag3Text;

    protected EditText bag4Text;

    protected EditText bag5Text;

    protected EditText bag6Text;

    protected EditText bag7Text;

    protected EditText bag8Text;

    protected EditText bag9Text;

    protected EditText bag10Text;


    private  List<String> weights = new ArrayList<>();
    // This activity is called when user has less than 10 bags to be sent and fills the form to ask for usps label
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_don);

        nameText = (EditText) findViewById(R.id.signup_input_name);
        addressText = (EditText) findViewById(R.id.signup_input_address);
        emailText = (EditText) findViewById(R.id.signup_input_email);
        cityZipText = (EditText) findViewById(R.id.signup_input_Cityzip);
        bag1Text = (EditText) findViewById(R.id.signup_input_bag1);
        bag2Text = (EditText) findViewById(R.id.signup_input_bag2);
        bag3Text = (EditText) findViewById(R.id.signup_input_bag3);
        bag4Text = (EditText) findViewById(R.id.signup_input_bag4);
        bag5Text = (EditText) findViewById(R.id.signup_input_bag5);
        bag6Text = (EditText) findViewById(R.id.signup_input_bag6);
        bag7Text = (EditText) findViewById(R.id.signup_input_bag7);
        bag8Text = (EditText) findViewById(R.id.signup_input_bag8);
        bag9Text = (EditText) findViewById(R.id.signup_input_bag9);
        bag10Text = (EditText) findViewById(R.id.signup_input_bag10);

        Button submit = (Button) findViewById(R.id.btn_signup);
        //On filling of the form by the user and clicking submit button the info is saved in database with the user info
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weights.add(bag1Text.getText().toString());
                weights.add(bag2Text.getText().toString());
                weights.add(bag3Text.getText().toString());
                weights.add(bag4Text.getText().toString());
                weights.add(bag5Text.getText().toString());
                weights.add(bag6Text.getText().toString());
                weights.add(bag7Text.getText().toString());
                weights.add(bag8Text.getText().toString());
                weights.add(bag9Text.getText().toString());
                weights.add(bag10Text.getText().toString());
                //The code which enters the information about each bag weight into the database for generation of the usps label
                FirebaseDatabase.getInstance()
                        .getReference().child("uspsLabel").child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".", "|"))
                        .push().setValue(new USPSLabel(new Date(), nameText.getText().toString(), emailText.getText().toString(), addressText.getText().toString(), cityZipText.getText().toString(), weights));
                Toast.makeText(getApplicationContext(), "Your request has been submitted, We will contact you soon", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
