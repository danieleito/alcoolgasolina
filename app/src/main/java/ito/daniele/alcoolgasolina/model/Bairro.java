package ito.daniele.alcoolgasolina.model;

/**
 * Created by Daniele on 30/11/2016.
 */

public class Bairro {
    private int codigo;
    private String nome;
    private int codigoMunicipio;
    public Bairro() {
    }

    public Bairro(int codigo, String nome, int codigoMunicipio) {
        this.codigo = codigo;
        this.nome = nome;
        this.codigoMunicipio = codigoMunicipio;
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

    public int getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(int codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
