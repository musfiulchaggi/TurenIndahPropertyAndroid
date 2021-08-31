package com.example.turenindahproperty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;

public class Detail extends AppCompatActivity {

    private TextView harga;
    private TextView lblt;
    private TextView description;
    private TextView alamat;
    private ImageView images;

    private MapboxMap mapboxMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_detail);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);

        lblt = (TextView) findViewById(R.id.lblt);
        description = (TextView) findViewById(R.id.description);
        harga = (TextView) findViewById(R.id.harga);
        alamat = (TextView) findViewById(R.id.alamat);
        images = (ImageView) findViewById(R.id.image);

        data_property property = (data_property) getIntent().getSerializableExtra("data");
        if(property!=null){
            description.setText(property.getDeskripsi());
            alamat.setText(property.getAlamat());
            lblt.setText("LB "+property.getLb()+" / LT "+property.getLt());
            Glide.with(this).load(property.getImage()).into(images);

            SupportMapFragment mapFragment;
            if (savedInstanceState == null){
                final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                MapboxMapOptions options = MapboxMapOptions.createFromAttributes(this, null);
                options.camera(new CameraPosition.Builder()
                        .target(new LatLng(new LatLng(Double.parseDouble(property.getMap_lat()), Double.parseDouble(property.getMap_long()))))
                        .zoom(9)
                        .build());
                mapFragment = SupportMapFragment.newInstance(options);

                transaction.add(R.id.mapView, mapFragment, "com.mapbox.map");
                transaction.commit();
            } else {
                mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag("com.mapbox.map");
            }

            if (mapFragment != null) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull MapboxMap mapboxMap) {
                        Detail.this.mapboxMap = mapboxMap;

                        MarkerOptions options = new MarkerOptions();
                        options.title(property.getAlamat());
                        options.position(new LatLng(Double.parseDouble(property.getMap_lat()), Double.parseDouble(property.getMap_long())));
                        mapboxMap.addMarker(options);

                        mapboxMap.setStyle(Style.MAPBOX_STREETS);
                    }
                });
            }
        }

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.email){
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{property.getAgent_email()});

                    //need this to prompts email client only
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                }

                if (id == R.id.phone){
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+property.getAgent_phone()));
                    startActivity(intent);
                }

                if (id == R.id.whatsapp){
                    boolean installed = appInstalledOrNot("com.whatsapp");

                    if (installed){
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+property.getAgent_whatsapp()));
                        startActivity(intent);
                    }else{
                        Toast.makeText(Detail.this, "Whatsapp not installed on your device", Toast.LENGTH_SHORT);
                    }
                }
                return true;
            }
        });
    }

    private boolean appInstalledOrNot(String url){
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url, packageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }

        return app_installed;
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, Detail.class);
    }
}