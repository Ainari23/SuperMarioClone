Structure du Projet
1. Classes Principales
   Le projet est constitué des classes suivantes :

Personnage : Classe de base pour tous les personnages du jeu, incluant des méthodes pour le déplacement et la détection de collision.
Mario : Hérite de Personnage et représente Mario, avec des méthodes spécifiques pour ses mouvements (marche, saut, mort).
Tortue : Hérite aussi de Personnage et représente un ennemi (la tortue), avec des méthodes pour le déplacement et les interactions avec Mario.
Objet : Classe représentant un objet dans le jeu, tel qu'une plateforme ou une pièce.
2. Packages
   Le projet est organisé dans les packages suivants :

com.iantsa.personnages : Contient les classes Personnage, Mario, et Tortue.
com.iantsa.objets : Contient la classe Objet et ses sous-classes (telles que Piece).
com.iantsa.jeu : Contient la classe principale qui initialise et gère la scène du jeu.
Installation
Prérequis
Java 8 ou supérieur : Assurez-vous que Java est installé sur votre machine. Vous pouvez télécharger et installer Java depuis le site officiel : Java Downloads.
IDE de développement : Il est recommandé d'utiliser un IDE comme IntelliJ IDEA ou Eclipse.
Cloner le projet
bash
Copier
git clone https://github.com/ton-repository/projet-jeu-mario.git
cd projet-jeu-mario
Exécution du Projet
Ouvrir le projet dans votre IDE.
Lancer la classe principale (qui est généralement la classe qui initialise la scène du jeu, souvent dans le package com.iantsa.jeu).
Détails du Code
1. Classe Personnage
   java
   Copier
   package com.iantsa.personnages;

import javax.swing.*;
import java.awt.*;

public class Personnage {
private int largeurs, hauteur;
private int x, y;
private boolean marche;
private boolean versDroite;
public int compteur;
protected boolean vivant;

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
    
    // GETTERS and SETTERS
    // Méthodes de déplacement, collision et contact
    // Détection de contact avant, arrière, dessous, dessus, etc.
}
Description : La classe Personnage contient les propriétés de base d'un personnage, telles que sa position, sa taille, et son état (marche, direction, vivant).
Méthodes importantes : marche(), Deplacement(), contactAvant(), contactArriere(), contactDessous(), contactDessus().
2. Classe Mario
   java
   Copier
   package com.iantsa.personnages;

import com.iantsa.jeu.Main;
import com.iantsa.objets.Objet;
import com.iantsa.objets.Piece;

import javax.swing.*;
import java.awt.*;

public class Mario extends Personnage {
private Image imgMario;
private ImageIcon icoMario;
private boolean saut;
private int compteurSaut;
private int compteurMort;

    public Mario(int x, int y) {
        super(x, y, 28, 50);
        this.icoMario = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
        this.imgMario = this.icoMario.getImage();
        this.saut = false;
        this.compteurSaut = 0;
        this.compteurMort = 0;
    }

    // GETTERS and SETTERS
    // Méthodes pour la marche, le saut, et la gestion des collisions avec les objets
}
Description : La classe Mario hérite de Personnage et ajoute des fonctionnalités spécifiques à Mario, comme le saut, la mort, et l'animation de marche.
Méthodes importantes : marche(), saute(), contact(), meurt().
3. Classe Tortue
   java
   Copier
   package com.iantsa.personnages;

import javax.swing.*;
import java.awt.*;

public class Tortue extends Personnage implements Runnable {
private Image imgTortue;
private ImageIcon icoTortue;
private final int PAUSE = 15;
private int dxTortue;

    public Tortue(int x, int y) {
        super(x, y, 43, 50);
        super.setVersDroite(true);
        super.setMarche(true);
        this.dxTortue = 1;
        this.icoTortue = new ImageIcon(getClass().getResource("/images/tortueArretGauche.png"));
        this.imgTortue = this.icoTortue.getImage();
        Thread chronoChamp = new Thread(this);
        chronoChamp.start();
    }

    public void bouge() {
        if (super.isVersDroite() == true) { this.dxTortue = 1; }
        else { this.dxTortue = -1; }
        super.setX(super.getX() + this.dxTortue);
    }

    @Override
    public void run() {
        try { Thread.sleep(20); } catch (InterruptedException e) { }
        while (true) {
            if (this.vivant == true) { this.bouge(); }
            try { Thread.sleep(PAUSE); } catch (InterruptedException e) { }
        }
    }
}
Description : La classe Tortue représente un ennemi qui se déplace de façon autonome dans le jeu. Elle implémente l'interface Runnable pour faire bouger la tortue dans un thread séparé.
Méthodes importantes : bouge(), run(), contact().
4. Classe Objet
   java
   Copier
   package com.iantsa.objets;

public class Objet {
private int x, y, largeur, hauteur;

    public Objet(int x, int y, int largeur, int hauteur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    // GETTERS and SETTERS
}
Description : La classe Objet représente un objet quelconque du jeu, comme des plateformes ou des pièces que Mario peut collecter.
Méthodes importantes : Les méthodes d'accès aux coordonnées et tailles de l'objet.
Fonctionnalités du Jeu
Déplacement de Mario : Mario peut se déplacer de gauche à droite, marcher, sauter et interagir avec l'environnement.

Mécanisme de Saut : Mario peut sauter pour atteindre des hauteurs ou éviter des obstacles.

Collisions avec des objets et ennemis : Mario détecte les collisions avec des objets et ennemis, et les ennemis, comme la tortue, changent de direction lorsqu'ils rencontrent des obstacles.

Animation : Le mouvement de Mario et des ennemis est animé, avec des images différentes pour la marche, le saut et la mort.

Mort et Repos : Lorsque Mario ou la tortue meurt, une animation spéciale est affichée et des effets sonores sont joués.

Auteurs
Développeur Principal : Iantsa Ny Aina
Contributeurs : Neant
