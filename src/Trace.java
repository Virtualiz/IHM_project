import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui decrit un trace
 * @author Allan
 *
 */
public class Trace implements Dessinable{
	
	private List<Trait> lt;
	private Color couleur;
	
	public Trace(Color c){
		lt = new ArrayList<Trait>();
		couleur = c;
	}
	
	public void addTrait(Trait t){
		lt.add(t);
	}
	
	@Override
	public void affiche(Graphics g) {
		g.setColor(couleur);
		for(Trait i: lt){
			i.affiche(g);
		}
		
	}

}
