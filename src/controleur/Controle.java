package controleur;

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
public class Controle implements AsyncResponse {

	private EntreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private String typeJeu;
	private static final int PORT = 6666;

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
	
	public void evenementEntreeJeu(String info) {
		if (info == "serveur") {
			typeJeu = info;
			ServeurSocket serveurSocket = new ServeurSocket(this, PORT);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
		}
		else {
			typeJeu = "client";
			ClientSocket clientSocket = new ClientSocket(this, info, PORT);
		}
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case "connexion" :
			if (typeJeu == "client") {
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur();
				this.frmChoixJoueur.setVisible(true);
			}
			break;
		case "réception" :
			break;
		case "deconnexion" :
			break;
		}
	}

}
