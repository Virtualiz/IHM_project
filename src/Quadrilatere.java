/**
 * classe modélisant un quadrilatère
 * @author sinteff3u, demarbre1u
 *
 */
public class Quadrilatere extends Polygone{
	
	/**
	 * constructeur, créer sur le modèle d'un polygone
	 */
	public Quadrilatere(){
		super();
	}
	
	/**
	 * renvoie le nombre de point d'un quadrilatère : 4
	 */
	@Override
	public int nbPoints() {
		
		return 4;
	}

}
