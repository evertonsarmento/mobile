package com.example.sala01.bilioapp;

/**
 * Created by sala01 on 12/05/2017.
 */
public class Item {

    private String nome;
    private String imagem;

    public Item(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}