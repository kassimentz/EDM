package edm.kassimentz.geolocationsenac;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 630910144 on 18/06/16.
 */
public interface MyInterfaceRetrofit {
    @GET("v2/576539791100005a0ea92a52")
    Call<Posicoes> searchPositions();
}
