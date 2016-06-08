package edm.kassimentz.geolocationsenac;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Cassio on 07/06/16.
 */
public class CursoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
