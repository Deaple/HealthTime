package br.edu.ifba.mobile.healthtime.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import br.edu.ifba.mobile.healthtime.R;

/**
 * Created by isaac on 22/06/16.
 */
public class FragmentoCadastroLembrete  extends Fragment{
    private static FragmentoCadastroLembrete instancia = null;
    private View tela =  null;

    //private EditText etQuantidadeDiaria = null;
    //private EditText etHorarioInicial = null;
    private TimePicker tpHorario = null;
    private NumberPicker npNumero = null;
    public static  FragmentoCadastroLembrete getInstancia(){
        if(instancia == null){
            instancia = new FragmentoCadastroLembrete();
        }

        return instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_cad_lembrete, vgrupo, false);
        preparar();

        return tela;
    }

    private void preparar(){
        tpHorario = (TimePicker) tela.findViewById(R.id.horario);
        npNumero = (NumberPicker) tela.findViewById(R.id.quantidade);

        npNumero.setMinValue(1);
        npNumero.setMaxValue(24);

        //etQuantidadeDiaria = (EditText) tela.findViewById(R.id.etQuantidadeDiaria);
        //etHorarioInicial = (EditText) tela.findViewById(R.id.etHorarioInicial);
    }

}
