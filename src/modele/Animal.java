/*
 * Animal.java                     30/11/2015
 * Licence PRO RTAI
 */

package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static modele.CasePossible.*;
import static modele.OrientationPossible.*;
import static modele.Plateau.NB_COLONNES;
import static modele.Plateau.NB_LIGNES;

/**
 * Caractérise un animal dans le jeu labyrinthe "Chats & Souris"
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public abstract class Animal implements Serializable, Cloneable {

    /**
     * La position de l'animal en abscisse
     */
    protected int positionX;

    /**
     * La position de l'animal en ordonnée
     */
    protected int positionY;

    /**
     * Le nom de l'animal
     */
    protected String nom;

    /**
     * Valeur de l'orientation du déplacement de l'animal
     */
    protected OrientationPossible orientation;


    /**
     * Définit un animal avec sa position x, sa position y, son nom et son
     * orientation de déplacement
     *
     * @param x     l'abscisse de l'animal
     * @param y     l'ordonnée de l'animal
     * @param leNom le nom de l'animal
     * @param sens  sens de déplacement de l'animal
     */
    public Animal(int x, int y, String leNom, OrientationPossible sens) {
        positionX = x;
        positionY = y;
        nom = leNom;
        orientation = sens;
    }


    /**
     * Récupère la position en abscisse de l'animal
     *
     * @return positionX la position en abscisse de l'animal
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Modifie la valeur de la position en abscisse de l'animal
     *
     * @param positionX la nouvelle position en abscisse de l'animal
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Récupère la position en ordonnée de l'animal
     *
     * @return positionY la position en ordonnée de l'animal
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Modifie la valeur de la position en ordonnée de l'animal
     *
     * @param positionY la nouvelle position en ordonnée de l'animal
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Récupère le nom de l'animal
     *
     * @return nom le nom de l'animal
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie la valeur du nom de l'animal
     *
     * @param nom le nouveau nom de l'animal
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère l'orientation du déplacement de l'animal
     *
     * @return orientation l'orientation du déplacement de l'animal
     */
    public OrientationPossible getOrientation() {
        return orientation;
    }

    /**
     * Modifie la valeur de l'orientation de l'animal
     *
     * @param orientation la nouvelle orientation de l'animal
     */
    public void setOrientation(OrientationPossible orientation) {
        this.orientation = orientation;
    }
    
    /**
     * Permet à un animal de se déplacer dans une direction de case en case
     */
    public void deplacer(Case[][] lesCases) {
        lesCases[positionX][positionY].removeAnimal(this);
        
        //Cas on tombe sur une case avec un téléporteur
        if (lesCases[positionX][positionY].getTypeCase() == CasePossible.TELEPORTEUR) {
            ArrayList<Case> teleporteurs = new ArrayList<>();
            for(int i = 0; i < NB_COLONNES; i++) {
                for (int j = 0; j < NB_LIGNES; j++) {
                    //Récupèration des téléporteurs dans une liste sans le téléporteur d'entrée
                    if(lesCases[i][j].getTypeCase() == TELEPORTEUR &&
                     !(lesCases[i][j].equals(lesCases[positionX][positionY]))){
                        teleporteurs.add(lesCases[i][j]);
                    }
                }
            }
 
            //Si il ya plus de 1 téléporteur placé sur le plateau 
            //sinon on fait rien
            if(teleporteurs.size() >= 1){
                Case teleport;
                int random = new Random().nextInt(teleporteurs.size());
                teleport = teleporteurs.get(random);
                lesCases[positionX][positionY].removeAnimal(this);
                positionX = teleport.getCoordonneeX();
                positionY = teleport.getCoordonneeY();
                lesCases[positionX][positionY].addAnimal(this);
            }
        }
    }

    public Animal clone() {
        return null;
    }

    /**
     * Permet de changer l'orientation d'un animal
     */
    public void changerOrientation(){
        if(this instanceof Chat){
            if(this.getOrientation() == HAUT){
                this.setOrientation(BAS);
            }else if(this.getOrientation() == DROITE){
                this.setOrientation(GAUCHE);
            }else if(this.getOrientation() == GAUCHE){
                this.setOrientation(DROITE);
            }else{
                this.setOrientation(HAUT);
            }
        }else{
            //TODO Cas ou on veut changer l'orientation d'un souris
        }
    }
}
