import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Classe permettant de creer une zone de dessin
 * @author sinteff3u, demarbre1u
 */
public class DessinFigures extends JPanel{
	
	/**
	 * Tableau permettant de stocker tout les elements de la zone de dessin
	 */
	private Dessinable[] lfg;
	
	/**
	 * Nombre actuel de figure dans le tableau de Dessinable 
	 */
	private int nbf;
	
	/**
	 * Index de la dernière figure selectionnee
	 */
	private int sel;
	
	/**
	 * Permet d'instancier un ManipulateurFormes et de modifier une figure
	 */
	private ManipulateurFormes mf;
	
	/**
	 * Permet d'instancier un Scribble et de dessiner dans la zone de dessin
	 */
	private Scribble scribble;
	
	/**
	 * Constructeur par defaut de la classe 
	 */
	public DessinFigures(){
		super();
		lfg = new Dessinable[99999];
		nbf = 0;
		sel=-1;
		mf = new ManipulateurFormes();
	}
	
	/**
	 * Méthode permettant de déssiner dans le JPanel
	 * @param g zone de dessin
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<nbf;i++){
			if(lfg[i]!=null) lfg[i].affiche(g);
		}
	}
	
	/**
	 * Méthode permettant d'effacer la totalite de la zone de dessin
	 */
	public void effacerTout()
	{
		for (int i = 0 ; i < nbf ; i++)
		{
			lfg[i] = null;
		}
		nbf = 0;
		sel = -1;
		repaint();
	}
	
	/**
	 * Méthode permettant d'ajouter une figure a la zone de dessin
	 * @param fc le dessinable a ajouter
	 */
	public void ajoute(Dessinable fc){
		if(fc != null && nbf<lfg.length){
			lfg[nbf]=fc;
			nbf++;
		}
		repaint();
	}
	
	/**
	 * Méthode permettant de creer une nouvelle figure en instanciant un FabricantFigures
	 * @param fc La FigureColore a construire
	 */
	public void construit(FigureColoree fc){
		this.addMouseListener(new FabricantFigures(fc));
	}
	
	/**
	 * Méthode qui retourne le nombre de figure contenues dans la zone de dessin
	 * @return le nombre de figure dans la zone de dessin
	 */
	public int nbFigures(){
		return nbf;
	}
	
	/**
	 * Méthode qui ajoute les listeners pour construire une figure dans la zone de dessin
	 */
	public void activeManipulationsSouris(){
		this.addMouseListener(mf);
		this.addMouseMotionListener(mf);
	}
	
	/**
	 * Méthode qui retire les listeners suite a la creation d'une figure
	 */
	public void desactiveManipulationsSouris(){
		if(sel != -1){
			((FigureColoree)lfg[sel]).deSelectionne();
			sel = -1;
		}
		this.removeMouseMotionListener(mf);
		this.removeMouseListener(mf);
		repaint();
	}
	
	/**
	 * Méthode qui retire tous les listeners de la zone de dessin
	 */
	public void supprimeAuditeurs(){
		MouseListener[] ml=this.getMouseListeners();
		for(int i=0; i<ml.length; i++){
			this.removeMouseListener(ml[i]);
		}
		MouseMotionListener[] mml=this.getMouseMotionListeners();
		for(int i=0; i<mml.length; i++){
			this.removeMouseMotionListener(mml[i]);
		}
	}
	
	
	/**
	 * Méthode permettant d'ajouter les listeners pour pouvoir dessiner dans la zone de dessin
	 * @param jcb Couleur du trait
	 */
	public void activerScribble(JComboBox jcb){
		scribble = new Scribble(jcb);
		this.addMouseListener(scribble);
		this.addMouseMotionListener(scribble);
	}
	
	/**
	 * Méthode permettant de retirer les listeners permettant de tracer des traits dans la zone de dessin
	 */
	public void desactiverScribble(){
		scribble = null;
		this.removeMouseListener(scribble);
		this.removeMouseMotionListener(scribble);
	}
	
	
	/**
	 * Classe privée ManipulateurFormes permettant de manipuler des figures dans la zone de dessin
	 * @author sinteff3u, demarbre1u
	 */
	private class ManipulateurFormes implements MouseListener, MouseMotionListener{
		/**
		 * Indice de la figure clique, coordonnees de la souris
		 */
		private int indice, lastx, lasty;
		
