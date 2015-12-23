/*
 * Plateau.java                     30/11/2015
 * Licence PRO RTAI
 */

package modele;
import controleur.Observateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static modele.CasePossible.*;
import static modele.OrientationPossible.*;

/**
 * Caractérise la Labyrinthe dans lequel vont se déplacer les Souris et les Chats
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class Plateau implements Serializable, Cloneable {
    
    /** Liste des cases du plateau */
    private Case[][] lesCases;
    
    /** Le nom du niveau que l'on est en train de créer */
    private String nomNiveau;
    
    /** Le nombre de chats dans le jeu */
    private int nbChats;
    
    /** Le nombre de souris au départ */
    private int nbSouris;

    /** Le nombre de souris sur le plateau */
    private int nbSourisPlateau;

    /** Le nombre de souris mortes */
    private int nbSourisMortes;

    /** Le nombre de porte d'entrées dans le jeu */
    private int nbEntrees;
    
    /** Le nombre de porte de sorties dans le jeu */
    private int nbSorties;
    
    /** Nombre de flèches droites disponible pour jouer */
    private int nbFlechesDroite;
    
    /** Nombre de flèches gauche disponible pour jouer */
    private int nbFlechesGauche;
    
    /** Nombre de flèches hauts disponible pour jouer */
    private int nbFlechesHaut;
    
    /** Nombre de flèches bas disponible pour jouer */
    private int nbFlechesBas;
    
    /** Nombre de cases vides dans le jeu */
    private int nbCasesVides;
    
    /** Nombre de téléporteurs dans le jeu */
    private int nbTeleporteurs;
    
    /** Score du joueur : Nombre de souris sorties */
    private int score;
    
    /** Liste d'observateurs */
    private transient ArrayList<Observateur> listeObservateur; // transient : pour ne pas serialiser les fenetres
    
    /** Constante correspondant au nombre de case par ligne */
    public static int NB_COLONNES = 8;
    
    /** Constante correspondant au nombre de case par colonne */
    public static int NB_LIGNES = 6;
    
    /**Liste des animaux */
    private ArrayList<Animal> listeAnimaux;

    /**
     * Constructeur permettant de cloner un plateau (permet de casser les pointeurs)
     * @param p Le plateau a cloner
     */
    public Plateau(Plateau p) {
        this(p.getNbChats(), p.getNbSouris(), p.getNbEntrees(), p.getNbSorties(), p.getNbFlechesDroite(), p.getNbFlechesGauche(), p.getNbFlechesHaut(), p.getNbFlechesBas(), p.getNbCasesVides(), p.getNbTeleporteurs());
        this.lesCases = p.cloneCases();
        this.nomNiveau = p.getNomNiveau();
        this.listeAnimaux = p.cloneAnimaux();
    }

    /**
     * Définit le plateau avec le nombre des différents éléments qu'il contient
     * @param chats nombre de chats
     * @param souris nombre de souris 
     * @param entrees nombre d'entrées 
     * @param sorties nombre de sorties 
     * @param fdroites nombre de flèches droites
     * @param fgauches nombre de flèches gauches 
     * @param fhauts nombre de flèches hauts 
     * @param fbas nombre de flèches bas 
     * @param casesVides nombre de cases vides 
     * @param teleporteurs nombre de téléporteurs 
     */
    public Plateau(int chats, int souris, int entrees, int sorties, 
            int fdroites, int fgauches, int fhauts, int fbas, int casesVides,
            int teleporteurs) {
        lesCases = new Case[NB_COLONNES][NB_LIGNES];
        nomNiveau = "";
        listeAnimaux = new ArrayList<>();
        listeObservateur = new ArrayList<>();
        nbChats = chats;
        nbSouris = souris;
        nbEntrees = entrees;
        nbSorties = sorties;
        nbFlechesDroite = fdroites;
        nbFlechesGauche = fgauches;
        nbFlechesHaut = fhauts;
        nbFlechesBas = fbas;
        nbCasesVides = casesVides;
        nbTeleporteurs = teleporteurs;
    }
    
    /**
     * Permet la création du plateau par défaut
     */
    public Plateau() {
        nomNiveau = "Niveau par défaut";
        listeObservateur = new ArrayList<>();
        listeAnimaux = new ArrayList<>();

        Chat cat1 = new Chat(0, 2, DROITE);
        Chat cat2 = new Chat(4, 2, BAS);

        listeAnimaux.add(cat1);
        listeAnimaux.add(cat2);

        Case case1 = new Case(0, 0, ENTREE);
        Case case2 = new Case(1, 0, VIDE);
        Case case3 = new Case(2, 0, VIDE);
        Case case4 = new Case(3, 0, MUR);
        Case case5 = new Case(4, 0, VIDE);
        Case case6 = new Case(5, 0, VIDE);
        Case case7 = new Case(6, 0, VIDE);
        Case case8 = new Case(7, 0, VIDE);
        Case case9 = new Case(0, 1, VIDE);
        Case case10 = new Case(1, 1, MUR);
        Case case11 = new Case(2, 1, VIDE);
        Case case12 = new Case(3, 1, VIDE);
        Case case13 = new Case(4, 1, VIDE);
        Case case14 = new Case(5, 1, MUR);
        Case case15 = new Case(6, 1, VIDE);
        Case case16 = new Case(7, 1, VIDE);
        Case case17 = new Case(0, 2, VIDE);
        Case case18 = new Case(1, 2, VIDE);
        Case case19 = new Case(2, 2, VIDE);
        Case case20 = new Case(3, 2, MUR);
        Case case21 = new Case(4, 2, VIDE);
        Case case22 = new Case(5, 2, MUR);
        Case case23 = new Case(6, 2, MUR);
        Case case24 = new Case(7, 2, VIDE);
        Case case25 = new Case(0, 3, VIDE);
        Case case26 = new Case(1, 3, VIDE);
        Case case27 = new Case(2, 3, MUR);
        Case case28 = new Case(3, 3, VIDE);
        Case case29 = new Case(4, 3, VIDE);
        Case case30 = new Case(5, 3, VIDE);
        Case case31 = new Case(6, 3, VIDE);
        Case case32 = new Case(7, 3, VIDE);
        Case case33 = new Case(0, 4, VIDE);
        Case case34 = new Case(1, 4, VIDE);
        Case case35 = new Case(2, 4, VIDE);
        Case case36 = new Case(3, 4, MUR);
        Case case37 = new Case(4, 4, MUR);
        Case case38 = new Case(5, 4, VIDE);
        Case case39 = new Case(6, 4, MUR);
        Case case40 = new Case(7, 4, VIDE);
        Case case41 = new Case(0, 5, VIDE);
        Case case42 = new Case(1, 5, MUR);
        Case case43 = new Case(2, 5, VIDE);
        Case case44 = new Case(3, 5, VIDE);
        Case case45 = new Case(4, 5, VIDE);
        Case case46 = new Case(5, 5, VIDE);
        Case case47 = new Case(6, 5, VIDE);
        Case case48 = new Case(7, 5, SORTIE);

        lesCases = new Case[NB_COLONNES][NB_LIGNES];
        lesCases[0][0] = case1;
        lesCases[1][0] = case2;
        lesCases[2][0] = case3;
        lesCases[3][0] = case4;
        lesCases[4][0] = case5;
        lesCases[5][0] = case6;
        lesCases[6][0] = case7;
        lesCases[7][0] = case8;
        lesCases[0][1] = case9;
        lesCases[1][1] = case10;
        lesCases[2][1] = case11;
        lesCases[3][1] = case12;
        lesCases[4][1] = case13;
        lesCases[5][1] = case14;
        lesCases[6][1] = case15;
        lesCases[7][1] = case16;
        lesCases[0][2] = case17;
        lesCases[1][2] = case18;
        lesCases[2][2] = case19;
        lesCases[3][2] = case20;
        lesCases[4][2] = case21;
        lesCases[5][2] = case22;
        lesCases[6][2] = case23;
        lesCases[7][2] = case24;
        lesCases[0][3] = case25;
        lesCases[1][3] = case26;
        lesCases[2][3] = case27;
        lesCases[3][3] = case28;
        lesCases[4][3] = case29;
        lesCases[5][3] = case30;
        lesCases[6][3] = case31;
        lesCases[7][3] = case32;
        lesCases[0][4] = case33;
        lesCases[1][4] = case34;
        lesCases[2][4] = case35;
        lesCases[3][4] = case36;
        lesCases[4][4] = case37;
        lesCases[5][4] = case38;
        lesCases[6][4] = case39;
        lesCases[7][4] = case40;
        lesCases[0][5] = case41;
        lesCases[1][5] = case42;
        lesCases[2][5] = case43;
        lesCases[3][5] = case44;
        lesCases[4][5] = case45;
        lesCases[5][5] = case46;
        lesCases[6][5] = case47;
        lesCases[7][5] = case48;

        nbChats = 2;
        nbSouris = 4;
        nbEntrees = 1;
        nbSorties = 1;
        nbFlechesDroite = 2;
        nbFlechesGauche = 0;
        nbFlechesHaut = 1;
        nbFlechesBas = 2;
        nbCasesVides = 35;
        nbTeleporteurs = 0;

    }

    /**
     * Affiche le score qui correspond au nombre de souris sorties au total
     * @return le score
     */
    public String afficheScore() {
        // TODO
        return null;
    }

    /**
     * Récupère les cases du plateau 
     * @return la liste des cases 
     */
    public Case[][] getLesCases() {
        return lesCases;
    }

    /**
     * Récupère le nombre de chats présents dans le jeu
     * @return le nombre de chats
     */
    public int getNbChats() {
        return nbChats;
    }

    /**
     * Récupère le nombre de souris présents dans le jeu 
     * @return le nombre de souris
     */
    public int getNbSouris() {
        return nbSouris;
    }

    /**
     * Récupère le nombre de souris mortes au cours du jeu
     * @return le nombre de souris mortes
     */
    public int getNbSourisMortes() {
        return nbSourisMortes;
    }

    /**
     * Récupère le nombre de portes d'entrées 
     * @return le nombre de portes d'entrées 
     */
    public int getNbEntrees() {
        return nbEntrees;
    }

    /**
     * Récupère le nombre de portes de sorties 
     * @return le nombre de portes de sorties 
     */
    public int getNbSorties() {
        return nbSorties;
    }

    /**
     * Récupère le nombre de flèches droites 
     * pouvant être placer sur le plateau
     * @return le nombre de flèches droites
     */
    public int getNbFlechesDroite() {
        return nbFlechesDroite;
    }

    /**
     * Récupère le nombre de flèches gauches 
     * pouvant être placer sur le plateau
     * @return le nombre de flèches gauches
     */
    public int getNbFlechesGauche() {
        return nbFlechesGauche;
    }

    /**
     * Récupère le nombre de flèches hauts 
     * pouvant être placer sur le plateau
     * @return le nombre de flèches hauts
     */
    public int getNbFlechesHaut() {
        return nbFlechesHaut;
    }

    /**
     * Récupère le nombre de flèches bas 
     * pouvant être placer sur le plateau 
     * @return le nombre de flèches bas
     */
    public int getNbFlechesBas() {
        return nbFlechesBas;
    }

    /**
     * Récupère le nombre de cases vides sur le plateau 
     * @return le nombre de cases vides 
     */
    public int getNbCasesVides() {
        return nbCasesVides;
    }

    /**
     * Récupère le nombre de téléporteurs 
     * pouvant être placer sur le plateau
     * @return le nombre de téléporteurs
     */
    public int getNbTeleporteurs() {
        return nbTeleporteurs;
    }

    /**
     * Récupère le score c'est-à-dire le nombre de souris sorties
     * @return le nombre de souris sorties
     */
    public int getScore() {
        return score;
    }

    /**
     * Récupère le nom du niveau 
     * @return le nom du niveau (plateau)
     */
    public String getNomNiveau() {
        return nomNiveau;
    }

    /**
     * Récupère la liste des observateurs
     * @return la liste des observateurs
     */
    public ArrayList<Observateur> getListeObservateur() {
        return listeObservateur;
    }

    /**
     * Permet d'ajouter un observateur
     * @param obs l'observateur a ajouter
     */
    public void addObservateur(Observateur obs){
        // Préventif en cas de désérialisation => Initialisation de la liste
        if(listeObservateur == null)
            listeObservateur = new ArrayList<>();
        listeObservateur.add(obs); 
    }
    
    /**
     * Permet de supprimer un observatreur
     * @param obs observateur a supprimer
     */
    public void removeObservateur(Observateur obs){
        listeObservateur.remove(obs);
    }

    /**
     * Permet de modifier le nom du plateau
     * @param nomNiveau le nouveau nom du plateau
     */
    public void setNomNiveau(String nomNiveau) {
        this.nomNiveau = nomNiveau;
    }

    /**
     * Permet de modifier le nombre de flêche droite
     * @param nbFlechesDroite le nouveau nombre de flêche droite
     */
    public void setNbFlechesDroite(int nbFlechesDroite) {
        this.nbFlechesDroite = nbFlechesDroite;
    }

    /**
     * Permet de modifier le nombre de flêche gauche
     * @param nbFlechesGauche le nouveau nombre de flêche gauche
     */
    public void setNbFlechesGauche(int nbFlechesGauche) {
        this.nbFlechesGauche = nbFlechesGauche;
    }

    /**
     * Permet de modifier le nombre de flêche Haute
     * @param nbFlechesHaut le nouveau nombre de flêche haute
     */
    public void setNbFlechesHaut(int nbFlechesHaut) {
        this.nbFlechesHaut = nbFlechesHaut;
    }

    /**
     * Permet de modifier le nombre de flêche Basse
     * @param nbFlechesBas le nouveau nombre de flêche jobasse
     */
    public void setNbFlechesBas(int nbFlechesBas) {
        this.nbFlechesBas = nbFlechesBas;
    }
    
    /**
     * Permet de modifier le nombre de téléporteur
     * @param nbTeleporteurs le nouveau nombre de téléporteur
     */
    public void setNbTeleporteurs(int nbTeleporteurs) {
        this.nbTeleporteurs = nbTeleporteurs;
    }

    /**
     * Permet de modifier le nombre de souris
     * @param nbSouris le nouveau nombre de souris
     */
    public void setNbSouris(int nbSouris) {
        this.nbSouris = nbSouris;
    }

    /**
     * Récupère la liste des animaux du plateau
     * @return  la liste des animaiux
     */
    public ArrayList<Animal> getListeAnimaux() {
        return listeAnimaux;
    }

    /**
     * Récupère le nombre de souris sur le plateau
     * @return le nombre de souris du plateau
     */
    public int getNbSourisPlateau() {
        return nbSourisPlateau;
    }

    /**
     * Modifie le nombre de souris présente sur le plateau
     * @param nbSourisPlateau le nouveau  ombre de souris sur le plateau
     */
    public void setNbSourisPlateau(int nbSourisPlateau) {
        this.nbSourisPlateau = nbSourisPlateau;
    }

    /**
     * Permet d'avertir l'observateur
     * @param x correspond à l'abscisse de la case
     * @param y correspond à l'ordonnée de la case
     */
    public void avertirObs(int x, int y){
        for (Observateur listeObs : listeObservateur) {
            listeObs.avertir(x, y);
        }
    }
    
    /**
     * Permet d'initialiser la grille avec des murs sur toutes les case
     */
    public void initialisePlateau(){
        for(int i = 0; i < NB_COLONNES; i++){
            for(int j = 0; j < NB_LIGNES; j++){
                lesCases[i][j] = new Case(i, j, MUR);
            }
        }
        listeAnimaux.clear();
    }

    /**
     * Récupère la liste des chats de la liste des animaux
     * @return  la liste des chats du palteau
     */
    public ArrayList<Chat> getLesChats() {
        ArrayList<Chat> listeChats = new ArrayList<>();
        for (Animal aListeAnimaux : listeAnimaux) {
            if (aListeAnimaux instanceof Chat) {
                listeChats.add((Chat) aListeAnimaux);
            }
        }
        return listeChats;
    }

    /**
     * Récupère la liste des souris de la liste des animaux
     * @return  la liste des souris du plateau
     */
    public ArrayList<Souris> getLesSouris() {
        ArrayList<Souris> listeSouris = new ArrayList<>();
        for (Animal animal : listeAnimaux) {
            if (animal instanceof Souris) {
                listeSouris.add((Souris) animal);
            }
        }
        return listeSouris;
    }

    /**
     *  Permet l'afficahge textuelle du plateau d'édition
     */
    public void plateauToString(){
        for(int i = 0; i < NB_COLONNES; i++){
            for(int j = 0; j < NB_LIGNES; j++){
                    for(int k= 0; k < lesCases[i][j].getAnimaux().size(); k++){
                        if(lesCases[i][j].getAnimaux().get(k) != null){
                            System.out.print(lesCases[i][j].getTypeCase()+ "/" +
                            lesCases[i][j].getAnimaux().get(k).getNom() + " " +
                            lesCases[i][j].getAnimaux().get(k).getOrientation() + "    ");
                        }
                    }
                    System.out.print(lesCases[i][j].getTypeCase() + " \t");
            }
            System.out.println("");
        }
        System.out.println("In : " + nbEntrees + " Out : " + nbSorties + 
                           " Chats : " + nbChats + " Case Vide : " + nbCasesVides);
    }
    
    /**
     * Permet d'éditer le plateau de jeu en fonction des cases choisi
     * @param x correspond à l'abcsisse de la case
     * @param y correspond à l'ordonnée de la case
     * @param newCase la nouvelle case choisie
     * @param orientation l'orientation du chat
     * @param chatPresent vaut true si la case contient un chat false sinon
     */
    public void editer(int x, int y, CasePossible newCase, 
                       OrientationPossible orientation, 
                       boolean chatPresent){ 

        CasePossible currentCase = lesCases[x][y].getTypeCase(); // type de la current case
        Case curCase = lesCases[x][y];
        
        // Cas où on a choisit de placer un chat sur une case
        if(orientation != null && chatPresent && 
           currentCase == VIDE && curCase.getAnimaux().isEmpty()){
            // Il faut qu'il y ai max un chat toutes les 6 cases vides
            if(nbChats < nbCasesVides/6){
                Chat minou = new Chat(x,y,orientation);
                listeAnimaux.add(minou);  // Ajout du chat dans la liste des animaux
                curCase.addAnimal(minou); // Ajout du chat à la case
                nbChats++; // Incrémente le nombre de chats
            }
        }else if(!chatPresent && !curCase.getAnimaux().isEmpty()){
            //Enlève un animal si on le remplace par une autre case
            curCase.removeAnimal(curCase.getAnimaux().get(0));
            
            //Enlève le chat de la liste d'animaux
            for(int i = 0; i < listeAnimaux.size(); i++){
                if(listeAnimaux.get(i).getPositionX() == curCase.getCoordonneeX()
                && listeAnimaux.get(i).getPositionY() == curCase.getCoordonneeY()){
                    listeAnimaux.remove(listeAnimaux.get(i));
                }
            }
            
            //Décrementaiton du nombre de chat
            nbChats--;
        }

        //Cas ou on veut rajouter une entrée et qu'on a a déjà deux
        if(newCase == ENTREE && nbEntrees < 2){
            lesCases[x][y].setTypeCase(newCase);
            
            //Compte le nombre de d'entreé et enlève 
            //des entree / sortie / vide si besoin
            nbEntrees++;
            decrementeQuantites(currentCase);
        //Cas ou on veut rajouter une sortie et qu'n en a déjà deux
        }else if(newCase == SORTIE && nbSorties < 2){
            lesCases[x][y].setTypeCase(newCase);
            
            //Compte le nombre de sortie et enlève des 
            //entree / sortie / vide si besoin
            nbSorties++;
            decrementeQuantites(currentCase);
        }else if(newCase == MUR || newCase == VIDE){
            if(!(chatPresent && (currentCase == ENTREE || currentCase == SORTIE))){
               lesCases[x][y].setTypeCase(newCase); 
               //COmpte le nombre de case Vide
                if(newCase == VIDE){
                    nbCasesVides++;
                    decrementeQuantites(currentCase);
                //Enlève des case vide /entree/sortie si nouvelle case = mur
                }else {
                    decrementeQuantites(currentCase);
                }
            }
        }

        //Cas ou on enlève une case vide et qu'il n'y a plus 
        //assez de case vide pour avoir un chat
        if((nbChats == 1 && nbCasesVides < 6) ||
            (nbChats == 2 && nbCasesVides < 12) ||
            (nbChats == 3 && nbCasesVides < 18) ||
            (nbChats == 4 && nbCasesVides < 24) ||
            (nbChats == 5 && nbCasesVides < 30) ||
            (nbChats == 6 && nbCasesVides < 36) ||
            (nbChats == 7 && nbCasesVides < 42) ||
            (nbChats == 8 && nbCasesVides < 48)){
            
            ArrayList<Chat> listeCats = getLesChats();
            
            if(listeCats.get(listeCats.size()-1) != null){

                Chat lastCat = listeCats.get(listeCats.size()-1);

                //Décrmente le nombre de chat
                nbChats--;
                
                //Supprime dans la liste des animaux
                for(int i = 0; i < listeAnimaux.size(); i++){
                    if(listeAnimaux.get(i).getPositionX() == 
                       lastCat.getPositionX() && 
                       listeAnimaux.get(i).getPositionY() == 
                       lastCat.getPositionY()){
                       listeAnimaux.remove(listeAnimaux.get(i));
                    }
                }
                
                //Enlève le dernier chat placé
                lesCases[lastCat.getPositionX()][lastCat.getPositionY()].
                removeAnimal(lesCases[lastCat.getPositionX()]
                            [lastCat.getPositionY()].getAnimaux().get(0));
            }
        }
        
        //Averti l'observateur
        avertirObs(x,y);
    }

    /**
     * Décremente le nombre de cases vides, de sorties ou d'entrees
     * en fonction du type de case courant
     * @param typeCase le type de la current case
     */
    public void decrementeQuantites(CasePossible typeCase) {
        if(typeCase == ENTREE){
            nbEntrees--;
        }else if(typeCase == SORTIE){
            nbSorties--;
        }else if(typeCase == VIDE){
            nbCasesVides--;
        }
    }

    /**
     * Permet de faire déplacer les animaux et ainsi de lancer le jeu
     */
    public void jouer() {
        // Si le nombre de souris est SUPERIEUR au nombre de souris sur le plateau + le nombre de souris sauvées
        if(nbSouris > nbSourisPlateau + score + nbSourisMortes) {
            Case entree;
            if(getEntrees().size() > 1) {
                int random = new Random().nextInt(getEntrees().size());
                entree = getEntrees().get(random);
            } else {
                entree = getEntrees().get(0);
            }

            listeAnimaux.add(new Souris(entree.getCoordonneeX(),
                                        entree.getCoordonneeY(),
                                        OrientationPossible.DROITE));
            nbSourisPlateau++;
        }

        // Déplacement des animaux
        for(Animal a : listeAnimaux) {
            a.deplacer(lesCases);
        }

        mangerSouris(); // Suppression des souris mangées par les chats
        sauverSouris(); // Score : nombre de souris sauvées

        // Averti l'observateur
        avertirObs(-1,-1);
    }

    /**
     * Récupère les cases ENTREE présente sur le plateau
     * @return la liste des entrées
     */
    private ArrayList<Case> getEntrees() {
        ArrayList<Case> res = new ArrayList<>();
        for(int i = 0; i < NB_COLONNES; i++) {
            for (int j = 0; j < NB_LIGNES; j++) {
                if(lesCases[i][j].getTypeCase() == ENTREE)
                    res.add(lesCases[i][j]);
            }
        }
        return res;
    }


    /**
     * Vérifie l'existance d'une souris et d'un chat sur la même position
     * Si c'est le cas, la souris meurt. Le nombre de souris mortes s'incrémente.
     */
    private void mangerSouris() {
        ArrayList<Animal> toRemove = new ArrayList<>();
        for (Animal a1 : listeAnimaux) {
            for (Animal a2 : listeAnimaux) {
                if (a1.positionX == a2.positionX && a1.positionY == a2.positionY) {
                    if (a1 instanceof Chat && a2 instanceof Souris) {
                        toRemove.add(a2); // Place la souris dans la liste des animaux a supprimer
                    } else if (a1 instanceof Souris && a2 instanceof Chat) {
                        toRemove.add(a1); // Place la souris dans la liste des animaux a supprimer
                    }
                }
            }
        }

        // Parcours la liste des animaux qui doivent être supprimés
        for (Animal a : toRemove) {
            if (a instanceof Souris) {
                nbSourisPlateau--; // décremente le nombre de souris qui a sur le plateau
                nbSourisMortes++;
            }
            listeAnimaux.remove(a); // Supprime l'animal de la liste des animaux
        }
    }

    /**
     * Compte le nombre de souris sorties
     */
    private void sauverSouris() {
        ArrayList<Souris> sourisSauvees = new ArrayList<>();
        for (Souris s : getLesSouris()) {
            if (lesCases[s.getPositionX()][s.positionY].getTypeCase() == SORTIE) { // si une souris est sur une entrée
                sourisSauvees.add(s);
            }
        }

        // Parcours la liste des souris sauvées
        for (Souris s : sourisSauvees) {
            score++;
            System.out.println("Score : " + getScore()); // A supprimer
            nbSourisPlateau--;
            listeAnimaux.remove(s);
        }
    }

    /**
     * Va permettre de placer les flêches ou le téléporteur sur le plateau de jeu
     * @param x la corddonnée x correspondant a l'axe des abscisses
     * @param y la coordonnée y correspondant à l'axe des ordonnées
     * @param fleche la flêche choisi par le radio Button
     */
    public void placerFleche(int x, int y, CasePossible fleche){
        Case currentCase = lesCases[x][y];

        //Vérifie dans un premier temps si il y a assez
        //de fleche pour positioner la fleche choisie
        if((fleche == FLECHE_BAS && nbFlechesBas > 0) ||
           (fleche == FLECHE_DROITE && nbFlechesDroite > 0) ||
           (fleche == FLECHE_GAUCHE && nbFlechesGauche > 0) ||
           (fleche == FLECHE_HAUT && nbFlechesHaut > 0 ) ||
           (fleche == TELEPORTEUR && nbTeleporteurs > 0) ||
            fleche == VIDE){

            //Cas normal ou il y a pas d'animaux ou la case courante est
            //vide et le bouton choisi n'est pas une case vide
            if(currentCase.getAnimaux().isEmpty() &&
               currentCase.getTypeCase() == VIDE && fleche != VIDE){
                currentCase.setTypeCase(fleche);

                decrementeFleche(fleche);
                System.out.println("test");
            //Cas ou il n'y a pas d'animaux et que la case courante est une fleche haute
            } else if (currentCase.getAnimaux().isEmpty() && currentCase.getTypeCase() == FLECHE_HAUT) {
                modifValeurFleche(currentCase.getTypeCase(), fleche);
                currentCase.setTypeCase(fleche);
            //Cas ou il n'y a pas d'animaux et que la case courante est une fleche basse
            }else if(currentCase.getAnimaux().isEmpty() && currentCase.getTypeCase() == FLECHE_BAS){
                modifValeurFleche(currentCase.getTypeCase(), fleche);
                currentCase.setTypeCase(fleche);

            //Cas ou il n'y a pas d'animaux et que la case courante est une fleche gauche
            }else if(currentCase.getAnimaux().isEmpty() && currentCase.getTypeCase() == FLECHE_GAUCHE){
                modifValeurFleche(currentCase.getTypeCase(), fleche);
                currentCase.setTypeCase(fleche);

            //Cas ou il n'y a pas d'animaux et que la case courante est une fleche droite
            }else if(currentCase.getAnimaux().isEmpty() && currentCase.getTypeCase() == FLECHE_DROITE){
                modifValeurFleche(currentCase.getTypeCase(), fleche);
                currentCase.setTypeCase(fleche);

            //Cas ou il n'y a pas d'animaux et que la case courante est un teleporteur
            }else if(currentCase.getAnimaux().isEmpty() && currentCase.getTypeCase() == TELEPORTEUR){
                modifValeurFleche(currentCase.getTypeCase(), fleche);
                currentCase.setTypeCase(fleche);
            }
        }

        avertirObs(x,y);
    }

    /**
     * Permet de décrémenter le nombre de fleche si on a placer la fleche
     * @param fleche la fleche a décrémenter une fois placé
     */
    public void decrementeFleche(CasePossible fleche){
        if(fleche == FLECHE_HAUT){
            nbFlechesHaut--;
            nbCasesVides--;
        }else if(fleche == FLECHE_BAS){
            nbFlechesBas--;
            nbCasesVides--;
        }else if(fleche == FLECHE_DROITE){
            nbFlechesDroite--;
            nbCasesVides--;
        }else if(fleche == FLECHE_GAUCHE){
            nbFlechesGauche--;
            nbCasesVides--;
        }else if(fleche == TELEPORTEUR){
            nbTeleporteurs--;
            nbCasesVides--;
        }
    }

    /**
     * Permet de modifier les valeur du nombre de fleche si jamais il y avait
     * déjà une fleche dans la case courante (cas d'une modification d'une fleche)
     * @param currentCase la case courante cliqué
     * @param fleche la fleche ou le téléporteur a placer
     */
    public void modifValeurFleche(CasePossible currentCase, CasePossible fleche){
        if (currentCase == FLECHE_HAUT) {
            //Si le bouton choisi est une case vide (pour la supression d'une fleche)
            if (fleche == VIDE) {
                nbCasesVides++;
                nbFlechesHaut++;
            } else if (fleche == FLECHE_BAS) {
                nbFlechesBas--;
                nbFlechesHaut++;
            } else if (fleche == FLECHE_GAUCHE) {
                nbFlechesGauche--;
                nbFlechesHaut++;
            } else if (fleche == FLECHE_DROITE) {
                nbFlechesDroite--;
                nbFlechesHaut++;
            } else if (fleche == TELEPORTEUR) {
                nbTeleporteurs--;
                nbFlechesHaut++;
            }
            //Cas ou il n'y a pas d'animaux et que la case courante est une fleche basse
        } else if (currentCase == FLECHE_BAS) {
            //Si le bouton choisi est une case vide (pour la supression d'une fleche)
            if (fleche == VIDE) {
                nbCasesVides++;
                nbFlechesBas++;
            } else if (fleche == FLECHE_HAUT) {
                nbFlechesHaut--;
                nbFlechesBas++;
            } else if (fleche == FLECHE_GAUCHE) {
                nbFlechesGauche--;
                nbFlechesBas++;
            } else if (fleche == FLECHE_DROITE) {
                nbFlechesDroite--;
                nbFlechesBas++;
            } else if (fleche == TELEPORTEUR) {
                nbTeleporteurs--;
                nbFlechesBas++;
            }
            //Cas ou il n'y a pas d'animaux et que la case courante est une fleche gauche
        } else if (currentCase == FLECHE_GAUCHE) {
            if (fleche == VIDE) {
                nbCasesVides++;
                nbFlechesGauche++;
            } else if (fleche == FLECHE_HAUT) {
                nbFlechesHaut--;
                nbFlechesGauche++;
            } else if (fleche == FLECHE_BAS) {
                nbFlechesBas--;
                nbFlechesGauche++;
            } else if (fleche == FLECHE_DROITE) {
                nbFlechesDroite--;
                nbFlechesGauche++;

            } else if (fleche == TELEPORTEUR) {
                nbTeleporteurs--;
                nbFlechesGauche++;
            }
            //Cas ou il n'y a pas d'animaux et que la case courante est une fleche droite
        } else if (currentCase == FLECHE_DROITE) {
            if (fleche == VIDE) {
                nbCasesVides++;
                nbFlechesDroite++;
            } else if (fleche == FLECHE_HAUT) {
                nbFlechesHaut--;
                nbFlechesDroite++;
            } else if (fleche == FLECHE_BAS) {
                nbFlechesBas--;
                nbFlechesDroite++;
            } else if (fleche == FLECHE_GAUCHE) {
                nbFlechesGauche--;
                nbFlechesDroite++;

            } else if (fleche == TELEPORTEUR) {
                nbTeleporteurs--;
                nbFlechesDroite++;
            }
            //Cas ou il n'y a pas d'animaux et que la case courante est un teleporteur
        } else if (currentCase == TELEPORTEUR) {
            if (fleche == VIDE) {
                nbCasesVides++;
                nbTeleporteurs++;
            } else if (fleche == FLECHE_HAUT) {
                nbFlechesHaut--;
                nbTeleporteurs++;
            } else if (fleche == FLECHE_BAS) {
                nbFlechesBas--;
                nbTeleporteurs++;
            } else if (fleche == FLECHE_GAUCHE) {
                nbFlechesGauche--;
                nbTeleporteurs++;

            } else if (fleche == FLECHE_DROITE) {
                nbFlechesDroite--;
                nbTeleporteurs++;
            }
        }
    }

    /**
     * Clone un plateau
     * @return le clone
     */
    @Override
    public Plateau clone() {
        return new Plateau(this);
    }

    /**
     * Clone la liste des animaux
     * @return la liste clonée
     */
    private ArrayList<Animal> cloneAnimaux() {
        ArrayList<Animal> res = new ArrayList<>(listeAnimaux.size());
        for(Animal a : listeAnimaux)
            res.add(a.clone());
        return res;
    }

    /**
     * Clone la matrice des cases
     * @return la matrice clonée
     */
    private Case[][] cloneCases() {
        Case[][] res = new Case[Plateau.NB_COLONNES][Plateau.NB_LIGNES];
        for(int i = 0; i < NB_COLONNES; i++) {
            for (int j = 0; j < NB_LIGNES; j++) {
                res[i][j] = lesCases[i][j].clone();
            }
        }
        return res;
    }
}
