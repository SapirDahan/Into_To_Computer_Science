package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex4.geo.GeoShapeable;
import Ex4.geo.Point2D;
import Ex4.geo.Polygon2D;
import Ex4.geo.Segment2D;
import Ex4.geo.Triangle2D;

class Polygon2DTest {
	
	@Test
	void Polygon2DTest1() {
		Point2D p1 = new Point2D(8,2);
		Point2D p2 = new Point2D(3,7);
		Point2D[] arr = {p1, p2};
		Polygon2D pl = new Polygon2D(arr);
		assertEquals(p1.x(), pl.getPoints()[0].x(), 8);
		assertEquals(p2.x(), pl.getPoints()[0].y(), 2);
		assertEquals(p1.y(), pl.getPoints()[1].x(), 3);
		assertEquals(p2.y(), pl.getPoints()[1].y(), 7);
	}
	
	@Test
	void toStringTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(5,0);
		Point2D p3 = new Point2D(8,2);
		Point2D p4 = new Point2D(3,2);
		Point2D[] arr = {p1, p2, p3, p4};
		Polygon2D pl = new Polygon2D(arr);
		assertEquals(pl.toString(), "0.0,0.0,5.0,0.0,8.0,2.0,3.0,2.0");
	}
	
	@Test
	void containsTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(3,0);
		Point2D p3 = new Point2D(0,4);
		Point2D[] arr = {p1, p2, p3};
		Polygon2D pl = new Polygon2D(arr);
		Point2D p4 = new Point2D(1,2);
		assertTrue(pl.contains(p4));
	}
	
	@Test
	void areaTest1() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(3,0);
		Point2D p3 = new Point2D(0,4);
		Point2D[] arr = {p1, p2, p3};
		Polygon2D pl = new Polygon2D(arr);
		Triangle2D t = new Triangle2D(p1, p2, p3);
		assertEquals(pl.area(), t.area());
	}
	
	@Test
	void areaTest2() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(5,0);
		Point2D p3 = new Point2D(8,2);
		Point2D p4 = new Point2D(3,2);
		Point2D[] arr = {p1, p2, p3, p4};
		Polygon2D pl = new Polygon2D(arr);
		assertEquals(pl.area(), 10);
	}
	
	@Test
	void perimeterTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(3,0);
		Point2D p3 = new Point2D(0,4);
		Point2D[] arr = {p1, p2, p3};
		Polygon2D pl = new Polygon2D(arr);
		assertEquals(pl.perimeter(), 12);
	}
	
	@Test
	void moveTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(4,4);
		Point2D[] arr = {p1, p2, p3};
		Polygon2D pl = new Polygon2D(arr);
		Point2D p4 = new Point2D(4,4);
		pl.move(p4);
		assertEquals(pl.getPoints()[0].x(), 4);
		assertEquals(pl.getPoints()[0].y(), 4);
		assertEquals(pl.getPoints()[1].x(), 6);
		assertEquals(pl.getPoints()[1].y(), 6);
		assertEquals(pl.getPoints()[2].x(), 8);
		assertEquals(pl.getPoints()[2].y(), 8);
	}

	@Test
	void copyTest() {
		Point2D p1 = new Point2D(0,1);
		Point2D p2 = new Point2D(3,2);
		Point2D p3 = new Point2D(4,7);
		Point2D p4 = new Point2D(2,1);
		Point2D p5 = new Point2D(8,5);
		Point2D p6 = new Point2D(4,3);
		Point2D[] arr = {p1, p2, p3, p4, p5, p6};
		Polygon2D p = new Polygon2D(arr);
		GeoShapeable p0 = p.copy();
		assertEquals(((Polygon2D) p0).getPoints()[0].x(), 0);
		assertEquals(((Polygon2D) p0).getPoints()[0].y(), 1);
		assertEquals(((Polygon2D) p0).getPoints()[1].x(), 3);
		assertEquals(((Polygon2D) p0).getPoints()[1].y(), 2);
		assertEquals(((Polygon2D) p0).getPoints()[2].x(), 4);
		assertEquals(((Polygon2D) p0).getPoints()[2].y(), 7);
		assertEquals(((Polygon2D) p0).getPoints()[3].x(), 2);
		assertEquals(((Polygon2D) p0).getPoints()[3].y(), 1);
		assertEquals(((Polygon2D) p0).getPoints()[4].x(), 8);
		assertEquals(((Polygon2D) p0).getPoints()[4].y(), 5);
		assertEquals(((Polygon2D) p0).getPoints()[5].x(), 4);
		assertEquals(((Polygon2D) p0).getPoints()[5].y(), 3);
	}
	
	@Test
	void scaleTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Point2D[] arr = {p1, p2, p3};
		Polygon2D pl = new Polygon2D(arr);
		Point2D cen = new Point2D(0,0);
		pl.scale(cen, 0.5);
		assertEquals(pl.getPoints()[0].x(), 2);
		assertEquals(pl.getPoints()[0].y(), 2);
		assertEquals(pl.getPoints()[1].x(), 1);
		assertEquals(pl.getPoints()[1].y(), 1);
		assertEquals(pl.getPoints()[2].x(), 3);
		assertEquals(pl.getPoints()[2].y(), 3);
		pl.scale(cen, 4);
		assertEquals(pl.getPoints()[0].x(), 8);
		assertEquals(pl.getPoints()[0].y(), 8);
		assertEquals(pl.getPoints()[1].x(), 4);
		assertEquals(pl.getPoints()[1].y(), 4);
		assertEquals(pl.getPoints()[2].x(), 12);
		assertEquals(pl.getPoints()[2].y(), 12);
	}
	
	@Test
	void rotate() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Point2D[] arr = {p1, p2, p3};
		Polygon2D pl = new Polygon2D(arr);
		Point2D cen = new Point2D(0,0);
		pl.rotate(cen, 180);
		assertEquals(pl.getPoints()[0].x(), -4);
		assertEquals(pl.getPoints()[0].y(), -4);
		assertEquals(pl.getPoints()[1].x(), -2);
		assertEquals(pl.getPoints()[1].y(), -2);
		assertEquals(pl.getPoints()[2].x(), -6);
		assertEquals(pl.getPoints()[2].y(), -6);
		
		pl.rotate(cen, 180);
		assertEquals(pl.getPoints()[0].x(), 4);
		assertEquals(pl.getPoints()[0].y(), 4);
		assertEquals(pl.getPoints()[1].x(), 2);
		assertEquals(pl.getPoints()[1].y(), 2);
		assertEquals(pl.getPoints()[2].x(), 6);
		assertEquals(pl.getPoints()[2].y(), 6);
	}
	
	@Test
	void getPointsTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Point2D[] points = {p1, p2, p3};
		Polygon2D pl = new Polygon2D(points);
		Point2D[] arr = pl.getPoints();
		assertEquals(arr[0].x(), 4);
		assertEquals(arr[0].y(), 4);
		assertEquals(arr[1].x(), 2);
		assertEquals(arr[1].y(), 2);
		assertEquals(arr[2].x(), 6);
		assertEquals(arr[2].y(), 6);
	}

}
