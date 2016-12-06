package layout;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ito.daniele.alcoolgasolina.R;
import ito.daniele.alcoolgasolina.adapter.NothingSelectedSpinnerAdapter;
import ito.daniele.alcoolgasolina.entity.Carro;
import ito.daniele.alcoolgasolina.model.Bairro;
import ito.daniele.alcoolgasolina.model.Estado;
import ito.daniele.alcoolgasolina.model.Municipio;
import ito.daniele.alcoolgasolina.model.Posto;
import ito.daniele.alcoolgasolina.servidor.ImportarBairros;
import ito.daniele.alcoolgasolina.servidor.ImportarEstados;
import ito.daniele.alcoolgasolina.servidor.ImportarMunicipios;
import ito.daniele.alcoolgasolina.servidor.ImportarPostos;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private View view;
    private Spinner spnCarro, spnEstado, spnMunicipio, spnBairro, spnPosto;
    private TextView tvResultado, tvAlcool, tvGasolina;
    private RadioGroup rg;
    private RadioButton rbCidade, rbViagem;
    private Context context;
    private ProgressDialog progressDialog;
    private ImageView ivAtualizar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        iniciarAtributos();
        popularSpinnerCarros();
        popularSpinnerEstados();
        return view;
    }

    private void iniciarAtributos() {
        context = view.getContext();
        spnCarro = (Spinner) view.findViewById(R.id.spn_carros);
        spnEstado = (Spinner) view.findViewById(R.id.spn_estados);
        spnEstado.setOnItemSelectedListener(onEstadoSelected);
        spnMunicipio = (Spinner) view.findViewById(R.id.spn_municipios);
        spnMunicipio.setOnItemSelectedListener(onMunicipioSelected);
        spnBairro = (Spinner) view.findViewById(R.id.spn_bairros);
        spnBairro.setOnItemSelectedListener(onBairroSelected);
        spnPosto = (Spinner) view.findViewById(R.id.spn_postos);
        spnPosto.setOnItemSelectedListener(onPostoSelected);
        tvResultado = (TextView) view.findViewById(R.id.tv_resultado);
        tvGasolina = (TextView) view.findViewById(R.id.tv_gasolina);
        tvAlcool = (TextView) view.findViewById(R.id.tv_alcool);
        rg = (RadioGroup) view.findViewById(R.id.rg_cidade_viagem);
        rbCidade = (RadioButton) view.findViewById(R.id.rb_cidade);
        rbCidade.setOnClickListener(onGroupClick);
        rbViagem = (RadioButton) view.findViewById(R.id.rb_viagem);
        rbViagem.setOnClickListener(onGroupClick);
        ivAtualizar = (ImageView) view.findViewById(R.id.iv_atualiza);
        ivAtualizar.setOnClickListener(onAtualizarClick);
    }

    private void popularSpinnerCarros() {
        List<Carro> carros = Carro.listAll(Carro.class);
        ArrayAdapter<Carro> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, carros);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCarro.setAdapter(new NothingSelectedSpinnerAdapter(adapter,
                R.layout.contact_spinner_row_nothing_selected, context, "[Selecione um carro...]"));
    }

    private void iniciarDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Aguarde");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void popularSpinnerEstados() {
        ImportarEstados importarEstados = new ImportarEstados();
        ArrayList<Estado> estados;
        try {
            iniciarDialog();
            estados = importarEstados.execute().get();
            ArrayAdapter<Estado> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, estados);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnEstado.setAdapter(new NothingSelectedSpinnerAdapter(adapter,
                    R.layout.contact_spinner_row_nothing_selected, context, "[Selecione um estado...]"));
            progressDialog.dismiss();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void popularSpinnerMunicipios(int codigoEstado) {
        ImportarMunicipios importarMunicipios = new ImportarMunicipios();
        ArrayList<Municipio> municipios;
        try {
            iniciarDialog();
            municipios = importarMunicipios.execute().get();
            ArrayList<Municipio> municipios1 = new ArrayList<>();
            for (int i = 0; i < municipios.size(); i++) {
                if (municipios.get(i).getCodigoEstado() == codigoEstado) {
                    municipios1.add(municipios.get(i));
                }
            }
            ArrayAdapter<Municipio> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, municipios1);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnMunicipio.setAdapter(new NothingSelectedSpinnerAdapter(adapter,
                    R.layout.contact_spinner_row_nothing_selected, context, "[Selecione um município...]"));
            progressDialog.dismiss();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private AdapterView.OnItemSelectedListener onEstadoSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (id == -1) {
                return;
            }
            Estado estado = (Estado) spnEstado.getItemAtPosition(position);
            popularSpinnerMunicipios(estado.getCodigo());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void popularSpinnerBairros(int codigoMunicipio) {
        ImportarBairros importarBairros = new ImportarBairros();
        ArrayList<Bairro> bairros;
        try {
            iniciarDialog();
            bairros = importarBairros.execute().get();
            ArrayList<Bairro> bairros1 = new ArrayList<>();
            for (int i = 0; i < bairros.size(); i++) {
                if (bairros.get(i).getCodigoMunicipio() == codigoMunicipio) {
                    bairros1.add(bairros.get(i));
                }
            }
            ArrayAdapter<Bairro> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, bairros1);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnBairro.setAdapter(new NothingSelectedSpinnerAdapter(adapter,
                    R.layout.contact_spinner_row_nothing_selected, context, "[Selecione um bairro...]"));
            progressDialog.dismiss();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private AdapterView.OnItemSelectedListener onMunicipioSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (id == -1) {
                return;
            }
            Municipio municipio = (Municipio) spnMunicipio.getItemAtPosition(position);
            popularSpinnerBairros(municipio.getCodigo());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void popularSpinnerPostos(int codigoBairro) {
        ImportarPostos importarPostos = new ImportarPostos();
        ArrayList<Posto> postos;
        try {
            iniciarDialog();
            postos = importarPostos.execute().get();
            ArrayList<Posto> postos1 = new ArrayList<>();
            for (int i = 0; i < postos.size(); i++) {
                if (postos.get(i).getCodigoBairro() == codigoBairro) {
                    postos1.add(postos.get(i));
                }
            }
            ArrayAdapter<Posto> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, postos1);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPosto.setAdapter(new NothingSelectedSpinnerAdapter(adapter,
                    R.layout.contact_spinner_row_nothing_selected, context, "[Selecione um posto...]"));
            progressDialog.dismiss();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private AdapterView.OnItemSelectedListener onBairroSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (id == -1) {
                return;
            }
            Bairro bairro = (Bairro) spnBairro.getItemAtPosition(position);
            popularSpinnerPostos(bairro.getCodigo());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener onPostoSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (id == -1) {
                return;
            }
            try {
                Posto posto = (Posto) spnPosto.getItemAtPosition(position);
                Carro carro = (Carro) spnCarro.getSelectedItem();
                calcula(posto, carro);
            } catch (Exception ex) {
                Log.e(TAG, "onPostSelected", ex);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void calcula(Posto posto, Carro carro) {
        double gasolina, alcool;
        if (rg.getCheckedRadioButtonId() == R.id.rb_cidade) {
            gasolina = carro.getGasolinaCidade();
            alcool = carro.getAlcoolCidade();
        } else {
            gasolina = carro.getGasolinaViagem();
            alcool = carro.getAlcoolViagem();
        }
        double precoKmGasolina = posto.getGasolina() / gasolina;
        double precoKmAlcool = posto.getAlcool() / alcool;

        tvGasolina.setText(context.getString(R.string.gasolina_).replace("[VALOR]", String.valueOf(posto.getGasolina())));
        tvGasolina.setTextColor(context.getResources().getColor(R.color.vermelho));

        tvAlcool.setText(context.getString(R.string.alcool_).replace("[VALOR]", String.valueOf(posto.getAlcool())));
        tvAlcool.setTextColor(context.getResources().getColor(R.color.verde));

        if (precoKmAlcool < precoKmGasolina) {
            tvResultado.setText(context.getString(R.string.abasteca_alcool));
            tvResultado.setTextColor(context.getResources().getColor(R.color.verde));
        } else if (precoKmAlcool > precoKmGasolina) {
            tvResultado.setText(context.getString(R.string.abasteca_gasolina));
            tvResultado.setTextColor(context.getResources().getColor(R.color.vermelho));
        } else {
            tvResultado.setText(context.getString(R.string.precos_equivalentes));
        }
    }

    private View.OnClickListener onGroupClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Posto posto = (Posto) spnPosto.getSelectedItem();
                Carro carro = (Carro) spnCarro.getSelectedItem();
                calcula(posto, carro);
            } catch (Exception ex) {
                Log.e(TAG, "onGroupClick", ex);
            }
        }
    };

    private View.OnClickListener onAtualizarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            popularSpinnerCarros();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        popularSpinnerCarros();
    }
}
