package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu {

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
		lesJoueurs.put(connection, new Joueur());
	}

	@Override
	public void reception(Connection connection, Object information) {
		String temp = information.toString();
		String[] info = temp.split("~");
		System.out.println(info[0]);
		if (info[0].contains("pseudo")) {
			(lesJoueurs.get(connection)).initPerso(info[1], Integer.parseInt(info[2]));
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
	}
	
}
