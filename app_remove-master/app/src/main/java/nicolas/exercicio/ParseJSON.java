package nicolas.exercicio;

// classe criada para administrar o request dos JSON

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import nicolas.ex1.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ParseJSON implements IParseJSON {

    private JSONObject jObj;
    private String json, URL, aux;

    // custom dialog
    private AlertDialog progress;
    private Context context;

    // metodo construtor, com o contexto passado por parametro
    public ParseJSON(String url, Context context) {
        jObj = null;
        json = "";
        URL = url;
        this.context = context;

        // executa em background
        new GetJson().execute();
    }

    // recebe uma URL da onde quer extrair o JSON e retorna o objeto JSON
    @Override
    public JSONObject getJsonObj(){
        return jObj;
    }

    // classe é executada em background
    private class GetJson extends AsyncTask<String, String, JSONObject> {

        // método executado antes de começar a buscar o JSON
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // LOADING
            progress = new SpotsDialog(context, R.style.CustomDialogProgress);
            progress.show();
        }

        // String... param é um tipo de conteiner de variaveis, que podem ser acessadas como um array
        // Para usar o OkHttp, coloque na suas dependencias: compile 'com.squareup.okhttp3:okhttp:3.2.0'

        // Por questão de constante eficiencia entre as diferentes versões de Android o okHttp se torna
        // uma boa opção, sendo cliente HTTP HTTP2 e multipex de requests. Se fosse usar as APIs padrão,
        // teria que usar a Apache ou o HttpURLConnection.
        @Override
        protected JSONObject doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();

            // API: okHtttp request // monta o request
            Request request = new Request.Builder().url(URL).build();

            // executa o request
            Response response;
            try {
                response = client.newCall(request).execute();

                // se conseguiu uma resposta e transformar para um objeto JSON e então o retorna
                transformToJSONObj(response);
                return jObj;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        // método para transformar a resposta em um objeto JSON, retorna true se conseguiu
        private void transformToJSONObj(Response response) {
            // conteudo do JSON
            try {
                json = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }

            // tenta transformar em ojeto JSON
            try {
                jObj = new JSONObject(json);
                aux = jObj.getString("cidade");

            } catch (JSONException jsonE) {
                jsonE.printStackTrace();
            }
        }

        // Quando o processo acabar
        @Override
        protected void onPostExecute(JSONObject jsonObj) {
            // para o dialog de progresso
            progress.dismiss();
        }
    }
}
