package proyecto.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import proyecto.pokemon.entity.PokemonEntity;
import proyecto.pokemon.service.ApiImplementation;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void ingresar(View view){

        Button button;
        final EditText namePokemon;
        final EditText typePokemon;
        button = (Button)findViewById(R.id.btningresar);
        namePokemon = (EditText)findViewById(R.id.edtName);
        typePokemon = (EditText)findViewById(R.id.edtType);

        ApiImplementation.getService().getPokemons(new Callback<ArrayList<PokemonEntity>>() {
            @Override
            public void success(ArrayList<PokemonEntity> lista, Response response) {
                // Entra si es que todo ha ido bien, si hubo internet, si se conectó, si
                // el archivo json esta bien armado
                for(PokemonEntity p: lista) {
                    if (p.getNombre().equals(namePokemon.getText().toString())
                            && p.getTipo().equals(typePokemon.getText().toString())) {

                        startActivity(new Intent(MainActivity.this, ListPokemonActivity.class));
                        Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_SHORT).show();
                    }
                }



            }

            @Override
            public void failure(RetrofitError retrofitError) {
                // entra aqui si hay algun error...
                Toast.makeText(MainActivity.this,"Credenciales incorrectas!",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

}
