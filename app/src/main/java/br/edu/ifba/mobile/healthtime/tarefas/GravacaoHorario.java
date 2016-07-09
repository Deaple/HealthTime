package br.edu.ifba.mobile.healthtime.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.healthtime.bd.FachadaBD;
import br.edu.ifba.mobile.healthtime.bd.Horario;

/**
 * Created by isaac on 07/07/16.
 */
public class GravacaoHorario extends AsyncTask<Void,Void,String> {
    private Context contexto = null;
    private Horario horario = null;

    public GravacaoHorario(Context contexto, Horario horario){
        this.contexto = contexto;
        this.horario = horario;
    }


    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        long codigo = -1;

        if(horario.getCodigo()==-1){
            codigo = FachadaBD.getInstancia().inserirHorario(horario);
        } else {
            codigo = FachadaBD.getInstancia().atualizarHorario(horario);
        }

        if(codigo>0){
            mensagem = "Horário gravado com sucesso!";
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
