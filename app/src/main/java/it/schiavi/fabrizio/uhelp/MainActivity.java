package it.schiavi.fabrizio.uhelp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import it.schiavi.fabrizio.uhelp.utils.Conf;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView mTextMessage;
    private double lat;
    private double lon;

    private FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    ft.replace(R.id.container, new HomeFragment());
                    ft.commit();
                    return true;

                case R.id.navigation_help:
                    mTextMessage.setText(R.string.title_help);
                    return true;

                case R.id.navigation_contacts:
                    mTextMessage.setText(R.string.title_contatti);
                    return true;

                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profilo);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ft.replace(R.id.content, new HomeFragment());
        ft.commit();

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if ( ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("PERMISSION", "NON dati");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Conf.MIN_TIME, Conf.MIN_DIST, this);
        // Legge la posizione attuale e la imposta come iniziale
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public void setNewLocation(Location location){
        lat = location.getLatitude();
        lon = location.getLongitude();
    }

    private void showMessage(String message) {
        Toast t = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        t.setGravity(Gravity.TOP, 0, 0);
        t.show();
    }

    // IMPLEMENTAZIONE METODI DELL'INTERFACCIA: LOCATION MANAGER
    @Override
    public void onLocationChanged(Location location) {

        setNewLocation(location);
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String s) {
        showMessage("Ricevitore GPS Abilitato.");

    }

    @Override
    public void onProviderDisabled(String s) {
        showMessage("Ricevitore GPS Disabilitato \nAbilitare nella sezione localizzazione.");

    }
}
