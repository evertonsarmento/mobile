package com.example.sala01.myapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

public class Dialog2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog2);

    }

    public void clique(View view)
    {
        switch (view.getId()) {
            case R.id.button91:
                criaDialog();
        }

    }

    public void criaDialog () {

        new MaterialDialog.Builder(this)
                .title(R.string.title)
                .content(R.string.desc)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        String[] mArray = getResources().getStringArray(R.array.mArray);

                        ((Button)findViewById(R.id.button91)).setText(position);
                    }
                })
                .show();


    }
}
