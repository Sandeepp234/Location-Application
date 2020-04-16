package locationapplication.com.locationapplication;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
   private  GoogleMap googleMap;
Double lat;
Double lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();

        lat = intent.getExtras().getDouble("tvlat");
        lng = intent.getExtras().getDouble("tvlng");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       this.googleMap=googleMap;
        LatLng latLng = new LatLng(lat,lng);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("My Place"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
