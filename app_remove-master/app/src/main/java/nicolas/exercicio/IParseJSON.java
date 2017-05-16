package nicolas.exercicio;

// criada uma interface para organizar melhor

import org.json.JSONObject;

public interface IParseJSON {
    // método para conseguir o objeto do JSON da url fornecida
    JSONObject getJsonObj();
}
