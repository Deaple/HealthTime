package br.edu.ifba.mobile.healthtime.bd;

/**
 * Created by isaac on 06/07/16.
 */
public class Horario {
    private String horarioInicial;
    private int intervaloHorarios;
    private long codigo = -1;
    //valor deduzido com base na variável anterior
    private int quantidadeDiaria;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public int getIntervaloHorarios() {
        return intervaloHorarios;
    }

    public void setIntervaloHorarios(int intervaloHorarios) {
        this.intervaloHorarios = intervaloHorarios;
    }

    public int getQuantidadeDiaria() {
        return quantidadeDiaria;
    }

    public void setQuantidadeDiaria(int quantidadeDiaria) {
        this.quantidadeDiaria = quantidadeDiaria;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    @Override
    public String toString(){
        return "Próx. Horario: "+horarioInicial+"\n "+quantidadeDiaria+
                " vez(es) ao dia, a cada "+intervaloHorarios+" Hrs.";
    }
}
