package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex4.geo.Point2D;
import Ex4.geo.Circle2D;


class CircleTest {

	@Test
	void scaleTest() {
		Point2D cen = new Point2D(0,0);
		double radius = 2;
		Point2D p = new Point2D(0,0);
		Circle2D c = new Circle2D(cen, radius);
		c.scale(p, 0.5);
		assertEquals(c.getCenter().x(), 0);
		assertEquals(c.getCenter().y(), 0);
		assertEquals(c.getRadius(), 1);
		c.scale(p, 4);
		assertEquals(c.getCenter().x(), 0);
		assertEquals(c.getCenter().y(), 0);
		assertEquals(c.getRadius(), 4);
	}
	
	@Test
	void rotateTest() {
		Point2D cen = new Point2D(1,0);
		double radius = 2;
		Point2D p = new Point2D(0,0);
		Circle2D c = new Circle2D(cen, radius);
		c.rotate(p, 90);
		assertEquals(c.getCenter().x(), 0);
		assertEquals(c.getCenter().y(), 1);
	}

}
