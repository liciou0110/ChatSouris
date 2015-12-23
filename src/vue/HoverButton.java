/*
 * HoverButton.java                      30/11/2015
 * Licence PRO RTAI
 */

package vue;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Classe permettant de faire des effets sur les boutons
 * @author Audélia Bertaina & Alicia Masmayoux
 */
public class HoverButton extends JButton {
    /**
     * Méthode permettant de faire un effet sur le bouton au moment 
     * ou l'on passe sur le bouton ou au moment ou on le presse
     * @param icon l'icone du bouton
     * @param hover l'icon au moment on au passe sur le bouton
     */
    public HoverButton(Icon icon, Icon hover) {
      super(icon);
      setFocusPainted(false);
      setRolloverEnabled(true);
      setRolloverIcon(hover);
      setBorderPainted(false);
      setContentAreaFilled(false);
    }

}