import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * Classe qui definit un Cercle
 * @author Allan
 *
 */
public class Cercle extends FigureColoree{
	
	/**
	 * Le centre du cercle, le rayon du cercle
	 */
	private Point centre, surRayon;
	/**
	 * Un objet Shape correspondant au cercle
	 */
	private Shape cercle;
	
	/**
	 * Constructeur par defaut
	 */
	public Cercle(){
		
	}
	
	/**
	 * Methode qui renvoi le nombre de points d'une figure
	 * @return le nombre de points d'une figure
	 */
	@Override
	public int nbPoints() {
		
		return 2;
	}

	/**
	 * Methode qui retourne le nombre de clics d'une figure 
	 * @return le nombre de clics d'une figure 
	 */
	@Override
	public int nbClics() {
		
		return 2;
	}
	
	/**
	 * Methode qui permet d'afficher une figure dans une zone de dessin
	 * @param g la zone de dessin
	 */
	public void affiche(Graphics g){
		
		g.setColor(couleur);
		double x= centre.rendreX()-centre.distance(surRayon);
		double y= centre.rendreY()-centre.distance(surRayon);
		double width= 2*centre.distance(surRayon);
		double height= 2*centre.distance(surRayon);
		
		
		g.fillOval((int)Math.round(x), (int)Math.round(y), (int)Math.round(width), (int)Math.round(height));
		
		if (selected)
		{
			g.setColor(Color.white);
			g.fillRect(tab_mem[0].rendreX()-TAILLE_CARRE_SELECTION/2, tab_mem[0].rendreY()-TAILLE_CARRE_SELECTION/2, TAILLE_CARRE_SELECTION, TAILLE_CARRE_SELECTION);
			g.setColor(Color.black);
			g.drawRect(tab_mem[0].rendreX()-TAILLE_CARRE_SELECTION/2, tab_mem[0].rendreY()-TAILLE_CARRE_SELECTION/2, TAILLE_CARRE_SELECTION, TAILLE_CARRE_SELECTION);
			
		}
	}

	/**
	 * Methode permettant de savoir si un point se trouve dans une figure 
	 * @return true si le point est dans la figure, sinon false
	 */
	@Override
	public boolean estDedans(int x, int y) {
		
		return new Area(cercle).contains(x, y);
	}

	/**
	 * Methode permettant de modifier les points d'une figure 
	 * @param points le tableau de points a modifier 
	 */
	@Override
	public void modifierPoints(Point[] points) {
		if(points[0]!=null)centre = points[0];
		if(points[1]!=null)surRayon = points[1];
		tab_mem= new Point[2];
		tab_mem[0] = centre;
		tab_mem[1] = surRayon;
		
		double x= centre.rendreX()-centre.distance(surRayon);
		double y= centre.rendreY()-centre.distance(surRayon);
		double width= 2*centre.distance(surRayon);
		double height= 2*centre.distance(surRayon);
		cercle = new Ellipse2D.Float((float) x, (float)y, (float)width, (float)height);
		
	}

}
