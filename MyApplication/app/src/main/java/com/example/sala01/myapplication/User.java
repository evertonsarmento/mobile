package com.example.sala01.myapplication;

import java.io.Serializable;

/**
 * Created by sala01 on 09/05/2017.
 */

public class User implements Serializable {

    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
