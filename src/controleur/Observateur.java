/*
 * Observateur.java                     30/11/2015
 * Licence PRO RTAI
 */
package controleur;

/**
 * Observateur qui va permettre d'observer les modifications des modèles
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public interface Observateur {

    /** Méthode permettant d'avertir un modèle
     * @param x correspond à l'abcsisse de la case
     * @param y correspond à l'ordonnée de la case
     */
    public void avertir(int x, int y);
    
}
