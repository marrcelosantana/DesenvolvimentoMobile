package com.example.atv02;

import java.io.Serializable;

public class Jogador implements Serializable {
    private String nome;
    private String clube;
    private String nacionalidade;

    public Jogador(String nome, String clube, String nacionalidade) {
        this.nome = nome;
        this.clube = clube;
        this.nacionalidade = nacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", clube='" + clube + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }
}
