package com.iantsa.jeu;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scene scene;
    public static void main(String[] args) {

        //Fenetre de l'application
        JFrame fenetre = new JFrame("Super Mario Bros");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(700,360);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        fenetre.setAlwaysOnTop(true);

        //Instanciation de l'objet scene
        Main.scene = new Scene();
        fenetre.setContentPane(Main.scene); //Association de la scene a la fenetre de l'app
        fenetre.setVisible(true);
    }
}

