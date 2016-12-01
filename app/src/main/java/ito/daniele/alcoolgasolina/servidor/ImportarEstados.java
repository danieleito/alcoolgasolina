package ito.daniele.alcoolgasolina.servidor;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ito.daniele.alcoolgasolina.interfaces.IAsyncResponse;
import ito.daniele.alcoolgasolina.model.Estado;

/**
 * Created by Daniele on 30/11/2016.
 */

public class ImportarEstados extends AsyncTask<Void, String, ArrayList<Estado>> {

    private final String URL = "http://www.inf.ufpr.br/dhi10/android/estados.json";
    public IAsyncResponse delegate = null;



    @Override
    protected ArrayList<Estado> doInBackground(Void... params) {
        Servidor servidor = new Servidor();
        try {
            String json = servidor.importar(URL);
            Gson gson = new Gson();
            ArrayList<Estado> estados = gson.fromJson(json, new TypeToken<ArrayList<Estado>>() {}.getType());
            return estados;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    protected void onPostExecute(ArrayList<Estado> estados) {
//        super.onPostExecute(estados);
//        delegate.processFinish(estados);
//    }
}
