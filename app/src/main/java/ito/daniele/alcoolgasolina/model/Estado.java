package ito.daniele.alcoolgasolina.model;

/**
 * Created by Daniele on 30/11/2016.
 */

public class Estado {
    private int codigo;
    private String nome;
    private String uf;

    public Estado() {
    }

    public Estado(int codigo, String nome, String uf) {
        this.codigo = codigo;
        this.nome = nome;
        this.uf = uf;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
