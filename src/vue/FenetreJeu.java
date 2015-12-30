/*
 * FenetreJeu.java                     30/11/2015
 * Licence PRO RTAI
 */

package vue;

import controleur.ControleurFleche;
import controleur.ControleurJouer;
import controleur.Observateur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modele.*;

/**
 * Classe correspondant à la vue pour le jeu
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class FenetreJeu extends javax.swing.JFrame implements Observateur {

    /** Définition de l'image de fond */
    private final Image mainBackgroundImage = new ImageIcon(getClass().
            getResource("/background.png")).getImage();

    /** Modèle correspondant à l'état du palteau */
    private final Plateau unPlateau;

    /** Définit tous les éléments présent sur le plateau */
    private static final ImageIcon VIDE = new ImageIcon("./src/imgs/vide.png");
    private static final ImageIcon IN = new ImageIcon("./src/imgs/in.png");
    private static final ImageIcon OUT = new ImageIcon("./src/imgs/out.png");
    private static final ImageIcon CHAT = new ImageIcon("./src/imgs/chat.png");
    private static final ImageIcon SOURIS = new ImageIcon("./src/imgs/souris.png");
    private static final ImageIcon TELPORTEUR = new ImageIcon("./src/imgs/teleporteur.png");
    private static final ImageIcon MUR = new ImageIcon("./src/imgs/mur.png");
    private static final ImageIcon ICON = new ImageIcon("./src/imgs/icon.png");
    private static final ImageIcon ICONHOVER = new ImageIcon("./src/imgs/iconHover.png");
    private static final ImageIcon HAUT = new ImageIcon("./src/imgs/up.png");
    private static final ImageIcon BAS = new ImageIcon("./src/imgs/down.png");
    private static final ImageIcon GAUCHE = new ImageIcon("./src/imgs/left.png");
    private static final ImageIcon DROITE = new ImageIcon("./src/imgs/right.png");

    /** Défintiion  des bouton pour les flêches et les téléporteurs */
    private final ImageRadioButton up = new ImageRadioButton("../imgs/up_detoure.png", 40);
    private final ImageRadioButton right = new ImageRadioButton("../imgs/right_detoure.png", 40);
    private final ImageRadioButton down = new ImageRadioButton("../imgs/down_detoure.png", 40);
    private final ImageRadioButton left = new ImageRadioButton("../imgs/left_detoure.png", 40);
    private final ImageRadioButton teleporteur = new ImageRadioButton("../imgs/teleporteur_detoure.png", 40);
    private final ImageRadioButton caseVide = new ImageRadioButton("../imgs/vide_detoure.png", 43);

    /** Défintion du plateau de jeu */
    JLabel[][] jboard;

    /** Déclaration des JLabel avec l'afficahge des quantité de fleche **/
    private JLabel nbUp;
    private JLabel nbRight;
    private JLabel nbDown;
    private JLabel nbLeft;
    private JLabel nbTele;
    
    /** Fenêtre parent à la fenêtre courante */
    FenetreChoixPlateau frameChoixPlateau;
    
    /** Fenêtre pour l'affichage du score */
    FenetreScore frameScore;

    /** Score de la partie */
    int score = 0;
    
    /** Nombre de téléporteur du plateau */
    int nbTeleporteurPlateau = 0;

    /**
     * Constructeur par défaut
     * @param p           le palteau choisi dans la fenêtre parente
     * @param frameParent fenêtre parent du chois du plateau
     */
    public FenetreJeu(Plateau p, FenetreChoixPlateau frameParent) {
        //Récupération de la fenêtre Parent pour récupérer le timer
        frameChoixPlateau = frameParent;
        
        //Récupération du plateau sélectionné
        unPlateau = p;
        initComponents();
        definitionAffichage();
    }

    /**
     * Définit l'affichage de la fenêtre courante
     */
    public final void definitionAffichage() {
        //Bouton représenté par l'icon de l'application permettant 
        //de revenir au menu principal      
        FenetreJeu currentFrame = this;
        HoverButton btnIcon = new HoverButton(ICON, ICONHOVER);
        panneauIcon.add(btnIcon);
        panneauIcon.revalidate();
        panneauIcon.repaint();

        //Action au clic sur l'icone l'application
        btnIcon.addActionListener(
        new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Arrête le timer au clic sur le logo
                frameChoixPlateau.getControl().cancel();
                frameChoixPlateau.getTimer().cancel();
                frameChoixPlateau.getTimer().purge();
                currentFrame.dispose();
            }
        });


        //Action à la fermeture de la fenêtre
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //Arrêt du Timer
                frameChoixPlateau.getControl().cancel();
                frameChoixPlateau.getTimer().cancel();
                frameChoixPlateau.getTimer().purge();
            }
        });
        
        //Rend inactif les radio button si il n'y pas de flêche
        if (unPlateau.getNbFlechesHaut() == 0) {
            up.setEnabled(false);
        }
        if (unPlateau.getNbFlechesBas() == 0) {
            down.setEnabled(false);
        }
        if (unPlateau.getNbFlechesDroite() == 0) {
            right.setEnabled(false);
        }
        if (unPlateau.getNbFlechesGauche() == 0) {
            left.setEnabled(false);
        }
        if (unPlateau.getNbTeleporteurs() == 0) {
            teleporteur.setEnabled(false);
        }
        
        //Selectionne un bouton par défaut*/
        if (unPlateau.getNbFlechesHaut() != 0) {
            up.setSelected(true);
        }else if (unPlateau.getNbFlechesBas() != 0) {
            down.setSelected(true);
        }else if (unPlateau.getNbFlechesDroite() != 0) {
            right.setSelected(true);
        }else if (unPlateau.getNbFlechesGauche() != 0) {
            left.setSelected(true);
        }else if (unPlateau.getNbTeleporteurs() != 0) {
            teleporteur.setSelected(true);
        }else{
            caseVide.setSelected(true);
        }

        //Ajoute les bouton au groupe de bouton
        btnGroup.add(up);
        btnGroup.add(right);
        btnGroup.add(down);
        btnGroup.add(left);
        btnGroup.add(teleporteur);
        btnGroup.add(caseVide);

        //Ajoute les lable pour le texte a coté des composants
        JLabel haut = new JLabel("Haut");
        haut.setForeground(Color.white);
        haut.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel droite = new JLabel("Droite");
        droite.setForeground(Color.white);
        droite.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel bas = new JLabel("Bas");
        bas.setForeground(Color.white);
        bas.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel gauche = new JLabel("Gauche");
        gauche.setForeground(Color.white);
        gauche.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel tele = new JLabel("Téléporteur");
        tele.setForeground(Color.white);
        tele.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel vide = new JLabel("Case Vide");
        vide.setForeground(Color.white);
        vide.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Ajoute la quantité de flêche pour la partie
        nbUp = new JLabel("x " + unPlateau.getNbFlechesHaut());
        nbUp.setForeground(Color.white);
        nbRight = new JLabel("x " + unPlateau.getNbFlechesDroite());
        nbRight.setForeground(Color.white);
        nbDown = new JLabel("x " + unPlateau.getNbFlechesBas());
        nbDown.setForeground(Color.white);
        nbLeft = new JLabel("x " + unPlateau.getNbFlechesGauche());
        nbLeft.setForeground(Color.white);
        nbTele = new JLabel("x " + unPlateau.getNbTeleporteurs());
        nbTele.setForeground(Color.white);

        //Ajoute les composents au panneau
        panneauBouton.add(up);
        panneauBouton.add(haut);
        panneauBouton.add(nbUp);
        panneauBouton.add(right);
        panneauBouton.add(droite);
        panneauBouton.add(nbRight);
        panneauBouton.add(down);
        panneauBouton.add(bas);
        panneauBouton.add(nbDown);
        panneauBouton.add(left);
        panneauBouton.add(gauche);
        panneauBouton.add(nbLeft);
        panneauBouton.add(teleporteur);
        panneauBouton.add(tele);
        panneauBouton.add(nbTele);
        panneauBouton.add(caseVide);
        panneauBouton.add(vide);

        //Création static de la grille de jeu
        jboard = new JLabel[Plateau.NB_COLONNES][Plateau.NB_LIGNES];

        for (int j = 0; j < Plateau.NB_LIGNES; j++) {
            for (int i = 0; i < Plateau.NB_COLONNES; i++) {

                jboard[i][j] = new JLabel(MUR);

                //Récupère le type de case de la case de coordoné i, j
                CasePossible caseP = unPlateau.getLesCases()[i][j].getTypeCase();

                //Affiche toute les case du plateau définit précédément
                jboard[i][j] = new JLabel(caseP == CasePossible.ENTREE ? IN :
                        caseP == CasePossible.SORTIE ? OUT :
                                caseP == CasePossible.VIDE ? VIDE : MUR);

                //Affiche dans la grille du jeu la grille précédente
                grilleJeu.add(jboard[i][j]);

                jboard[i][j].addMouseListener(new
                        ControleurFleche(unPlateau, this, i, j));
            }
        }

        // Redessine les chats
        for (Animal a : unPlateau.getListeAnimaux()) {
            if (a instanceof Chat) {
                jboard[a.getPositionX()][a.getPositionY()].setIcon(CHAT);
            } else {
                jboard[a.getPositionX()][a.getPositionY()].setIcon(SOURIS);
            }
        }

    }

    /**
     * Permet le lancement d'une partie (Début du timer)
     * @param control controelur du jeu
     * @param timer   le timer pour les déplacements des animaux
     */
    public void debut(TimerTask control, Timer timer) {
        // On définit le temps de répétition (2000)
        timer.scheduleAtFixedRate(control, 2000, 2000);
    }
    
    @Override
    public void avertir(int x, int y) {
        //Gestion des cas d'erreurs
        //Si la case est vide on peut placer la fleche sinon erreur et 
        //on peut décrémenter la fleche
        
        if(x != -1 && y != -1){   
            
            //Désactive les bouton si le nombre de fleche 
            //est a zéro les réactive sinon
            if(unPlateau.getNbFlechesBas() == 0){
                nbDown.setText("x " + unPlateau.getNbFlechesBas());
                down.setEnabled(false);
            }else{
                down.setEnabled(true);
            }
            
            if(unPlateau.getNbFlechesDroite() == 0){
                nbRight.setText("x " + unPlateau.getNbFlechesDroite());
                right.setEnabled(false);
            }else{
                right.setEnabled(true);
            }

            if(unPlateau.getNbFlechesGauche() == 0){
                nbLeft.setText("x " + unPlateau.getNbFlechesGauche());
                left.setEnabled(false);
            }else{
                left.setEnabled(true);
            }

            if(unPlateau.getNbFlechesHaut()== 0){
                nbUp.setText("x " + unPlateau.getNbFlechesHaut());
                up.setEnabled(false);
            }else{
                up.setEnabled(true);
            }
            
            if(teleporteur.isSelected() && nbTeleporteurPlateau == 0){
                JOptionPane.showMessageDialog(this, 
                    "Pour que vos animaux puisse se téléporter "
                  + "il faut au minimum deux téléporteur.", 
                    "Jeu du Chat et de la Souris - Erreurs",
                JOptionPane.INFORMATION_MESSAGE);
            }
            
            //on verifie si la case ou on place l'élément est vide
            if(jboard[x][y].getIcon() == VIDE){
                if(up.isSelected()){
                    nbUp.setText("x " + unPlateau.getNbFlechesHaut());
                } else if (down.isSelected()) {
                    nbDown.setText("x " + unPlateau.getNbFlechesBas());
                } else if (right.isSelected()) {
                    nbRight.setText("x " + unPlateau.getNbFlechesDroite());
                } else if (left.isSelected()) {
                    nbLeft.setText("x " + unPlateau.getNbFlechesGauche());
                } else if (teleporteur.isSelected()) {
                    nbTele.setText("x " + unPlateau.getNbTeleporteurs());
                }
                
            }else if(jboard[x][y].getIcon() == HAUT || 
                     jboard[x][y].getIcon() == BAS ||
                     jboard[x][y].getIcon() == DROITE ||
                     jboard[x][y].getIcon() == GAUCHE ||
                     jboard[x][y].getIcon() == TELPORTEUR){

                nbTele.setText("x " + unPlateau.getNbTeleporteurs());
                nbLeft.setText("x " + unPlateau.getNbFlechesGauche());
                nbRight.setText("x " + unPlateau.getNbFlechesDroite());
                nbUp.setText("x " + unPlateau.getNbFlechesHaut());
                nbDown.setText("x " + unPlateau.getNbFlechesBas());
            }else{
                //Si on place une case vide sur un case autre q'une case 
                //fleche teleporteur ou vide et sans animaux
                if(caseVide.isSelected()){
                    JOptionPane.showMessageDialog(this, 
                        "Une case vide ne peut pas être placée que sur une "
                      + "flêche, un téléporteur ou une autre case vide.", 
                        "Jeu du Chat et de la Souris - Erreurs",
                    JOptionPane.ERROR_MESSAGE);
                //Si on place un téléporteur sur un case autre 
                //qu'une case vide et sans animaux
                }else if(teleporteur.isSelected()){
                    JOptionPane.showMessageDialog(this, 
                        "Un téléporteur ne peut être placé que sur une "
                      + "case vide, une flêche ou un autre téléporteur.", 
                        "Jeu du Chat et de la Souris - Erreurs",
                    JOptionPane.ERROR_MESSAGE);
                //Cas ou on place une flêche sur un case 
                //autre qu'une case vide et sans animaux
                }else{
                    JOptionPane.showMessageDialog(this, 
                        "Une flêhce ne peut être placée que sur une case "
                      + "vide, un tléporteur ou une autre flêche.", 
                        "Jeu du Chat et de la Souris - Erreurs",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        // Redessine le plateau
        for (int i = 0; i < Plateau.NB_COLONNES; i++) {
            for (int j = 0; j < Plateau.NB_LIGNES; j++) {
                CasePossible cp = unPlateau.getLesCases()[i][j].getTypeCase();
                switch (cp) {
                    case ENTREE:
                        jboard[i][j].setIcon(IN);
                        break;
                    case SORTIE:
                        jboard[i][j].setIcon(OUT);
                        break;
                    case FLECHE_BAS:
                        jboard[i][j].setIcon(BAS);
                        break;
                    case FLECHE_HAUT:
                        jboard[i][j].setIcon(HAUT);
                        break;
                    case FLECHE_DROITE:
                        jboard[i][j].setIcon(DROITE);
                        break;
                    case FLECHE_GAUCHE:
                        jboard[i][j].setIcon(GAUCHE);
                        break;
                    case TELEPORTEUR:
                        jboard[i][j].setIcon(TELPORTEUR);
                        nbTeleporteurPlateau++;
                        break;
                    case MUR:
                        jboard[i][j].setIcon(MUR);
                        break;
                    default:
                        jboard[i][j].setIcon(VIDE);
                }
            }
        }

        // Redessine les chats et les souris
        for (Animal a : unPlateau.getListeAnimaux()) {
            if (a instanceof Chat) {
                jboard[a.getPositionX()][a.getPositionY()].setIcon(CHAT);
            } else {
                jboard[a.getPositionX()][a.getPositionY()].setIcon(SOURIS);
            }
        }
 
        //Affichage du score
        score = unPlateau.getScore();

        //Cas ou il ya encore des souris sur le plateau
        if(unPlateau.getNbSourisPlateau() != 0){
            labScore.setText(String.valueOf(score));
        //Cas ou il n'y a plus de souris sur le plateau
        }else if(unPlateau.getNbSourisPlateau() == 0 && 
                 ControleurJouer.getTmp() > 2) {
            //Arrêt du timer à l'affichage
            frameChoixPlateau.getControl().cancel();
            frameChoixPlateau.getTimer().cancel();
            frameChoixPlateau.getTimer().purge();
            labScore.setText(String.valueOf(score));
            frameScore = new FenetreScore(this, true, score, unPlateau.getNbSouris(), unPlateau.getNbSourisMortes());
            frameScore.setVisible(true);
        }
    }

    /**
     * Méthode permettant de récupérer la fleche choisi en fonction
     * du bouton radio sélectionné
     *
     * @param x l'abscisse de la case selectionnée
     * @param y l'ordonnée de la case selectionnée
     * @return le type de la case
     */
    public CasePossible getChoix(int x, int y) {
        //Récupère le bouton radio choisi 
        if (up.isSelected()) {
            return CasePossible.FLECHE_HAUT;
        } else if (down.isSelected()) {
            return CasePossible.FLECHE_BAS;
        } else if (right.isSelected()) {
            return CasePossible.FLECHE_DROITE;
        } else if (left.isSelected()) {
            return CasePossible.FLECHE_GAUCHE;
        }else if(teleporteur.isSelected()){
            return CasePossible.TELEPORTEUR;
        }else{
            return CasePossible.VIDE;
        }
    }

    /**
     * Score de la partie en cours
     * @return le socre de la partie
     */
    public int getScore() {
        return score;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        panneauFond = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mainBackgroundImage, 0, 0, null);
            }
        };
        grilleJeu = new javax.swing.JPanel();
        panneauBouton = new javax.swing.JPanel();
        panneauIcon = new javax.swing.JPanel();
        btnPause = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        labSc = new javax.swing.JLabel();
        labScore = new javax.swing.JLabel();
        labSourisSauv = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        grilleJeu.setBackground(new java.awt.Color(52, 55, 58));
        grilleJeu.setBorder(null);
        grilleJeu.setLayout(new java.awt.GridLayout(6, 8));

        panneauBouton.setOpaque(false);
        panneauBouton.setLayout(new java.awt.GridLayout(0, 3));

        panneauIcon.setOpaque(false);
        panneauIcon.setLayout(new java.awt.GridLayout(1, 0));

        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnPlay.setText("Play");
        btnPlay.setEnabled(false);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        labSc.setForeground(new java.awt.Color(255, 255, 255));
        labSc.setText("Score :");
        labSc.setToolTipText("");

        labScore.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        labScore.setForeground(new java.awt.Color(255, 255, 255));
        labScore.setText("0");

        labSourisSauv.setForeground(new java.awt.Color(255, 255, 255));
        labSourisSauv.setText("Souris sauvées");

        javax.swing.GroupLayout panneauFondLayout = new javax.swing.GroupLayout(panneauFond);
        panneauFond.setLayout(panneauFondLayout);
        panneauFondLayout.setHorizontalGroup(
            panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauFondLayout.createSequentialGroup()
                .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panneauFondLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(panneauIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panneauFondLayout.createSequentialGroup()
                        .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panneauFondLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauFondLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauFondLayout.createSequentialGroup()
                                        .addComponent(labSc)
                                        .addGap(27, 27, 27)
                                        .addComponent(labScore, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labSourisSauv)
                                        .addGap(75, 75, 75))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauFondLayout.createSequentialGroup()
                                        .addComponent(panneauBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                        .addComponent(grilleJeu, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        panneauFondLayout.setVerticalGroup(
            panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauFondLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panneauIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(grilleJeu, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panneauFondLayout.createSequentialGroup()
                        .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labSc)
                            .addComponent(labScore)
                            .addComponent(labSourisSauv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panneauBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPause)
                            .addComponent(btnPlay))))
                .addGap(42, 42, 42))
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

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        btnPlay.setEnabled(true);
        btnPause.setEnabled(false);

        //Stop le timer après click sur play
        frameChoixPlateau.getTimer().cancel();
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        btnPlay.setEnabled(false);
        btnPause.setEnabled(true);

        //Relencer le timer pour les déplacements des animaux 
        frameChoixPlateau.resetControl();
        frameChoixPlateau.setTimer(new Timer());
        debut(frameChoixPlateau.getControl(), frameChoixPlateau.getTimer());
    }//GEN-LAST:event_btnPlayActionPerformed

    public FenetreChoixPlateau getFrameChoixPlateau() {
        return frameChoixPlateau;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnPlay;
    private javax.swing.JPanel grilleJeu;
    private javax.swing.JLabel labSc;
    private javax.swing.JLabel labScore;
    private javax.swing.JLabel labSourisSauv;
    private javax.swing.JPanel panneauBouton;
    private javax.swing.JPanel panneauFond;
    private javax.swing.JPanel panneauIcon;
    // End of variables declaration//GEN-END:variables

}
