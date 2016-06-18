package edm.kassimentz.googledirections;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 630910144 on 18/06/16.
 */
public interface MyInterfaceRetrofit {

    @GET("maps/api/directions/json")
    Call<Retorno> searchPositions(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key") String key
    );

    //https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=YOUR_API_KEY
    //base https://maps.googleapis.com
    //endpoint maps/api/directions/json
    //querys origin=Toronto&destination=Montreal&key=YOUR_API_KEY

}
