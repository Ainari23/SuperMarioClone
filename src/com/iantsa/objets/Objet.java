package com.iantsa.objets;

import com.iantsa.jeu.Main;

import javax.swing.*;
import java.awt.*;

public class Objet {
    private int largeur, hauteur;   //dimensions de l'objet
    private int x, y;               //positions de l'objet
    protected Image imgObjet;
    protected ImageIcon icoObjet;

    public Objet(int x, int y, int largeur, int hauteur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    //GETTERS
    public int getX() {return x;}
    public int getY() {return y;}
    public int getLargeur() {return largeur;}
    public int getHauteur() {return hauteur;}
    public Image getImgObjet() {return imgObjet;}

    //SETTERS
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setLargeur(int largeur) {this.largeur = largeur;}
    public void setHauteur(int hauteur) {this.hauteur = hauteur;}
    //METHODES
    public void Deplacement (){
        if (Main.scene.getxPos() >= 0){
            this.x = this.x - Main.scene.getDx();
        }
    }
}
