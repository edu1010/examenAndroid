package com.tiempociudades.edu1010.examen;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentInicial.OnFragmentInteractionListener, FragmentHacerReserva.OnFragmentInteractionListener{

    List<Platos> platos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        FragmentManager fm = getSupportFragmentManager();
        FragmentInicial frag = new FragmentInicial();
        fm.beginTransaction().replace(R.id.fragment_container, frag,"fragmentInicial").commit();

        MiHilo hilo = new MiHilo();
        hilo.execute("https://jdarestaurantapi.firebaseio.com/menu.json");


    }
@Override
public void escribirFirebase(String enviar){

        FirebaseDatabase.getInstance().getReference().child("reserva").push().setValue(enviar);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Debo inflar el recurso de tipo Menu que debo tener generado
        //en res/menu
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //Este método será invocado cada vez que alguien pulse
    //alguno de los items del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Escojo que hacer en función del botón pulsado
        switch (item.getItemId()){
            case R.id.plats:
                cargar_FragmentPlatos();
                break;



            case R.id.realizarReserva:
                cargar_FragmentHacerReserva();
             break;

            case R.id.visualizarReservas:
                cargar_FragmentVisualizarReserva();
             break;
        }

        return super.onOptionsItemSelected(item);


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void cargar_FragmentPlatos( ) {

        //Proceso habitual de cargar un fragment, pero añadiendo un TAG al fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new FragmentPlatos();
        fm.beginTransaction().replace(R.id.fragment_container, fragment , "Platos").addToBackStack("Platos").commit();

    }

    private void cargar_FragmentVisualizarReserva( ) {

        //Proceso habitual de cargar un fragment, pero añadiendo un TAG al fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new FragmentVisualizarReserva();
        fm.beginTransaction().replace(R.id.fragment_container, fragment , "Visualizar").addToBackStack("Visualizar").commit();

    }
    private void cargar_FragmentHacerReserva( ) {

        //Proceso habitual de cargar un fragment, pero añadiendo un TAG al fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new FragmentHacerReserva();
        fm.beginTransaction().replace(R.id.fragment_container, fragment , "Hacer").addToBackStack("Hacer").commit();

    }


    private  void enviarLista(){
        //usersFragment.addUserToList(item);
        FragmentPlatos fragmentPlatos = (FragmentPlatos) getSupportFragmentManager().findFragmentByTag("CHAT");
        fragmentPlatos.añadirLista(platos);
    }

    public class MiHilo extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {//... indica que el numero de argumentos es variable
            HttpURLConnection connection;
            URL url;
            String result;
            result = "";

            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while (data != -1){
                    result+=(char) data;
                    data=inputStream.read();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.i("EDUARD",result);
            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            try{
                JSONObject json = new JSONObject(data);
                JSONArray jsonArray = json.getJSONArray("entrantes");

                for(int i=0; i<jsonArray.length();i++)
                {
                    JSONObject jsonitem=jsonArray.getJSONObject(i);
                    Platos platos1 = new Platos
                            (jsonitem.getString("ingredientes"),
                                    jsonitem.getString("nombre"),
                                    jsonitem.getString("precio"));
                    //metodo para añadir el objeto a la lista y luego al recicler
                    platos.add(platos1);

                }
                //metodo
                enviarLista();
                Toast.makeText(MainActivity.this, "hecho", Toast.LENGTH_SHORT).show();

                Log.i("RESULTADO",json.getJSONObject("weather").toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }






}
