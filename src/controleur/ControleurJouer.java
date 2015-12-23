/*
 * ControleurJouer.java                     30/11/2015
 * Licence PRO RTAI
 */
package controleur;
import java.util.TimerTask;
import modele.*;
import vue.*;

/**
 * Controleur propre au jeu 
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class ControleurJouer extends TimerTask{
    
    /** Correspond au modèle */
    private final Plateau model;
    
    /** Correspond à la vue */
    private final FenetreJeu vue;

    /**
     * Constructeur par défaut
     * @param m correspond au modèle
     * @param v correpsond à la vue
     */
    public ControleurJouer(Plateau m, FenetreJeu v){
        model  = m;
        vue = v;
    }

    @Override
    public void run() {
        model.jouer();
//        model.plateauToString();
//        System.out.println("");
    }   
}
