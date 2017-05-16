package nicolas.ex1.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nicolas.ex1.Animacao;
import nicolas.ex1.JSON.GetImgDaWeb;
import nicolas.ex1.JSON.GetJSON;
import nicolas.ex1.JSON.LugarJSON;
import nicolas.ex1.ListViewComentarios.CustomAdapterComentarios;
import nicolas.ex1.ListViewComentarios.ItemRowComentario;
import nicolas.ex1.ListViewInicial.CustomAdapterInicial;
import nicolas.ex1.ListViewInicial.ItemRowInicial;
import nicolas.ex1.R;

// problema em carrefgar em imagem, as vezes nao carrega

public class TelaPrincipal extends AppCompatActivity implements OnMapReadyCallback {

    private String URL_, ID;

    private boolean backClicado = false;
    private Intent intent;
    private TextView titleToolbar, tituloLocal, textoLocal, enderecoMapa;
    private ImageButton backBtn, searchButton, ligar, servicos, endereco, comentarios, favoritos;
    private ImageView marker, fotoLocal, logoLocal, peliculaMapa;
    private LinearLayout layoutLogos, conjuntoLogos;
    private ScrollView scroll;
    private FrameLayout layEndereco, layMapa;
    private ListView listView;

    private GoogleMap gMapa;

    private List<ItemRowComentario> itemRowComentario;
    private GetJSON getJSON;
    private String JSON;
    private JSONArray jArrayComentario;
    private JSONObject jObj, jObjComent;
    private LugarJSON lugarJSON;
    private GetImgDaWeb getImgDaWeb;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        getIDfromIntent();

