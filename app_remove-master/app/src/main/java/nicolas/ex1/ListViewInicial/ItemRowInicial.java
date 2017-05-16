package nicolas.ex1.ListViewInicial;

// Clsse criada para admnistrar a lista de opções iniciais
// Para ter mais flexibilidade de customização e ampliação essa classe é unica para a lista inicial
public class ItemRowInicial {

    String nome;

    public ItemRowInicial(String n){
        nome = n;
    }

    public void setNome(String n){
        nome = n;
    }

    public String getNome(){
        return nome;
    }
}
