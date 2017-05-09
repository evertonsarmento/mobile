package com.example.sala01.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txt = (TextView)findViewById(R.id.textView);

        txt.setText(getIntent().getStringExtra("mTag"));

        User usuario = new Gson().fromJson(getIntent().getStringExtra("user"), User.class);


        Button btIrPara = (Button) findViewById(R.id.btVoltar);

        btIrPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToAnother();

            }

        });

    }

    private void goToAnother() {
        Intent intent = new Intent(this, TesteAPP.class);
        startActivity(intent);
    }

}
