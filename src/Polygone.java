import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;

/**
 * Classe abstraite permettant de definir un polygone
 * @author sinteff3u, demarbre1u
 */
public abstract class Polygone extends FigureColoree{

	/**
	 * Le polygoe en train d'etre cree
	 */
	protected Polygon p;
	
	/**
	 * Constructeur par defaut de polyone
	 */
	public Polygone(){
		super();
		tab_mem = new Point[nbClics()];
	}
	
	/**
	 * Méthode permettant d'afficher un polygone dans une zone de dessin
	 * @param g zone de dessin
	 */
	public void affiche(Graphics g){
		super.affiche(g);
	}
	
	/**
	 * Renvoi le nombre de clics d'une figure
	 * @return nombre de clics d'une figure
	 */
	@Override
	public int nbClics() {
		return 4;
	}

	/**
	 * Methode indiquant si un point se trouve dans une figure
	 * @param x coordonnee x du point
	 * @param y coordonnee y du point 
	 * @return true si le point est dans la figure, sinon false
	 */
	@Override
	public boolean estDedans(int x, int y) {
		
		Polygon pol = new Polygon();
		for (int i = 0 ; i < tab_mem.length ; i++)
		{
			pol.addPoint(tab_mem[i].rendreX(),tab_mem[i].rendreY());
		}
		p = pol;
		
		boolean res = new Area(p).contains(x, y);
		return res;
	}

	/**
	 * Méthode permettanrt de modifier les points d'une figure
	 * @param points tableau de point a modifier
	 */
	@Override
	public void modifierPoints(Point[] points) {
		Polygon pol = new Polygon();
		for (int i = 0 ; i < points.length ; i++)
		{
			pol.addPoint(points[i].rendreX(), points[i].rendreY());
		}
		p = pol;
		tab_mem = points;
	}

}
