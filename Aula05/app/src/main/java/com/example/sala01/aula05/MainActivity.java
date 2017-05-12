package com.example.sala01.aula05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> arrayOfItens = new ArrayList<Item>();

        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));

        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));
        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));
        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));
        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));
        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));
        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));
        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));
        arrayOfItens.add(new Item("Everton"));
        arrayOfItens.add(new Item("Luis"));
        arrayOfItens.add(new Item("Diego"));


        // Create the adapter to convert the array to views
        ListaAdapter adapter = new ListaAdapter(this, arrayOfItens);


        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvItems);
        listView.setAdapter(adapter);


    }
}
