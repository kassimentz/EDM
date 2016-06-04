package edm.kassimentz.receiversms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 630910144 on 04/06/16.
 */
public class StartedService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("SERVICE", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SERVICE", "onStartCommand");
        //setar um tempo para que o service finalize
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopSelf();
        return 1;

        //server para falar: caso o SO android mate esse service, aqui fala como ele recria o service:
        // se recria reutilizando a intent ou nao e se vai recriar ou nao.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("SERVICE", "onDestroy");
    }
}
