package controleur;

public interface Global {
	/**
	 * port du serveur
	 */
	public static final int PORT = 6666;
	/**
	 * vie de départ pour tous les joueurs
	 */
	public static final int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	public static final int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	public static final int PERTE = 2 ;
	/**
	 * chemin vers l'image de fond de l'arène
	 */
	public static final String CHEMINFOND = "fonds/fondarene.jpg";
	/**
	 * nombre maximal de personnages différents dans la sélection de personnage
	 */
	public static final int NBPERSO = 3;
	/**
	 * chemin vers l'image de fond du menu choix joueur
	 */
	public static final String CHEMINCHOIX = "fonds/fondchoix.jpg";
}
