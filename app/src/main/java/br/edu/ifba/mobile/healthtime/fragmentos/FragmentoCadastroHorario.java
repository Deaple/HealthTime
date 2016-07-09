package br.edu.ifba.mobile.healthtime.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.Calendar;

import br.edu.ifba.mobile.healthtime.R;
import br.edu.ifba.mobile.healthtime.bd.Horario;
import br.edu.ifba.mobile.healthtime.tarefas.GravacaoHorario;

/**
 * Created by isaac on 22/06/16.
 */
public class FragmentoCadastroHorario extends Fragment{
    private static FragmentoCadastroHorario instancia = null;
    private View tela =  null;

    private TimePicker tpHorario = null;
    private NumberPicker npIntHorarios =null;
    private Button btGravarHorario = null;

    private Horario horario= null;

    public static FragmentoCadastroHorario getInstancia(){
        if(instancia == null){
            instancia = new FragmentoCadastroHorario();
        }

        return instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_cad_horario, vgrupo, false);
        preparar();

        return tela;
    }

    private void preparar(){
        tpHorario = (TimePicker) tela.findViewById(R.id.horario_inicial);

        npIntHorarios = (NumberPicker) tela.findViewById(R.id.qntDiaria);
        npIntHorarios.setMinValue(1);
        npIntHorarios.setMaxValue(24);

        btGravarHorario = (Button) tela.findViewById(R.id.btnGravarHorario);
        btGravarHorario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GravacaoHorario gravacao = new GravacaoHorario(getContexto(),getHorario());
                gravacao.execute();
            }
        });
    }

    private Horario getHorario() throws ArithmeticException{
        //horario = new Horario();
        horario.setHorarioInicial(tpHorario.getCurrentHour()+":"+tpHorario.getCurrentMinute());
        horario.setIntervaloHorarios(npIntHorarios.getValue());
        horario.setQuantidadeDiaria(24/horario.getIntervaloHorarios());
        return horario;
    }

    private Context getContexto(){
        return this.getContext();
    }

    public void exibirHorarioSelecionado(){
        horario = FragmentoListagemHorario.getInstancia().getHorarioSelecionado();

        if(horario.getCodigo()==-1){
            limparCampos();
        } else {
            carregarCampos();
        }

    }

    private void limparCampos(){
        //atualiza para hora atual
        tpHorario.setCurrentHour(Calendar.HOUR_OF_DAY);
        tpHorario.setCurrentMinute(Calendar.MINUTE);

        npIntHorarios.setValue(npIntHorarios.getMinValue());
    }

    private void carregarCampos(){
        //atualiza para hora atual
        tpHorario.setCurrentHour(Calendar.HOUR_OF_DAY);
        tpHorario.setCurrentMinute(Calendar.MINUTE);

        npIntHorarios.setValue(horario.getIntervaloHorarios());
    }
}
