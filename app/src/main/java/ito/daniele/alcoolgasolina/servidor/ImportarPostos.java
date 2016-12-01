package ito.daniele.alcoolgasolina.servidor;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ito.daniele.alcoolgasolina.interfaces.IAsyncResponse;
import ito.daniele.alcoolgasolina.model.Posto;

/**
 * Created by Daniele on 30/11/2016.
 */

public class ImportarPostos extends AsyncTask<Void, String, ArrayList<Posto>> {

    private final String URL = "http://www.inf.ufpr.br/dhi10/android/postos.json";
    public IAsyncResponse delegate = null;

    @Override
    protected ArrayList<Posto> doInBackground(Void... params) {
        Servidor servidor = new Servidor();
        try {
            String json = servidor.importar(URL);
            Gson gson = new Gson();
            ArrayList<Posto> postos = gson.fromJson(json, new TypeToken<ArrayList<Posto>>() {}.getType());
            return postos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
