package proyecto.pokemon.service;

/**
 * Created by Carlos on 03/06/2016.
 */

import retrofit.RestAdapter;

/**
 * Para los Servicios
 */
public class ApiImplementation {

    private static RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://victorcasass.com/api/")
            .build();

    public static ApiService getService(){
        return restAdapter.create(ApiService.class);
    }
}
