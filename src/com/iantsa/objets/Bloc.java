package com.iantsa.objets;

import javax.swing.*;
import java.awt.*;

public class Bloc extends Objet{

    public Bloc(int x, int y){
        super(x, y, 30, 30);
        super.icoObjet = new ImageIcon(getClass().getResource("/images/bloc.png"));
        super.imgObjet = this.icoObjet.getImage();
    }
    //GETTERS
    //SETTERS
    //METHODES
}
