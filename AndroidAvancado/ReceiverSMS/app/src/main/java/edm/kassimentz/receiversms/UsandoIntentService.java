package edm.kassimentz.receiversms;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 630910144 on 04/06/16.
 */
public class UsandoIntentService extends IntentService {

    public UsandoIntentService(){
        super("UsandoIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("INTENTSERVICE", "onHandleIntent");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("INTENTSERVICE", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("INTENTSERVICE", "onDestroy");
    }
}
