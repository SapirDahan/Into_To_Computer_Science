package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex4.geo.Point2D;

class Point2DTest {
	
	@Test
	void scaleTest1() {
		Point2D p1 = new Point2D(3,4);
		Point2D p2 = new Point2D(2,3);
		p1.scale(p2, 3);
		assertEquals(p1.x(), 5.0);
		assertEquals(p1.y(), 6.0);
	}
	
	@Test
	void scaleTest2() {
		Point2D p1 = new Point2D(17,-39);
		Point2D p2 = new Point2D(4.56,-70.8);
		p1.scale(p2, 1);
		assertEquals(p1.x(), 17);
		assertEquals(p1.y(), -39);
		p1.scale(p2, 0);
		assertEquals(p1.x(), p2.x());
		assertEquals(p1.y(), p2.y());
	}
	
	@Test
	void scaleTest3() {
		Point2D p1 = new Point2D(3,7);
		Point2D p2 = new Point2D(3,89);
		p1.scale(p2, 4.5);
		assertEquals(p1.x(), p2.x());
	}
	
	@Test
	void scaleTest4() {
		Point2D p1 = new Point2D(8,7);
		Point2D p2 = new Point2D(3,7);
		p1.scale(p2, 4.5);
		assertEquals(p1.y(), p2.y());
	}

	@Test
	void rotateTest1() {
		Point2D p1 = new Point2D(1,0);
		Point2D p2 = new Point2D(0,0);
		double degree = 180;
		p1.rotate(p2, degree);
		assertEquals(p1.x(), -1);
		assertEquals(p1.y(), 0);
		p1 = new Point2D(1,0);
		p1.rotate(p2, (degree/4));
		assertEquals(p1.x(), 0.7071);
		assertEquals(p1.y(), 0.7071);
	}
	
	@Test
	void rotateTest2() {
		Point2D p1 = new Point2D(-5,6);
		Point2D p2 = new Point2D(4,-19);
		double degree = 0;
		p1.rotate(p2, degree);
		assertEquals(p1.x(), -5.0);
		assertEquals(p1.y(), 6.0);
		degree = 360;
		p1.rotate(p2, degree);
		assertEquals(p1.x(), -5.0);
		assertEquals(p1.y(), 6.0);
	}
	
	@Test
	void rotateTest3() {
		Point2D p1 = new Point2D(3,3);
		Point2D p2 = new Point2D(2,3);
		double degree = 0;
		p1.rotate(p2, degree);
		assertEquals(p1.x(), 3);
		assertEquals(p1.y(), 3);
		degree = 90;
		p1.rotate(p2, degree);
		assertEquals(p1.x(), 2);
		assertEquals(p1.y(), 4);
		p1.rotate(p2, degree);
		assertEquals(p1.x(), 1);
		assertEquals(p1.y(), 3);
		p1.rotate(p2, degree);
		assertEquals(p1.x(), 2);
		assertEquals(p1.y(), 2);
		p1.rotate(p2, degree);
		assertEquals(p1.x(), 3);
		assertEquals(p1.y(), 3);
		degree = 30;
		p1.rotate(p2, degree);
		assertEquals(p1.y(), 3.5);
		p1.rotate(p2, degree);
		assertEquals(p1.x(), 2.5);
	}
	
	
}
