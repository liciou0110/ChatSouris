/*
* Start.java                     08/12/2015
* Licence PRO RTAI
*/

package vue;

import java.util.ArrayList;
import modele.*;

/**
 * Classe correspondant au main pour l'exécution de l'application
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class Start {
    
    /** Liste des plateaux chargés */
    private static ArrayList<Plateau> lesPlateaux;
    
    
    /**
     * Récupère la liste des plateau de jeu
     * @return la liste des plateaux créé
     */
    public static ArrayList<Plateau> getLesPlateaux() {
        return lesPlateaux;
    }
    
    /**
     * Permet d'ajouter un plateau à la liste des plateaux sauvegardés
     * (lors du clic sur "valider niveau")
     * @param p le palteau à valider
     */
    public static void addPlateau(Plateau p) {
        lesPlateaux.add(p);
        Sauvegarde.sauvegarde(lesPlateaux);
    }
    
    /**
     * Supprime un plateau de la liste des plateaux sauvegardés
     * @param p le plateau à supprimer
     */
    public static void removePlateau(Plateau p) {
        lesPlateaux.remove(p);
        Sauvegarde.sauvegarde(lesPlateaux);
    }
    
    /**
     * Récupére un plateau suivant son nom
     * @param nomPlateau Nom du plateau recherché
     * @return Plateau si trouvé, null sinon
     */
    public static Plateau getPlateauByNom(String nomPlateau) {
        Plateau plateau = null;
        
        for(Plateau lePlateau : lesPlateaux) {
            if(lePlateau.getNomNiveau().equalsIgnoreCase(nomPlateau)) {
                plateau = lePlateau;
            }
        }
        return plateau;
    }
    
    /**
     * Vérifie si un plateau du même nom existe
     * @param nomPlateau nom du plateau
     * @return true si le nom de plateau existe déjà
     */
    public static boolean existsPlateau(String nomPlateau) {
        boolean ok = false;
        for (Plateau lePlateau : lesPlateaux) {
            if (lePlateau.getNomNiveau().equalsIgnoreCase(nomPlateau)) {
                ok = true;
            }
        }
        return ok;
    }
    
     public static void main(String[] args) {
         lesPlateaux = Sauvegarde.charger();

         //A PERMIS LA CREATION DU NIVEAU PAR DEFAUT
         if (lesPlateaux.isEmpty()) {
             Plateau plateauDefaut = new Plateau();
             addPlateau(plateauDefaut);
         }

         //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
         try {
             for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                 if ("Nimbus".equals(info.getName())) {
                     javax.swing.UIManager.setLookAndFeel(info.getClassName());
                     break;
                 }
             }
         } catch (ClassNotFoundException ex) {
             java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
             java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
             java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }
         //</editor-fold>

         new FenetrePrincipale().setVisible(true);
     }
}
