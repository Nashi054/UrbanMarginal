package modele;

import java.util.ArrayList;
import java.util.Collection;
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
	 * getter sur lesJoueurs
	 * @return retourne les joueurs
	 */
	public Collection getLesJoueurs() {
		return lesJoueurs.values();
	}
	
	/**
	 * Constructeur
	 */
	public JeuServeur(Controle controle) {
		this.controle = controle;
	}
	
	/**
	 * Créer un nouveau joueur dans le dictionnaire à lorsqu'un client se connecte à l'arène du serveur
	 * @param connection sert de clé pour le dictionnaire
	 */
	@Override
	public void connexion(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this));
	}

	/**
	 * Reception des informations à afficher dans le serveur (murs et personnages)
	 * @param connection objet de connexion
	 * @param information contient le pseudo et le numéro du personnage
	 */
	@Override
	public void reception(Connection connection, Object information) {
		String temp = information.toString();
		String[] info = temp.split("~");
		System.out.println(info[0]);
		switch (info[0]) {
			case PSEUDO:
				controle.evenementJeuServeur(AJOUTPANELMUR, connection);
				(lesJoueurs.get(connection)).initPerso(info[1], Integer.parseInt(info[2]), lesJoueurs.values(), lesMurs);
				controle.evenementJeuServeur(AJOUTPHRASE, "*** "+info[1]+" vient de se connecter ***");
				break;
			case TCHAT:
				controle.evenementJeuServeur(AJOUTPHRASE, lesJoueurs.get(connection).getPseudo() + " > " + info[1]);
				break;
			case ACTION:
				lesJoueurs.get(connection).action(Integer.parseInt(info[1]), lesJoueurs.values(), lesMurs);
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
	public void envoi(Object info) {
		for (Connection connection : lesJoueurs.keySet()) {
			super.envoi(connection, info);
		}
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
	
	/**
	 * Envoi les joueurs de l'arène serveur vers tous les clients
	 */
	public void envoiJeuATous() {
		for (Connection connection : lesJoueurs.keySet()) {
			controle.evenementJeuServeur(AJOUTPANELJEU, connection);
		}
	}
	
}
