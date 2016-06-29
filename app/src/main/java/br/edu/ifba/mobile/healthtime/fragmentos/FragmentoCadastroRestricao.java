package br.edu.ifba.mobile.healthtime.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.healthtime.R;
import br.edu.ifba.mobile.healthtime.bd.Medicamento;

/**
 * Created by isaac on 22/06/16.
 */
public class FragmentoCadastroRestricao extends Fragment{

    private static FragmentoCadastroRestricao instancia = null;

    private View tela = null;
    private EditText etRestricoes = null;
    private Button btnGravar = null;
    Medicamento medicamento = null;

    public static FragmentoCadastroRestricao getInstancia(){
        if(instancia == null){
            instancia = new FragmentoCadastroRestricao();
        }

        return instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_cad_restricoes,vgrupo,false);

        preparar();

        return tela;
    }

    private Context getContexto(){
        return this.getContext();
    }

    private void preparar(){
        etRestricoes = (EditText) tela.findViewById(R.id.etRestricoes);
        btnGravar = (Button) tela.findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(),"HEY THERE!",Toast.LENGTH_LONG).show();
            }
        });
    }

}
