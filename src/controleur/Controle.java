package controleur;

import vue.demarrage;

/**
 * Contrôleur et point d'entrée de l'applicaton 
 * @author emds
 *
 */
public class Controle {

	private demarrage frmEntreeJeu ;

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
		this.frmEntreeJeu = new demarrage() ;
		this.frmEntreeJeu.setVisible(true);
	}

}
