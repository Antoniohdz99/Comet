package com.example.ricky.comet.inicio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ricky.comet.MenuP.Noticias;
import com.example.ricky.comet.MenuP.Pedidos;
import com.example.ricky.comet.MenuP.Perfil;
import com.example.ricky.comet.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity implements OnMapReadyCallback {


    private FirebaseAuth mAuth;
    BottomNavigationView navigationView;

    Noticias noticias = new Noticias();
    Pedidos pedidos = new Pedidos();
    Perfil perfil = new Perfil();
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        navigationView = findViewById(R.id.menu);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapView);
        mapFragment.getMapAsync(Principal.this);


        mAuth = FirebaseAuth.getInstance();


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Perfil: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragmento, perfil).commit();
                        return true;
                    }
                    case R.id.Pedidos: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragmento, pedidos).commit();
                        return true;
                    }
                    case R.id.Noticias: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragmento, noticias).commit();
                        return true;
                    }

                    default: {
                        return false;
                    }
                }


            }
        });

    }

    @Override
    public void onMapReady(GoogleMap map2) {
        this.map = map2;
        LatLng sydney = new LatLng(-33.867, 151.206);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        Marker melbourne = map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("hola bebe")
                .position(sydney));
        melbourne.showInfoWindow();
    }

}
