package br.edu.ifba.mobile.healthtime.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifba.mobile.healthtime.R;

/**
 * Created by isaac on 22/06/16.
 */

public class FragmentoInformacao extends Fragment{
    private static FragmentoInformacao instancia = null;

    private View tela = null;

    public static FragmentoInformacao getInstancia(){
        if(instancia==null){
            instancia = new FragmentoInformacao();
        }

        return instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_informacao, vgrupo, false);
        return tela;
    }

    public String showMyTag(){
        return this.getTag();
    }
}

