package br.edu.ifba.mobile.healthtime.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.healthtime.bd.FachadaBD;
import br.edu.ifba.mobile.healthtime.bd.Medicamento;

/**
 * Created by isaac on 28/06/16.
 */
public class RemocaoMedicamento extends AsyncTask<Void,Void,String>{
    private Medicamento medicamento;
    private Context contexto;

    public RemocaoMedicamento(Context contexto, Medicamento medicamento){
        this.medicamento = medicamento;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(medicamento.getCodigo()!=-1){
            if (FachadaBD.getInstancia().remover(medicamento) == 0){
                mensagem = "Falha ao remover medicamento!";
            } else {
                mensagem = "Medicamento "+medicamento.getNomeMedicamento()+" foi removido com sucesso.";
                medicamento.setCodigo(-1);
            }
        } else  {
            mensagem = "Selecione um medicamento!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}
