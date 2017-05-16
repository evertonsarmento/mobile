package nicolas.ex1.JSON;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import nicolas.ex1.InternetStatus;
import nicolas.ex1.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// É interessante que essa classe esteja separada da main ou de outro lugar, pois assim é possível
// utiliza-la mais de uma vez
// Com essa classe estou apenas buscando em background o JSON
public class GetJSON extends AsyncTask<String, String, String>{

    private String URL;
    private Context context;
    // custom dialog
    private AlertDialog progress;


    /** interface do listener **/
    // nesse caso se saiu bem útil para poder acessar de outras classe
    // interface abstrata, não é instanciada, mas é acessada como uma subInterface

    public interface listenerGetJSON {
        // método retorna o JSON "cru"
        void getJSON(String json);
    }
    private listenerGetJSON listener = null;
    /** fim do listener **/


    // método construtor tendo contexto, url e o listener como referencia
    public GetJSON(Context context, String url, listenerGetJSON listener){
        this.context = context;
        this.listener = listener;
        URL = url;
    }

    // método executado antes de começar a buscar o JSON
    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        InternetStatus internetStatus = new InternetStatus(context);
        // se não tiver conexao com internet cancela tarefa em background
        if(!internetStatus.temConexao()) cancel(true);
        else {
            // LOADING
            progress = new SpotsDialog(context, R.style.CustomDialogProgress);
            progress.setCancelable(false);
            progress.show();
        }
    }

    // String... param é um tipo de conteiner de variaveis, que podem ser acessadas como um array
    @Override
    protected String doInBackground(String... param){
        // cria uma demanda http no socket
        OkHttpClient okClient = new OkHttpClient();

        /*
        Para usar o OkHttp, coloque nas dependencias: compile 'com.squareup.okhttp3:okhttp:3.2.0'
        Por questão de constante eficiencia entre as diferentes versões de Android o okHttp
        se torna uma boa opção, sendo cliente HTTP HTTP2 e multiplex de requests. Se fosse
        usar as APIs padrão, teria que usar a Apache ou o HttpURLConnection.
        */

        // cria um pedido para a url
        Request request = new Request.Builder().url(URL).build();

        // tenata conseguir uma resposta
        Response response = null;
        try{
            response = okClient.newCall(request).execute();
        } catch (IOException e){
            e.printStackTrace();
        }

        // se conseguiu algo, tenta retornar o que conseguiu
        try{
            if(response != null) return response.body().string();
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    // se tudo ocorreu bem, chama o método para popular os títulos
    @Override
    protected void onPostExecute(String json){
        progress.dismiss();
        if(json != null) listener.getJSON(json);
    }

}
