package ito.daniele.alcoolgasolina.servidor;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ito.daniele.alcoolgasolina.interfaces.IAsyncResponse;
import ito.daniele.alcoolgasolina.model.Municipio;

/**
 * Created by Daniele on 30/11/2016.
 */

public class ImportarMunicipios extends AsyncTask<Void, String, ArrayList<Municipio>> {

    private final String URL = "http://www.inf.ufpr.br/dhi10/android/municipios.json";
    public IAsyncResponse delegate = null;

    @Override
    protected ArrayList<Municipio> doInBackground(Void... params) {
        Servidor servidor = new Servidor();
        try {
            String json = servidor.importar(URL);
            Gson gson = new Gson();
            ArrayList<Municipio> municipios = gson.fromJson(json, new TypeToken<ArrayList<Municipio>>() {}.getType());
            return municipios;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
