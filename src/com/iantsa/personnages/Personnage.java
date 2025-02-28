package com.iantsa.personnages;

import com.iantsa.jeu.Main;
import com.iantsa.objets.Objet;

import javax.swing.*;
import java.awt.*;

public class Personnage {
    //Variables
    private int largeurs, hauteur;  //dimensions du personnage
    private int x, y;               //position du personnage
    private boolean marche;         //Vrai quand le personnage marche
    private boolean versDroite;     //Vrai quand le personnage est tourné vers la droite
    public int compteur;            //Compteur de pas du personage
    protected boolean vivant;       //Vrai si le personnage est vivant

    public Personnage(int x, int y, int largeurs, int hauteur) {
        this.x = x;
        this.y = y;
        this.largeurs = largeurs;
        this.hauteur = hauteur;
        this.compteur = 0;
        this.marche = false;
        this.versDroite = true;
        this.vivant = true;
    }
    //GETTERS
    public int getX() {return x;}
    public int getY() {return y;}
    public int getLargeurs() {return largeurs;}
    public int getHauteur() {return hauteur;}
    public boolean isMarche() {return marche;}
    public boolean isVersDroite() {return versDroite;}
    public int getCompteur() {return compteur;}
    public boolean isVivant() {return vivant;}

    //SETTERS
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setMarche(boolean marche) {this.marche = marche;}
    public void setVersDroite(boolean versDroite) {this.versDroite = versDroite;}
    public void setCompteur(int compteur) {this.compteur = compteur;}
    public void setVivant(boolean vivant) {this.vivant = vivant;}
    //METHODES
    public Image marche(String nom, int frequence){
        String str = "";
        ImageIcon ico;
        Image img;
        if (this.marche == false){
            if (this.versDroite == true){str = "/images/" + nom + "ArretDroite.png";}
            else {str = "/images/" + nom + "ArretGauche.png";}
        }else {
            this.compteur++;
            if (this.compteur / frequence == 0){
                if (this.versDroite == true){str = "/images/" + nom + "ArretDroite.png";}
                else {str = "/images/" + nom + "ArretGauche.png";}
            }else {
                if (this.versDroite == true){str = "/images/" + nom + "MarcheDroite.png"; }
                else {str = "/images/" + nom + "MarcheGauche.png";}
            }
            if (this.compteur == 2 * frequence){this.compteur = 0;}
        }
        //Affichage de l'image de personnage
        ico = new ImageIcon(getClass().getResource(str));
        img = ico.getImage();
        return img;
    }
    public void Deplacement(){
        if (Main.scene.getxPos() >= 0){
            this.x = this.x - Main.scene.getDx();
        }
    }
    // Détection contact a droite de Mario
    protected boolean contactAvant(Objet objet){
        if(this.x + this.largeurs < objet.getX() || this.x + this.largeurs > objet.getX() + 5 ||
                this.y + this.hauteur <= objet.getY() || this.y >= objet.getY() + objet.getHauteur()){return false;}
        else{return true;}
    }
    // Détection contact a gauche de Mario
    protected boolean contactArriere(Objet objet){
        if(this.x > objet.getX() + objet.getLargeur() || this.x + this.largeurs < objet.getX() + objet.getLargeur() - 5 ||
                this.y + this.hauteur <= objet.getY() || this.y >= objet.getY() + objet.getHauteur()){return false;}
        else{return true;}
    }
    // Détection contact en dessous de Mario
    protected boolean contactDessous(Objet objet){
        if(this.x + this.largeurs < objet.getX() + 5 || this.x > objet.getX() + objet.getLargeur() - 5 ||
                this.y + this.hauteur < objet.getY() || this.y + this.hauteur > objet.getY() + 5){return false;}
        else{return true;}
    }
    // Détection contact au-dessus de Mario
    protected boolean contactDessus(Objet objet){
        if(this.x + this.largeurs < objet.getX() + 5 || this.x > objet.getX() + objet.getLargeur() - 5 ||
                this.y < objet.getY() + objet.getHauteur() || this.y > objet.getY() + objet.getHauteur() + 5){return false;}
        else{return true;}
    }
    public boolean proche(Objet objet){
        if((this.x > objet.getX() - 10 && this.x < objet.getX() + objet.getLargeur() + 10)
                || (this.x + this.largeurs > objet.getX() - 10 && this.x + this.largeurs < objet.getX() + objet.getLargeur() + 10)){return true;}
        else{return false;}
    }


    protected boolean contactAvant(Personnage personnage){
        if(this.isVersDroite() == true){
            if(this.x + this.largeurs < personnage.getX() || this.x + this.largeurs > personnage.getX() + 5 ||
                    this.y + this.hauteur <= personnage.getY() || this.y >= personnage.getY() + personnage.getHauteur()){return false;}
            else{return true;}
        }else{return false;}
    }

    protected boolean contactArriere(Personnage personnage){
        if(this.x > personnage.getX() + personnage.getLargeurs() || this.x + this.largeurs < personnage.getX() + personnage.getLargeurs() - 5 ||
                this.y + this.hauteur <= personnage.getY() || this.y >= personnage.getY() + personnage.getHauteur()){return false;}
        else{return true;}
    }

    protected boolean contactDessous(Personnage personnage){
        if(this.x + this.largeurs < personnage.getX() || this.x > personnage.getX() + personnage.getLargeurs() ||
                this.y + this.hauteur < personnage.getY() || this.y + this.hauteur > personnage.getY()){return false;}
        else{return true;}
    }

    public boolean proche(Personnage personnage){
        if((this.x > personnage.getX() - 10 && this.x < personnage.getX() + personnage.getLargeurs() + 10)
                || (this.x + this.largeurs > personnage.getX() - 10 && this.x + this.largeurs < personnage.getX() + personnage.getLargeurs() + 10)){
            return true;}
        else{return false;}
    }
}
