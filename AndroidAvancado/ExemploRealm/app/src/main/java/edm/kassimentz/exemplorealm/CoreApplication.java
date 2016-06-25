package edm.kassimentz.exemplorealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by 630910144 on 25/06/16.
 */
public class CoreApplication extends Application{

    public Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        //usar essa linha ate o app estar com o banco 100%.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        //quando o banco estiver ok, usar assim:
        //RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();

    }
}
