package br.edu.ifba.mobile.healthtime.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.edu.ifba.mobile.healthtime.R;

/**
 * Created by isaac on 22/06/16.
 */
public class FragmentoCadastroMedicamento extends Fragment {

    private View tela = null;
    private static FragmentoCadastroMedicamento instancia = null;

    private EditText etNomeMedicamento = null;
    private EditText etNomeFarmacia = null;
    private EditText etTelFarmacia = null;
    private EditText etQuantidadeDiaria = null;

    public static FragmentoCadastroMedicamento getInstancia(){
        if(instancia == null){
            instancia = new FragmentoCadastroMedicamento();
        }

        return  instancia;
    }
    @Override
    public View onCreateView(LayoutInflater inflador,  ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_cadastro_med,vgrupo,false);
        preparar();
        return tela;
    }

    private void preparar(){
        etNomeFarmacia = (EditText) tela.findViewById(R.id.etNomeMedicamento);
        etNomeFarmacia = (EditText) tela.findViewById(R.id.etNomeFarmacia);
        etTelFarmacia = (EditText) tela.findViewById(R.id.etTelFarmacia);
        etQuantidadeDiaria = (EditText)tela.findViewById(R.id.etQuantidadeDiaria);
    }

}
