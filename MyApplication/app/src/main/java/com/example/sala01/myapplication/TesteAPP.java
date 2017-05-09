package com.example.sala01.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

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

        Button btIrPara = (Button) findViewById(R.id.btIrPara);

        btIrPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               goToAnother();

            }

        });
    }

    private void goToAnother (){
        Intent intent = new Intent(this, MainActivity.class);

        final EditText edit = (EditText) findViewById(R.id.edit);

        intent.putExtra("mTag", edit.getText().toString() );

        User usuario = new User();
        usuario.setId(1);
        usuario.setNome("nomeUsuario");


        intent.putExtra("user", new Gson().toJson(usuario) );

        startActivity(intent);
    }

}
