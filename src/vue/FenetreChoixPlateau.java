/*
 * FenetreChoixPlateau.java                     30/11/2015
 * Licence PRO RTAI
 */
package vue;

import controleur.ControleurJouer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

import modele.Plateau;

/**
 * Classe permettant de choisir le plateau de jeu sur lequel jouer
 *
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class FenetreChoixPlateau extends javax.swing.JFrame {

    /**
     * Plateau actuellement sélectionné
     */
    private Plateau currentPlateau;

    /**
     * Controleur du palteau de jeu
     */
    private TimerTask control;

    /**
     * Timer pour les déplacements des animaux
     */
    private Timer timer;

    /**
     * FrameJeu
     */
    private FenetreJeu frameJeu;

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Constructeur par défaut
     */
    public FenetreChoixPlateau(java.awt.Frame parent) {
        initComponents();
        ArrayList<Plateau> listePlateau = Start.getLesPlateaux();

        for (int i = 0; i < listePlateau.size(); i++) {
            comboPlateau.addItem(listePlateau.get(i).getNomNiveau());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        comboPlateau = new javax.swing.JComboBox();
        btnJouer = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(75, 80, 84));

        jPanel2.setBackground(new java.awt.Color(75, 80, 84));

        btnJouer.setText("Jouer");
        btnJouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJouerActionPerformed(evt);
            }
        });

        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(comboPlateau, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnJouer, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(comboPlateau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnJouer)
                                        .addComponent(btnSupprimer))
                                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJouerActionPerformed
        nouveauJeu();
        this.dispose(); // On ferme cette fenetre
    }//GEN-LAST:event_btnJouerActionPerformed

    /**
     * Lance un nouveau jeu avec pour plateau celui selectionné dans la combobox
     */
    public void nouveauJeu() {
        initPlateau();
        frameJeu = new FenetreJeu(currentPlateau, this);
        //Ajout de l'observateur au moment ou on joue
        currentPlateau.addObservateur(frameJeu);

        //Création du controleur
        control = new ControleurJouer(currentPlateau, frameJeu);
        timer = new Timer();

        // On lance la partie
        frameJeu.debut(control, timer);
        frameJeu.setVisible(true);
    }

    /**
     * Méthode permettant de réinitialiser le controleur
     */
    public void resetControl() {
        control = new ControleurJouer(currentPlateau, frameJeu);
    }

    /**
     * Clone un nouveau plateau afin qu'il soit jouable
     */
    public void initPlateau() {
        currentPlateau = Start.getPlateauByNom((String) comboPlateau.getSelectedItem()).clone();
    }

    /**
     * Récupère le TimerTask controleur du la fenêtre jeu
     *
     * @return le controleur de la fenêtre jeu
     */
    public TimerTask getControl() {
        return control;
    }

    /**
     * Récupère le timer de la fenêtre du jeu
     *
     * @return le timer de la fenêtre de jeu
     */
    public Timer getTimer() {
        return timer;
    }

    public Plateau getCurrentPlateau() {
        return currentPlateau;
    }

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        Plateau p = Start.getPlateauByNom((String) comboPlateau.getSelectedItem());
        if (((String) comboPlateau.getSelectedItem()).equalsIgnoreCase("Niveau par défaut")) {
            JOptionPane.showMessageDialog(this,
                    "Impossible de supprimer le niveau par défaut.",
                    "Jeu du Chat et de la Souris - Erreurs",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            Start.removePlateau(p);
            comboPlateau.removeItem(comboPlateau.getSelectedItem());
        }
    }//GEN-LAST:event_btnSupprimerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJouer;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JComboBox comboPlateau;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
