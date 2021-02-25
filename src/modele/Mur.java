package modele;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;

/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global {

	/**
	 * Constructeur
	 */
	public Mur() {
		posX = (int)Math.round(Math.random()* (LARENE-LMUR));
		posY = (int)Math.round(Math.random()* (HARENE-HMUR));
		jLabel = new JLabel();
		jLabel.setBounds(posX, posY, LMUR, HMUR);
		URL ressourceMur = getClass().getClassLoader().getResource(CHEMINMUR);
		jLabel.setIcon(new ImageIcon(ressourceMur));
	}
	
}
