package controleur;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

/**
 * Contrôleur et point d'entrée de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse, Global {

	/**
	 * frame de type EntreeJeu
	 */
	private EntreeJeu frmEntreeJeu ;
	/**
	 * frame de type Arene
	 */
	private Arene frmArene;
	/**
	 * frame de type ChoixJoueur
	 */
	private ChoixJoueur frmChoixJoueur;
	/**
	 * objet de type Jeu
	 */
	private Jeu leJeu;

	/**
	 * Méthode de démarrage
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Méthode d'entrée de jeu
	 * Teste si le jeu lancé est de type serveur ou client, puis agit en conséquence
	 * @param info contient l'information "serveur" ou l'adresse IP du client
	 */
	public void evenementEntreeJeu(String info) {
		/*
		 * si le jeu lancé est un serveur, créer un socket serveur, ferme la frame d'entrée jeu et créer une frame arène
		 */
		if (info == SERVEUR) {
			ServeurSocket serveurSocket = new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene(this, SERVEUR);
			((JeuServeur)this.leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		}
		/**
		 * sinon, créer un socket client
		 */
		else {
			ClientSocket clientSocket = new ClientSocket(this, info, PORT);
		}
	}
	
	/**
	 * Méthode de reception d'un jeu client
	 */
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case "connexion" :
			if (!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene(this, CLIENT);
				this.frmArene.setVisible(false);
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			}
			else {
				this.leJeu.connexion(connection);
			}
			break;
		case "réception" :
			this.leJeu.reception(connection, info);
			break;
		case "deconnexion" :
			break;
		}
	}
	
	/**
	 * Méthode de choix du joueur
	 * Ferme la frame ChoixJoueur et rend visible la frame Arene, puis envoi les informations du joueur vers le jeu client
	 * @param pseudo contient le nom du joueur
	 * @param numPerso contient le numéro du personnage sélectionné
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient)this.leJeu).envoi("pseudo"+"~"+pseudo+"~"+numPerso);
	}
	
	/**
	 * Méthode appelant la méthode envoi de l'objet connection
	 * @param connection appelant la méthode d'envoi de la classe Connection
	 * @param objet l'objet à envoyer
	 */
	public void envoi(Connection connection, Object objet) {
		connection.envoi(objet);
	}
	
	/**
	 * Méthode de création de l'arène d'un jeu serveur
	 * @param ordre indique l'ordre à executer
	 * @param info contient l'objet à ajouter à l'arène
	 */
	public void evenementJeuServeur(String ordre, Object info) {
		if (ordre.contains(AJOUTMUR)) {
			frmArene.ajoutMurs(info);
		}
		else if (ordre.contains(AJOUTPANELMUR)) {
			leJeu.envoi((Connection)info, frmArene.getJpnMurs());
		}
		else if (ordre.contains(AJOUTJLABELJEU)) {
			frmArene.ajoutJLabelJeu((JLabel)info);
		}
		else if (ordre.contains(AJOUTPANELJEU)) {
			leJeu.envoi((Connection)info, frmArene.getJpnJeu());
		}
		else if (ordre.contains(AJOUTPHRASE)) {
			frmArene.ajoutTchat((String)info);
			((JeuServeur)leJeu).envoi(frmArene.getTxtChat());
		}
	}
	
	/**
	 * Envoi les informations du jeu serveur vers les jeux clients
	 * @param ordre indique l'ordre à executer
	 * @param info contient l'objet à ajouter à l'arène
	 */
	public void evenementJeuClient(String ordre, Object info) {
		if (ordre.contains(AJOUTPANELMUR)) {
			frmArene.setJpnMurs((JPanel)info);
		}
		else if (ordre.contains(AJOUTPANELJEU)) {
			frmArene.setJpnJeu((JPanel)info);
		}
		else if (ordre.contains(MODIFTCHAT)) {
			frmArene.setTxtChat((String)info);
		}
	}
	
	public void evenementArene(Object objet) {
		if (objet instanceof String) {
			((JeuClient)this.leJeu).envoi(TCHAT+"~"+objet);
		}
		else if (objet instanceof Integer) {
			((JeuClient)this.leJeu).envoi(ACTION+"~"+objet);
		}
	}
}
