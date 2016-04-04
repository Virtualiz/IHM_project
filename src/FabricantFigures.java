import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe permettant de creer des figures dans la zone de dessin
 * @author Allan
 *
 */
public class FabricantFigures implements MouseListener {

	/**
	 * La figure a creer
	 */
	private FigureColoree fc;
	
	/**
	 * Le nombre de clics necessaire pour creer la figure
	 */
	private int nbclics;
	
	/**
	 * Tableau contenant les coordonnees de la figure a creer
	 */
	private Point[] pts;

	/**
	 * Constructeur par defaut
	 * @param f la FigureColore a creer
	 */
	public FabricantFigures(FigureColoree f){
		if(f!=null){
			fc = f;
			pts = new Point[fc.nbClics()];
			nbclics = 0;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Méthode s'effectuant lorsqu'un bouton de souris est enfonce
	 * @param e l'evenement souris
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Point p = new Point(e.getX(),e.getY());
		if(nbclics<fc.nbClics()){
			pts[nbclics] = p;
			nbclics ++;
		}
		if(nbclics == fc.nbClics()){
			fc.modifierPoints(pts);
			((DessinFigures)e.getSource()).ajoute(fc);
			((DessinFigures)e.getSource()).removeMouseListener(this);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
