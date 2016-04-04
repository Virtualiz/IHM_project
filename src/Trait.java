import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe qui decrit un trait
 * @author sinteff3u, demarbre1u
 */
public class Trait implements Dessinable{
	
	/**
	 * les coordonnees du trait
	 */
	private int x1,y1,x2,y2;
	
	/**
	 * la couleur du trait
	 */
	private Color couleur;
	
	/**
	 * Constructeur de trait
	 * @param nx1 coordonnee x du 1er point 
	 * @param ny1 coordonnee y du 1er point
	 * @param nx2 coordonnee x du 2nd point
	 * @param ny2 coordonnee y du 2nd point
	 * @param c couleur du trait
	 */
	public Trait(int nx1, int ny1, int nx2, int ny2, Color c){
		x1=nx1;
		y1=ny1;
		x2=nx2;
		y2=ny2;
		couleur = c;
	}
	
	/**
	 * Methode permettant d'afficher un trait dans une zone de dessin 
	 * @param g la zone de dessin
	 */
	@Override
	public void affiche(Graphics g) {
		g.setColor(couleur);
		g.drawLine(x1, y1, x2, y2);
	}

}
