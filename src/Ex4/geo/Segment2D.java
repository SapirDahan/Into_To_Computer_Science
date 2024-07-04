package Ex4.geo;
import Ex4.Ex4_Const;

/*
 * @author Sapir Dahan ID: 325732972
 *
 */

/*
 * This class represents a 2D segment on the plane, 
 */

public class Segment2D implements GeoShapeable{
	private Point2D p1;
	private Point2D p2;
	
	// Constructor
	public Segment2D (Point2D p1, Point2D p2) {
		this.p1 = new Point2D(p1.x(), p1.y());
		this.p2 = new Point2D(p2.x(), p2.y());
	}
	
	@Override
	public String toString(){ 
		return p1.toString()+","+p2.toString();
	}
	
	@Override
	public boolean contains(Point2D ot) {
		double d = p1.distance(p2);
		double d1 = p1.distance(ot);
		double d2 = p2.distance(ot);
		
		// Check if the sum of lengths of the point to each end of the segment is equal up to 
		// epsilon to the length of the segment
		if (Math.abs(d - d1 - d2) < Ex4_Const.EPS1) {
			return true;
		}
		return false;
	}

	@Override
	public double area() {
		return 0; //The area of a segment is always 0.
	}

	@Override
	public double perimeter() {
		
		// The perimeter of the segment is twice the length of a segment.
		return 2*(p1.distance(p2));
	}

	@Override
	public void move(Point2D vec) {
		
		// Move each point of the segment in the given vector
		p1.move(vec);
		p2.move(vec);	
	}

	@Override
	public GeoShapeable copy() {
		
		// Deep copy
		return new Segment2D(p1, p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		
		// Scale each point of the segment on the given center and the given ratio
		p1.scale(center, ratio);
		p2.scale(center, ratio);	
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		
		// Rotate each point of the segment
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		
		// Return an array with the points of the segment
		Point2D[] arr = {p1, p2};
		return arr;
	}
	
	public Point2D getPoint1() {
		return p1;
	}
	
	public Point2D getPoint2() {
		return p2;
	}
	
}