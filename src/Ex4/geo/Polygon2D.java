package Ex4.geo;

import java.awt.geom.GeneralPath;

/*
 * @author Sapir Dahan ID: 325732972
 *
 */

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 */

public class Polygon2D implements GeoShapeable{
	Point2D[] poly;

	// Constructor
	public Polygon2D (Point2D[] poly) {
		this.poly = new Point2D[poly.length];

		// Deep copy
		for(int i = 0; i < poly.length; i++) {
			this.poly[i] = new Point2D(poly[i]);
		}
	}

	@Override
	public String toString(){ 
		String ans = "";
		for(int i = 0; i < getPoints().length; i++) {
			ans = ans + getPoints()[i].toString() + ",";
		}
		// Delete "," at the end of the string	
		ans = ans.substring(0, ans.length() - 1);
		
		return ans;
	}

	@Override
	public boolean contains(Point2D ot) {
		double[] x = new double[poly.length]; // array of X'es
		double[] y = new double[poly.length]; // array of Y'es

		for(int i = 0; i < poly.length; i++) {
			x[i] = poly[i].x(); // Set X array
			y[i] = poly[i].y(); // Set Y array

		}

		// Create the path
		GeneralPath path = new GeneralPath();
		path.moveTo(x[0], y[0]);
		for (int i = 0; i < poly.length; i++) {
			path.lineTo(x[i], y[i]);
		}
		path.closePath(); // Close the path

		return path.contains(ot.x(), ot.y()); // Check if the point in inside the path
	}

	@Override
	public double area() {
		// The equation was taken from:
		// https://en.wikipedia.org/wiki/Polygon
		double area = 0;

		for(int i = 0; i < poly.length; i++) {
			area = area + poly[i].x()*poly[(i+1)%(poly.length)].y() - poly[(i+1)%(poly.length)].x()*poly[i].y();
		}

		return area/2;
	}

	@Override
	public double perimeter() {
		double perimeter = 0;

		// Sum the length of the sides
		for(int i=0; i < poly.length; i++) {
			perimeter = perimeter + poly[i].distance(poly[(i+1)%(poly.length)]);
		}

		return perimeter;
	}

	@Override
	public void move(Point2D vec) {

		// Move each point of the polygon in the given vector
		for(int i=0; i < poly.length; i++) {
			poly[i].move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {

		// Deep copy
		return new Polygon2D(poly);
	}

	@Override
	public void scale(Point2D center, double ratio) {

		// Scale each point of the polygon on the given center and the given ratio
		for(int i=0; i < poly.length; i++) {
			poly[i].scale(center, ratio);;
		}		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {

		// Rotate each point of the polygon
		for(int i=0; i < poly.length; i++) {
			poly[i].rotate(center, angleDegrees);;
		}		
	}

	@Override
	public Point2D[] getPoints() {

		// Return an array with the points of the polygon
		return poly;
	}

}
