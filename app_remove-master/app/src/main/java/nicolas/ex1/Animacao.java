package nicolas.ex1;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

// classe criada só para admnistrar os métodos de animação

public class Animacao {

    private boolean temListener;
    private boolean status;

    // Importante: o listener tem que ser passado como parametro no construtor,
    // para conseguir acessar o segundo listener da Animation
    public Animacao(ListenerAnimacao listener){
        temListener = true;
        status = false;
        listenerAnimacao = listener;
    }

    // construtor sem listener
    public Animacao(){
        temListener = false;
        status = false;
    }

    // método que faz uma aniação de fade in
    public void animaFadeIn(Context context, View view){
        anima(context, view, R.anim.fade_in);
    }


    // método que faz uma aniação de fade out
    public void animaFadeOut(Context context, View view){
        anima(context, view, R.anim.fade_out);
    }


    // anima a view em modo slide de cima para baixo
    public void animaSlideDeCima(Context context, View view){
        anima(context, view, R.anim.slide_cimabaixo);
    }


    // anima a view de cima para baixo
    public void animaCresDeBaixo(Context context, View view){
        anima(context, view, R.anim.cres_gradual_baixocima);
    }

    // anima a view de cima para baixo
    public void animaCresDeCima(Context context, View view){
        anima(context, view, R.anim.cres_gradual_cimabaixo);
    }

    // anima a view da esquerda para a direita
    public void animaCresDaEsquerda(Context context, View view){
        anima(context, view, R.anim.cres_gradual_esqdir);
    }

    // anima a view da direita para a esquerda
    public void animaCresDaDireita(Context context, View view){
        anima(context, view, R.anim.cres_gradual_diresq);
    }

    // anima uma rotação 360 em sentido horario
    public void animaRotacionaHorario(Context context, View view){
        anima(context, view, R.anim.rotacao_horaria);
    }


    // classe generica de animação
    private void anima(Context context, View view, int resource){
        Animation anim = AnimationUtils.loadAnimation(context, resource);
        view.clearAnimation();
        view.startAnimation(anim);

        // se criar o objeto com listener poderá saber o estado que a animação se encontra
        if(temListener) {
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    //listenerAnimacao.animacaoIniciou(true);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    listenerAnimacao.animacaoAcabou(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    //listenerAnimacao.animacaoRepetiu(true);
                }
            });
        }

    }

    /** interface que serve como listener **/
    // TODO - nao consigo invocar outros métdos dentro dessa interface
    public interface ListenerAnimacao{
       // void animacaoIniciou(boolean iniciou);
        void animacaoAcabou(boolean acabou);
        //void animacaoRepetiu(boolean repetiu);
    }
    ListenerAnimacao listenerAnimacao = null;
    /** fim do listener **/

}