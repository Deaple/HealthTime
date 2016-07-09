package br.edu.ifba.mobile.healthtime.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.healthtime.bd.FachadaBD;
import br.edu.ifba.mobile.healthtime.bd.Horario;

/**
 * Created by isaac on 28/06/16.
 */
public class RemocaoHorario extends AsyncTask<Void,Void,String>{
    private Horario horario;
    private Context contexto;

    public RemocaoHorario(Context contexto, Horario horario){
        this.horario = horario;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(horario.getCodigo()!=-1){
            if (FachadaBD.getInstancia().removerHorario(horario) == 0){
                mensagem = "Falha ao remover horario!";
            } else {
                mensagem = "Horario foi removido com sucesso.";
                horario.setCodigo(-1);
            }
        } else  {
            mensagem = "Selecione um horario!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}
