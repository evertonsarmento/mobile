package nicolas.ex1;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// classe de verificação de status de internet

public class InternetStatus {

    private NetworkInfo conexao;

    // status = false --> nao tem conexao com internet
    // status = true  --> tem conexao com internet
    private boolean status;

    // wifiStatus = false --> é conexao movel
    // wifiStatus = true  --> é conexao wifi
    private boolean wifiStatus;

    // método construtor, passa contexto como parametro
    public InternetStatus(Context context){
        // cria um objeto de serviço de conexao
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // verifica o tipo de conexao
        conexao = connectivityManager.getActiveNetworkInfo();

        // determina tem conexao com internet
        status = conexao != null && conexao.isConnected();

        // se tem conexao, determina se é wifi ou movel
        if(status) wifiStatus = conexao.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public boolean temConexao(){
        return status;
    }

    public boolean ehWifi(){
        return wifiStatus;
    }
}
