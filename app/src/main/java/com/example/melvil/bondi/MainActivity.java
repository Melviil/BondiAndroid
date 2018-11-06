package com.example.melvil.bondi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call functions and ask the location permissions
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        // Try is necessarry as the location persmissions can be denied
        try{

            Button button = (Button)findViewById(R.id.positionbutton);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //Create the Location service context
                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    //Get the last known location
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    // Affichaaaaaaage
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    TextView latitudeText = findViewById(R.id.latitudeTextView);
                    latitudeText.setText("Latitude : " + Double.toString(latitude));
                    TextView longitudeText = findViewById(R.id.longitudeTextView);
                    longitudeText.setText("Longitude : " + Double.toString(latitude));


                }
            });
        // If permissions denied, print the exception for the bebug
        }catch(SecurityException e ){
            Log.d("e", e.toString());
        }

    }
    private Boolean permissionsGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public boolean checkLocationPermission()
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
}