package modele;

import javax.swing.JPanel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu implements Global {
	
	private Connection connection;
	private Boolean mursOK;
	/**
	 * Controleur
	 */
	public JeuClient(Controle controle) {
		this.controle = controle;
		this.mursOK = false;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object information) {
		if (information instanceof JPanel && mursOK == false) {
			controle.evenementJeuClient(AJOUTPANELMUR, information);
			mursOK = true;
		}
		else if (information instanceof JPanel && mursOK == true) {
			controle.evenementJeuClient(AJOUTPANELJEU, information);
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 */
	public void envoi(String informations) {
		super.envoi(connection, informations);
	}

}
