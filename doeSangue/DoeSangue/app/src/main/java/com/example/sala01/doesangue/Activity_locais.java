package com.example.sala01.doesangue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Activity_locais extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locais);



        List<Item> arrayOfItens = new ArrayList<Item>();

        arrayOfItens.add(new Item("local 1", "endereco 1"));
        arrayOfItens.add(new Item("local 2", "endereco 2"));

        // Create the adapter to convert the array to views
        ListaItemAdapter adapter = new ListaItemAdapter(this, arrayOfItens);


        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listaLocais);
        listView.setAdapter(adapter);
    }
}
