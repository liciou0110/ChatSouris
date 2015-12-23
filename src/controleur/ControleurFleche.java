/*
 * ControleurFleche.java                     30/11/2015
 * Licence PRO RTAI
 */
package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modele.*;
import vue.*;

/**
 * Controleur propre au fleche et au téléporteur pour la partie jeu
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class ControleurFleche extends MouseAdapter{
    
    /** Correspond au modèle */
    private final Plateau model;
    
    /** Correspond à la vue */
    private final FenetreJeu vue;
    
    /** Correspond à l'abscisse de la coordonnée de la case */
    private final int cordX;
    
    /** Correspond à l'ordonnée de la coordonnées de la case */
    private final int cordY;

    /**
     * Constructeur par défaut
     * @param m correspond au modèle
     * @param v correpsond à la vue
     * @param x l'abscisse de la coordonnée de la case
     * @param y l'ordonnée de la coordonnées de la case
     */
    public ControleurFleche(Plateau m, FenetreJeu v, int x, int y){
        model  = m;
        vue = v;
        cordX = x;
        cordY = y;
    }  
    
    @Override
    public void mouseClicked(MouseEvent e){
        model.placerFleche(cordX, cordY, vue.getChoix(cordX, cordY));
        model.plateauToString();
    }
    
}
