/*
 * ImageRadioButton.java                     30/11/2015
 * Licence PRO RTAI
 */
package vue;

import javax.swing.*;
import java.awt.*;

/**
 * Permet de créer un Radio Button avec une image
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class ImageRadioButton extends JRadioButton {

    /** Image du jRadioButton */
    private final ImageIcon image;

    /** Padding gauche */
    private int paddingLeft = 15;

    /**
     * Constructeur permettant d'ajouter une image à un Radion button
     * @param chemin le chemin ou se situe l'image
     */
    public ImageRadioButton(String chemin) {
        image = new ImageIcon(getClass().getResource(chemin));
        setSize(image.getIconWidth(), image.getIconHeight());
        setBorder(BorderFactory.createBevelBorder(10));
        setOpaque(false);
    }

    /**
     * Constructeur permettant d'ajouter une image à un Radion button
     * avec un padding à gauche
     * @param chemin le chemin ou se situe l'image
     * @param paddingLeft la marge interne à gauche
     */
    public ImageRadioButton(String chemin, int paddingLeft) {
        this(chemin);
        this.paddingLeft = paddingLeft;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image.getImage(), paddingLeft, 10, image.getIconWidth(), 
                    image.getIconHeight(), null);
        super.paint(g);
    }
}