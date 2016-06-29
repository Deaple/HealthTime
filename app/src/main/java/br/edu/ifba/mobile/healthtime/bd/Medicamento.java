package br.edu.ifba.mobile.healthtime.bd;

import java.util.Calendar;

/**
 * Created by isaac on 25/06/16.
 */
public class Medicamento {
    private String nomeMedicamento;
    private String nomeFarmacia;
    private String telFarmacia;
    private int nroLembretesDiarios;
    private int quantidadeDiaria;
    private String horarioInicial;
    private String restricoes;
    private long codigo = -1;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(String restricoes) {
        this.restricoes = restricoes;
    }

    public int getQuantidadeDiaria() {
        return quantidadeDiaria;
    }

    public void setQuantidadeDiaria(int quantidadeDiaria) {
        this.quantidadeDiaria = quantidadeDiaria;
    }

    public int getNroLembretesDiarios() {
        return nroLembretesDiarios;
    }

    public void setNroLembretesDiarios(int nroLembretesDiarios) {
        this.nroLembretesDiarios = nroLembretesDiarios;
    }

    public String getTelFarmacia() {
        return telFarmacia;
    }

    public void setTelFarmacia(String telFarmacia) {
        this.telFarmacia = telFarmacia;
    }

    public String getNomeFarmacia() {
        return nomeFarmacia;
    }

    public void setNomeFarmacia(String nomeFarmacia) {
        this.nomeFarmacia = nomeFarmacia;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    @Override
    public String toString(){
        return "Medicamento: "+nomeMedicamento+" horário: "+Calendar.getInstance().getTime();
    }

}
