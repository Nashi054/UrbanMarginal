package modele;

import java.awt.Font;
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
	 * n� correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * num�ro d'�tape dans l'animation (de la marche, touch� ou mort)
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
	 * tourn� vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	/**
	 * contient le message � envoyer
	 */
	private JLabel message;
	
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
	 * Initialisation d'un joueur (pseudo et num�ro, calcul de la 1�re position, affichage, cr�ation de la boule)
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		jLabel = new JLabel();
		message = new JLabel();
		message.setHorizontalTextPosition(SwingConstants.CENTER);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		premierePosition(lesJoueurs, lesMurs);
		jeuServeur.ajoutJLabelJeuAren(jLabel);
		jeuServeur.ajoutJLabelJeuAren(message);
		affiche(MARCHE, etape);
		System.out.println("joueur " + pseudo + " - num perso " + numPerso + " cr��");
	}

	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		jLabel.setBounds(0, 0, MLJOUEUR, MHJOUEUR);
		do {
			posX = (int)Math.round(Math.random()* (LARENE-MLJOUEUR));
			posY = (int)Math.round(Math.random()* (HARENE-MHJOUEUR-HNOM));
		} while(this.toucheJoueur(lesJoueurs) || this.toucheMur(lesMurs));
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		jLabel.setBounds(posX, posY, MLJOUEUR, MHJOUEUR);
		String cheminPerso = "personnages/perso" + numPerso + "marche1d" + orientation + ".gif";
		URL resourcePerso = getClass().getClassLoader().getResource(cheminPerso);
		jLabel.setIcon(new ImageIcon(resourcePerso));
		message.setBounds(posX-10, posY+MHJOUEUR, MLJOUEUR+10, HNOM);
		message.setText(pseudo + ":" + vie);
		jeuServeur.envoiJeuATous();
	}

	/**
	 * G�re une action re�ue et qu'il faut afficher (d�placement, tire de boule...)
	 */
	public void action() {
	}

	/**
	 * G�re le d�placement du personnage
	 */
	private void deplace() { 
	}

	/**
	 * Contr�le si le joueur touche un des autres joueurs
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
		for (Joueur joueur : lesJoueurs) {
			if (this.toucheObjet(joueur) && joueur != this) {
				return true;
			}
		}
		return false;
	}

	/**
	* Contr�le si le joueur touche un des murs
	 * @return true si un joueur touche un mur
	 */
	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
		for (int k = 0; k < lesMurs.size()-1; k++) {
			if (this.toucheObjet(lesMurs.get(k))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie() {
	}
	
	/**
	 * Perte de points de vie apr�s avoir �t� touch� 
	 */
	public void perteVie() {
	}
	
	/**
	 * vrai si la vie est � 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}
	
	/**
	 * Le joueur se d�connecte et disparait
	 */
	public void departJoueur() {
	}
	
}
