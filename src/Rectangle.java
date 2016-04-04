import java.awt.Polygon;



/**
 * Classe qui definit un rectangle
 * @author sinteff3u, demarbre1u
 */
public class Rectangle extends Quadrilatere 
{
	/**
	 * Constructeur par defaut de rectangle
	 */
	public Rectangle()
	{
		super();
	}
	
	/**
	 * Methode qui renvoie le nombre de clic d'une figure
	 */
	public int nbClics()
	{
		return 2;
		
	}
	
	/**
	 * Methode qui modifie les points d'une figure
	 * @param tab les points a modifier
	 */
	public void modifierPoints(Point[] tab)
	{	
		int supgaucheX, supgaucheY, basdroitX, basdroitY;
		if(tab.length == 2){
			
			if(tab[0].rendreX()<tab[1].rendreX()){
				supgaucheX=tab[0].rendreX();
				basdroitX=tab[1].rendreX();
			}
			else{
				supgaucheX=tab[1].rendreX();
				basdroitX=tab[0].rendreX();
			}
			
			if(tab[0].rendreY()<tab[1].rendreY()){
				supgaucheY=tab[0].rendreY();
				basdroitY=tab[1].rendreY();
			}
			else{
				supgaucheY=tab[1].rendreY();
				basdroitY=tab[0].rendreY();
			}
			
			tab_mem=new Point[4];
			tab_mem[0]= new Point(supgaucheX,supgaucheY);
			tab_mem[1]= new Point(basdroitX,supgaucheY);
			tab_mem[2]= new Point(basdroitX,basdroitY);
			tab_mem[3]= new Point(supgaucheX,basdroitY);
			
			Polygon pol = new Polygon();
			for (int i = 0 ; i < tab_mem.length ; i++)
			{
				pol.addPoint( tab_mem[i].rendreX(), tab_mem[i].rendreY());
			}
			p = pol;
		}
	}
	
	
	public void transformation(int a, int b, int c)	{}
}
