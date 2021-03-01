package modele;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {
	
	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'étape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * tourné vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	/**
	 * contient le message à envoyer
	 */
	private JLabel message;
	
	/**
	 * setter sur etape
	 * @param etape
	 */
	public void setEtape(int etape) {
		this.etape = etape;
	}
	
	/**
	 * getter sur orientation
	 * @return retourne orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * getter sur pseudo
	 * @return pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * setter sur pseudo
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		vie = MAXVIE;
		etape = 1;
		orientation = 1;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur> lesJoueurs, Collection<Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		jLabel = new JLabel();
		message = new JLabel();
		boule = new Boule(jeuServeur);
		message.setHorizontalTextPosition(SwingConstants.CENTER);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		premierePosition(lesJoueurs, lesMurs);
		jeuServeur.ajoutJLabelJeuAren(jLabel);
		jeuServeur.ajoutJLabelJeuAren(message);
		jeuServeur.ajoutJLabelJeuAren(boule.getjLabel());
		affiche(MARCHE, etape);
		System.out.println("joueur " + pseudo + " - num perso " + numPerso + " créé");
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(Collection lesJoueurs, Collection lesMurs) {
		jLabel.setBounds(0, 0, MLJOUEUR, MHJOUEUR);
		do {
			posX = (int)Math.round(Math.random()* (LARENE-MLJOUEUR));
			posY = (int)Math.round(Math.random()* (HARENE-MHJOUEUR-HNOM));
		} while(toucheCollectionObjet(lesJoueurs) != null || toucheCollectionObjet(lesMurs) != null);
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		jLabel.setBounds(posX, posY, MLJOUEUR, MHJOUEUR);
		String cheminPerso = "personnages/perso" + numPerso + etat + etape + "d" + orientation + ".gif";
		URL resourcePerso = getClass().getClassLoader().getResource(cheminPerso);
		jLabel.setIcon(new ImageIcon(resourcePerso));
		message.setBounds(posX-10, posY+MHJOUEUR, MLJOUEUR+10, HNOM);
		message.setText(pseudo + ":" + vie);
		jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 */
	public void action(Integer action, Collection<Joueur> lesJoueurs, Collection<Mur> lesMurs) {
		if (!this.estMort()) {
			switch (action) {
				case KeyEvent.VK_UP:
					posY = deplace(posY, KeyEvent.VK_UP, -PAS, lesJoueurs, lesMurs);
					break;
				case KeyEvent.VK_DOWN:
					posY = deplace(posY, KeyEvent.VK_DOWN, PAS, lesJoueurs, lesMurs);
					break;
				case KeyEvent.VK_LEFT:
					orientation = 0;
					posX = deplace(posX, KeyEvent.VK_LEFT, -PAS, lesJoueurs, lesMurs);
					break;
				case KeyEvent.VK_RIGHT:
					orientation = 1;
					posX = deplace(posX, KeyEvent.VK_RIGHT, PAS, lesJoueurs, lesMurs);
					break;
				case KeyEvent.VK_SPACE:
					if (!boule.getjLabel().isVisible()) {
						boule.tireBoule(this, lesMurs);
					}
					break;
			}
			affiche(MARCHE, etape);
		}
	}

	/**
	 * Gère le déplacement du personnage
	 */
	private int deplace(int position, int direction, int pas, Collection lesJoueurs, Collection lesMurs) { 
		if (!this.estMort()) {
			int posDep = position;
			position += pas;
			// déplacement et collision avec les bords de l'arène
			if (direction == KeyEvent.VK_LEFT || direction == KeyEvent.VK_RIGHT) {
				position = Math.min(position, LARENE-MLJOUEUR);
				position = Math.max(position, 0);
				posX = position;
			}
			else {
				position = Math.min(position, HARENE-MHJOUEUR);
				position = Math.max(position, 0);
				posY = position;
			}
			// collision avec les joueurs et les murs
			if (toucheCollectionObjet(lesJoueurs) != null || toucheCollectionObjet(lesMurs) != null) {
				position = posDep;
			}
			// avancée de l'étape de l'animation du personnage
			etape++;
			if (etape > 4) {	
				etape = 1;
			}
		}
		return position;
	}
	
	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
		this.vie = Math.min(this.vie += GAIN, MAXVIE);
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
		this.vie = Math.max(0, this.vie -= PERTE);
	}
	
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		if (vie > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}
	
}
