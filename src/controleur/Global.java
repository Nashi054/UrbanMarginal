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
	/**
	 * hauteur de l'ar�ne
	 */
	public static final Integer HARENE = 600;
	/**
	 * longueur de l'ar�ne
	 */
	public static final Integer LARENE = 800;
	/**
	 * hauteur d'un mur
	 */
	public static final Integer HMUR = 35;
	/**
	 * largeur d'un mur
	 */
	public static final Integer LMUR = 34;
	/**
	 * chemin vers l'image d'un mur
	 */
	public static final String CHEMINMUR = "murs/mur.gif";
	/**
	 * ordre "ajout mur"
	 */
	public static final String AJOUTMUR = "ajout mur";
	/**
	 * nombre de murs � g�n�rer
	 */
	public static final Integer NBMURS = 20;
	/**
	 * moyenne hauteur label joueur
	 */
	public static final Integer MHJOUEUR = 40;
	/**
	 * moyenne largeur label joueur
	 */
	public static final Integer MLJOUEUR = 34;
	/**
	 * ordre "ajout jlabel jeu"
	 */
	public static final String AJOUTJLABELJEU = "ajout jlabel jeu";
	/**
	 * �tat "marche"
	 */
	public static final String MARCHE = "marche";
	/**
	 * hauteur nom perso
	 */
	public static final Integer HNOM = 8;
	/**
	 * ordre "ajout panel mur"
	 */
	public static final String AJOUTPANELMUR = "ajout panel mur";
	/**
	 * ordre "ajout panel jeu"
	 */
	public static final String AJOUTPANELJEU = "ajout panel jeu";
	/**
	 * ordre "ajout phrase"
	 */
	public static final String AJOUTPHRASE = "ajout phrase";
	/**
	 * ordre "modif tchat"
	 */
	public static final String MODIFTCHAT = "modif tchat";
	/**
	 * ordre "client"
	 */
	public static final String CLIENT = "client";
	/**
	 * ordre "serveur"
	 */
	public static final String SERVEUR = "serveur";
}
