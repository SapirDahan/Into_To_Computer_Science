package Ex2;

/** 
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * In general, such an array {-1,2,3.1} represents the following polynom 3.1x^2+2x-1=0,
 * The index of the entry represents the power of x.
 * 
 * @author Sapir Dahan ID: 325732972
 *
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};

	/** Two polynoms are equal if and only if the have the same coefficients - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true if p1 represents the same polynom as p2.
	 */
	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		
		int lenght_max = Math.max(p1.length, p2.length); // Find the longest polynom
		double[] arr = new double[lenght_max]; // Create an array with the maximal length
		
		//
		for(int i=0; i<p1.length; i++) {
			arr[i] = p1[i]; // Add the first polynom
		}
		
		for(int i=0; i<p2.length; i++) {
			arr[i] = arr[i] - p2[i]; // Subtract the second polynom
		}
		
		// Check that every object in the array is less then EPS
		for(int i=0; i<lenght_max; i++) {
			if(Math.abs(arr[i]) > EPS) {
				ans = false;
				break;
			}
		}

		return ans;
	}

	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */
	public static double f(double[] poly, double x) {
		double ans = 0;
		for(int i=0; i<poly.length; i++) {
			ans = ans + poly[i]*Math.pow(x, i); // Assign x into the polynom
		}
		return ans;
	}
	/** 
	 * Computes a String representing the polynom.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynom represented as an array of doubles
	 * @return String representing the polynom: 
	 */
	public static String poly(double[] poly) {
		String ans = ""; // Reset the polynom the string
		int counter = poly.length-1;
		
		// Eliminate leading 0 coefficients
		while(poly[counter] == 0 || counter == 0) { 
			counter--;
		}
		ans = Double.toString(poly[counter]) + "x^" + counter; // Avoid leading plus for the first coefficient
		
		for(int i=counter-1; 1<i; i--) {
			if(poly[i] == 0) { // Avoid zero coefficient
				continue;
			}
			else if(poly[i]>0) { // Append next positive coefficient 
				ans = ans + " +" + poly[i] + "x^" + i;
			}
			else if(poly[i]<0) { // Append next negative coefficient 
				ans = ans + " " + poly[i] + "x^" + i;
			}
		}
		
		if(poly[1] != 0) { // Append x^1 as x
			if (poly[1]>0) {
				ans = ans + " +" + poly[1] + "x";
			}
			else if(poly[1]<0) {
				ans = ans + " " + poly[1] + "x";
			}
		}

		if(counter>1) { // Append x^0 as a constant
			if (poly[0]>0) {
				ans = ans + " +" + poly[0];
			}
			else if(poly[0]<0) {
				ans = ans + " " + poly[0];
			}
		}

		return ans;
	}

	/**
	 * Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		while(Math.abs(f(p,x12)) > eps) { // While the goal is not reached:
			if((f(p,x12))>0 && f(p,x1)<0) { // if the value of the function is positive in the middle of the range and the function is increasing:
				x2 = x12; // replace the right side of the range with the middle of the range.
			}
			else if((f(p,x12))>0 && f(p,x2)<0) { // if the value of the function is positive in the middle of the range and the function is decreasing:
				x1 = x12; // replace the left side of the range with the middle of the range.
			}
			else if((f(p,x12))<0 && f(p,x1)<0) { // if the value of the function is negative in the middle of the range and the function is increasing:
				x1 = x12; // replace the left side of the range with the middle of the range.
			}
			else if((f(p,x12))<0 && f(p,x2)<0){ // if the value of the function is negative in the middle of the range and the function is decreasing:
				x2 = x12; // replace the right side of the range with the middle of the range.
			}
			x12 = (x1+x2)/2; // recalculate the middle of the range.
		}

		return x12;
	}
	/** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented recursivly.
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		if(Math.abs(f(p,x12)) < eps) { // Stopping condition
			return x12;
		}
		if(((f(p,x12))>0 && f(p,x2)>0) || (((f(p,x12))<0 && f(p,x2)<0))) { // When in the next step the right side is the middle of the range:
			x12 = root_rec(p, x1, x12, eps); // replace the right side of the range with the middle of the range.
		}
		else { // When in the next step the left side is the middle of the range:
			x12 = root_rec(p, x12, x2, eps); // replace the left side of the range with the middle of the range.
		}

		return x12;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
	 */
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2; // find the middle of the range
		while(Math.abs(f(p1,x12)-f(p2,x12)) > eps) { // While the goal is not reached:
			
			//Assign the middle of the range to the right or left of the range based on the relationship between the functions
			if(f(p1,x12)>f(p2,x12) && f(p1,x1)>f(p2,x1)) {
				x1 = x12;
			}
			else if(f(p1,x12)>f(p2,x12) && f(p1,x1)<f(p2,x1)) {
				x2 = x12;
			}
			else if(f(p1,x12)<f(p2,x12) && f(p1,x1)>f(p2,x1)) {
				x2 = x12;
			}
			else {
				x1 = x12;
			}
			x12 = (x1+x2)/2;
		}

		return x12;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of "boxes". 
	 * This function computes an approximation of the area between the polynoms within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfBoxes - a natural number representing the number of boxes between xq and x2.
	 * @return the approximated area between the two polynoms within the [x1,x2] range.
	 */
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfBoxes) {
		double ans = 0; // Reset the area 
		final double GAP = (x2-x1)/(double)numberOfBoxes; // Calculate the width of each box
		double x_index = x1; // Initialize the position of the first box
		for (int i = 0; i < numberOfBoxes; i++) { //Run through the boxes
			ans = ans + Math.abs(f(p1,x_index)-f(p2,x_index))*GAP; // Accumulate the area of each box
			x_index = x_index + GAP; // Calculate the position of the next box
		}

		return ans;
	}
	/**
	 * This function computes the array representation of a polynom from a String
	 * representation. Note: given a polynom represented as a double array,  
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynom.
	 * @return
	 */
	public static double[] getPolynomFromString(String p) {
		
		//Split based on plus or minus. Remove the plus and keep the minus
		String[] splits = p.split("\\+|((?=\\-))");
		
		//Find the highest power
		int max_power = 0; // Initialize the highest power
		for(int i=0; i<splits.length; i++) { // Run through all the powers
			if(getPowerFromString(splits[i])>max_power) {
				max_power = getPowerFromString(splits[i]); // Replace with highest power
			}
		}
		
		// Compose an array that represent the polynom
		double[] pol = new double[max_power+1]; 
		for(int i=0; i<splits.length; i++) {
			int power = getPowerFromString(splits[i]); // Extract power
			double num = getNumberFromString(splits[i]); // Extract coefficient
			pol[power] = num; // Place coefficient in power position
		}

		return pol;
	}
	/**
	 * This function computes the polynom which is the sum of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] add(double[] p1, double[] p2) {
		int lenght_max = Math.max(p1.length, p2.length); // Find the longest polynom
		int lenght1 = p1.length;
		int lenght2 = p2.length;
		double[] sum_po = new double[lenght_max]; // Create an array with the maximal length

		for (int i=0; i<lenght1; i++) { 
			sum_po[i] = sum_po[i] + p1[i]; // Add the first polynom
		}

		for (int i=0; i<lenght2; i++) {
			sum_po[i] = sum_po[i] + p2[i]; // Add the second polynom
		}

		return sum_po;
		
	}
	/**
	 * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		double[] p = new double[p1.length + p2.length - 1];
		for(int i=0; i<p1.length; i++) {
			for(int j=0; j<p2.length; j++) {
				p[i+j] = p[i+j] + p1[i]*p2[j]; // Accumulate the value of the i+j coefficient by adding the product of the ith coefficient of p1 with the jth coefficient of p2
			}
		}
		return p;

	}
	/**
	 * This function computes the derivative polynom of po.
	 * @param po
	 * @return
	 */
	public static double[] derivative (double[] po) {
		if (po.length == 1) { //If constant derivative is zero
			return ZERO;
		}
		double[] pol = new double[po.length-1];
		for(int i=1; i<po.length; i++) { 
			pol[i-1] = po[i]*i; // Polynomial derivative
		}
		return pol;
	}
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * Note: this function only works for a set of points containing three points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynom.
	 * Note: you can assume xx[0]!=xx[1]!=xx[2]
	 */
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {
		if(xx!=null && yy!=null && xx.length==3 && yy.length==3) {
			// Calculate the coefficents of a second degree polynomial 
			// Link: https://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
			double denom = (xx[0] - xx[1]) * (xx[0] - xx[2]) * (xx[1] - xx[2]);
			double A = (xx[2] * (yy[1] - yy[0]) + xx[1] * (yy[0] - yy[2]) + xx[0] * (yy[2] - yy[1])) / denom;
			double B = (xx[2]*xx[2] * (yy[0] - yy[1]) + xx[1]*xx[1] * (yy[2] - yy[0]) + xx[0]*xx[0] * (yy[1] - yy[2])) / denom;
			double C = (xx[1] * xx[2] * (xx[1] - xx[2]) * yy[0] + xx[2] * xx[0] * (xx[2] - xx[0]) * yy[1] + xx[0] * xx[1] * (xx[0] - xx[1]) * yy[2]) / denom;
			double [] ans = {C, B, A};
			return ans;

		}
		return null;
	}
	
	///////////////////// Private /////////////////////
	// you can add any additional functions (private) below
	
	private static double getNumberFromString (String str) {
		String[] arr = str.split("x"); // Separate the coefficient from the power
		double num = Double.parseDouble(arr[0]); // Extract only the coefficient
		return num;
	}

	private static int getPowerFromString (String str) {
		int power;
		if(!str.contains("^")) {
			if(!str.contains("x")) {
				power = 0; // power of a constant
			}
			else {
				power = 1; // power of x
			}
		}

		else {
			String[] arr = str.split("\\^");
			power = Integer.parseInt(arr[1].trim()); // power greater than one
		}

		return power;
	}
}

