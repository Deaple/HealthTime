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
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoCadastroLembrete;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoCadastroMedicamento;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoCadastroRestricao;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoInformacao;
import br.edu.ifba.mobile.healthtime.fragmentos.FragmentoListagemHorario;

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
        if(position == 4){
            FragmentoListagemHorario.getInstancia().atualizar();
        }
        //editar
        /*else if(position){
            FragmentoListagemHorario.getInstancia().
        }*/
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
                    frag = FragmentoCadastroLembrete.getInstancia();

                    break;
                case 3:
                    frag = FragmentoCadastroRestricao.getInstancia();
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
                    return "Cadastro Medicamento";
                case 2:
                    return "Cadastro Lembrete";
                case 3:
                    return "Cadastro Restrições";
                case 4:
                    return "Exibir";
            }
            return null;
        }
    }
}
