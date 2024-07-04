package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex4.geo.GeoShapeable;
import Ex4.geo.Point2D;
import Ex4.geo.Rect2D;
import Ex4.geo.Segment2D;

class Rect2DTest {

	@Test
	void Rect2DTest1() {
		Point2D p1 = new Point2D(2,1);
		Point2D p2 = new Point2D(3,4);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.getPoint1().x(), 2);
		assertEquals(rect.getPoint1().y(), 1);
		assertEquals(rect.getPoint2().x(), 3);
		assertEquals(rect.getPoint2().y(), 4);
	}
	
	@Test
	void toStringTest() {
		Point2D p1 = new Point2D(2,1);
		Point2D p2 = new Point2D(3,4);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.toString(), "2.0,1.0,3.0,1.0,3.0,4.0,2.0,4.0");
	}
	
	@Test
	void Rect2DTest2() {
		Point2D p1 = new Point2D(3,4);
		Point2D p2 = new Point2D(2,1);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.getPoint1().x(), 2);
		assertEquals(rect.getPoint1().y(), 1);
		assertEquals(rect.getPoint2().x(), 3);
		assertEquals(rect.getPoint2().y(), 4);
	}
	
	@Test
	void containsTest() {
		Point2D p1 = new Point2D(10,5);
		Point2D p2 = new Point2D(3,14);
		Rect2D rect = new Rect2D(p1, p2);
		Point2D p3 = new Point2D(7,10);
		assertTrue(rect.contains(p3));
		Point2D p4 = new Point2D(11,7);
		assertFalse(rect.contains(p4));
	}
	
	@Test
	void areaTest1() {
		Point2D p1 = new Point2D(3,4);
		Point2D p2 = new Point2D(2,1);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.area(), 3);
	}
	
	@Test
	void areaTest2() {
		Point2D p1 = new Point2D(10,5);
		Point2D p2 = new Point2D(3,14);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.area(), 63);
	}
	
	@Test
	void areaTest3() {
		Point2D p1 = new Point2D(10,5);
		Point2D p2 = new Point2D(10,14);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.area(), 0);
	}
	
	@Test
	void areaTest4() {
		Point2D p1 = new Point2D(13,5);
		Point2D p2 = new Point2D(10,5);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.area(), 0);
	}
	
	@Test
	void perimeterTest1() {
		Point2D p1 = new Point2D(3,4);
		Point2D p2 = new Point2D(2,1);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.perimeter(), 8);
	}
	
	@Test
	void perimeterTest2() {
		Point2D p1 = new Point2D(10,5);
		Point2D p2 = new Point2D(3,14);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.perimeter(), 32);
	}
	
	@Test
	void perimeterTest3() {
		Point2D p1 = new Point2D(10,5);
		Point2D p2 = new Point2D(10,14);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.perimeter(), 18);
	}
	
	@Test
	void perimeterTest4() {
		Point2D p1 = new Point2D(13,5);
		Point2D p2 = new Point2D(10,5);
		Rect2D rect = new Rect2D(p1, p2);
		assertEquals(rect.perimeter(), 6);
	}
	
	@Test
	void moveTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(2,2);
		Rect2D rect = new Rect2D(p1, p2);
		Point2D p3 = new Point2D(4,4);
		rect.move(p3);
		assertEquals(rect.getPoint1().x(), 4);
		assertEquals(rect.getPoint1().y(), 4);
		assertEquals(rect.getPoint2().x(), 6);
		assertEquals(rect.getPoint2().y(), 6);
	}
	
	@Test
	void copyTest() {
		Point2D p1 = new Point2D(0,1);
		Point2D p2 = new Point2D(3,2);
		Rect2D rect = new Rect2D(p1, p2);
		GeoShapeable rect1 = rect.copy();
		assertEquals(((Rect2D) rect1).getPoint1().x(), 0);
		assertEquals(((Rect2D) rect1).getPoint1().y(), 1);
		assertEquals(((Rect2D) rect1).getPoint2().x(), 3);
		assertEquals(((Rect2D) rect1).getPoint2().y(), 2);
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
