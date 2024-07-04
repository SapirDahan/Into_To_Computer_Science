package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex4.geo.GeoShapeable;
import Ex4.geo.Point2D;
import Ex4.geo.Segment2D;
import Ex4.geo.Triangle2D;

class Triangle2DTest {
	

	@Test
	void Triangle2DTest1() {
		Point2D p1 = new Point2D(8,2);
		Point2D p2 = new Point2D(3,7);
		Point2D p3 = new Point2D(4,5);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		assertEquals(p1.x(), tri.getPoint1().x());
		assertEquals(p2.x(), tri.getPoint2().x());
		assertEquals(p3.x(), tri.getPoint3().x());
		assertEquals(p1.y(), tri.getPoint1().y());
		assertEquals(p2.y(), tri.getPoint2().y());
		assertEquals(p3.y(), tri.getPoint3().y());
	}
	
	@Test
	void toStringTest() {
		Point2D p1 = new Point2D(8,2);
		Point2D p2 = new Point2D(3,7);
		Point2D p3 = new Point2D(4,5);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		assertEquals(tri.toString(), "8.0,2.0,3.0,7.0,4.0,5.0");
	}
	
	@Test
	void containsTest() {
		Point2D p1 = new Point2D(1,0);
		Point2D p2 = new Point2D(3,0);
		Point2D p3 = new Point2D(2,2);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		Point2D p4 = new Point2D(2,1);
		Point2D p5 = new Point2D(2,0);

		assertTrue(tri.contains(p4));
		assertTrue(tri.contains(p5));
		
		assertTrue(tri.contains(p1));
		assertTrue(tri.contains(p2));
		assertTrue(tri.contains(p3));
		
		Point2D p6 = new Point2D(10,0);
		Point2D p7 = new Point2D(0,0);
		Point2D p8 = new Point2D(1,-0.05);
		
		assertFalse(tri.contains(p6));
		assertFalse(tri.contains(p7));
		assertFalse(tri.contains(p8));
	}
	
	@Test
	void areaTest() {
		Point2D p1 = new Point2D(1,0);
		Point2D p2 = new Point2D(3,0);
		Point2D p3 = new Point2D(2,2);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		assertEquals(tri.area(), 2);
	}
	
	@Test
	void perimeterTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(3,0);
		Point2D p3 = new Point2D(0,4);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		assertEquals(tri.perimeter(), 12);

	}
	
	@Test
	void moveTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(4,4);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		Point2D p4 = new Point2D(4,4);
		tri.move(p4);
		assertEquals(tri.getPoint1().x(), 4);
		assertEquals(tri.getPoint1().y(), 4);
		assertEquals(tri.getPoint2().x(), 6);
		assertEquals(tri.getPoint2().y(), 6);
		assertEquals(tri.getPoint3().x(), 8);
		assertEquals(tri.getPoint3().y(), 8);
	}
	
	@Test
	void copyTest() {
		Point2D p1 = new Point2D(0,1);
		Point2D p2 = new Point2D(3,2);
		Point2D p3 = new Point2D(4,9);
		Triangle2D t = new Triangle2D(p1, p2, p3);
		GeoShapeable t1 = t.copy();
		assertEquals(((Triangle2D) t1).getPoint1().x(), 0);
		assertEquals(((Triangle2D) t1).getPoint1().y(), 1);
		assertEquals(((Triangle2D) t1).getPoint2().x(), 3);
		assertEquals(((Triangle2D) t1).getPoint2().y(), 2);
		assertEquals(((Triangle2D) t1).getPoint3().x(), 4);
		assertEquals(((Triangle2D) t1).getPoint3().y(), 9);
	}
	
	@Test
	void scaleTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		Point2D cen = new Point2D(0,0);
		tri.scale(cen, 0.5);
		assertEquals(tri.getPoint1().x(), 2);
		assertEquals(tri.getPoint1().y(), 2);
		assertEquals(tri.getPoint2().x(), 1);
		assertEquals(tri.getPoint2().y(), 1);
		assertEquals(tri.getPoint3().x(), 3);
		assertEquals(tri.getPoint3().y(), 3);
		tri.scale(cen, 4);
		assertEquals(tri.getPoint1().x(), 8);
		assertEquals(tri.getPoint1().y(), 8);
		assertEquals(tri.getPoint2().x(), 4);
		assertEquals(tri.getPoint2().y(), 4);
		assertEquals(tri.getPoint3().x(), 12);
		assertEquals(tri.getPoint3().y(), 12);
	}
	
	@Test
	void rotateTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		Point2D cen = new Point2D(0,0);
		tri.rotate(cen, 180);
		assertEquals(tri.getPoint1().x(), -4);
		assertEquals(tri.getPoint1().y(), -4);
		assertEquals(tri.getPoint2().x(), -2);
		assertEquals(tri.getPoint2().y(), -2);
		assertEquals(tri.getPoint3().x(), -6);
		assertEquals(tri.getPoint3().y(), -6);
		
		tri.rotate(cen, 180);
		assertEquals(tri.getPoint1().x(), 4);
		assertEquals(tri.getPoint1().y(), 4);
		assertEquals(tri.getPoint2().x(), 2);
		assertEquals(tri.getPoint2().y(), 2);
		assertEquals(tri.getPoint3().x(), 6);
		assertEquals(tri.getPoint3().y(), 6);
	}
	
	@Test
	void getPointsTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D tri = new Triangle2D(p1, p2, p3);
		Point2D[] arr = tri.getPoints();
		assertEquals(arr[0].x(), 4);
		assertEquals(arr[0].y(), 4);
		assertEquals(arr[1].x(), 2);
		assertEquals(arr[1].y(), 2);
		assertEquals(arr[2].x(), 6);
		assertEquals(arr[2].y(), 6);
	}

}
