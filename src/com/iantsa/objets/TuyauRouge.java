package com.iantsa.objets;

import javax.swing.*;
import java.awt.*;

public class TuyauRouge extends Objet{
    public TuyauRouge(int x, int y){
        super(x, y, 43, 65);
        super.icoObjet = new ImageIcon(getClass().getResource("/images/tuyauRouge.png"));
        super.imgObjet = this.icoObjet.getImage();
    }
    //GETTERS
    //SETTERS
    //METHODES
}
