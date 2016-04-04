/**
 * Classe qui definit un triangle
 * @author Allan
 *
 */
public class Triangle  extends Polygone
{
	/**
	 * Constructeur par defaut
	 */
	public Triangle()
	{
		super();
	}
	
	/**
	 * Methode qui renvoie le nombre de clics d'une figure
	 * @return le nombre de clics d'une figure
	 */
	public int nbClics(){
		return 3;
	}
	
	/**
	 * Methode qui renvoie le nombre de points d'une figure
	 * @return le nombre de points d'une figure
	 */
	public int nbPoints()
	{
		return 3;
	}
}
