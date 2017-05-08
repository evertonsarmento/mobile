package com.example.sala01.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TesteAPP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_app);

        final TextView txtDinamico = (TextView)findViewById(R.id.txtDinamico);
        Button btClique = (Button) findViewById(R.id.btClique);

        btClique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDinamico.setText("Fui Clicado");
            }

        });
    }


}
