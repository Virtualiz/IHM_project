/**
 * classe modélisant un point
 * @author sinteff3u, demarbre1u
 *
 */
public class Point {
	/**
	 * coordonnées du point
	 */
	private int x,y;
	
	/**
	 * Constructeur
	 * @param abs abscisse du point
	 * @param ord ordonnée du point
	 */
	public Point(int abs, int ord){
		x=abs;
		y=ord;
	}

	/**
	 * méthode qui calcule la distance entre le point this et le point en paramètre
	 * @param p2
	 * @return distance
	 */
	public double distance(Point p2){
		if(p2!=null){
			double cote1= Math.abs((x-p2.x));
			double cote2= Math.abs((y-p2.y));
			double dist = Math.sqrt(cote1*cote1+cote2*cote2);
			return dist;
		}
		else return 0;
	}

	/**
	 * méthode qui incrémente l'abscisse du point de incx
	 * @param incx
	 */
	public void incrementerX(int incx){
		
		 x+=incx;
		
	}
	
	/**
	 * méthode qui incrémente l'ordonnée du point de incy
	 * @param incy
	 */
	public void incrementerY(int incy){
		
		y+=incy;
		
	}
	
	/**
	 * modifie l'abscisse du point
	 * @param nx nouvelle abscisse
	 */
	public void modifierX(int nx){
		if(nx>=0) x = nx;
	}
	
	/**
	 * modifie l'ordonnée du point
	 * @param ny nouvelle ordonnée
	 */
	public void modifierY(int ny){
		if(ny>=0) y = ny;
	}
	
	/**
	 * retourne l'abscisse du point
	 * @return
	 */
	public int rendreX(){
		return x;
	}
	
	/**
	 * retourne l'ordonnée du point
	 * @return
	 */
	public int rendreY(){
		return y;
	}
	
	/**
	 * méthode permettant de translater un point
	 * @param dx à incrémenter aux abscisses
	 * @param dy à incrémenter aux ordonnées
	 */
	public void translation(int dx, int dy){
		
			incrementerX(dx);
			incrementerY(dy);
		
	}

}
