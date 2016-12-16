package com.example.ravi.basicmaps;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void searchPlaces(View view){
            if (view.getId() == R.id.search1){

                EditText editText= (EditText) findViewById(R.id.search);
                     String location  = editText.getText().toString().trim();
                List<Address> addressList = null;
                        if(location!=null || !location.equals("") )
                        {
                            Geocoder geocoder = new Geocoder(this);
                            try {
                                addressList = geocoder.getFromLocationName(location, 1);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Address address = addressList.get(0);
                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        }




            }




    }

    public void zoomCamera(View view){


          switch (view.getId()){



              case R.id.zoomin:

                 mMap.animateCamera(CameraUpdateFactory.zoomIn());


                  break;



                  case R.id.zoomout:

                      mMap.animateCamera(CameraUpdateFactory.zoomOut());

                      break;


              }



    }
    public void mapsType(View view) {


        switch (view.getId()) {

            case R.id.normaltype:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                break;


            case R.id.satelitetype:

                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                break;
            case R.id.Marker:


                LatLng latLng = new LatLng(17.509546, 78.508129);
                Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Hyderabad").snippet("population:10croes"));
                 mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                 mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                CircleOptions circleOptions=new CircleOptions();
                circleOptions.center(latLng);
                circleOptions.radius(20);
                circleOptions.strokeColor(Color.BLACK);
                circleOptions.fillColor(0x30ff0000);
                circleOptions.strokeWidth(2);
                 mMap.addCircle(circleOptions);







                break;
            case R.id.gps:

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
                mMap.setMyLocationEnabled(true);



                     break;



             }




    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

      //  LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
