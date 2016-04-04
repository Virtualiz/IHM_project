import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Classe qui definit une figure 
 * @author sinteff3u, demarbre1u
 */
public abstract class FigureColoree implements Dessinable{
	
	/**
	 * La taille d'un carrre de selection
	 */
	static final int TAILLE_CARRE_SELECTION = 5;
	
	/**
	 * La peripherie d'un carre de selection
	 */
	static final int PERIPHERIE_CARRE_SELECTION = 2;
	
	/**
	 * Boolean indiquant si une figure est selectionnee ou non
	 */
	protected boolean selected;
	
	/**
	 * la couleur actuelle de la figure
	 */
	protected Color couleur;
	
	/**
	 * Le tableau contenant l'ensemble des points de la figure
	 */
	protected Point[] tab_mem;
	
	/**
	 * Constructeur par defaut
	 */
	public FigureColoree(){
		couleur = Color.black;
	}
	
	/**
	 * Méhtode qui retourne le nombre de points d'une figure
	 * @return le nombre de points d'une figure
	 */
	public abstract int nbPoints();
	
	/**
	 * Méthode qui retourne le nombre de clics d'une figure 
	 * @return nombre de clics d'une figure
	 */
	public abstract int nbClics();
	
	/**
	 * Méthode qui renvoi un boolean si le point en parametre se trouve dans une figure
	 * @param x coordonnee x du point
	 * @param y coordonnee y du point
	 * @return true si le point est dedans, sinon false
	 */
	public abstract boolean estDedans(int x,int y);
	
	/**
	 * Méthode permettant de modifier un tableau de points
	 * @param points tableau de points a modifier
	 */
	public abstract void modifierPoints(Point[] points);
	
	/**
	 * Méthode permettant d'afficher une figure dans une zone de dessin
	 * @param g zone de dessin 
	 */
	public void affiche(Graphics g)
	{
		Polygon p = new Polygon();
		for (int i = 0 ; i < tab_mem.length ; i++)
		{
			p.addPoint(tab_mem[i].rendreX(), tab_mem[i].rendreY());
		}
		g.setColor(couleur);
		g.fillPolygon(p);
		if (selected)
		{
			g.setColor(Color.white);
			for (int i = 0 ; i < tab_mem.length ; i++)
			{
				g.fillRect(tab_mem[i].rendreX()-TAILLE_CARRE_SELECTION/2, tab_mem[i].rendreY()-TAILLE_CARRE_SELECTION/2, TAILLE_CARRE_SELECTION, TAILLE_CARRE_SELECTION);
			}
			g.setColor(Color.black);
			for (int i = 0 ; i < tab_mem.length ; i++)
			{
				g.drawRect(tab_mem[i].rendreX()-TAILLE_CARRE_SELECTION/2, tab_mem[i].rendreY()-TAILLE_CARRE_SELECTION/2, TAILLE_CARRE_SELECTION, TAILLE_CARRE_SELECTION);
			}
		}
	}
	
	/**
	 * Methode permettant de translater une figure 
	 * @param x le nombre de pixels en x a translater
	 * @param y le nombre de pixels en y a translater
	 */
	public void translation(int x,int y){
		for (int i = 0 ; i < tab_mem.length ; i++)
		{
			tab_mem[i].translation(x, y);
		}
		modifierPoints(tab_mem);
	}
	
	/**
	 * Méthode permettant de transforer une figure
	 * @param a la nouvelle coordonnee x
	 * @param b la nouvelle coordonnee y
	 * @param c l'indice du point a transformer
	 */
	public void transformation(int a, int b, int c){
		if (c >= 0 && c < tab_mem.length)
		{
			tab_mem[c].modifierX(a);
			tab_mem[c].modifierY(b);
		}
	}
	
	/**
	 * Methode permettant de savoir si un point se trouve dans un carre de selection
	 * @param x la coordonnee x du point
	 * @param y la coordonnee y du point
	 * @return l'indice du point pres duquel le point se trouve, sinon -1
	 */
	public int carreDeSelection(int x, int y)
	{
		for (int i = 0 ; i < tab_mem.length ; i++)
		{
			if (tab_mem[i].rendreX()+PERIPHERIE_CARRE_SELECTION >= x && tab_mem[i].rendreX()-PERIPHERIE_CARRE_SELECTION <= x 
					&& tab_mem[i].rendreY()+PERIPHERIE_CARRE_SELECTION >= y && tab_mem[i].rendreY()-PERIPHERIE_CARRE_SELECTION <= y)
			{
				return i;
			}
		}
		return -1;
		
	}
	
	/**
	 * Méthode permettant de selectionner une figure
	 */
	public void selectionne(){
		selected = true;
	}
	
	/**
	 * Methode permettant de deselectionner une figure
	 */
	public void deSelectionne(){
		selected = false;
	}
	
	/**
	 * Méthode permettant de changer la couleur d'une figure
	 * @param c
	 */
	public void changeCouleur(Color c){
		if(c!=null)couleur = c;
	}

}
