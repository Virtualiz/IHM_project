import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe principale permettant d'instancier une interface graphique
 * @author sinteff3u, demarbre1u
 */
public class Fenetre extends JFrame{
	
	/**
	 * la zone de dessin qui sera affichee dans la fenetre
	 */
	private static DessinFigures dessin;
	
	/**
	 * Constructeur de Fenetre
	 * @param nom nom de la fenetre 
	 * @param x hauteur de la fenetre
	 * @param y largeur de la fenetre
	 */
	public Fenetre(String nom, int x, int y){
		super(nom);
		this.setPreferredSize(new Dimension(x,y));
		dessin = new DessinFigures();
	}
	
	/**
	 * Methode main
	 */
	public static void main(String[] args) {
		Fenetre f = new Fenetre("Application",750,500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		
		f.setContentPane(jp);
		jp.setPreferredSize(f.getPreferredSize());
		jp.setLayout(new BorderLayout());
		
		jp.add(new PanneauChoix(dessin), BorderLayout.NORTH);
		jp.add(dessin,BorderLayout.CENTER);
		
		f.pack();
		f.setVisible(true);

	}

}
