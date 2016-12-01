package ito.daniele.alcoolgasolina.model;

/**
 * Created by Daniele on 30/11/2016.
 */

public class Municipio {
    private int codigo;
    private String nome;
    private int codigoEstado;

    public Municipio() {
    }

    public Municipio(int codigo, String nome, int codigoEstado) {
        this.codigo = codigo;
        this.nome = nome;
        this.codigoEstado = codigoEstado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
