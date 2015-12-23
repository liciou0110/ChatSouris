/*
 * FenetreEditer.java                     30/11/2015
 * Licence PRO RTAI
 */

package vue;

import controleur.ControleurCase;
import controleur.Observateur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modele.CasePossible;
import modele.Chat;
import modele.Plateau;

/**
 * Classe correspondant à la vue pour l'édition d'un niveau
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class FenetreEditer extends javax.swing.JFrame implements Observateur{

    /** Définition de l'image de fond **/
    private final Image mainBackgroundImage = new ImageIcon(getClass().
                  getResource("/background.png")).getImage();
    
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
    
    /** Radio Button pour les éléments à positionner */
    private final ImageRadioButton in = new ImageRadioButton("../imgs/in_detoure.png", 40);
    private final ImageRadioButton out = new ImageRadioButton("../imgs/out_detoure.png", 40);
    private final ImageRadioButton cat = new ImageRadioButton("../imgs/chat_detoure.png", 40);
    private final ImageRadioButton caseVide = new ImageRadioButton("../imgs/vide_detoure.png", 40);
    private final ImageRadioButton wall = new ImageRadioButton("../imgs/mur_detoure.png", 40);
        
    /** Défintion du plateau de jeu */
    private JLabel[][] jboard;
    
    /** Fenetre permettant de choisir l'orientation 
     * (le sens de déplacement du chat */
    private final FenetreOrientationChat frameOrientaitonCat = new 
            FenetreOrientationChat(this, true);

    /** Modèle correspondant au plateau de jeu */
    private final Plateau plateau;
    
    /** Fenetre permettant de poursuivre l'édition du niveau */
    private FenetreEditerNext frameEditNext;
    
    /** Boolean permettant de savoir si un chat est présent ou pas */
    private boolean chatPresent = false;
    
    /**
     * Constructeur par défaut
     * @param p le plateau p vide a editer
     */
    public FenetreEditer(Plateau p) {
        plateau = p;
        p.initialisePlateau();
                
        initComponents();
        definitionAffichage();
    }
    
    /**
     * Définit l'affichage de la fenêtre courante
     */
    public final void definitionAffichage(){
        //Bouton représenté par l'icon de l'application permettant 
        //de revenir au menu principal
        FenetreEditer currentFrame = this;
        HoverButton btnIcon = new HoverButton(ICON, ICONHOVER);
        panneauIcon.add(btnIcon);
        panneauIcon.revalidate();
        panneauIcon.repaint();
        champNom.setBorder(null);
        champNom.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        
        btnIcon.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                     currentFrame.dispose();
                }
        });
        
        jboard = new JLabel[Plateau.NB_COLONNES][Plateau.NB_LIGNES];
        for(int j = 0; j < Plateau.NB_LIGNES; j++){
            for(int i = 0; i < Plateau.NB_COLONNES; i++){
                jboard[i][j] = new JLabel(MUR);
                grilleJeu.add(jboard[i][j]);
                jboard[i][j].addMouseListener(new 
                ControleurCase(plateau, this, frameOrientaitonCat, i, j));
            }
        }
        
        //Ajoute les bouton au groupe de bouton
        btnGroup.add(in);
        btnGroup.add(out);
        btnGroup.add(cat);
        btnGroup.add(caseVide);
        btnGroup.add(wall);
        

        //Ajoute les label pour le texte a coté des composants
        JLabel vide = new JLabel("Case vide");
        vide.setForeground(Color.white);
        vide.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel entree = new JLabel("Entrée");
        entree.setForeground(Color.white);
        entree.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel sortie = new JLabel("Sortie");
        sortie.setForeground(Color.white);
        sortie.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel chat = new JLabel("Chat");
        chat.setForeground(Color.white);
        chat.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel mur = new JLabel("Mur");
        mur.setForeground(Color.white);
        mur.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        //Ajoute les composants au panneau
        panneauBouton.add(caseVide);
        panneauBouton.add(vide);
        panneauBouton.add(in);
        panneauBouton.add(entree);
        panneauBouton.add(out);
        panneauBouton.add(sortie);
        panneauBouton.add(cat);
        panneauBouton.add(chat);
        panneauBouton.add(wall);
        panneauBouton.add(mur);
        
        //Sélectionne une case vide par défaut
        caseVide.setSelected(true);
        
        //Remplis la grille de mur
        grilleJeu.setBackground(new Color(52,55,58));
    }
    
    /**
     * récupère le nom passer dans le champs du plateau
     * @return le contenu du champs nom
     */
    public String getNomPlateau(){
        return champNom.getText();
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
        btnNext = new javax.swing.JButton();
        labNom = new javax.swing.JLabel();
        champNom = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        grilleJeu.setBackground(new java.awt.Color(52, 55, 58));
        grilleJeu.setBorder(null);
        grilleJeu.setLayout(new java.awt.GridLayout(6, 8));

        panneauBouton.setOpaque(false);
        panneauBouton.setLayout(new java.awt.GridLayout(0, 2));

        panneauIcon.setOpaque(false);
        panneauIcon.setLayout(new java.awt.GridLayout(1, 0));

        btnNext.setText("Suivant");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        labNom.setForeground(new java.awt.Color(255, 255, 255));
        labNom.setText("Nom : ");

        champNom.setBackground(new java.awt.Color(75, 80, 84));
        champNom.setForeground(new java.awt.Color(255, 255, 255));
        champNom.setToolTipText("");

        javax.swing.GroupLayout panneauFondLayout = new javax.swing.GroupLayout(panneauFond);
        panneauFond.setLayout(panneauFondLayout);
        panneauFondLayout.setHorizontalGroup(
            panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauFondLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauFondLayout.createSequentialGroup()
                        .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panneauFondLayout.createSequentialGroup()
                                .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panneauFondLayout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(labNom)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(champNom)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauFondLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(panneauBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)))
                        .addComponent(grilleJeu, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(panneauFondLayout.createSequentialGroup()
                        .addComponent(panneauIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panneauFondLayout.setVerticalGroup(
            panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauFondLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panneauIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauFondLayout.createSequentialGroup()
                        .addGroup(panneauFondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labNom)
                            .addComponent(champNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panneauBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnNext))
                    .addComponent(grilleJeu, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
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

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if(plateau.getNbCasesVides() < 10){
            JOptionPane.showMessageDialog(this, 
                    "Il vous faut plus de 10 cases vides pour "
                  + "poursuivre l'édition du niveau.", 
                    "Jeu du Chat et de la Souris - Erreurs",
            JOptionPane.ERROR_MESSAGE);
        }else if(getNomPlateau().equals("")){
             JOptionPane.showMessageDialog(this, 
                "Veuillez saisir un nom à votre niveau.", 
                "Jeu du Chat et de la Souris - Erreurs",
             JOptionPane.ERROR_MESSAGE);  
             
        }else if(plateau.getNbEntrees() < 1){
             JOptionPane.showMessageDialog(this, 
                "Votre niveau doit au moins posséder une entrée.", 
                "Jeu du Chat et de la Souris - Erreurs",
             JOptionPane.ERROR_MESSAGE);  
        }else if(plateau.getNbSorties() < 1){
             JOptionPane.showMessageDialog(this, 
                "Votre niveau doit au moins posséder une sortie.", 
                "Jeu du Chat et de la Souris - Erreurs",
             JOptionPane.ERROR_MESSAGE);  
        } else if (Start.existsPlateau(getNomPlateau())) {
            JOptionPane.showMessageDialog(this, 
                "Un plateau du même nom existe déjà.", 
                "Jeu du Chat et de la Souris - Erreurs",
             JOptionPane.ERROR_MESSAGE);  
        }else{
            plateau.setNomNiveau(getNomPlateau());
            frameEditNext = new FenetreEditerNext(plateau);
            frameEditNext.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    int nbCaseVide;
    
    /** 
     * Modifie l'icone de la case sur laquel on a cliqué en fonction du 
     * radion button sélectionné
     * @param x l'abscisse de la case selectionnée
     * @param y l'ordonnée de la case selectionnée
     */
    public void setIconCase(int x, int y){
        if(caseVide.isSelected()){
            if(jboard[x][y].getIcon() != VIDE){
                nbCaseVide++;
            }
            jboard[x][y].setIcon(VIDE);
        }else if(in.isSelected()){
            if(jboard[x][y].getIcon() == VIDE){
                nbCaseVide--;
            }
            if(plateau.getNbEntrees() < 2 || jboard[x][y].getIcon() == IN){
                jboard[x][y].setIcon(IN);
            }else{
                JOptionPane.showMessageDialog(this, 
                        "Vous ne pouvez pas placer plus de deux entrées.", 
                        "Jeu du Chat et de la Souris - Erreurs",
                JOptionPane.ERROR_MESSAGE);
            }
            supprimeChat();
        }else if(out.isSelected()){
            if(jboard[x][y].getIcon() == VIDE){
                nbCaseVide--;
            }
            if(plateau.getNbSorties() < 2  || jboard[x][y].getIcon() == OUT){
                jboard[x][y].setIcon(OUT);
            }else{
                JOptionPane.showMessageDialog(this, 
                        "Vous ne pouvez pas place plus de deux sorties.", 
                        "Jeu du Chat et de la Souris - Erreurs",
                JOptionPane.ERROR_MESSAGE);
            }
            supprimeChat();
        }else if(cat.isSelected()){
            if(jboard[x][y].getIcon() == VIDE){
                
                //Affichage de la fenêtre des choix d'orientation d'un chat
                if(plateau.getNbChats() < plateau.getNbCasesVides()/6){
                    frameOrientaitonCat.setVisible(true);
                    //On verifie l'orientation du chat si jamais il 
                    //quitte le choix la case reste vide
                    if(frameOrientaitonCat.getOrientation() != null){
                        jboard[x][y].setIcon(CHAT);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, 
                            "Il n'y a pas assez de case vide pour "
                          + "positionner un nouveau chat.", 
                            "Jeu du Chat et de la Souris - Erreurs",
                    JOptionPane.ERROR_MESSAGE);
                }
            }else if(jboard[x][y].getIcon() == CHAT){
                JOptionPane.showMessageDialog(this, 
                        "Il y a déjà un chat sur cette case."
                      + " Placement du chat impossible", 
                        "Jeu du Chat et de la Souris - Erreurs",
                JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, 
                        "Le chat doit être placé sur une case vide."
                      + " Placement du chat impossible", 
                        "Jeu du Chat et de la Souris - Erreurs",
                JOptionPane.ERROR_MESSAGE);
            }
        }else{
            if(jboard[x][y].getIcon() == VIDE){
                nbCaseVide--;
            }
            jboard[x][y].setIcon(MUR);
            supprimeChat();
        }
    }
    
    /**
     * Méthode permettant d'enlever un chat si toute fois il n'y 
     * pas assez de case vide sur la grille
     */
    public void supprimeChat(){
        if(plateau.getNbChats() == 1 && nbCaseVide < 6 ||
           plateau.getNbChats() == 2 && nbCaseVide < 12 ||
           plateau.getNbChats() == 3 && nbCaseVide < 18 ||
           plateau.getNbChats() == 4 && nbCaseVide < 24 ||
           plateau.getNbChats() == 5 && nbCaseVide < 30 ||
           plateau.getNbChats() == 6 && nbCaseVide < 36 ||
           plateau.getNbChats() == 7 && nbCaseVide < 42 ||
           plateau.getNbChats() == 8 && nbCaseVide < 48){
            
            ArrayList<Chat> listeChat = plateau.getLesChats();

            //Récupère les coordonnées du dernier chat
            String coord = listeChat.get(listeChat.size()-1).getPositionX()
                   + "," + listeChat.get(listeChat.size()-1).getPositionY();

                        System.out.println(coord);
            
            //Découpe la coordonnée en deux
            String[] lesCoord = coord.split(",");

            //Enlève le chat dans la case 
            jboard[Integer.parseInt(lesCoord[0])]
                  [Integer.parseInt(lesCoord[1])].setIcon(VIDE);
        }
    }

    /**
     * Permet de savoir si un chat est présent dans une case ou pas
     * @return true si le chat est présent
     *         fasle sinon
     */
    public boolean isChatPresent() {
        return chatPresent;
    }

    @Override
    public void avertir(int x, int y) {
        chatPresent = false;
        if(cat.isSelected()){
            chatPresent = true;
        }
    }

    /**
     * Méthode permettant de récupérer le type d'un case en fonction 
     * du bouton radio sélectionné et pour le chat du clique sur la grille
     * @param x l'abscisse de la case selectionnée
     * @param y l'ordonnée de la case selectionnée
     * @return le type de la case
     */
    public CasePossible getChoix(int x, int y){
        //Vérifie si la case choisi au moment du clic sur la grille
        if(jboard[x][y].getIcon() != MUR){      
            //Récupère le bouton radio choisi 
            if(caseVide.isSelected()){
                return CasePossible.VIDE;
            }else if(in.isSelected()){
                return CasePossible.ENTREE;
            }else if(out.isSelected()){
                return CasePossible.SORTIE;
            }else if(cat.isSelected()){
                return CasePossible.VIDE;
            }else{
                return CasePossible.MUR;
            }
        }else{
            return CasePossible.MUR;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnNext;
    private javax.swing.JTextField champNom;
    private javax.swing.JPanel grilleJeu;
    private javax.swing.JLabel labNom;
    private javax.swing.JPanel panneauBouton;
    private javax.swing.JPanel panneauFond;
    private javax.swing.JPanel panneauIcon;
    // End of variables declaration//GEN-END:variables
}
