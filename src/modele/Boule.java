package modele;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;

/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Runnable, Global {

	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur ;
	/**
	 * instance du joueur
	 */
	private Joueur joueur;
	/**
	 * collection de murs
	 */
	private Collection lesMurs;
	
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		jLabel = new JLabel();
		jLabel.setBounds(0, 0, BOULE, BOULE);
		jLabel.setVisible(false);
		String cheminBoule = "boules/boule.gif";
		URL resourceBoule = getClass().getClassLoader().getResource(cheminBoule);
		jLabel.setIcon(new ImageIcon(resourceBoule));
	}
	
	/**
	 * Tire d'une boule
	 */
	public void tireBoule(Joueur joueur, Collection<Mur> lesMurs) {
		this.joueur = joueur;
		this.lesMurs = lesMurs;
		if (joueur.getOrientation() == 0) {
			posX = joueur.getPosX() - BOULE - 1 ;
		}else{
			posX = joueur.getPosX() + MLJOUEUR + 1 ;
		}
		posY = joueur.getPosY() + MHJOUEUR/2 ;
		new Thread(this).start();
	}

	/**
	 * méthode de déplacement de la boule
	 */
	@Override
	public void run() {
		jeuServeur.envoi(SONTIR);
		joueur.affiche(MARCHE, 1);
		jLabel.setVisible(true);
		Joueur victime = null;
		int lePas = 0;
		
		if (joueur.getOrientation() == 0) {
			lePas = -PAS;
		}
		else if (joueur.getOrientation() == 1) {
			lePas = PAS;
		}
		
		do {
			posX += lePas;
			jLabel.setBounds(posX, posY, BOULE, BOULE);
			this.jeuServeur.envoiJeuATous();
			Collection lesJoueurs = this.jeuServeur.getLesJoueurs();
			victime = (Joueur)super.toucheCollectionObjet(lesJoueurs);
		} while (posX >= 0 && posX <= LARENE && victime == null && this.toucheCollectionObjet(lesMurs) == null);
		
		if (victime != null && !victime.estMort()) {
			jeuServeur.envoi(SONBLESSE);
			victime.perteVie();
			joueur.gainVie();
			for (int k = 1; k < 3; k++) {
				victime.affiche(TOUCHE, k);
				pause(80, 0);
			}
			
			if (victime.estMort()) {
				jeuServeur.envoi(SONMORT);
				for (int k = 1; k < 3; k++) {
					victime.affiche(MORT, k);
					pause(80, 0);
				}
			}
			else {
				victime.affiche(MARCHE, 1);
			}
		}
		
		jLabel.setVisible(false);
		jeuServeur.envoiJeuATous();
	}
	
	/**
	 * méthode de pauses permettant de ralentir les animation
	 * @param milli millisecondes
	 * @param nano nanosecondes
	 */
	public void pause(long milli, int nano) {
		try {
			Thread.sleep(milli, nano);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
