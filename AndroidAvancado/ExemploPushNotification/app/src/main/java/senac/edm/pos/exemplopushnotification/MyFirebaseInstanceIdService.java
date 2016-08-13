package senac.edm.pos.exemplopushnotification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Cassio on 13/08/16.
 */
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    //google mandou sinal avisando que tem um novo token
    @Override
    public void onTokenRefresh() {
        //no app real, pegar o token e mandar para o service
        //XMPP manda notificacoes de um app para o FirebaseCloudMessage que envia para todos
        //usuarios do aplicativo
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("TESTEFCM", "token no service: " + token);
    }
}
