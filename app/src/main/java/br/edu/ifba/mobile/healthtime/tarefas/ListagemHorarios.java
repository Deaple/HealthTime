package br.edu.ifba.mobile.healthtime.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.healthtime.bd.FachadaBD;
import br.edu.ifba.mobile.healthtime.bd.Horario;

/**
 * Created by isaac on 07/07/16.
 */
public class ListagemHorarios extends AsyncTask<Void, Void, List<Horario>> {
    private Context contexto = null;
    private ListView listaHorarios = null;

    public ListagemHorarios(Context contexto, ListView listaHorarios){
        this.contexto = contexto;
        this.listaHorarios = listaHorarios;
    }

    @Override
    protected List<Horario> doInBackground(Void... params) {
        List<Horario> horario = FachadaBD.getInstancia().listaHorario();

        return  horario;
    }

    @Override
    protected  void onPostExecute(List<Horario> horarios){
        if(horarios.isEmpty()){
            Toast.makeText(contexto,"Lista vazia, adicione um horario.",Toast.LENGTH_LONG).show();
            //remove o medicamento da posicao 0
            listaHorarios.setAdapter(null);

        } else {
            ArrayAdapter<Horario> adaptador = new ArrayAdapter<Horario>(contexto,
                    android.R.layout.select_dialog_singlechoice,horarios);
            listaHorarios.setAdapter(adaptador);
        }
    }
}
