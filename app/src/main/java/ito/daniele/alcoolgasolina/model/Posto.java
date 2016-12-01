package ito.daniele.alcoolgasolina.model;

/**
 * Created by Daniele on 30/11/2016.
 */

public class Posto {
    private String nome;
    private int codigoBairro;
    private double alcool;
    private double gasolina;

    public Posto() {
    }

    public Posto(String nome, int codigoBairro, double alcool, double gasolina) {
        this.nome = nome;
        this.codigoBairro = codigoBairro;
        this.alcool = alcool;
        this.gasolina = gasolina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(int codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public double getAlcool() {
        return alcool;
    }

    public void setAlcool(double alcool) {
        this.alcool = alcool;
    }

    public double getGasolina() {
        return gasolina;
    }

    public void setGasolina(double gasolina) {
        this.gasolina = gasolina;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
