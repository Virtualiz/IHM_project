/**
 * classe mod�lisant un point
 * @author sinteff3u, demarbre1u
 *
 */
public class Point {
	/**
	 * coordonn�es du point
	 */
	private int x,y;
	
	/**
	 * Constructeur
	 * @param abs abscisse du point
	 * @param ord ordonn�e du point
	 */
	public Point(int abs, int ord){
		x=abs;
		y=ord;
	}

	/**
	 * m�thode qui calcule la distance entre le point this et le point en param�tre
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
	 * m�thode qui incr�mente l'abscisse du point de incx
	 * @param incx
	 */
	public void incrementerX(int incx){
		
		 x+=incx;
		
	}
	
	/**
	 * m�thode qui incr�mente l'ordonn�e du point de incy
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
	 * modifie l'ordonn�e du point
	 * @param ny nouvelle ordonn�e
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
	 * retourne l'ordonn�e du point
	 * @return
	 */
	public int rendreY(){
		return y;
	}
	
	/**
	 * m�thode permettant de translater un point
	 * @param dx � incr�menter aux abscisses
	 * @param dy � incr�menter aux ordonn�es
	 */
	public void translation(int dx, int dy){
		
			incrementerX(dx);
			incrementerY(dy);
		
	}

}
