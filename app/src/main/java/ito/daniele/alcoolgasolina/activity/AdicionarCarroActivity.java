package ito.daniele.alcoolgasolina.activity;

/**
 * Created by Daniele on 24/11/2016.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ito.daniele.alcoolgasolina.R;
import ito.daniele.alcoolgasolina.entity.Carro;

public class AdicionarCarroActivity extends AppCompatActivity {

    private EditText edtNome, edtAlcoolCidade, edtGasolinaCidade, edtAlcoolViagem, edtGasolinaViagem;
    private final String TAG = "AdicionarCarroActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_carro);
        iniciarAtributos();
    }

    private void iniciarAtributos() {
        edtNome = (EditText) findViewById(R.id.edt_nome_carro);
        edtAlcoolCidade = (EditText) findViewById(R.id.edt_alcool_cidade);
        edtGasolinaCidade = (EditText) findViewById(R.id.edt_gasolina_cidade);
        edtAlcoolViagem = (EditText) findViewById(R.id.edt_alcool_viagem);
        edtGasolinaViagem = (EditText) findViewById(R.id.edt_gasolina_viagem);
    }

    public void btnCadastrarClique(View view) {
        try {
            if (!camposPreenchidos()) {
                return;
            }
            Carro carro = new Carro();
            carro.setNome(edtNome.getText().toString());
            carro.setAlcoolCidade(Double.parseDouble(edtAlcoolCidade.getText().toString()));
            carro.setGasolinaCidade(Double.parseDouble(edtGasolinaCidade.getText().toString()));
            carro.setAlcoolViagem(Double.parseDouble(edtAlcoolViagem.getText().toString()));
            carro.setGasolinaViagem(Double.parseDouble(edtGasolinaViagem.getText().toString()));
            carro.save();
            Toast.makeText(this, R.string.cadastrado_com_sucesso, Toast.LENGTH_LONG).show();
            Log.d(TAG, "Cadastrado carro com sucesso.");
            finish();
        } catch (Exception ex) {
            Toast.makeText(this, R.string.erro_cadastrar_carro, Toast.LENGTH_LONG).show();
            Log.e(TAG, "Erro ao cadastrar carro.", ex);
        }
    }

    public boolean camposPreenchidos() {
        if (edtNome.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.preencher_campo_nome, Toast.LENGTH_LONG).show();
            return false;
        }
        if (edtAlcoolCidade.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.preencher_campo_alcool_cidade, Toast.LENGTH_LONG).show();
            return false;
        }
        if (edtGasolinaCidade.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.preencher_campo_gasolina_cidade, Toast.LENGTH_LONG).show();
            return false;
        }
        if (edtAlcoolViagem.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.preencher_campo_alcool_viagem, Toast.LENGTH_LONG).show();
            return false;
        }
        if (edtGasolinaViagem.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.preencher_campo_gasolina_viagem, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
