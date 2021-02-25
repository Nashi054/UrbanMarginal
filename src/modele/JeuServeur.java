package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 * Collection de joueurs
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 */
	public JeuServeur(Controle controle) {
		this.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object information) {
		String temp = information.toString();
		String[] info = temp.split("~");
		System.out.println(info[0]);
		switch (info[0]) {
			case "pseudo":
				controle.evenementJeuServeur(AJOUTPANELMUR, connection);
				(lesJoueurs.get(connection)).initPerso(info[1], Integer.parseInt(info[2]), lesJoueurs.values(), lesMurs);
				break;
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois à l'envoi de la classe Jeu
	 */
	public void envoi() {
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
		for (int k = 0; k < NBMURS; k++) {
			lesMurs.add(new Mur());
			this.controle.evenementJeuServeur(AJOUTMUR, lesMurs.get(lesMurs.size()-1).getjLabel());
		}
	}
	
	/**
	 * Ajoute un joueur dans l'arène serveur
	 * @param jLabel objet personnage joueur
	 */
	public void ajoutJLabelJeuAren(JLabel jLabel) {
		controle.evenementJeuServeur(AJOUTJLABELJEU, jLabel);
	}
	
	public void envoiJeuATous() {
		for (Connection connection : lesJoueurs.keySet()) {
			controle.evenementJeuServeur(AJOUTPANELJEU, connection);
		}
	}
	
}
