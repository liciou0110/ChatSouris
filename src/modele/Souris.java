/*
 * Souris.java                     30/11/2015
 * Licence PRO RTAI
 */

package modele;

import java.io.Serializable;

import static modele.OrientationPossible.*;


/**
 * Caractérise une souris dans le jeu labyrinthe "Chats & Souris"
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class Souris extends Animal implements Serializable {

    /**
     * Définit une souris avec sa position x, sa position y, son orientation
     * de déplacement 
     * @param x la position x de la souris
     * @param y la position y de la souris
     * @param sens l'orientation de déplacement
     */
    public Souris(int x, int y, OrientationPossible sens) {
        super(x, y, "Souris", sens);
    }
    
    /**
     * Permet de déplacer une souris en fonction de son orientation 
     * et des flèches placées par l'utilisateur
     */
    @Override
    public void deplacer(Case[][] lesCases) {
        super.deplacer(lesCases);
        if (lesCases[positionX][positionY].getTypeCase() == CasePossible.FLECHE_HAUT) {
            orientation = HAUT;
        } else if (lesCases[positionX][positionY].getTypeCase() == CasePossible.FLECHE_DROITE) {
            orientation = DROITE;
        } else if (lesCases[positionX][positionY].getTypeCase() == CasePossible.FLECHE_BAS) {
            orientation = BAS;
        } else if (lesCases[positionX][positionY].getTypeCase() == CasePossible.FLECHE_GAUCHE) {
            orientation = GAUCHE;
        }
        switch(orientation) {
            case HAUT:
                if (positionY > 0  && lesCases[positionX][positionY-1].isWalkable()) {
                    positionY--;
                    lesCases[positionX][positionY].addAnimal(this);
                } else {
                    orientation = DROITE;
                    deplacer(lesCases);
                }
                break;
            case BAS:
                if (positionY < Plateau.NB_LIGNES - 1 && lesCases[positionX][positionY+1].isWalkable()) {
                    positionY++;
                    lesCases[positionX][positionY].addAnimal(this);
                } else {
                    orientation = GAUCHE;
                    deplacer(lesCases);
                }
                break;
            case GAUCHE:
                if (positionX > 0 && lesCases[positionX-1][positionY].isWalkable()) {
                    positionX--;
                    lesCases[positionX][positionY].addAnimal(this);
                } else {
                    orientation = HAUT;
                    deplacer(lesCases);
                }
                break;
            case DROITE:
                if (positionX < Plateau.NB_COLONNES - 1 && lesCases[positionX+1][positionY].isWalkable()) {
                    positionX++;
                    lesCases[positionX][positionY].addAnimal(this);
                } else {
                    orientation = BAS;
                    deplacer(lesCases);
                }
                break;
        }
    }

    /**
     * Clone une souris
     * @return la souris clonée
     */
    @Override
    public Souris clone() {
        return new Souris(positionX, positionY, orientation);
    }
}
