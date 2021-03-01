package modele;

import java.util.Collection;

import javax.swing.JLabel;

/**
 * Informations communes à tous les objets (joueurs, murs, boules)
 * permet de mémoriser la position de l'objet et de gérer les  collisions
 *
 */
public abstract class Objet {

	/**
	 * position X de l'objet
	 */
	protected Integer posX ;
	/**
	 * position Y de l'objet
	 */
	protected Integer posY ;
	/**
	 * objet de type JLabel
	 */
	protected JLabel jLabel;
	
	/**
	 * getter sur posX
	 * @return retourne posX
	 */
	public Integer getPosX() {
		return posX;
	}

	/**
	 * getter sur posY
	 * @return retourne posY
	 */
	public Integer getPosY() {
		return posY;
	}
	
	/**
	 * getter sur jLabel
	 * @return retourne jLabel
	 */
	public JLabel getjLabel() {
		return jLabel;
	}

	/**
	 * contrôle si l'objet actuel touche l'objet passé en paramètre
	 * @param objet contient l'objet à contrôler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		if (this != null && objet != null) {
			return (this.posX + this.jLabel.getWidth() > objet.posX &&
					this.posX < objet.posX+objet.jLabel.getWidth() &&
					this.posY + this.jLabel.getHeight() > objet.posY &&
					this.posY < objet.posY+objet.jLabel.getHeight());
		}
		return false;
	}
	
	public Objet toucheCollectionObjet (Collection<Objet> lesObjets) {
		for (Objet unObjet : lesObjets) {
			if (!unObjet.equals(this)) {
				if (toucheObjet(unObjet)) {
					return unObjet;
				}
			}
		}
		return null;
	}
	
}
