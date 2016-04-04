import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
/**
 * Classe créant une interface graphique permettant d'intéragir avec le programme
 * @author sinteff3u, demarbre1u
 */
public class PanneauChoix extends JPanel
{
	/**
	 * la zone de dessin du programme
	 */
	private DessinFigures dessin;
	
	/**
	 * la figure colorée qui est en train d'être créé
	 */
	private FigureColoree fc;
	
	/**
	 * Constructeur de la classe PanneauChoix
	 * @param DessinFigures la zone de dessin
	 */
	public PanneauChoix(DessinFigures df)
	{
		final JRadioButton rb1 = new JRadioButton("Nouvelle Figure");
		final JRadioButton rb2 = new JRadioButton("Tracé à main levée");
		rb2.setSelected(true);
		final JRadioButton rb3 = new JRadioButton("Manipulations");
		ButtonGroup bg = new ButtonGroup();
		
		JButton effFig = new JButton("Effacer figure");
		JButton effTout = new JButton("Effacer tout");
		
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		
		final JComboBox cb_couleur = new JComboBox(new String[] {"Rouge", "Vert", "Jaune", "Bleu", "Noir", "Cyan", "Gris", "Orange", "Rose", "Magenta"});
		final JComboBox cb_figure = new JComboBox(new String[] {"Triangle", "Rectangle", "Quadrilatère","Cercle"}); // triangle et reclangle a ajouter plus tard
		
		cb_figure.setEnabled(false);
		
		/**
		 * ActionListener permettant de détecter une action sur un bouton radio
		 */
		ActionListener al = new ActionListener() 
		{
			@Override
			/**
			 * Action à effectuer lorsque un bouton radio est sélectionné
			 * @param e Evenement souris
			 */
			public void actionPerformed(ActionEvent e) {
				if (rb2.isSelected() || rb3.isSelected())
				{
					cb_couleur.setEnabled(true);
					cb_figure.setEnabled(false);
				}
				else
				{
					cb_couleur.setEnabled(false);
					cb_figure.setEnabled(true);
				}
				if (rb3.isSelected())
				{
					dessin.supprimeAuditeurs();
					dessin.activeManipulationsSouris();
				}
				else
				{
					dessin.desactiveManipulationsSouris();
				}
				if (rb2.isSelected())
				{
					dessin.activerScribble(cb_couleur);
				}
				else
				{
					dessin.desactiverScribble();
				}
				if (rb1.isSelected())
				{
					dessin.supprimeAuditeurs();
				}
			}
	    };
	    
	    /**
		 * ActionListener permettant de détecter une action sur une ComboBox
		 */
	    ActionListener alFig = new ActionListener()
	    {
			@Override
			/**
			 * Action à effectuer lorsque un un index est sélectionné
			 * @param e Evenement souris
			 */
			public void actionPerformed(ActionEvent e) {
				creeFigure(((JComboBox)e.getSource()).getSelectedIndex());
				dessin.construit(fc);
				
			}
	    	
	    };
	    
	    /**
		 * ActionListener permettant de détecter une action sur une ComboBox
		 */
	    ActionListener alCol = new ActionListener()
	    {
			@Override
			/**
			 * Action à effectuer lorsque un index est sélectionné
			 * @param e Evenement souris
			 */
			public void actionPerformed(ActionEvent e) {
				if (rb3.isSelected())
				{
					FigureColoree tmp = dessin.figureSelection();
					if (tmp != null)
					{
						tmp.changeCouleur(determineCouleur(((JComboBox)e.getSource()).getSelectedIndex()));
						dessin.repaint();
					}
				}
			}
	    };
	    
	    /**
		 * ActionListener permettant de détecter une action sur un bouton 
		 */
	    ActionListener alEff = new ActionListener()
	    {
			@Override
			/**
			 * Action à effectuer lorsque un bouton est cliqué
			 * @param e Evenement souris
			 */
			public void actionPerformed(ActionEvent e) 
			{
				dessin.effacerTout();
			}
	    };
	    
	    /**
		 * ActionListener permettant de détecter une action sur un bouton 
		 */
	    ActionListener alEffFig = new ActionListener()
	    {
			@Override
			/**
			 * Action à effectuer lorsque un bouton est cliqué
			 * @param e Evenement souris
			 */
			public void actionPerformed(ActionEvent e) {
				dessin.supprimerFigure();
			}   	
	    };

	    rb1.addActionListener(al);	
	    rb2.addActionListener(al);
	    rb3.addActionListener(al);
	    cb_figure.addActionListener(alFig);
	    cb_couleur.addActionListener(alCol);
	    effTout.addActionListener(alEff);
	    effFig.addActionListener(alEffFig);
	   
	    Border compound;
	    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	    compound = BorderFactory.createCompoundBorder(raisedbevel,loweredbevel);
	    this.setBorder(compound);
	    
		setLayout(new BorderLayout());
		
		JPanel haut = new JPanel();
		haut.setLayout(new FlowLayout());
		haut.add(rb1);
		haut.add(rb2);
		haut.add(rb3);
		
		JPanel bas = new JPanel();
		bas.setLayout(new FlowLayout());
		bas.add(cb_figure);
		bas.add(cb_couleur);
		
		JPanel centre = new JPanel();
		centre.setLayout(new BorderLayout());
		centre.add(haut, BorderLayout.NORTH);
		centre.add(bas, BorderLayout.CENTER);
		
		JPanel gauche = new JPanel();
		gauche.setLayout(new GridLayout(2, 1));
		gauche.add(effFig);
		gauche.add(effTout);
		
		add(centre, BorderLayout.CENTER);
		add(gauche, BorderLayout.WEST);
		
		dessin = df;
		fc = null;
	}
	
	/**
	 * Méthode qui met a jour le type de FigureColoree en train d'être créé
	 * @param index index de la JComboBox
	 * @return FigureColoree
	 */
	private FigureColoree creeFigure(int index)
	{
		switch(index)
		{
			case 0:
				fc = new Triangle();
				break;
			case 1:
				fc = new Rectangle();
				break;
			case 2:
				fc = new Quadrilatere();
				break;
			case 3:
				fc = new Cercle();
				break;
			default:
				fc = new Quadrilatere();
		}
		return fc;
	}
	
	/**
	 * Méthode déterminant la couleur sélectionnée dans la JComboBox
	 * @param index index sélectionnée dans la JComboBox
	 * @return Color
	 */
	public static Color determineCouleur(int index)
	{
		Color c = null;
		switch(index)
		{
			case 0:
				c = Color.red;
				break;
			case 1:
				c = Color.green;
				break;
			case 2:
				c = Color.yellow;
				break;
			case 3:
				c = Color.blue;
				break;
			case 4:
				c = Color.black;
				break;
			case 5:
				c = Color.cyan;
				break;
			case 6:
				c = Color.gray;
				break;
			case 7:
				c = Color.orange;
				break;
			case 8:
				c = Color.pink;
				break;
			case 9:
				c = Color.magenta;
				break;
		}
		return c;
	}
}
