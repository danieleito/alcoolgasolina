package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import ito.daniele.alcoolgasolina.R;
import ito.daniele.alcoolgasolina.adapter.NothingSelectedSpinnerAdapter;
import ito.daniele.alcoolgasolina.entity.Carro;

public class ViagemFragment extends Fragment {
    private View view;
    private Spinner spnCarro;
    private Context context;
    private EditText etKmViagem;
    private TextView tvResultado;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_viagem, container, false);
        iniciarAtributos();
        popularSpinnerCarros();
        return view;
    }

    private void iniciarAtributos() {
        context = view.getContext();
        spnCarro = (Spinner) view.findViewById(R.id.spn_carros);
        etKmViagem = (EditText) view.findViewById(R.id.et_km_viagem);
        tvResultado = (TextView) view.findViewById(R.id.tv_resultado);
    }

    private void popularSpinnerCarros() {
        List<Carro> carros = Carro.listAll(Carro.class);
        ArrayAdapter<Carro> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, carros);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCarro.setAdapter(new NothingSelectedSpinnerAdapter(adapter,
                R.layout.contact_spinner_row_nothing_selected, context, "[Selecione um carro...]"));
    }

    private AdapterView.OnItemSelectedListener onCarroSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (id == -1) {
                return;
            }
            if (etKmViagem.getText() == null || etKmViagem.getText().toString().isEmpty()) {
                return;
            }
            Carro carro = (Carro) spnCarro.getItemAtPosition(position);
            double km = Double.parseDouble(etKmViagem.getText().toString());
//            calcula(carro, km);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}
