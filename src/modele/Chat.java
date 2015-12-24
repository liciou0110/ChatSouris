/*
 * Chat.java                     30/11/2015
 * Licence PRO RTAI
 */

package modele;

import java.io.Serializable;

import static modele.OrientationPossible.*;


/**
 * Caractérise un chat dans le jeu labyrinthe "Chats & Souris"
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class Chat extends Animal implements Serializable {

    /**
     * Définit un chat avec sa position x, sa position y et son orientation 
     * de déplacement 
     * @param x position en abscisse du chat 
     * @param y position en ordonné du chat 
     * @param sens orientation de déplacement du chat 
     */
    public Chat(int x, int y, OrientationPossible sens) {
        super(x, y, "Chat", sens);
    }

    /**
     * Permet de déplacer un chat en ligne ou en colonne
     */
    @Override
    public void deplacer(Case[][] lesCases) {
        super.deplacer(lesCases);
        switch(orientation) {
            case HAUT:
                if (positionY > 0 && lesCases[positionX][positionY-1].isWalkableCat()) {
                    positionY--;
                    lesCases[positionX][positionY].addAnimal(this);
                } else {
                    orientation = BAS;
                    deplacer(lesCases);
                }
                break;
            case BAS:
                if (positionY < Plateau.NB_LIGNES - 1 && lesCases[positionX][positionY+1].isWalkableCat())  {
                    positionY++;
                    lesCases[positionX][positionY].addAnimal(this);
                } else {
                    orientation = HAUT;
                    deplacer(lesCases);
                }
                break;
            case GAUCHE:
                if (positionX > 0 && lesCases[positionX-1][positionY].isWalkableCat()) {
                    positionX--;
                    lesCases[positionX][positionY].addAnimal(this);
                } else {
                    orientation = DROITE;
                    deplacer(lesCases);
                }
                break;
            case DROITE:
                if (positionX < Plateau.NB_COLONNES - 1 && lesCases[positionX+1][positionY].isWalkableCat())  {
                    positionX++;
                    lesCases[positionX][positionY].addAnimal(this);
                } else {
                    orientation = GAUCHE;
                    deplacer(lesCases);
                }
                break;
        }
        //System.out.println("[D] Chat : " + positionX + ";" + positionY + ";" + orientation);
    }

    @Override
    public Chat clone() {
        return new Chat(positionX, positionY, orientation);
    }
}
