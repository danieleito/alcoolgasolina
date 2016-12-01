package ito.daniele.alcoolgasolina.servidor;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ito.daniele.alcoolgasolina.interfaces.IAsyncResponse;
import ito.daniele.alcoolgasolina.model.Bairro;

/**
 * Created by Daniele on 30/11/2016.
 */

public class ImportarBairros extends AsyncTask<Void, String, ArrayList<Bairro>> {

    private final String URL = "http://www.inf.ufpr.br/dhi10/android/bairros.json";
    public IAsyncResponse delegate = null;

    @Override
    protected ArrayList<Bairro> doInBackground(Void... params) {
        Servidor servidor = new Servidor();
        try {
            String json = servidor.importar(URL);
            Gson gson = new Gson();
            ArrayList<Bairro> bairros = gson.fromJson(json, new TypeToken<ArrayList<Bairro>>() {}.getType());
            return bairros;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

