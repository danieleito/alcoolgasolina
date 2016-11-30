package ito.daniele.alcoolgasolina.activity;

/**
 * Created by Daniele on 24/11/2016.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ito.daniele.alcoolgasolina.R;
import ito.daniele.alcoolgasolina.entity.Carro;

public class EditarCarroActivity extends AppCompatActivity {

    private EditText edtNome, edtAlcoolCidade, edtGasolinaCidade, edtAlcoolViagem, edtGasolinaViagem;
    private Carro carro;
    private final String TAG = "EditarCarroActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_carro);
        iniciarAtributos();
        popularCampos();
    }

    private void popularCampos() {
        edtNome.setText(carro.getNome());
        edtAlcoolCidade.setText(String.valueOf(carro.getAlcoolCidade()));
        edtGasolinaCidade.setText(String.valueOf(carro.getGasolinaCidade()));
        edtAlcoolViagem.setText(String.valueOf(carro.getAlcoolViagem()));
        edtGasolinaViagem.setText(String.valueOf(carro.getGasolinaViagem()));
    }

    private void iniciarAtributos() {
        edtNome = (EditText) findViewById(R.id.edt_nome_carro);
        edtAlcoolCidade = (EditText) findViewById(R.id.edt_alcool_cidade);
        edtGasolinaCidade = (EditText) findViewById(R.id.edt_gasolina_cidade);
        edtAlcoolViagem = (EditText) findViewById(R.id.edt_alcool_viagem);
        edtGasolinaViagem = (EditText) findViewById(R.id.edt_gasolina_viagem);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        carro = Carro.findById(Carro.class, id);
    }

    public void btnEditarClique(View view) {
        try {
            if (!camposPreenchidos()) {
                return;
            }
            carro.setNome(edtNome.getText().toString());
            carro.setAlcoolCidade(Double.parseDouble(edtAlcoolCidade.getText().toString()));
            carro.setGasolinaCidade(Double.parseDouble(edtAlcoolCidade.getText().toString()));
            carro.setAlcoolViagem(Double.parseDouble(edtAlcoolViagem.getText().toString()));
            carro.setGasolinaViagem(Double.parseDouble(edtGasolinaViagem.getText().toString()));
            carro.save();
            Toast.makeText(this, R.string.editado_com_sucesso, Toast.LENGTH_LONG).show();
            Log.d(TAG, "Editado carro com sucesso.");
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
