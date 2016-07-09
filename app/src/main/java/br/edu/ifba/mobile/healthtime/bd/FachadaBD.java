package br.edu.ifba.mobile.healthtime.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isaac on 25/06/16.
 */
public class FachadaBD extends SQLiteOpenHelper{

    private static FachadaBD instancia = null;
    private static final String NOME_BANCO = "HorarioMedicamentos";
    private static final int VERSAO_BANCO = 1;
    private static final String COMANDO_CRIACAO_TABELA_MEDICAMENTOS =
            "CREATE TABLE MEDICAMENTO(codigoMed INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nomeMedicamento TEXT, nomeFarmacia TEXT, telFarmacia VARCHAR(14), restricoes TEXT)";

    private static final String COMANDO_CRIACAO_TABELA_HORARIOS_DOSAGEM =
            "CREATE TABLE HORARIOS(codHorario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "horarioInicial TEXT, intervaloHorarios INTEGER, dosesDiarias INTEGER)";

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
        db.execSQL(COMANDO_CRIACAO_TABELA_HORARIOS_DOSAGEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {

    }
    //CRUD
    public long inserirMedicamento(Medicamento medicamento){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nomeMedicamento", medicamento.getNomeMedicamento());
        valores.put("nomeFarmacia", medicamento.getNomeFarmacia());
        valores.put("telFarmacia",medicamento.getTelFarmacia());
        valores.put("restricoes",medicamento.getRestricoes());

        long codigo = db.insert("MEDICAMENTO", null, valores);
        return codigo;
    }

    public long inserirHorario(Horario horario){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("horarioInicial", horario.getHorarioInicial());
        valores.put("intervaloHorarios", horario.getIntervaloHorarios());
        valores.put("dosesDiarias", horario.getQuantidadeDiaria());

        long codigo = db.insert("HORARIOS", null, valores);

        return codigo;
    }

    public List<Medicamento> listarMedicamentos(){
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT codigo, nomeMedicamento, nomeFarmacia,telFarmacia, " +
                "restricoes FROM MEDICAMENTO";

        Cursor cursor = db.rawQuery(selecao,null);

        if(cursor != null){
            boolean temProximo = cursor.moveToFirst();

            while (temProximo){
                Medicamento medicamento = new Medicamento();
                medicamento.setCodigo(cursor.getLong(cursor.getColumnIndex("codigo")));
                medicamento.setNomeMedicamento(cursor.getString(cursor.getColumnIndex("nomeMedicamento")));
                medicamento.setNomeFarmacia(cursor.getString(cursor.getColumnIndex("nomeFarmacia")));
                medicamento.setTelFarmacia(cursor.getString(cursor.getColumnIndex("telFarmacia")));
                medicamento.setRestricoes(cursor.getString(cursor.getColumnIndex("restricoes")));

                medicamentos.add(medicamento);
                temProximo = cursor.moveToNext();
            }
        }

        return medicamentos;
    }

    public long atualizarMedicamento(Medicamento medicamento){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nomeMedicamento", medicamento.getNomeMedicamento());
        valores.put("nomeFarmacia", medicamento.getNomeFarmacia());
        valores.put("telFarmacia",medicamento.getTelFarmacia());
        valores.put("restricoes",medicamento.getRestricoes());

        long codigo = db.update("MEDICAMENTO", valores, "codigo = "+medicamento.getCodigo(), null);

        return codigo;
    }

    public long atualizarHorario(Horario horario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("horarioInicial",horario.getHorarioInicial());
        valores.put("intervaloHorarios",horario.getIntervaloHorarios());
        valores.put("dosesDiarias",horario.getQuantidadeDiaria());

        long codigo = db.update("HORARIOS", valores, "codHorario = "+horario.getCodigo(), null);

        return codigo;
    }

    public List<Horario> listaHorario(){
        List<Horario> horarios = new ArrayList<Horario>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT codHorario, horarioInicial, intervaloHorarios, dosesDiarias " +
                "FROM HORARIOS";

        Cursor cursor = db.rawQuery(selecao,null);

        if(cursor!=null){
            boolean temProximo = cursor.moveToFirst();

            while (temProximo){
                Horario horario = new Horario();

                horario.setCodigo(cursor.getLong(cursor.getColumnIndex("codHorario")));
                horario.setHorarioInicial(cursor.getString(cursor.getColumnIndex("horarioInicial")));
                horario.setIntervaloHorarios(cursor.getInt(cursor.getColumnIndex("intervaloHorarios")));
                horario.setQuantidadeDiaria(cursor.getInt(cursor.getColumnIndex("dosesDiarias")));

                horarios.add(horario);
                temProximo = cursor.moveToNext();
            }
        }

        return horarios;

    }

    public int removerMedicamento(Medicamento medicamento){
        SQLiteDatabase db = this.getWritableDatabase();

        int codigo = db.delete("MEDICAMENTO", "codigo = "+medicamento.getCodigo(), null);
        return codigo;
    }

    public int removerHorario(Horario horario){
        SQLiteDatabase db = this.getWritableDatabase();

        int codigo = db.delete("HORARIOS", "codHorario = "+horario.getCodigo(), null);

        return codigo;
    }
}

