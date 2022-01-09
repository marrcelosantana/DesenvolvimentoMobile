package com.example.trabalho03.model;

public class Jogador {
    private String uid;
    private String nome;
    private String clube;
    private String nacionalidade;

    public Jogador() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
        return "Jogador: " + nome + " - " + clube + " - " + nacionalidade + '\n';
    }
}
