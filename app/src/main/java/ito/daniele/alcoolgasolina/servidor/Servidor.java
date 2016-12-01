package ito.daniele.alcoolgasolina.servidor;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Daniele on 30/11/2016.
 */

public class Servidor {
    public String importar(String URL) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(URL);

        HttpResponse httpResponse = httpClient.execute(httpget);
        HttpEntity httpEntity = httpResponse.getEntity();
        //se código de resposta for diferente de 200, então deu erro
        if (httpResponse.getStatusLine().getStatusCode() != 200) {
            throw new Exception("("+URL+") - HTTP error code: " + httpResponse.getStatusLine().getStatusCode());
        } else {
            InputStream is = httpEntity.getContent();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            String s;
            String r = "";
            while((s = reader2.readLine())!=null) {
                r += s;
            }
            //return reader2.readLine();
            return r;
        }
    }
}
