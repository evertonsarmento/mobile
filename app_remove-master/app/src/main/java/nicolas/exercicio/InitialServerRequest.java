package nicolas.exercicio;

// classe criada para administrar o request inicial da lista

public class InitialServerRequest implements I_InitialServerRequest {

    // URL http://dev.4all.com
    // Porta 3003
    // Logo... http://dev.4all.com:3003/

    // Com essa url, dá para acessar alguns diretórios, como:
    // http://dev.4all.com:3003/tarefa/
    // Onde seu conteúdo é: {"lista":["1","b","c33","ultimo"]}

    // Para acessar um id, basta usar a URL abaixo:
    // http://dev.4all.com:3003/tarefa/:id
    // ":id" ex. http://dev.4all.com:3003/tarefa/c33

    static final private String url = "http://dev.4all.com:3003/tarefa/";

    public boolean getHttp(){
        

        return false;
    }

}
