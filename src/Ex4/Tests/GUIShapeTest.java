package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Ex4.geo.Circle2D;
import Ex4.geo.Point2D;
import Ex4.geo.Polygon2D;
import Ex4.geo.Rect2D;
import Ex4.geo.Triangle2D;
import Ex4.gui.Ex4;
import Ex4.GUIShape;
import Ex4.GUI_Shapeable;
import Ex4.ShapeCollectionable;

class GUIShapeTest {
	
	@Test 
	void initTest1() {
		String[] arr = {"GUIShape","0","true","1","Circle2D","3.0","4.0","2.0"};
		GUIShape g = new GUIShape();
		g.init(arr);
		assertEquals(g.getTag(), 1);
		assertTrue(g.getShape() instanceof Circle2D);
		assertEquals(g.getColor().getRGB() - Color.black.getRGB(), 0);
		assertEquals(g.getShape().getPoints()[0].x(), 3.0);
		assertEquals(g.getShape().getPoints()[0].y(), 4.0);
	}
	
	@Test 
	void initTest2() {
		String[] arr = {"GUIShape","0","true","2","Rect2D","3.0","4.0","2.0","7.0","6.0","5.0","11.0","10.0"};
		GUIShape g = new GUIShape();
		g.init(arr);
		assertEquals(g.getTag(), 2);
		assertTrue(g.getShape() instanceof Polygon2D);
		assertEquals(g.getColor().getRGB() - Color.black.getRGB(), 0);
		assertEquals(g.getShape().getPoints()[0].x(), 3.0);
		assertEquals(g.getShape().getPoints()[0].y(), 4.0);
	}
	
	@Test
	void toStringTest() {
		Point2D p1 = new Point2D(3,4);
		Circle2D c1 = new Circle2D(p1,2);
		GUI_Shapeable gs1 = new GUIShape(c1, true, Color.black, 1);
		assertEquals(gs1.toString(), "GUIShape,0,true,1,Circle2D,3.0,4.0,2.0");
	}

	@Test
	void setShapeTest() {
		Point2D p1 = new Point2D(5,2);
		Point2D p2 = new Point2D(0,1);
		Point2D p3 = new Point2D(-7,8);
		Triangle2D triangle = new Triangle2D(p1, p2, p3);
		GUIShape shape = new GUIShape(triangle, false, Color.orange, 9);
		
		Point2D p4 = new Point2D(0,1);
		Point2D p5 = new Point2D(3,2);
		Rect2D rect = new Rect2D(p4, p5);
		
		shape.setShape(rect);
		
		assertTrue(shape.getShape() instanceof Rect2D);
	}

}
