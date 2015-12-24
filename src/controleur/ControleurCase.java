/*
 * ControleurCase.java                     30/11/2015
 * Licence PRO RTAI
 */
package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modele.*;
import vue.*;

/**
 * Controleur propre au composant d'un plateau pour la partie Edtion d'un niveau
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class ControleurCase extends MouseAdapter{

     /** Correspond au modèle */
    private final Plateau model;
    
    /** Correspond à la vue Editer*/
    private final FenetreEditer vue;
    
    /** Correspond à la vue du chois de l'orientation*/
    private final FenetreOrientationChat vue2;
    
    /** Correspond à l'abscisse de la coordonnée de la case */
    private final int cordX;
    
    /** Correspond à l'ordonnée de la coordonnées de la case */
    private final int cordY;

    /**
     * Constructeur par défaut
     * @param m correspond au modèle
     * @param v correpsond à la vue
     * @param v2 correspond à la deuxième vue pour la chois de l'orientation 
     *           du chat
     * @param x l'abscisse de la coordonnée de la case
     * @param y l'ordonnée de la coordonnées de la case
     */
    public ControleurCase(Plateau m, FenetreEditer v, FenetreOrientationChat v2, int x, int y){
        model = m;
        vue = v;
        vue2 = v2;
        cordX = x;
        cordY = y;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        // Avertissement afin d'update isChatPresent();
        model.avertirObs(cordX, cordX);
        vue.setIconCase(cordX, cordY);
        model.editer(cordX, cordY, vue.getChoix(cordX, cordY), 
                     vue2.getOrientation(), vue.isChatPresent());
    } 
}
