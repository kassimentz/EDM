package edm.kassimentz.geolocationsenac;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeAct extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Marker markerMyLocation;

    private ClusterManager mClusterManager;
    ClusterItem first = null;
    ClusterItem second = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
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

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this) //ConnectionsCallBack
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onConnected(Bundle bundle) {
        //o client conectou com o play service

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        /*PolylineOptions polOpt = new PolylineOptions();
        polOpt.add(new LatLng(-30.032564, -51.227706));
        polOpt.add(new LatLng(-30.035556, -51.227968));
        polOpt.color(Color.BLUE);
        polOpt.width(3);
        mMap.addPolyline(polOpt);

        CircleOptions circle = new CircleOptions();
        circle.center(new LatLng(-29.973658, -51.194998));
        circle.fillColor(Color.BLUE);
        circle.strokeColor(Color.BLACK);
        circle.radius(2000);
        mMap.addCircle(circle);*/

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        /*for (LatLng posicao : posicoes){
            MarkerOptions mOpt = new MarkerOptions();
            mOpt.position(posicao);
            mMap.addMarker(mOpt);
        }*/

        mClusterManager = new ClusterManager<MyItem>(this, mMap);
        /*for(LatLng posicao : posicoes){
            MyItem offSetItem = new MyItem(posicao.latitude, posicao.longitude);
            mClusterManager.addItem(offSetItem);

        }*/

        Call<Posicoes> call = ((CursoApplication) getApplication()).service.searchPositions();
        call.enqueue(new Callback<Posicoes>() {
            @Override
            public void onResponse(Call<Posicoes> call, Response<Posicoes> response) {
                mClusterManager = new ClusterManager<MyItem>(HomeAct.this, mMap);
                for (Posicao posicao : response.body().posicoes) {
                    MyItem offsetItem = new MyItem(posicao.latitude, posicao.longitude);
                        mClusterManager.addItem(offsetItem);

                    }
                mMap.setOnCameraChangeListener(mClusterManager);
                mMap.setOnMarkerClickListener(mClusterManager);
                }

            @Override
            public void onFailure(Call<Posicoes> call, Throwable t) {
                Log.e("CURSO", "Pepino: " + t.getLocalizedMessage());
            }
        });


        mMap.setOnCameraChangeListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener() {
            @Override
            public boolean onClusterItemClick(ClusterItem clusterItem) {

                if (first == null) {
                    first = clusterItem;
                } else {
                    second = clusterItem;
                }

                PolylineOptions polOpt = new PolylineOptions();
                if (first != null && second != null) {
                    polOpt.add(new LatLng(first.getPosition().latitude, first.getPosition().longitude));
                    polOpt.add(new LatLng(second.getPosition().latitude, second.getPosition().longitude));
                    polOpt.color(Color.BLUE);
                    polOpt.width(3);
                    mMap.addPolyline(polOpt);
                }

                return false;
            }

        });

        //showMe();
    }

    private void showMe() {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        updateLocations(mLastLocation);
    }

    private void updateLocations(Location mLastLocation) {
        LatLng eu = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        if(markerMyLocation == null){
            markerMyLocation = mMap.addMarker(new MarkerOptions().position(eu).title("Estou aqui"));
        }else{
            markerMyLocation.setPosition(eu);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(eu, 16));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showMe();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

        //conexao suspensa
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // falha na comunicacao
    }

    @Override
    public void onLocationChanged(Location location) {
        //updateLocations(location);
    }
}
