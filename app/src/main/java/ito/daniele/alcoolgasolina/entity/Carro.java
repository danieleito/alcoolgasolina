package ito.daniele.alcoolgasolina.entity;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;

/**
 * Created by Daniele on 24/11/2016.
 */

public class Carro extends SugarRecord {

    @NotNull
    private String nome;
    @NotNull
    private double alcoolCidade;
    @NotNull
    private double gasolinaCidade;
    @NotNull
    private double alcoolViagem;
    @NotNull
    private double gasolinaViagem;

    public Carro() {
    }

    public Carro(String nome, double alcoolCidade, double gasolinaCidade, double alcoolViagem, double gasolinaViagem) {
        this.nome = nome;
        this.alcoolCidade = alcoolCidade;
        this.gasolinaCidade = gasolinaCidade;
        this.alcoolViagem = alcoolViagem;
        this.gasolinaViagem = gasolinaViagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAlcoolCidade() {
        return alcoolCidade;
    }

    public void setAlcoolCidade(double alcoolCidade) {
        this.alcoolCidade = alcoolCidade;
    }

    public double getGasolinaCidade() {
        return gasolinaCidade;
    }

    public void setGasolinaCidade(double gasolinaCidade) {
        this.gasolinaCidade = gasolinaCidade;
    }

    public double getAlcoolViagem() {
        return alcoolViagem;
    }

    public void setAlcoolViagem(double alcoolViagem) {
        this.alcoolViagem = alcoolViagem;
    }

    public double getGasolinaViagem() {
        return gasolinaViagem;
    }

    public void setGasolinaViagem(double gasolinaViagem) {
        this.gasolinaViagem = gasolinaViagem;
    }

    @Override
    public String toString() {
        return nome;
    }
}
