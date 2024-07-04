package Ex4.geo;

/*
 * @author Sapir Dahan ID: 325732972
 *
 */

/*
 * This class represents a 2D Triangle in the plane.
 */

public class Triangle2D implements GeoShapeable{
	private Point2D p1;
	private Point2D p2;
	private Point2D p3;
	
	// Constructor
	public Triangle2D (Point2D p1, Point2D p2, Point2D p3) {
		this.p1 = new Point2D(p1.x(), p1.y());
		this.p2 = new Point2D(p2.x(), p2.y());
		this.p3 = new Point2D(p3.x(), p3.y());
	}
	
	@Override
	public String toString(){ 
		return p1.toString()+","+p2.toString()+","+p3.toString();
	}

	@Override
	public boolean contains(Point2D ot) {
		// If a point is inside a triangle then the three triangles that
		// create from the point and 2 of the vertices of a triangle
		// is equal to the area of the triangle.
		Triangle2D t123 = new Triangle2D(p1,p2,p3);
		Triangle2D t12ot = new Triangle2D(p1,p2,ot);
		Triangle2D t1ot3 = new Triangle2D(p1,ot,p3);
		Triangle2D tot23 = new Triangle2D(ot,p2,p3);
		
		double area_t123 = t123.area();
		double area_t12ot = t12ot.area();
		double area_t1ot3 = t1ot3.area();
		double area_tot23 = tot23.area();
		
		if(area_t123 == area_t12ot + area_t1ot3 + area_tot23) {
			return true;
		}
		return false;
	}

	@Override
	public double area() {
		// The formula of the area of a triangle from 3 points is taking from:
		// https://www.cuemath.com/geometry/area-of-triangle-in-coordinate-geometry/
		return 0.5*Math.abs(p1.x()*(p2.y()-p3.y())+p2.x()*(p3.y()-p1.y())+p3.x()*(p1.y()-p2.y()));
	}

	@Override
	public double perimeter() {
		
		// Perimeter of triangle
		return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
	}

	@Override
	public void move(Point2D vec) {
		
		// Move each point of the triangle on the given vector
		p1.move(vec);
		p2.move(vec);	
		p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		
		// Deep copy
		return new Triangle2D(p1, p2, p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		
		// Scale each point of the triangle on the given center and the given ratio
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		
		// Rotate each point of the triangle
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
		p3.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		
		// Return an array with the points of the triangle
		Point2D[] arr = {p1, p2, p3};
		return arr;
	}
	
	public Point2D getPoint1() {
		return p1;
	}
	
	public Point2D getPoint2() {
		return p2;
	}
	
	public Point2D getPoint3() {
		return p3;
	}
	
}
