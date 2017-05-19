package com.example.sala01.doesangue;

/**
 * Created by sala01 on 12/05/2017.
 */
public class Item {

    private String nome;
    private String endereco;

    public Item(String nome, String endereco) {
        this.nome = nome;
        this.endereco  = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereço(String endereco) {
        endereco = endereco;
    }
}