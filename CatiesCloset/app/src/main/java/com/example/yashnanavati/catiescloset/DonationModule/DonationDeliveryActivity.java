package com.example.yashnanavati.catiescloset.DonationModule;

/**
 * Created by Game of Threads
 */


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.yashnanavati.catiescloset.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DonationDeliveryActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


    GoogleMap mGoogleMap;
    GoogleApiClient mGoogleApiClient;
    LocationManager locationManager;
    android.location.LocationListener locationListener;
    private int box_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent pick = getIntent();
        //To hceck the box count , and enable the correct view
        box_count = Integer.parseInt(pick.getStringExtra("pickup"));

        if (googleServicesAvailable()) {
            Toast.makeText(this, "Perfect!!!", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_donation_delivery);
            //Initialising the Map Fragment
            initMap();
        } else {
            // No Google Maps Layout
            Toast.makeText(this, "Nope not Perfect!!!", Toast.LENGTH_LONG).show();

        }
    }

    //Method Initialising the Map Fragment
    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }


    //Checking Th google Services Availability
    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }


    //Getting the location of the user
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
        LatLng center = new LatLng(42.664376, -71.323779);
        mGoogleMap.addMarker(new MarkerOptions().position(center).icon(bitmapDescriptor).title("Donation Center"));
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(center, 8);
        mGoogleMap.animateCamera(update);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                // Get the userLocation
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

                double lat1 = location.getLatitude();
                double lng1 = location.getLongitude();
                mGoogleMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        /******************************************/
        try {
            LocationRequest mNewLocationRequest = new LocationRequest();
            mNewLocationRequest.setInterval(10);
            mNewLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mNewLocationRequest.setFastestInterval(10);
            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
            LocationCallback mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    System.out.println(" *** " + locationResult.getLastLocation());
                    System.out.println(" *** " + DateFormat.getTimeInstance().format(new Date()));
                    try {
                        LatLng myLoc = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
                        //Marking the location on the Map
                        mGoogleMap.addMarker(new MarkerOptions().position(myLoc).title("User Location"));
                        CameraUpdate newupdate = CameraUpdateFactory.newLatLngZoom(myLoc, 10);
                        mGoogleMap.animateCamera(newupdate);
                        System.out.println(" *** " + new Geocoder(getApplicationContext()).getFromLocation(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude(), 1).get(0));
                    } catch (SecurityException | IOException ex) {
                        ex.printStackTrace();
                    }
                    super.onLocationResult(locationResult);
                }
            };
            System.out.println(" *** Calling Fused Client...");
            mFusedLocationClient.requestLocationUpdates(mNewLocationRequest,
                    mLocationCallback, Looper.myLooper());
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();


    }

    private void updateMyCurrentLoc(Location location) {


        if (location != null) {

            // other codes to get the address and display
            Toast.makeText(getBaseContext(), "provider used : ", Toast.LENGTH_SHORT).show();   //testing purpose
        } else {
            String str = "No location found";
            Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
        }

    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);


    }

    Marker marker;

    public void myClick(View view) throws IOException {
        if(box_count==1){
            Intent i = new Intent(getApplicationContext(), TruckPickActivity.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(getApplicationContext(), PickDonActivity.class);
            startActivity(i);
        }
    }

    public void pickUp(View view) throws IOException {
        Intent i = new Intent(getApplicationContext(), DeliveryActivity.class);
        startActivity(i);

    }


    ArrayList<Marker> markers = new ArrayList<Marker>();
    static final int POLYGON_POINTS = 5;
    Polygon shape;

    //setting a marker
    private void setMarker(String locality, double lat, double lng) {
        if (markers.size() == POLYGON_POINTS) {
            removeEverything();
        }

        MarkerOptions options = new MarkerOptions()
                .title(locality)
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .position(new LatLng(lat, lng))
                .snippet("I am Here");

        markers.add(mGoogleMap.addMarker(options));

        if (markers.size() == POLYGON_POINTS) {
            drawPolygon();
        }


    }

    private void drawPolygon() {
        PolygonOptions options = new PolygonOptions()
                .fillColor(0x330000FF)
                .strokeWidth(3)
                .strokeColor(Color.RED);

        for (int i = 0; i < POLYGON_POINTS; i++) {
            options.add(markers.get(i).getPosition());
        }
        shape = mGoogleMap.addPolygon(options);

    }

    private void removeEverything() {
        for (Marker marker : markers) {
            marker.remove();
        }
        markers.clear();
        shape.remove();
        shape = null;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    LocationRequest mLocationRequest;

    //onConnnected
    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    //getting if the location is changed
    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            Toast.makeText(this, "Cant get current location", Toast.LENGTH_LONG).show();
        } else {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
         }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
