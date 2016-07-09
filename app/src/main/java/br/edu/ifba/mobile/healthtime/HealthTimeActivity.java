package br.edu.ifba.mobile.healthtime;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.edu.ifba.mobile.healthtime.bd.FachadaBD;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoCadastroHorario;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoCadastroMedicamento;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoInformacao;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoListagemHorario;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoListagemMedicamento;

public class HealthTimeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager paginador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthtime);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //adapter que retorna as seçoes do activity
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        paginador = (ViewPager) findViewById(R.id.container);
        paginador.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(paginador);

        //cria o BD
        FachadaBD.criarInstancia(this.getApplicationContext());

        paginador.addOnPageChangeListener(this);
    }

    //tres metodos do OnPageChangeListener
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //atualiza informacoes com pase na posicao/pagina
        if(position == 1){
            FragmentoCadastroMedicamento.getInstancia().exibirMedicamentoSelecionado();
        } else if(position == 2){
            FragmentoCadastroHorario.getInstancia().exibirHorarioSelecionado();
        } else if(position == 3){
            FragmentoListagemMedicamento.getInstancia().atualizar();
        } else if(position == 4){
            FragmentoListagemHorario.getInstancia().atualizar();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            switch(position){
                case 0:
                    frag = FragmentoInformacao.getInstancia();

                    break;
                case 1:
                    frag = FragmentoCadastroMedicamento.getInstancia();

                    break;
                case 2:
                    frag = FragmentoCadastroHorario.getInstancia();

                    break;
                case 3:
                    frag = FragmentoListagemMedicamento.getInstancia();
                    break;
                case 4:
                    frag = FragmentoListagemHorario.getInstancia();
                    break;
            }

            return frag;

        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Sobre";
                case 1:
                    return "CAD. Medicamento";
                case 2:
                    return "CAD. Horario";
                case 3:
                    return "Exibir Medicamentos";
                case 4:
                    return "Exibir Horarios";

            }
            return null;
        }
    }
}
