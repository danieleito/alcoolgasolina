package layout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ito.daniele.alcoolgasolina.R;
import ito.daniele.alcoolgasolina.activity.AdicionarCarroActivity;
import ito.daniele.alcoolgasolina.activity.EditarCarroActivity;
import ito.daniele.alcoolgasolina.entity.Carro;

public class CarrosFragment extends Fragment {
    private final String TAG = "CarrosFragment";
    private View view;
    private ListView lvCarros;
    private Context context;
    private List<Carro> carros;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), AdicionarCarroActivity.class);
            startActivity(intent);
        }
    };
    private AdapterView.OnItemLongClickListener onLongClickCarros = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            excluir(position);
            return true;
        }
    };
    private AdapterView.OnItemClickListener onItemClickCarros = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(context, EditarCarroActivity.class);
            intent.putExtra("id", carros.get(position).getId());
            startActivity(intent);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_carros, container, false);
        context = view.getContext();

        Button btnAdicionar = (Button) view.findViewById(R.id.btn_adicionar_carro);
        btnAdicionar.setOnClickListener(onClickListener);

        lvCarros = (ListView) view.findViewById(R.id.lv_carros);
        lvCarros.setOnItemLongClickListener(onLongClickCarros);
        lvCarros.setOnItemClickListener(onItemClickCarros);
        popularLista();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        popularLista();
    }

    private void popularLista() {
        carros = Carro.listAll(Carro.class);
        ArrayAdapter<Carro> adapter = new ArrayAdapter<Carro>(context, android.R.layout.simple_list_item_1, carros);
        lvCarros.setAdapter(adapter);
    }

    private void excluir(int posicao) {
        final int p = posicao;
        try {
            new AlertDialog.Builder(context)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage(R.string.deseja_excluir_carro)
                    .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Carro carro = (Carro) lvCarros.getAdapter().getItem(p);
                            carro.delete();
                            carros.remove(p);
                            ((ArrayAdapter<Carro>) lvCarros.getAdapter()).notifyDataSetChanged();
                            Toast.makeText(context, R.string.carro_excluido_sucesso, Toast.LENGTH_LONG).show();
                        }

                    })
                    .setNegativeButton(R.string.nao, null)
                    .show();
        } catch (Exception ex) {
            Log.e(TAG, "Erro ao excluir carro.", ex);
            Toast.makeText(context, R.string.erro_excluir_carro, Toast.LENGTH_LONG).show();
        }
    }





}
