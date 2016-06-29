package br.edu.ifba.mobile.healthtime.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


import br.edu.ifba.mobile.healthtime.bd.FachadaBD;
import br.edu.ifba.mobile.healthtime.bd.Medicamento;

/**
 * Created by isaac on 28/06/16.
 */
public class ListagemMedicamentos extends AsyncTask<Void,Void,List<Medicamento>> {
    private Context contexto = null;
    private ListView listaMedicamentos = null;

    public ListagemMedicamentos(Context contexto, ListView listaMedicamentos){
        this.contexto = contexto;
        this.listaMedicamentos = listaMedicamentos;
    }
    @Override
    protected List<Medicamento> doInBackground(Void... params) {
       List<Medicamento> medicamentos = FachadaBD.getInstancia().listarMedicamentos();

       return  medicamentos;
    }

    @Override
    protected  void onPostExecute(List<Medicamento> medicamentos){
        if(medicamentos.isEmpty()){
            Toast.makeText(contexto,"Lista vazia, adicione um medicamento.",Toast.LENGTH_LONG).show();
            //remove o medicamento da posicao 0
            listaMedicamentos.setAdapter(null);

        } else {
            ArrayAdapter<Medicamento> adaptador = new ArrayAdapter<Medicamento>(contexto,
                    android.R.layout.select_dialog_singlechoice,medicamentos);
            listaMedicamentos.setAdapter(adaptador);
        }
    }
}
