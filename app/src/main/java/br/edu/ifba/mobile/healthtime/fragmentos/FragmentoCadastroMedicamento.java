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
import br.edu.ifba.mobile.healthtime.tarefas.GravacaoMedicamento;

/**
 * Created by isaac on 22/06/16.
 */
public class FragmentoCadastroMedicamento extends Fragment {

    private View tela = null;
    private static FragmentoCadastroMedicamento instancia = null;

    private EditText etNomeMedicamento = null;
    private EditText etNomeFarmacia = null;
    private EditText etTelFarmacia = null;
    private EditText etRestricoes = null;

    private Button btnGravarMed = null;

    private Medicamento medicamento = null;

    public static FragmentoCadastroMedicamento getInstancia(){
        if(instancia == null){
            instancia = new FragmentoCadastroMedicamento();
        }

        return  instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflador,  ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_cadastro_med,vgrupo,false);
        preparar();
        return tela;
    }

    private void preparar(){
        etNomeMedicamento = (EditText) tela.findViewById(R.id.etNomeMedicamento);
        etNomeFarmacia = (EditText) tela.findViewById(R.id.etNomeFarmacia);
        etTelFarmacia = (EditText) tela.findViewById(R.id.etTelFarmacia);
        etRestricoes = (EditText) tela.findViewById(R.id.etRestricoes);

        btnGravarMed = (Button) tela.findViewById(R.id.btnGravarMed);
        btnGravarMed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                GravacaoMedicamento gravacao = new GravacaoMedicamento(getContexto(),getMedicamento());
                gravacao.execute();
                limparCampos();
            }
        });
    }

    private Context getContexto(){
        return this.getContext();
    }

    private Medicamento getMedicamento(){
        //medicamento = new Medicamento();

        medicamento.setNomeMedicamento(etNomeMedicamento.getText().toString());
        medicamento.setNomeFarmacia(etNomeFarmacia.getText().toString());
        medicamento.setTelFarmacia(etTelFarmacia.getText().toString());
        medicamento.setRestricoes(etRestricoes.getText().toString());

        return medicamento;
    }

    public void exibirMedicamentoSelecionado(){
        medicamento = FragmentoListagemMedicamento.getInstancia().getMedicamentoSelecionado();
        if(medicamento.getCodigo()==-1){
            limparCampos();
        } else {
            carregarCampos();
        }
    }

    private void limparCampos(){
        etNomeMedicamento.setText("");
        etNomeFarmacia.setText("");
        etTelFarmacia.setText("");
        etRestricoes.setText("");
    }

    private void carregarCampos(){
        etNomeMedicamento.setText(medicamento.getNomeMedicamento());
        etNomeFarmacia.setText(medicamento.getNomeFarmacia());
        etTelFarmacia.setText(medicamento.getTelFarmacia());
        etRestricoes.setText(medicamento.getRestricoes());
    }
}
