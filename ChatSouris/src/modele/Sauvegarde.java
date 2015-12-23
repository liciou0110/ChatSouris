/*
 * Sauvegarde.java                     08/12/2015
 * Licence PRO RTAI
 */

package modele;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Classe permettant de sauvegarder un plateau de jeu
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class Sauvegarde {
    
    /** Nom du fichier contenant la sauvegarde du tableau de clic */
    private static String FICHIER = "sauvegarde.dat";
    
    /**
     * Sauvegarde des plateaux de jeu
     * @param nouveauPlateau 
     */
    public static void sauvegarde(ArrayList<Plateau> nouveauPlateau) {
        try {
            // Declaration et creation d'un fichier binaire (mode ecriture)
            ObjectOutputStream flotEcriture = new ObjectOutputStream(
                                              new FileOutputStream(FICHIER));
            // ecriture de l'objet Plateau dans le fichier
            flotEcriture.writeObject(nouveauPlateau);
            flotEcriture.close();  // fermeture du fichier
            
            System.out.println("Sauvegardes des plateaux terminée. " + 
                               nouveauPlateau.size() + 
                               " plateau(x) sauvegardé(s).");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
   /**
    * Charge les plateaux sauvegardés
    * @return la liste des plateaux sauvegardés
    */
   public static ArrayList<Plateau> charger() {
        try {
            ObjectInputStream flotLecture = new ObjectInputStream(
                                            new FileInputStream(FICHIER));
            ArrayList<Plateau> plateaux = (ArrayList<Plateau>) 
                                          flotLecture.readObject();
            flotLecture.close();
            
            System.out.println("Chargement des plateaux terminé. " + 
                               plateaux.size() + " plateau(x) chargé(s).");
            return plateaux;
        } catch (IOException | ClassNotFoundException ex) {
            if(ex instanceof FileNotFoundException) {
                System.out.println("Pas encore de sauvegarde disponible.");
            }
            else {
                ex.printStackTrace();
            }
            return new ArrayList<>();
        }
    }
}
