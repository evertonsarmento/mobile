package nicolas.ex1.ListViewComentarios;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nicolas.ex1.R;

public class CustomAdapterComentarios extends ArrayAdapter<ItemRowComentario> {

    private Context context;

    // construtor
    public CustomAdapterComentarios(Context context, int id, List<ItemRowComentario> itens){
        super(context, id, itens);
        this.context = context;
    }

    // view holder para popular a view
    private class ViewHolder{
        ImageView foto, estrela1, estrela2, estrela3, estrela4, estrela5;
        TextView nome, titulo, comentario;
    }

    // TODO não sei muito bem o motivo, mas não consigo separar em pequenas funções como, setviews e popula
    // retorna uma view do item
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        ItemRowComentario item = getItem(position);

        // infla a view
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // seta as views
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lista_comentario, null);
            holder = new ViewHolder();

            holder.foto = (ImageView) convertView.findViewById(R.id.foto_comentario);

            holder.estrela1 = (ImageView) convertView.findViewById(R.id.estrela1);
            holder.estrela2 = (ImageView) convertView.findViewById(R.id.estrela2);
            holder.estrela3 = (ImageView) convertView.findViewById(R.id.estrela3);
            holder.estrela4 = (ImageView) convertView.findViewById(R.id.estrela4);
            holder.estrela5 = (ImageView) convertView.findViewById(R.id.estrela5);

            holder.nome = (TextView) convertView.findViewById(R.id.nome_comentario);
            holder.titulo = (TextView) convertView.findViewById(R.id.titulo_comentario);
            holder.comentario = (TextView) convertView.findViewById(R.id.comentario_comentario);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // popula as views do holder
        holder.foto.setImageBitmap(item.getFoto());
        holder.nome.setText(item.getNome());
        holder.titulo.setText(item.getTitulo());
        holder.comentario.setText(item.getComentario());

        // mostra estrelas
        for(int i = 0; i < item.getNota(); i++){
            switch (i){
                case 0: holder.estrela1.setVisibility(View.VISIBLE); break;
                case 1: holder.estrela2.setVisibility(View.VISIBLE); break;
                case 2: holder.estrela3.setVisibility(View.VISIBLE); break;
                case 3: holder.estrela4.setVisibility(View.VISIBLE); break;
                case 4: holder.estrela5.setVisibility(View.VISIBLE); break;
                default:break;
            }}

        return convertView;
    }

}
