package com.example.sala01.bilioapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sala01 on 12/05/2017.
 */

public class ListaAdapter  extends ArrayAdapter<Item> {

    List<Item> items;


    // View lookup cache
    private static class ViewHolder {
        TextView nome;
    }


    public ListaAdapter(Context context, List<Item> users) {
        super(context, R.layout.item_user, users);

        items = users;
    }

    /* exemplo sem viewHolder
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);

        Log.d("mlog", items.get(position).getNome());

        // Lookup view for data population
        TextView nome = (TextView) convertView.findViewById(R.id.nome);

        nome.setText(items.get(position).getNome());
        //tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }*/

    //exemplo com viewHolder

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;


        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);

        //Log.d("mlog", items.get(position).getNome());
        viewHolder = new ViewHolder();
        viewHolder.nome = (TextView) convertView.findViewById(R.id.nome);

        convertView.setTag(viewHolder);

        viewHolder.nome.setText(items.get(position).getNome());

        // Return the completed view to render on screen
        return convertView;
    }

}
