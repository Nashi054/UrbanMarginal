package modele;

import javax.swing.JPanel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� client
 *
 */
public class JeuClient extends Jeu implements Global {
	
	/**
	 * Objet de connection
	 */
	private Connection connection;
	/**
	 * Variable pour tester si les murs ont �t� g�n�r�s ou non
	 */
	private Boolean mursOK;
	
	/**
	 * Controleur
	 */
	public JeuClient(Controle controle) {
		this.controle = controle;
		this.mursOK = false;
	}
	
	/**
	 * Connection au serveur
	 * @param connection objet de connection
	 */
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Reception des informations envoy�es par le serveur (murs et personnages)
	 * @param connection objet de connection
	 * @param information contient l'information � afficher sur le jeu client
	 */
	@Override
	public void reception(Connection connection, Object information) {
		if (information instanceof JPanel && mursOK == false) {
			controle.evenementJeuClient(AJOUTPANELMUR, information);
			mursOK = true;
		}
		else if (information instanceof JPanel && mursOK == true) {
			controle.evenementJeuClient(AJOUTPANELJEU, information);
		}
		else if (information instanceof String) {
			controle.evenementJeuClient(MODIFTCHAT, information);
		}
		else if (information instanceof Integer) {
			controle.evenementJeuClient(JOUESON, information);
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois � l'envoi dans la classe Jeu
	 */
	public void envoi(String informations) {
		super.envoi(connection, informations);
	}

}