		/**
		 * Constructeur par defaut
		 */
		ManipulateurFormes(){
			indice = -1;
		}

		/**
		 * Méthode s'effectuant lorsque la souris est deplacee avec un bouton de souris enfonce
		 * @param e l'evenement souris
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			//boolean depPossible=true;
			if(sel != -1) {
				if(lfg[sel]instanceof FigureColoree){
					if(SwingUtilities.isLeftMouseButton(e)){

						((FigureColoree) lfg[sel]).translation(e.getX()-lastx, e.getY()-lasty);
						lastx = e.getX();
						lasty=e.getY();
						repaint();

					}
				}
			}

		}
		
		/**
		 * Méthode s'effectuant lorsqu'un bouton de souris est enfonce
		 * @param e l'evenement souris
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			lastx = e.getX();
			lasty=e.getY();
			for(int i=0;i<nbf;i++){
				if(lfg[i] instanceof FigureColoree){
					if(((FigureColoree) lfg[i]).estDedans(e.getX(), e.getY())){
						indice=i;
					}
				}
			}

			if(indice!=-1 && sel != -1){
				if(lfg[sel]instanceof FigureColoree ){
					
					((FigureColoree) lfg[sel]).deSelectionne();
					((FigureColoree) lfg[indice]).selectionne();
					sel=indice;
				}
			}else{
				if(sel != -1){
					if(lfg[sel]instanceof FigureColoree){
						((FigureColoree) lfg[sel]).deSelectionne();
						sel=-1;
					}
				}
				else{
					if(indice != -1){
						((FigureColoree) lfg[indice]).selectionne();
						sel=indice;
					}
				}
			}
			indice=-1;
			repaint();
		}
		
		
		@Override
		public void mouseMoved(MouseEvent e) {}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * Classe privee pour instancier un Scribble permettant de dessiner dans la zone de dessin
	 * @author sinteff3u, demarbre1u
	 */
	private class Scribble implements MouseListener, MouseMotionListener{
		
		/**
		 * La JComboBox de couleurs
		 */
		private JComboBox jcb_col;
		
		/**
		 * Les coordonnees de la souris
		 */
		private int lastX,lastY;
		
		/**
		 * Constructeur par defaut
		 * @param jcb JComboBox de couleurs
		 */
		Scribble(JComboBox jcb){
			jcb_col = jcb;
		}
		
		/**
		 * Méthode s'effectuant lorsque la souris est deplacee avec un bouton de souris enfonce
		 * @param e l'evenement souris
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			if(SwingUtilities.isLeftMouseButton(e)){
				ajoute(new Trait(lastX,lastY,e.getX(), e.getY(),PanneauChoix.determineCouleur(jcb_col.getSelectedIndex())));
				lastX= e.getX();
				lastY= e.getY();
				repaint();
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {}
		@Override
		public void mouseClicked(MouseEvent e) {}

		/**
		 * Méthode s'effectuant lorsqu'un bouton de souris est enfonce
		 * @param e l'evenement souris
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			//tc = new ArrayList<Trait>();
			lastX= e.getX();
			lastY= e.getY();
			ajoute(new Trait(e.getX(),e.getY(),e.getX(), e.getY(),PanneauChoix.determineCouleur(jcb_col.getSelectedIndex())));
			repaint();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	/**
	 * Méthode retournant s'il y en a une la FigureColore selectionne
	 * @return La FigureColore selectionne
	 */
	public FigureColoree figureSelection(){
		if(sel != -1 && lfg[sel]instanceof FigureColoree) return (FigureColoree) lfg[sel];
		else return null;
	}
	
	/**
	 * Méthode permettant de supprimer une figure de la zone de dessin
	 */
	public void supprimerFigure(){
		if(sel != -1){
			lfg[sel] = null;
			sel = -1;
		}
		repaint();
	}
}
