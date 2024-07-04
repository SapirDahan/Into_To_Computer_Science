package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex4.geo.GeoShapeable;
import Ex4.geo.Point2D;
import Ex4.geo.Segment2D;

class Segment2DTest {
	
	@Test
	void Segment2DTest1() {
		Point2D p1 = new Point2D(8,2);
		Point2D p2 = new Point2D(3,7);
		Segment2D seg = new Segment2D(p1, p2);
		assertEquals(p1.x(), seg.getPoint1().x());
		assertEquals(p2.x(), seg.getPoint2().x());
		assertEquals(p1.y(), seg.getPoint1().y());
		assertEquals(p2.y(), seg.getPoint2().y());
	}
	
	@Test
	void toStringTest() {
		Point2D p1 = new Point2D(8,2);
		Point2D p2 = new Point2D(3,7);
		Segment2D seg = new Segment2D(p1, p2);
		assertEquals(seg.toString(), "8.0,2.0,3.0,7.0");
	}

	
	@Test
	void containsTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(2,2);
		Segment2D seg = new Segment2D(p1, p2);
		Point2D p3 = new Point2D(1,1);
		assertTrue(seg.contains(p3));
		assertTrue(seg.contains(p1));
		assertTrue(seg.contains(p2));
		Point2D p4 = new Point2D(0,1);
		assertFalse(seg.contains(p4));
	}
	
	@Test
	void areaTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(100,-219);
		Segment2D seg = new Segment2D(p1, p2);
		assertEquals(seg.area(), 0);
	}
	
	@Test
	void perimeterTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(3,4);
		Segment2D seg = new Segment2D(p1, p2);
		assertEquals(seg.perimeter(), 10);
	}
	
	@Test 
	void moveTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(2,2);
		Segment2D seg = new Segment2D(p1, p2);
		Point2D p3 = new Point2D(4,4);
		seg.move(p3);
		assertEquals(seg.getPoint1().x(), 4);
		assertEquals(seg.getPoint1().y(), 4);
		assertEquals(seg.getPoint2().x(), 6);
		assertEquals(seg.getPoint2().y(), 6);
	}
	
	@Test
	void copyTest() {
		Point2D p1 = new Point2D(0,1);
		Point2D p2 = new Point2D(3,2);
		Segment2D seg = new Segment2D(p1, p2);
		GeoShapeable seg1 = seg.copy();
		assertEquals(((Segment2D) seg1).getPoint1().x(), 0);
		assertEquals(((Segment2D) seg1).getPoint1().y(), 1);
		assertEquals(((Segment2D) seg1).getPoint2().x(), 3);
		assertEquals(((Segment2D) seg1).getPoint2().y(), 2);	
	}
	
	@Test
	void scaleTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Segment2D seg = new Segment2D(p1, p2);
		Point2D cen = new Point2D(0,0);
		seg.scale(cen, 0.5);
		assertEquals(seg.getPoint1().x(), 2);
		assertEquals(seg.getPoint1().y(), 2);
		assertEquals(seg.getPoint2().x(), 1);
		assertEquals(seg.getPoint2().y(), 1);
		seg.scale(cen, 4);
		assertEquals(seg.getPoint1().x(), 8);
		assertEquals(seg.getPoint1().y(), 8);
		assertEquals(seg.getPoint2().x(), 4);
		assertEquals(seg.getPoint2().y(), 4);
	}
	
	@Test
	void rotateTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Segment2D seg = new Segment2D(p1, p2);
		Point2D cen = new Point2D(0,0);
		seg.rotate(cen, 180);
		assertEquals(seg.getPoint1().x(), -4);
		assertEquals(seg.getPoint1().y(), -4);
		assertEquals(seg.getPoint2().x(), -2);
		assertEquals(seg.getPoint2().y(), -2);
		seg.rotate(cen, 180);
		assertEquals(seg.getPoint1().x(), 4);
		assertEquals(seg.getPoint1().y(), 4);
		assertEquals(seg.getPoint2().x(), 2);
		assertEquals(seg.getPoint2().y(), 2);
	}
	
	@Test
	void getPointsTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Segment2D seg = new Segment2D(p1, p2);
		Point2D[] arr = seg.getPoints();
		assertEquals(arr[0].x(), 4);
		assertEquals(arr[0].y(), 4);
		assertEquals(arr[1].x(), 2);
		assertEquals(arr[1].y(), 2);
	}

}
