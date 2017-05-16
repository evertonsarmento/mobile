package nicolas.ex1.ListViewInicial;

// Classe que admnistra os itens da tela inicial

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import nicolas.ex1.R;

public class CustomAdapterInicial extends ArrayAdapter<ItemRowInicial> {

    Context context;

    // Método construtor da lista
    public CustomAdapterInicial(Context context, int id, List<ItemRowInicial> itens){
        super(context,id, itens);
        this.context = context;
    }

    // Holder de views da celula
    private class ViewHolder{
        TextView nome;
    }

    // Retorna a célula já populada
    public View getView(int pos, View view, ViewGroup viewGroup){
        ViewHolder viewHolder;
        ItemRowInicial item = getItem(pos);

        // Cria o espaço da célula
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // Deixa a view pronta para ser populada se ela já não estiver pronta
        if(view == null){
            view = inflater.inflate(R.layout.item_lista_inicial, null);

            viewHolder = new ViewHolder();
            viewHolder.nome = (TextView) view.findViewById(R.id.nomeInicial);
            view.setTag(viewHolder);

        } else viewHolder = (ViewHolder) view.getTag();

        // popula de fato a view
        viewHolder.nome.setText(item.getNome());

        // retorna a view já populada
        return view;
    }

}
