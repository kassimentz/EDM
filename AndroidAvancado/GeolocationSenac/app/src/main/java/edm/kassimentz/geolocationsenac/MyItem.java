package edm.kassimentz.geolocationsenac;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by 630910144 on 11/06/16.
 */
public class MyItem implements ClusterItem {

    private final LatLng mPosition;

    public MyItem(double lat, double lng) {
        this.mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
