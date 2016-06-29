package br.edu.ifba.mobile.healthtime.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.healthtime.R;
import br.edu.ifba.mobile.healthtime.bd.Medicamento;
import br.edu.ifba.mobile.healthtime.tarefas.ListagemMedicamentos;
import br.edu.ifba.mobile.healthtime.tarefas.RemocaoMedicamento;

/**
 * Created by isaac on 22/06/16.
 */
public class FragmentoListagemHorario extends Fragment {
    private static FragmentoListagemHorario instancia = null;
    private View tela = null;
    private ListView lista = null;

    public static FragmentoListagemHorario getInstancia(){
        if(instancia == null){
            instancia = new FragmentoListagemHorario();
        }

        return  instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_lista_medicamentos, vgrupo, false);
        preparar();
        return tela;
    }

    private void preparar(){
        lista = (ListView) tela.findViewById(R.id.listaMedicamentos);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.FOCUSABLES_TOUCH_MODE);
        this.setHasOptionsMenu(true);
    }

    public void atualizar(){
        ListagemMedicamentos listagem = new ListagemMedicamentos(this.getContext(),lista);
        listagem.execute();
    }

    //menu do lado direito superior
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador) {
        super.onCreateOptionsMenu(menu,inflador);
        inflador.inflate(R.menu.menu_health,menu);
    }


    //carrega as acoes do menu acima
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long id = item.getItemId();
        if(id!= AdapterView.INVALID_ROW_ID){
            if(id==R.id.acoes_menu){
                RemocaoMedicamento remocao = new RemocaoMedicamento(this.getContext(),this.getMedicamentoSelecionado());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public Medicamento getMedicamentoSelecionado(){
        Medicamento medicamento = new Medicamento();
        int pos = lista.getCheckedItemPosition();
        if(pos!=ListView.INVALID_POSITION){
            medicamento = (Medicamento)lista.getItemAtPosition(pos);
        }

        return medicamento;
    }

}
