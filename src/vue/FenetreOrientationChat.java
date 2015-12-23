/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modele.OrientationPossible;
import static modele.OrientationPossible.*;

/**
 *
 * @author Alicia
 */
public class FenetreOrientationChat extends javax.swing.JDialog {

    /** Orientation du chat */
    private OrientationPossible orientation;
    
    /**
     * Creates new form FenetreOrientationCat
     */
    public FenetreOrientationChat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        orientation = HAUT;
            
        //Ajout des bouton au groupe de bouton
        btnGroupOrientation.add(btnBas);
        btnGroupOrientation.add(btnHaut);
        btnGroupOrientation.add(btnDroite);
        btnGroupOrientation.add(btnGauche);
        
        //Action à la fermeture de la fenêtre
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                orientation = null;
            }
        });
        
        this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupOrientation = new javax.swing.ButtonGroup();
        panneauFond = new javax.swing.JPanel();
        btnGauche = new javax.swing.JRadioButton();
        btnBas = new javax.swing.JRadioButton();
        btnDroite = new javax.swing.JRadioButton();
        btnHaut = new javax.swing.JRadioButton();
        btnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(74, 79, 83));

        panneauFond.setBackground(new java.awt.Color(74, 79, 83));

        btnGauche.setForeground(new java.awt.Color(255, 255, 255));
        btnGauche.setText("GAUCHE");

        btnBas.setForeground(new java.awt.Color(255, 255, 255));
        btnBas.setText("BAS");

        btnDroite.setForeground(new java.awt.Color(255, 255, 255));
        btnDroite.setText("DROITE");

        btnHaut.setForeground(new java.awt.Color(255, 255, 255));
        btnHaut.setSelected(true);
        btnHaut.setText("HAUT");

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panneauFondLayout = new javax.swing.GroupLayout(panneauFond);
        panneauFond.setLayout(panneauFondLayout);
        panneauFondLayout.setHorizontalGroup(
            panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauFondLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panneauFondLayout.createSequentialGroup()
                        .addComponent(btnHaut)
                        .addGap(18, 18, 18)
                        .addComponent(btnDroite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBas)
                        .addGap(18, 18, 18)
                        .addComponent(btnGauche)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        panneauFondLayout.setVerticalGroup(
            panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauFondLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBas)
                    .addComponent(btnGauche)
                    .addComponent(btnDroite)
                    .addComponent(btnHaut))
                .addGap(18, 18, 18)
                .addComponent(btnOk)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panneauFond, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panneauFond, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if(btnBas.isSelected()){
            orientation = BAS;
        }else if(btnHaut.isSelected()){
            orientation = HAUT;
        }else if(btnDroite.isSelected()){
            orientation = DROITE;
        }else if(btnGauche.isSelected()){
            orientation = GAUCHE;
        }
        
        //Remet le bouton a sa valeur par défaut
        btnHaut.setSelected(true);
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    
    /**
     * Récupère l'orientation choisie
     * @return l'orientation choisie
     */
    public OrientationPossible getOrientation() {
        return orientation;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnBas;
    private javax.swing.JRadioButton btnDroite;
    private javax.swing.JRadioButton btnGauche;
    private javax.swing.ButtonGroup btnGroupOrientation;
    private javax.swing.JRadioButton btnHaut;
    private javax.swing.JButton btnOk;
    private javax.swing.JPanel panneauFond;
    // End of variables declaration//GEN-END:variables
}