package com.iantsa.personnages;

import com.iantsa.audio.Audio;
import com.iantsa.jeu.Main;
import com.iantsa.objets.Objet;
import com.iantsa.objets.Piece;

import javax.swing.*;
import java.awt.*;

public class Mario extends Personnage{
    //VARIABLE
    private Image imgMario;
    private ImageIcon icoMario;
    private boolean saut; //vrai si mario saute
    private int compteurSaut; // durée du saut et hauteur du saut
    private int compteurMort;

    public Mario(int x, int y){
        super(x, y, 28, 50);
        this.icoMario = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
        this.imgMario = this.icoMario.getImage();
        this.saut = false;
        this.compteurSaut = 0;
        this.compteurMort = 0;
    }
    //GETTERS
    public Image getImgMario(){return imgMario;}
    public boolean isSaut() {return saut;}
    //SETTERS
    public void setSaut(boolean saut) {this.saut = saut;}
    //METHODES
    @Override
    public Image marche(String nom, int frequence) {

        String str;
        ImageIcon ico;
        Image img;

        if (this.isMarche() == false || Main.scene.getxPos() <= 0 || Main.scene.getxPos() > 4430) {
            if(this.isVersDroite() == true){str = "src/images/" + nom + "ArretDroite.png";}
            else{str = "src/images/" + nom + "ArretGauche.png";}
        }else{
            this.compteur++;
            if (this.compteur / frequence == 0) { // quotient de la division euclidienne de compteur par frequence
                if(this.isVersDroite() == true){str = "src/images/" + nom + "ArretDroite.png";}
                else{str = "src/images/" + nom + "ArretGauche.png";}
            }else{
                if(this.isVersDroite() == true){str = "src/images/" + nom + "MarcheDroite.png";}
                else{str = "src/images/" + nom + "MarcheGauche.png";}
            }
            if (this.compteur == 2 * frequence) {this.compteur = 0;}
        }
        // Affichage de l'image du personnage
        ico = new ImageIcon(str);
        img = ico.getImage();
        return img;
    }
    public Image saute(){
        ImageIcon ico;
        Image img;
        String str;
        this.compteurSaut++;
        // Montée du saut
        if(this.compteurSaut <= 40){
            if(this.getY() > Main.scene.getHauteurPlafond()){this.setY(this.getY() - 4);}
            else{this.compteurSaut = 41;}
            if(this.isVersDroite() == true){str = "/images/marioSautDroite.png";}
            else{str = "/images/marioSautGauche.png";}

            // Retombée du saut
        }else if(this.getY() + this.getHauteur() < Main.scene.getySol()){this.setY(this.getY() + 1);
            if(this.isVersDroite() == true){str = "/images/marioSautDroite.png";}
            else{str = "/images/marioSautGauche.png";}
            // Saut terminé
        }else{
            if(this.isVersDroite() == true){str = "/images/marioArretDroite.png";}
            else{str = "/images/marioArretGauche.png";}
            this.saut = false;
            this.compteurSaut = 0;
        }
        // Affichage de l'image de mario
        ico = new ImageIcon(getClass().getResource(str));
        img = ico.getImage();
        return img;
    }
    public void contact(Objet objet) {
        // contact horizontal
        if((super.contactAvant(objet) == true && this.isVersDroite() == true) || (super.contactArriere(objet) == true && this.isVersDroite() == false)){
            Main.scene.setDx(0);
            this.setMarche(false);
        }
        // contact avec un objet en dessous
        if(super.contactDessous(objet) == true && this.saut == true){ // Mario saute sur un objet
            Main.scene.setySol(objet.getY());
        }else if(super.contactDessous(objet) == false){ // Mario tombe sur le sol initial
            Main.scene.setySol(293); // altitude du sol initial
            if(this.saut == false){this.setY(243);} // fait redescendre mario au sol
        }
        // contact avec un objet au-dessus
        if(super.contactDessus(objet) == true){
            Main.scene.setHauteurPlafond(objet.getY() + objet.getHauteur()); // le plafond devient le dessous de l'objet
        }else if(super.contactDessus(objet) == false && this.saut == false){
            Main.scene.setHauteurPlafond(0);// altitude du plafond initial (ciel)
        }
    }
    public boolean contactPiece(Piece piece){
        //Le contact avec une piece n'as aucune répercusion sur Mario
        if (this.contactArriere(piece) == true || this.contactAvant(piece) == true || this.contactDessous(piece) == true || this.contactDessus(piece) == true){
            return true;
        }else {return false;}
    }
    public void contact(Personnage personnage){
        if ((super.contactAvant(personnage) == true ) || (super.contactArriere(personnage) == true)){
            this.setMarche(false);
            this.setVivant(false);
        } else if (super.contactDessous(personnage) == true) {
            personnage.setMarche(false);
            personnage.setVivant(false);
        }
    }
    public Image meurt(){
        String str;
        ImageIcon ico;
        Image img;

        str = "/images/boom.png";
        if (this.compteurMort == 0){
            Audio.playSound("/audio/boum.wav");}
        if (this.compteurMort == 100){
            Audio.playSound("/audio/partiePerdue.wav");}
        this.compteurMort++;
        if(this.compteurMort > 100){
            str = "/images/marioMeurt.png";
            this.setY(this.getY() - 1);
        }
        ico = new ImageIcon(getClass().getResource(str));
        img = ico.getImage();
        return img;
    }
}
