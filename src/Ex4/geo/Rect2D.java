package Ex4.geo;

/*
 * @author Sapir Dahan ID: 325732972
 *
 */

/*
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 */

public class Rect2D implements GeoShapeable{
	private Point2D p1;
	private Point2D p2;

	// Constructor
	public Rect2D (Point2D p1, Point2D p2) {
		this.p1 = new Point2D(x_min(p1,p2), y_min(p1,p2));
		this.p2 = new Point2D(x_max(p1,p2), y_max(p1,p2));
	}
	
	@Override
	public String toString(){ 
		Point2D p3 = new Point2D(x_max(p1,p2), y_min(p1,p2));
		Point2D p4 = new Point2D(x_min(p1,p2), y_max(p1,p2));

		return p1.toString()+","+p3.toString()+","+p2.toString()+","+p4.toString();
	}

	@Override
	public boolean contains(Point2D ot) {
		
		// Check if the point is in x range and in y range
		return ot.x() >= p1.x() && ot.x() <= p2.x() && ot.y() >= p1.y() && ot.y() <= p2.y();
	}

	@Override
	public double area() {
		
		// Area of rectangle
		return (p2.x()-p1.x())*(p2.y()-p1.y());
	}

	@Override
	public double perimeter() {
		
		// Perimeter of rectangle
		return 2*(p2.x()-p1.x()) + 2*(p2.y()-p1.y());
	}

	@Override
	public void move(Point2D vec) {
		
		// Move each point of the rectangle on the given vector
		p1.move(vec);
		p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		
		// Deep copy
		return new Rect2D(p1, p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		
		// Scale each point of the rectangle on the given center and the given ratio
		p1.scale(center, ratio);
		p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		
		// Rotate each point of the rectangle
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		
		// Return the points of the rectangle
		Point2D[] arr = {p1, p2};
		return arr;
	}

	
	public Point2D getPoint1() {
		return p1;
	}

	public Point2D getPoint2() {
		return p2;
	}

	
	///////////////// private functions ///////////////////////
	
	private double x_max (Point2D p1, Point2D p2) {
		return Math.max(p1.x(), p2.x()); // Find the x maximum
	}

	private double x_min (Point2D p1, Point2D p2) {
		return Math.min(p1.x(), p2.x()); // Find the x minimum
	}

	private double y_max (Point2D p1, Point2D p2) {
		return Math.max(p1.y(), p2.y()); // Find the y maximum
	}

	private double y_min (Point2D p1, Point2D p2) {
		return Math.min(p1.y(), p2.y()); // Find the y minimum
	}

}
