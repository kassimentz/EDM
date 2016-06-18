package edm.kassimentz.googledirections;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private ClusterManager mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



    }

    public void showRota(View v){

        EditText OrigemTxt = (EditText) findViewById(R.id.txtOrigem);
        EditText DestinoTxt  = (EditText) findViewById(R.id.txtDestino);;

        String origem = OrigemTxt.getText().toString();
        String destino = DestinoTxt.getText().toString();
        String key = "AIzaSyBBw-jXywSKJZSnuV8NAKQO8VJM-uy72Yk";
        Call<Retorno> call = ((HomeApplication) getApplication()).service.searchPositions(origem, destino, key);

        call.enqueue(new Callback<Retorno>() {
            @Override
            public void onResponse(Call<Retorno> call, Response<Retorno> response) {
                for (Steps step : response.body().routes.get(0).legs.get(0).steps) {

                    PolylineOptions polOpt = new PolylineOptions();

                    polOpt.add(new LatLng(step.start_location.lat, step.start_location.lng));
                    polOpt.add(new LatLng(step.end_location.lat, step.end_location.lng));
                    polOpt.color(Color.BLUE);
                    polOpt.width(3);
                    mMap.addPolyline(polOpt);
                }

            }

            @Override
            public void onFailure(Call<Retorno> call, Throwable t) {
            }
        });

        mMap.setOnCameraChangeListener(mClusterManager);
    }

}
