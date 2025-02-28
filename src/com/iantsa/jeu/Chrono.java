package com.iantsa.jeu;

public class Chrono implements Runnable{
    private final int PAUSE = 3;//temps d'attente entre 2 tour de boucle

    @Override
    public void run() {
        while (true){
            Main.scene.repaint();//Appel de la methode PainComponent de l'objeet scene
            try {Thread.sleep(PAUSE);} //Temps de pause du flux (3ms)
            catch (InterruptedException e) {}
        }
    }
}
