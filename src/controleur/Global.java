package controleur;

public interface Global {
	/**
	 * port du serveur
	 */
	public static final int PORT = 6666;
	/**
	 * vie de d�part pour tous les joueurs
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
	 * chemin vers l'image de fond de l'ar�ne
	 */
	public static final String CHEMINFOND = "fonds/fondarene.jpg";
	/**
	 * nombre maximal de personnages diff�rents dans la s�lection de personnage
	 */
	public static final int NBPERSO = 3;
	/**
	 * chemin vers l'image de fond du menu choix joueur
	 */
	public static final String CHEMINCHOIX = "fonds/fondchoix.jpg";
}
