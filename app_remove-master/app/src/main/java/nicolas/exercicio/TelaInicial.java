package nicolas.exercicio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**  Olá, estou bem empolgado em fazer esse exercício, esse é meu primeiro app usando JSON, pois
 mmeu foco como dev android foi na área de pesquisa ciêntifica, da qual tive que aprender sozinho
 a plataforma android, assim como JAVA em sí haha, enfim, espero que gostem do código que fiz :)
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
    TextView tv;
    IParseJSON json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        Button bt = (Button) findViewById(R.id.button);
        json = new ParseJSON(URL, this);
    }

    public void get(View v){
        JSONObject JSONobj = json.getJsonObj();
    }
}
