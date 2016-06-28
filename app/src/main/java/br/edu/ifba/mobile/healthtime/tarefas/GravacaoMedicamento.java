package br.edu.ifba.mobile.healthtime.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.healthtime.bd.FachadaBD;
import br.edu.ifba.mobile.healthtime.bd.Medicamento;

/**
 * Created by isaac on 28/06/16.
 */
public class GravacaoMedicamento extends AsyncTask<Void,Void,String> {
    private Context contexto = null;
    private Medicamento medicamento = null;

    public GravacaoMedicamento(Context contexto, Medicamento medicamento){
        this.contexto = contexto;
        this.medicamento = medicamento;
    }


    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        long codigo = -1;
        if(medicamento.getCodigo()==-1){
            codigo = FachadaBD.getInstancia().inserir(medicamento);
        } else {
            codigo = FachadaBD.getInstancia().atualizar(medicamento);
        }

        if(codigo>0){
            mensagem = "Medicamento gravado com sucesso!";
        } else {
            mensagem = "Erro de gravação!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}
