package nicolas.ex1.ListViewComentarios;

import android.graphics.Bitmap;

public class ItemRowComentario {

    String nome, titulo, comentario;
    Bitmap foto;
    int nota;

    /* contrutor get e setters automatico no android studio*//** alt+insert **/

    public ItemRowComentario() {
    }

    public ItemRowComentario(String nome, String titulo, String comentario, Bitmap foto, int nota) {
        this.nome = nome;
        this.titulo = titulo;
        this.comentario = comentario;
        this.foto = foto;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
