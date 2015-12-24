/*
 * Case.java                     30/11/2015
 * Licence PRO RTAI
 */

package modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Caractérise une case du plateau dans le jeu labyrinthe "Chats & Souris"
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class Case implements Serializable {
    
    /** Coordonnée X en abscisse de la case */
    private int coordonneeX;
    
    /** Coordonnée Y en ordonnée de la case */
    private int coordonneeY;
    
    /** Type de la case */
    private CasePossible typeCase;

    /** Animal contenu ou pas dans la case */
    private ArrayList<Animal> animal = new ArrayList<>();
    
    /**
     * Définit une case avec sa coordonnée x, sa coordonnée y
     * et son type
     * @param x la position en abscisse de la case 
     * @param y la position en ordonnée de la case
     * @param type le type de case 
     */
    public Case(int x, int y, CasePossible type) {
        coordonneeX = x;
        coordonneeY = y;
        typeCase = type;
    }
    
    /**
     * Définit une case avec sa coordonnée x, sa coordonnée y
     * et son type et l'animal qu'el contient
     * @param x la position en abscisse de la case 
     * @param y la position en ordonnée de la case
     * @param type le type de case 
     * @param a l'animal souris ou chat présent dans la case
     */
    public Case(int x, int y, CasePossible type, Animal a ) {
        coordonneeX = x;
        coordonneeY = y;
        animal.add(a);
        typeCase = type;
    }

    /**
     * Ajout d'un animal à la case
     * @param a l'animal à ajouter
     */
    public void addAnimal(Animal a){
        animal.add(a);
    }


    /**
     * Supprime un animal d'un case
     * @param a supprime un animal de la liste
     */
    public void removeAnimal(Animal a){
        animal.remove(a);
    }
    
    /**
     * Récupère la position en abscisse de la case
     * @return la coordonnée X
     */
    public int getCoordonneeX() {
        return coordonneeX;
    }

    /**
     * Récupère la position en ordonnée de la case
     * @return la coordonnée Y
     */
    public int getCoordonneeY() {
        return coordonneeY;
    }

    /**
     * Récupère le type de la case 
     * @return le type de case 
     */
    public CasePossible getTypeCase() {
        return typeCase;
    }

    /**
     * Modifie le type de la case 
     * @param typeCase le nouveau type
     */
    public void setTypeCase(CasePossible typeCase) {
        this.typeCase = typeCase;
    }
    
    /**
     * Vérifie si deux case sont égale (même position et même composant)
     * @param c la case à comparer avec la case courante
     * @return true si les deux cases sont les même 
     *         false sinon
     */
    public boolean equals(Case c){
        return this.coordonneeX == c.coordonneeX && 
               this.coordonneeY == c.coordonneeY && 
               this.typeCase == c.typeCase;
    }

    /**
     * Récupère l'animal placer sur une case
     * @return l'animal placer sur la case ou null s'il y en a pas
     */
    public ArrayList<Animal> getAnimaux() {
        return animal;
    }

    /**
     * Définit si une case peut être franchi par une Souris
     * @return true si la case peut être franchie
     *          false sinon
     */
    public boolean isWalkableSouris() {
        return typeCase == CasePossible.VIDE || typeCase == CasePossible.SORTIE || typeCase == CasePossible.FLECHE_BAS
                || typeCase == CasePossible.FLECHE_DROITE || typeCase == CasePossible.FLECHE_GAUCHE
                || typeCase == CasePossible.FLECHE_HAUT || typeCase == CasePossible.TELEPORTEUR;
    }

    /**
     * Définit si une case peut être franchi par un Chat
     * @return true si la case peut être franchie
     *          false sinon
     */
    public boolean isWalkableCat() {
        return typeCase == CasePossible.VIDE || typeCase == CasePossible.FLECHE_BAS
                || typeCase == CasePossible.FLECHE_DROITE || typeCase == CasePossible.FLECHE_GAUCHE
                || typeCase == CasePossible.FLECHE_HAUT || typeCase == CasePossible.TELEPORTEUR;
    }
    
    /**
     * Clone une case
     * @return la case cloné
     */
    @Override
    public Case clone() {
        return new Case(coordonneeX, coordonneeY, typeCase);
    }
}
