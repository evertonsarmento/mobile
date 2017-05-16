package nicolas.ex1.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nicolas.ex1.Animacao;
import nicolas.ex1.InternetStatus;
import nicolas.ex1.JSON.GetJSON;
import nicolas.ex1.ListViewInicial.CustomAdapterInicial;
import nicolas.ex1.ListViewInicial.ItemRowInicial;
import nicolas.ex1.R;

/**  Olá, estou bem empolgado em fazer esse exercício, esse é meu primeiro app usando JSON e mapa,
 pois mmeu foco como dev android foi na área de pesquisa ciêntifica, da qual tive que aprender sozinho
 a plataforma android, assim como JAVA em sí haha, enfim, espero que gostem do código que fiz ;)
 **/

public class TelaInicial extends AppCompatActivity {

    // URL http://dev.4all.com
    // Porta 3003
    // Logo... http://dev.4all.com:3003/

    // Com essa url, dá para acessar alguns diretórios, como:
    // http://dev.4all.com:3003/tarefa/
    // Onde seu conteúdo é: {"lista":["1","b","c33","ultimo"]}

    // Para acessar um id, basta usar a URL abaixo:
    // http://dev.4all.com:3003/tarefa/:id
    // ":id" ex. http://dev.4all.com:3003/tarefa/c33

    static final private String URL = "http://dev.4all.com:3003/tarefa/";

    private ListView listV;
    private List<ItemRowInicial> itemRow;
    private CustomAdapterInicial adapter;
    private ImageButton retry;

    private GetJSON getJSON;
    private String JSON;
    private JSONObject jObj;
    private JSONArray jArray;

    private Intent intent;

    static final private String EXTRA = "hololooo_TelaInicial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        verificaConexao();
    }

    // verifica se há conexão, se tiver, executa tudo ok, senão deixa a opçao de retry
    private void verificaConexao(){
        retry = (ImageButton) findViewById(R.id.tentaDenovo);

        // verifica se tem conexão com a internet
        InternetStatus internetStatus = new InternetStatus(this);
        if(internetStatus.temConexao()) {
            /** Importante: **/
            // -criar o objeto lista
            // -popula-lo
            // -criar um adapter
            // -habilitar o click dos itens
            buscaDadosJSON();

        } else setRetry(); // se não tiver internet, ativa o botão de retr
    }


    // tenta recomeçar pedido do JSON, com animação de rotação no botao
    private void setRetry() {
        retry.setVisibility(View.VISIBLE);
        final Context context = this;

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animacao anim = new Animacao();
                anim.animaRotacionaHorario(context, retry);
                buscaDadosJSON();
            }
        });
    }

    // cuida de conseguir o JSON "cru"
    private void buscaDadosJSON(){
        listV = (ListView) findViewById(R.id.listViewInicial);

        // Executa em background para conseguir o JSON da url
        // Passa o Context como referencia para o progress, url e listener
        getJSON = new GetJSON(this, URL, (new GetJSON.listenerGetJSON() {
            @Override
            public void getJSON(String json) {
                JSON = json;

                retiraRetry();

                // salva os titulos e popula a lista quando o request acaba
                preparaObjetosDaLista();
                populaLista();
                criaListAdapter();
                animaLista();
                listClick();
            }
        }));
        getJSON.execute();
    }

    // retira o logo de retry com animação
    private void retiraRetry(){
        // retira o logo
        Animacao anim = new Animacao();
        anim.animaFadeOut(this, retry);
        retry.setVisibility(View.GONE);
    }


    // prepara a lista com os Objetos JSON
    private void preparaObjetosDaLista(){
        itemRow = new ArrayList<>();

        // tenta transformar o JSON em objeto JSON
        try {
            jObj = new JSONObject(JSON);
        } catch (JSONException e){
            e.printStackTrace();
        }

        // tenta colocar o array lista no jArray
        try {
            jArray = jObj.getJSONArray("lista");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    // popula a lista com os id do JSON
    private void populaLista(){
        // popula de fato a lista
        for(int i = 0; i < jArray.length(); i++){
            try{
                ItemRowInicial item = new ItemRowInicial(jArray.getString(i));
                itemRow.add(item);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    // Método que cria um adapter quando a lista já estiver populada
    private void criaListAdapter(){
        adapter = new CustomAdapterInicial(this, R.layout.item_lista_inicial, itemRow);
        listV.setAdapter(adapter);
    }

    // cria animação para a lista
    private void animaLista(){
        Animacao anim = new Animacao();
        anim.animaSlideDeCima(this, listV);
    }

    // Habilita o click dos itens da listview e manda o id para a próxima activity
    // Nesse estilo de método, a ação é executada quando para de pressionar em cima do item
    private void listClick(){
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                try {
                    nextActivity(jArray.getString(pos));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // método para ir para a próxima activity
    private void nextActivity(String id){
        intent =  new Intent(this, TelaPrincipal.class);
        intent.putExtra(EXTRA, id);
        startActivity(intent);
    }

    // retorna a key unica dessa activity
    static public String getExtra(){
        return EXTRA;
    }

    // retorna a URL inicial
    static public String getUrl(){
        return URL;
    }

}
