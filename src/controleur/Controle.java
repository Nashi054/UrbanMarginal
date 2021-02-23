package controleur;

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
 * Contr�leur et point d'entr�e de l'applicaton 
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
	 * M�thode de d�marrage
	 * @param args non utilis�
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
	 * M�thode d'entr�e de jeu
	 * Teste si le jeu lanc� est de type serveur ou client, puis agit en cons�quence
	 * @param info contient l'information "serveur" ou l'adresse IP du client
	 */
	public void evenementEntreeJeu(String info) {
		/*
		 * si le jeu lanc� est un serveur, cr�er un socket serveur, ferme la frame d'entr�e jeu et cr�er une frame ar�ne
		 */
		if (info == "serveur") {
			ServeurSocket serveurSocket = new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
		}
		/**
		 * sinon, cr�er un socket client
		 */
		else {
			ClientSocket clientSocket = new ClientSocket(this, info, PORT);
		}
	}
	
	/**
	 * M�thode de reception d'un jeu client
	 */
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case "connexion" :
			if (!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmArene.setVisible(false);
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			}
			else {
				this.leJeu.connexion(connection);
			}
			break;
		case "r�ception" :
			this.leJeu.reception(connection, info);
			break;
		case "deconnexion" :
			break;
		}
	}
	
	/**
	 * M�thode de choix du joueur
	 * Ferme la frame ChoixJoueur et rend visible la frame Arene, puis envoi les informations du joueur vers le jeu client
	 * @param pseudo contient le nom du joueur
	 * @param numPerso contient le num�ro du personnage s�lectionn�
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		JeuClient jeuClient = (JeuClient)leJeu;
		jeuClient.envoi("pseudo"+"~"+pseudo+"~"+numPerso);
	}
	
	/**
	 * M�thode appelant la m�thode envoi de l'objet connection
	 * @param connection appelant la m�thode d'envoi de la classe Connection
	 * @param objet l'objet � envoyer
	 */
	public void envoi(Connection connection, Object objet) {
		connection.envoi(objet);
	}

}