        setViews();
        setURL();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        buscaDadosJSON();
    }

    // método recebe o ID escolhido do JSON da activity anterior
    private void getIDfromIntent() {
        // recebe os dados passados da activity anterior
        intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) ID = bundle.getString(TelaInicial.getExtra());
    }

    // método instanciador das views
    private void setViews() {
        titleToolbar = (TextView) findViewById(R.id.TitleTelaPrincipal);
        marker = (ImageView) findViewById(R.id.marker);
        backBtn = (ImageButton) findViewById(R.id.backButton);
        setBackButton(backBtn);
        scroll = (ScrollView) findViewById(R.id.scroll_principal);

        /** itens do JSON **/
        setViewsPrimeirapt();
        setViewsSegundapt();
        setViewsTerceirapt();

        listView = (ListView) findViewById(R.id.listViewComentarios);
    }

    private void setViewsPrimeirapt(){
        // primeira prte
        fotoLocal = (ImageView) findViewById(R.id.imagemLocal);
        logoLocal = (ImageView) findViewById(R.id.imagem_flutuante);
        tituloLocal = (TextView) findViewById(R.id.tituloLocal);
    }

    private void setViewsSegundapt(){
        // segunda parte
        textoLocal = (TextView) findViewById(R.id.texto);
        layoutLogos = (LinearLayout) findViewById(R.id.layout_logos);
        conjuntoLogos = (LinearLayout) findViewById(R.id.conjunto_logos);
        animaLayoutLogos();
        ligar = (ImageButton) findViewById(R.id.bt_liga);
        servicos = (ImageButton) findViewById(R.id.bt_servicos);
        endereco = (ImageButton) findViewById(R.id.bt_endereco);
        comentarios = (ImageButton) findViewById(R.id.bt_comentarios);
        favoritos = (ImageButton) findViewById(R.id.bt_favoritos);
    }

    private void setViewsTerceirapt(){
        // terceira parte
        layMapa = (FrameLayout) findViewById(R.id.layout_mapa);
        enderecoMapa = (TextView) findViewById(R.id.descricao_mapa);
        peliculaMapa = (ImageView) findViewById(R.id.pelicula_mapa);
        layEndereco = (FrameLayout) findViewById(R.id.layout_endereco);
    }


    // anima o layout dos logos, mas não deixa eles clicaveis ainda
    private void animaLayoutLogos() {
        // começa a parte de animação do layout da segunda parte
        //animaFadeIn(this, layoutLogos);

        Animacao anim = new Animacao(new Animacao.ListenerAnimacao() {
            @Override
            public void animacaoAcabou(boolean acabou) {
                animaConjuntoLogos();
            }
        });
        //anim.comListener();
        //anim.animaCresDeCima(this, layoutLogos);
        //if(anim.ok()) animaConjuntoLogos();
        anim.animaCresDeCima(this, layoutLogos);

    }

    // anima o conjunto de logos
    private void animaConjuntoLogos() {
        // mostra o conjunto de logos, mas não deixa clicar ainda

        Animacao anim = new Animacao();
        anim.animaSlideDeCima(this, conjuntoLogos);
    }

    // pega a url inicial e adiciona o id escolhido
    private void setURL() {
        URL_ = TelaInicial.getUrl() + ID;
    }

    // cuida de conseguir o JSON "cru"
    private void buscaDadosJSON() {
        // Executa em background para conseguir o JSON da url
        // Passa o Context como referencia para o progress, url e listener
        getJSON = new GetJSON(this, URL_, (new GetJSON.listenerGetJSON() {
            @Override
            public void getJSON(String json) {
                // quando a tarefa em background acabar, executa as ações e metodos abaixo
                JSON = json;

                criaObjetoJSON(JSON);
                populaLugar();
                populaAsViews();

                exibeComentarios();
            }
        }));
        getJSON.execute();
    }

    // cria um ojeto JSON e organiza as variaveis
    private void criaObjetoJSON(String json) {
        // cria um objeto lugar para guardar as informações
        lugarJSON = new LugarJSON();

        // cria um Objeto JSON
        try {
            jObj = new JSONObject(json);
            jObjComent = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // método criado para popular a classe lugar
    private void populaLugar() {
        try {
            lugarJSON.setId(jObj.getString("id"));
            lugarJSON.setCidade(jObj.getString("cidade"));
            lugarJSON.setBairro(jObj.getString("bairro"));
            lugarJSON.setUrlFoto(jObj.getString("urlFoto"));
            lugarJSON.setUrlLogo(jObj.getString("urlLogo"));
            lugarJSON.setTitulo(jObj.getString("titulo"));
            lugarJSON.setTelefone(jObj.getString("telefone"));
            lugarJSON.setTexto(jObj.getString("texto"));
            lugarJSON.setEndereco(jObj.getString("endereco"));
            lugarJSON.setLatitude(jObj.getString("latitude"));
            lugarJSON.setLongitude(jObj.getString("longitude"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    // cria lista de comentarios
    private void exibeComentarios(){
        /** Importante: **/
        // -criar o objeto lista
        // -popula-lo
        // -criar um adapter
        // -habilitar o click dos itens

        preparaObjetosDaLista();
        populaLista();
        criaListAdapter();
        animaLista();
        listClick();
    }


    // prepara a lista com os Objetos JSON
    private void preparaObjetosDaLista(){
        itemRowComentario = new ArrayList<>();

        // tenta colocar o array lista no jArray
        try {
            jArrayComentario = jObjComent.getJSONArray("comentarios");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    // popula a lista com os id do JSON
    private void populaLista(){
        // popula de fato a lista
        for(int i = 0; i < jArrayComentario.length(); i++){
            try{
                // transforma cada item do JSON array em objeto
                JSONObject jObjtComentario = jArrayComentario.getJSONObject(i);
                final ItemRowComentario itemAux = new ItemRowComentario();

                String urlFoto = jObjtComentario.getString("urlFoto");

                /*
                new GetImgDaWeb(this, null, urlFoto,(new GetImgDaWeb.ListenerImg() {
                    @Override
                    public void getImgWeb(Bitmap bitmap) {
                        itemAux.setFoto(bitmap);
                    }
                }));
                */

                //get bitmap of the image
                Bitmap imageBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.um);
                itemAux.setFoto(getclip(imageBitmap));

                itemAux.setNome(jObjtComentario.getString("nome"));
                itemAux.setTitulo(jObjtComentario.getString("titulo"));
                itemAux.setNota(jObjtComentario.getInt("nota"));
                itemAux.setComentario(jObjtComentario.getString("comentario"));

                itemRowComentario.add(itemAux);
            } catch (JSONException | NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    public static Bitmap getclip(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    // Método que cria um adapter quando a lista já estiver populada
    private void criaListAdapter(){
        CustomAdapterComentarios adapter =
                new CustomAdapterComentarios(this, R.layout.item_lista_comentario, itemRowComentario);
        listView.setAdapter(adapter);
    }

    // cria animação para a lista
    private void animaLista(){
        Animacao anim = new Animacao();
        anim.animaSlideDeCima(this, listView);
    }


    // Habilita o click dos itens da listview e manda o id para a próxima activity
    // Nesse estilo de método, a ação é executada quando para de pressionar em cima do item
    private void listClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            }
        });
    }




    // Popula as view referentes ao JSON
    // Se fizer uma task em background acho que vai ter muito vazamento de memoria, pois são varias
    // coisas tentando ser populadas ao mesmo tempo
    private void populaAsViews() {
        setTitleToolbar();
        setImgDaWeb(fotoLocal, lugarJSON.getUrlFoto());
        setImgDaWeb(logoLocal, lugarJSON.getUrlLogo());
        setTituloLocal();
        setTextoLocal();
        setLogosClick();
        setEndereco();
        criaMapa();
        animaMapa();
    }


    // anima elementos do mapa
    private void animaMapa(){
        layMapa.setVisibility(View.VISIBLE);
        Animacao anim = new Animacao(new Animacao.ListenerAnimacao() {
            @Override
            public void animacaoAcabou(boolean acabou) {
                animaEndereco();
            }
        });
        anim.animaCresDeCima(this, layMapa);
    }


    // anima o layout do endereço
    private void animaEndereco(){
        Animacao anim = new Animacao();
        anim.animaCresDeBaixo(this, layEndereco);
    }


    // popula o endereço
    private void setEndereco(){
        String rua ="", numero ="";
        try {
            String[] sAux = lugarJSON.getEndereco().split(" ");

            // separa o que é numero e o que é palavra
            // supondo que não há erros no arquivo
            for (String s : sAux) {
                if (s.matches("\\d+")) numero = s;
                else rua += s + " ";
            }

            // virgula para dividir a rua do numero
            // não é necessario fazer append se passar a string no construtor, mas cuidado
            // toda alteração auxiliar será feita imediatamente
            StringBuilder sb = new StringBuilder(rua);
            sb.setCharAt(rua.length() - 2, ',');

        } catch (NullPointerException e){
            e.printStackTrace();
        }

        enderecoMapa.setText(rua + " " + numero);
    }


    // cria o mapa e prepara em background para receber parametros
    private void criaMapa() {
        // outra maneira mais facil e leve de ser implementada:
        // https://developers.google.com/maps/documentation/static-maps/intro#MapTypes
        // String url = "https://maps.googleapis.com/maps/api/staticmap?center=";
        // 40.714728,-73.998672&zoom=12&size=400x400&maptype=roadmap&key=YOUR_API_KEY
        // então seria só chamar no getImgDaWeb e popular a imageview em fez do fragment
        // mas não seria possivel navegar pelo maps, ele seria estatico no caso

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);

        try {
            mapFragment.getMapAsync(this);
        } catch(NullPointerException e){
            e.printStackTrace();
        }

        // cuida da navegação de modo que não interfira com o scrollview
        navegacaoDoMapa();
    }


    // método cuida da interferencia entre o scrollview e a navegação do mapa
    private void navegacaoDoMapa(){
        peliculaMapa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        scroll.requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_UP:
                        scroll.requestDisallowInterceptTouchEvent(false);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        scroll.requestDisallowInterceptTouchEvent(true);
                        return false;
                    default:
                        return true;
                }
            }
        });
    }


    // Não havia conseguido pensar em um jeito de fazer uma classe generica para cuidar disso
    // por causa das varias açoes assincronas ele acabou nao funcionando
    // esse método espera o mapa ser criado e quando estiver pronto popula-o
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMapa = googleMap;

        // adiciona um marcador
        LatLng latLng = new LatLng(Double.valueOf(lugarJSON.getLatitude()),
                Double.valueOf(lugarJSON.getLongitude()));

        gMapa.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(getPrimeiroNomeDoLugar())
                        .alpha(1)
                        .draggable(false)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)));
        gMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }



    // habilita os logos a serem clicados
    private void setLogosClick() {
        fadeInAnimacao(conjuntoLogos, 400);
        final Context context = this;

        ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // tenta fazer a ligação
                ligaParaNumero(context);
            }});

        servicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // vai pra outra activity
            }});

        endereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mostra popup com endereço
            }});

        comentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // faz scroll pros comentarios
            }});

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { /* do nothing club */}});

    }

    // checa se o usuario deu permissao para ligar direto
    private boolean podeLigarDireto(Context context){
        // pelo sistema de segurança do android, não da para simpesmente ligar e funcionar em todos aparelhos
        String permissao = "android.permission.CALL_PHONE";
        int resposta = context.checkCallingOrSelfPermission(permissao);

        return (resposta == PackageManager.PERMISSION_GRANTED);
    }


    // faz a ação de ligar ou apenas discar o numero
    private void ligaParaNumero(Context context){
        Intent intent;

        // verifica se tem permissao
        if(podeLigarDireto(context)){
            // se tem permissao liga direto
            intent = new Intent(Intent.ACTION_CALL);
        } else {
            // se não der apenas disca o numero
            intent = new Intent(Intent.ACTION_DIAL);
        }

        intent.setData(Uri.parse("tel:" + lugarJSON.getTelefone()));
        context.startActivity(intent);
    }


    // meia animação de fadeIn
    private void fadeInAnimacao(View view, int duration) {
        view.animate().alpha(1).alphaBy(view.getAlpha()).setDuration(duration).start();
    }


    // método para setar o texto descrevendo o local
    private void setTextoLocal() {
        animaFadeIn(textoLocal);
        Animacao anim = new Animacao();
        anim.animaCresDeCima(this, textoLocal);
        textoLocal.setText(lugarJSON.getTexto());
    }


    // método genérico para animação de fadein
    private void animaFadeIn(View view) {
        Animacao anim = new Animacao();
        anim.animaFadeIn(this, view);
    }


    // coloca o primeiro nome no titulo
    private void setTituloLocal() {
        // sombra de cima do container
        View view = findViewById(R.id.sombra_cima);
        animaFadeIn(view);

        tituloLocal.setText(getPrimeiroNomeDoLugar());
        Animacao anim = new Animacao();
        anim.animaCresDaEsquerda(this, tituloLocal);
    }


    // retorna apenas o primeiro nome do lugar
    private String getPrimeiroNomeDoLugar() throws NullPointerException {
        /** Não sabia se tinha que seguir meio que a risca o layout criado por vocês, então
         * resolvi fazer como na screenshoot, onde tinha apenas o primeiro nome e em upper
         */
        String[] sAux = lugarJSON.getTitulo().toUpperCase().split(" ");
        // percorre a lista a procura de uma palavra
        for (String s : sAux) {
            if (s != null) {
                return s;
            }
        }
        return null;
    }


    // troca o titulo da toolbar com animação
    private void setTitleToolbar() {
        Animacao animTitulo = new Animacao();
        titleToolbar.setText(lugarJSON.getCidade() + " - " + lugarJSON.getBairro());

        // animação de slide da direita para a esqerda do marker/pin
        Animation animMarker = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_diresq);
        marker.setAnimation(animMarker);

        // por fim, animação de crescimento da esquerda para a direita acontencendo ao mesmo tempo
        animTitulo.animaCresDaEsquerda(this, titleToolbar);
    }


    // adiciona a imagem do local salvando-a, passando a imageview e a url como parametro
    private void setImgDaWeb(final ImageView view, String url) {
        View progressView = findViewById(R.id.avloadingIndicatorView);
        final Context context = this;

        getImgDaWeb = new GetImgDaWeb(this, progressView, url, (new GetImgDaWeb.ListenerImg() {
            @Override
            public void getImgWeb(Bitmap bitmap) {
                view.setImageBitmap(bitmap);
                Animacao anim = new Animacao();
                anim.animaFadeIn(context, view);
            }
        }));
        getImgDaWeb.execute();

    }

    // seta o listener do botão para voltar
    private void setBackButton(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Se não tratar isso, o app fica bem inconsistente e lento, pois vai criando tarefas
                // em background assincronas que não tem mais uso, essa checagem faz muita diferença
                try {
                    if (getImgDaWeb.getStatus() != AsyncTask.Status.FINISHED)
                        getImgDaWeb.cancel(true);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                backClicado = true;

                // o método on back pressed, do qual chama o finish, não são bons de chamar, chama onDestoy
                // nesse caso não faria diferença, mas em outros casos em que precisa salva algo ou
                // cuidar com mais calma um método é interessante chamar o onStop ou onPause
                // ótimo material: http://developer.android.com/training/basics/activity-lifecycle/starting.html
                onStop();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();



        // sempre tem que cuidar pra não voltar por qualquer coisa
        if (backClicado) NavUtils.navigateUpFromSameTask(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }


}
