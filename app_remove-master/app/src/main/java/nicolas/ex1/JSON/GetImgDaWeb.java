package nicolas.ex1.JSON;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

import nicolas.ex1.InternetStatus;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetImgDaWeb extends AsyncTask<String, String, Bitmap> {

    private String URL;
    private View progressView;
    private Context context;

    // método construtor, view como parametro pois tem o progress
    public GetImgDaWeb(Context context, View view, String url, ListenerImg listenerImg){
        this.context = context;
        this.listenerImg = listenerImg;
        if(view != null) progressView = view;
        URL = url;
    }

    /** interface que tem função de listener **/
    public interface ListenerImg{
        void getImgWeb(Bitmap bitmap);
    }
    private ListenerImg listenerImg = null;
    /****/


    // método executado antes de começar a busca
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        InternetStatus internetStatus = new InternetStatus(context);
        // se não tiver conexao com internet cancela tarefa em background
        if(!internetStatus.temConexao()) cancel(true);
        else {
            // começa o custom loading
            progressView.setVisibility(View.VISIBLE);
        }
    }

    // String... param é um tipo de conteiner de variaveis, que podem ser acessadas como um array
    @Override
    protected Bitmap doInBackground(String... param) {
        // abre um pedido http
        OkHttpClient okClient = new OkHttpClient();

        // fazum request para a url
        Request request = new Request.Builder().url(URL).build();

        // tenta conseguir uma resposta
        Response response = null;
        try {
            response = okClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // se conseguiu uma resposta, transforma em bitmap
        if (response != null) {
            // consegue os bytes para formar o bitmap
            InputStream inputStream = response.body().byteStream();

            // retorna o bitmap conseguido apartir da inputstream
            return BitmapFactory.decodeStream(inputStream);

        } else return null;
    }

    // se tudo ocorreu bem devolve o listner devolve a bitmap
    @Override
    protected void onPostExecute(Bitmap bitmap){
        // termina o custom loading
        progressView.setVisibility(View.GONE);
        if(bitmap != null) listenerImg.getImgWeb(bitmap);
    }

}
