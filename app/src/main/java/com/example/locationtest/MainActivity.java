package com.example.locationtest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 100;
    FusedLocationProviderClient fusedLocationClient;
    TextView longitude, latitude;
    Button getLocation;
    Button nextPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("CODE MULAIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        getLocation = findViewById(R.id.get_location_button);

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ANYINGGGGG");

                getLastLocation();
            }
        });


        nextPageButton = findViewById(R.id.next_page_button);
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Go to activity 2");
                openActivity2();
            }
        });
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            System.out.println("location object " + location);
                            if (location != null) {
                                latitude.setText(String.valueOf(location.getLatitude()));
                                longitude.setText(String.valueOf(location.getLongitude()));
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG", "Exception: " + e.getMessage());
                        }
                    });
        } else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void openActivity2() {
        System.out.println("OPEN ACTIVITY 2");
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}