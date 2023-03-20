package edu.northeastern.numad23sp_bolangyu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.w3c.dom.Text;

public class Location extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    private TextView textlatitude;
    private TextView textlongitude;
    private TextView textdistance;
    private Button reset;
    private FusedLocationProviderClient fusedLocationClient;
    private android.location.Location currentLocation;
    private android.location.Location previousLocation;
    private double totalDistanceTraveled = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        textlatitude = findViewById(R.id.text_latitude);
        textlongitude = findViewById(R.id.text_longitude);
        textdistance = findViewById(R.id.text_distance);
        reset = findViewById(R.id.button_reset);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        // Request location updates
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                onLocationChanged(locationResult.getLastLocation());
            }
        }, null);
    }

    @Override
    public void onLocationChanged(@NonNull android.location.Location location) {
        // Update the current and previous location variables
        previousLocation = currentLocation;
        currentLocation = location;

        // Update the total distance traveled
        if (previousLocation != null) {
            float[] results = new float[1];

            android.location.Location.distanceBetween(previousLocation.getLatitude(),
                    previousLocation.getLongitude(),
                    currentLocation.getLatitude(),
                    currentLocation.getLongitude(),
                    results);
            float distanceInMeters = results[0];
            totalDistanceTraveled += distanceInMeters / 1000.0; // convert to km
        }


        textlatitude.setText("Latitude: " + currentLocation.getLatitude());
        textlongitude.setText("Longitude: " + currentLocation.getLongitude());
        textdistance.setText("Total distance traveled: " + totalDistanceTraveled + " km");

        // Show or hide the reset button based on whether there is any distance traveled
        if (totalDistanceTraveled > 0) {
            reset.setVisibility(View.VISIBLE);
        } else {
            reset.setVisibility(View.GONE);
        }
    }

    // Reset the total distance traveled when the reset button is clicked
    public void resetDistanceTraveled(View view) {
        totalDistanceTraveled = 0.0;
        textdistance.setText("Total Distance Traveled: "+totalDistanceTraveled+" km");
        reset.setVisibility(View.GONE);
    }

}