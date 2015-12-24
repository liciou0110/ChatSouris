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
    
    /** Int incrémenté chaque seconde pour la gestion del'affichage des scores */
    private static int tmp = 0;

    /**
     * Constructeur par défaut
     * @param m correspond au modèle
     * @param v correpsond à la vue
     */
    public ControleurJouer(Plateau m, FenetreJeu v){
        tmp = 0;
        model  = m;
        vue = v;
    }

    @Override
    public void run() {
        model.jouer();
        tmp++;
    }   

    /**
     * Permet de retrouner la valeur de l'int tmp
     * @return la valeur de tmp
     */
    public static int getTmp() {
        return tmp;
    }
    
    
}
