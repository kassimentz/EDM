package senac.edm.pos.exemplopushnotification;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by Cassio on 13/08/16.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> data = remoteMessage.getData();
        for (Map.Entry<String, String> entry : data.entrySet()){
            Log.e("TESTEFCM", "key: " + entry.getKey());
            Log.e("TESTEFCM", "value: " + entry.getValue());
        }
    }
}
