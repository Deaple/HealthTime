package br.edu.ifba.mobile.healthtime.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by isaac on 25/06/16.
 */
public class FachadaBD extends SQLiteOpenHelper{

    private static FachadaBD instancia = null;
    private static final String NOME_BANCO = "HorarioMedicamentos";
    private static final int VERSAO_BANCO = 1;
    private static final String COMANDO_CRIACAO_TABELA_MEDICAMENTOS =
            "CREATE TABLE MEDICAMENTO(codigo INTEGER PRIMARY KEY AUTO INCREMENT, " +
                    "nomeMedicamento TEXT, nomeFarmacia TEXT, telFarmacia VARCHAR(14), nroLembretesDiarios INTEGER, " +
                    "quantidadeDiaria INTEGER, horarioInicial DATETIME, restricoes TEXT)";

    public FachadaBD(Context contexto){
        super(contexto,NOME_BANCO,null,VERSAO_BANCO);
    }

    public static FachadaBD criarInstancia(Context contexto){
        if(instancia == null){
            instancia = new FachadaBD(contexto);
        }

        return instancia;
    }

    public static FachadaBD getInstancia(){
        return instancia;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_MEDICAMENTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {

    }
    //CRUD
    public long inserir(Medicamento medicamento){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nomeMedicamento", medicamento.getNomeMedicamento());
        valores.put("nomeFarmacia", medicamento.getNomeFarmacia());
        valores.put("telFarmacia",medicamento.getTelFarmacia());
        valores.put("nroLembretesDiarios", medicamento.getNroLembretesDiarios());
        valores.put("quantidadeDiaria", medicamento.getQuantidadeDiaria());
        valores.put("horarioInicial", medicamento.getHorarioInicial().toString());
        valores.put("restricoes",medicamento.getRestricoes());

        long codigo = db.insert("HorarioMedicamentos", null, valores);
        return codigo;
    }

    public List<Medicamento> listarMedicamentos(){
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT codigo, nomeMedicamento, nomeFarmacia,telFarmacia, nroLembretesDiarios," +
                " quantidadeDiaria, horarioInicial, restricoes";

        Cursor cursor = db.rawQuery(selecao,null);

        if(cursor != null){
            boolean temProximo = cursor.moveToFirst();

            while (temProximo){
                Medicamento medicamento = new Medicamento();
                medicamento.setCodigo(cursor.getLong(cursor.getColumnIndex("codigo")));
                medicamento.setNomeMedicamento(cursor.getString(cursor.getColumnIndex("nomeMedicamento")));
                medicamento.setNomeFarmacia(cursor.getString(cursor.getColumnIndex("nomeFarmacia")));
                medicamento.setTelFarmacia(cursor.getString(cursor.getColumnIndex("telFarmacia")));
                medicamento.setNroLembretesDiarios(cursor.getInt(cursor.getColumnIndex("nroLembretesDiarios")));
                medicamento.setQuantidadeDiaria(cursor.getInt(cursor.getColumnIndex("quantidadeDiaria")));
                String horario = cursor.getString(cursor.getColumnIndex("horarioInicial"));
                //converter String em tempo
                medicamento.setHorarioInicial(Calendar.getInstance());

                medicamentos.add(medicamento);
                temProximo = cursor.moveToNext();
            }
        }

        return medicamentos;
    }

    public long atualizar(Medicamento medicamento){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nomeMedicamento", medicamento.getNomeMedicamento());
        valores.put("nomeFarmacia", medicamento.getNomeFarmacia());
        valores.put("telFarmacia",medicamento.getTelFarmacia());
        valores.put("nroLembretesDiarios", medicamento.getNroLembretesDiarios());
        valores.put("quantidadeDiaria", medicamento.getQuantidadeDiaria());
        valores.put("horarioInicial", medicamento.getHorarioInicial().toString());
        valores.put("restricoes",medicamento.getRestricoes());

        long codigo = db.update("HorarioMedicamentos", valores, "codigo = "+medicamento.getCodigo(), null);

        return codigo;
    }

    public int remover(Medicamento medicamento){
        SQLiteDatabase db = this.getWritableDatabase();

        int codigo = db.delete("HorarioMedicamentos", "codigo = "+medicamento.getCodigo(), null);
        return codigo;
    }
}

