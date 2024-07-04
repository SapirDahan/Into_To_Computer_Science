package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import Ex4.Ex4_Const;
import Ex4.ShapeCollection;
import Ex4.ShapeCollectionable;
import Ex4.geo.Circle2D;
import Ex4.geo.Point2D;
import Ex4.geo.Polygon2D;
import Ex4.geo.Rect2D;
import Ex4.geo.Triangle2D;
import Ex4.GUIShape;
import Ex4.GUI_Shapeable;
import Ex4.geo.ShapeComp;

class ShapeCollectionTest {
	
	@Test
	void ShapeCollectionTest1() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D triangle1 = new Triangle2D(p1, p2, p3);
		GUIShape Triangle1 = new GUIShape(triangle1, true, Color.pink, 40);
		
		Point2D p4 = new Point2D(5,2);
		Point2D p5 = new Point2D(0,1);
		Point2D p6 = new Point2D(-7,8);
		Triangle2D triangle2 = new Triangle2D(p4, p5, p6);
		GUIShape Triangle2 = new GUIShape(triangle2, false, Color.orange, 9);

		
		Point2D p7 = new Point2D(0,1);
		Point2D p8 = new Point2D(3,2);
		Rect2D rect1 = new Rect2D(p7, p8);
		GUIShape Rect1 = new GUIShape(rect1, true, Color.black, -5);
		
		Point2D p9 = new Point2D(-3,1);
		Point2D p10 = new Point2D(3,2);
		Rect2D rect2 = new Rect2D(p7, p8);
		GUIShape Rect2 = new GUIShape(rect2, true, Color.white, 7);

		Point2D p11 = new Point2D(0,1);
		Point2D p12 = new Point2D(3,2);
		Point2D p13 = new Point2D(4,7);
		Point2D p14 = new Point2D(2,1);
		Point2D p15 = new Point2D(8,5);
		Point2D p16 = new Point2D(4,3);
		Point2D[] array = {p11, p12, p13, p14, p15, p16};
		Polygon2D poly = new Polygon2D(array);
		GUIShape Poly = new GUIShape(poly, true, Color.green, 13);
		
        ArrayList<GUI_Shapeable> arr = new ArrayList<>();
        arr.add(Rect2);
        arr.add(Triangle1);
        arr.add(Poly);
        arr.add(Triangle2);
        arr.add(Rect1);
        
        ShapeCollection col = new ShapeCollection(arr);
        
        assertEquals(col.get(0).getTag(), 7);
        assertEquals(col.get(1).getTag(), 40);
        assertEquals(col.get(2).getTag(), 13);
        assertEquals(col.get(3).getTag(), 9);
        assertEquals(col.get(4).getTag(), -5);

	}

	@Test
	void removeElementAtTest() {
        Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D triangle1 = new Triangle2D(p1, p2, p3);
		GUIShape Triangle1 = new GUIShape(triangle1, true, Color.pink, 40);
		
		Point2D p4 = new Point2D(5,2);
		Point2D p5 = new Point2D(0,1);
		Point2D p6 = new Point2D(-7,8);
		Triangle2D triangle2 = new Triangle2D(p4, p5, p6);
		GUIShape Triangle2 = new GUIShape(triangle2, false, Color.orange, 9);

		
		Point2D p7 = new Point2D(0,1);
		Point2D p8 = new Point2D(3,2);
		Rect2D rect1 = new Rect2D(p7, p8);
		GUIShape Rect1 = new GUIShape(rect1, true, Color.black, -5);
		
		Point2D p9 = new Point2D(-3,1);
		Point2D p10 = new Point2D(3,2);
		Rect2D rect2 = new Rect2D(p7, p8);
		GUIShape Rect2 = new GUIShape(rect2, true, Color.white, 7);

		Point2D p11 = new Point2D(0,1);
		Point2D p12 = new Point2D(3,2);
		Point2D p13 = new Point2D(4,7);
		Point2D p14 = new Point2D(2,1);
		Point2D p15 = new Point2D(8,5);
		Point2D p16 = new Point2D(4,3);
		Point2D[] array = {p11, p12, p13, p14, p15, p16};
		Polygon2D poly = new Polygon2D(array);
		GUIShape Poly = new GUIShape(poly, true, Color.green, 13);
		
        ArrayList<GUI_Shapeable> arr = new ArrayList<>();
        arr.add(Rect2);
        arr.add(Triangle1);
        arr.add(Poly);
        arr.add(Triangle2);
        arr.add(Rect1);
        
        ShapeCollection col = new ShapeCollection(arr);
        
        col.removeElementAt(0);
        assertNotEquals(col.get(0).getTag(), 7);
        assertEquals(col.get(0).getTag(), 40);
        assertEquals(col.size(), 4);
        
        col.removeElementAt(3);
        assertEquals(col.size(), 3);
        assertEquals(col.get(2).getTag(), 9);
	}
	
	@Test
	void AddAtTest() {
        Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D triangle1 = new Triangle2D(p1, p2, p3);
		GUIShape Triangle1 = new GUIShape(triangle1, true, Color.pink, 40);
		
		Point2D p4 = new Point2D(5,2);
		Point2D p5 = new Point2D(0,1);
		Point2D p6 = new Point2D(-7,8);
		Triangle2D triangle2 = new Triangle2D(p4, p5, p6);
		GUIShape Triangle2 = new GUIShape(triangle2, false, Color.orange, 9);

		
		Point2D p7 = new Point2D(0,1);
		Point2D p8 = new Point2D(3,2);
		Rect2D rect1 = new Rect2D(p7, p8);
		GUIShape Rect1 = new GUIShape(rect1, true, Color.black, -5);
		
		Point2D p9 = new Point2D(-3,1);
		Point2D p10 = new Point2D(3,2);
		Rect2D rect2 = new Rect2D(p7, p8);
		GUIShape Rect2 = new GUIShape(rect2, true, Color.white, 7);

		Point2D p11 = new Point2D(0,1);
		Point2D p12 = new Point2D(3,2);
		Point2D p13 = new Point2D(4,7);
		Point2D p14 = new Point2D(2,1);
		Point2D p15 = new Point2D(8,5);
		Point2D p16 = new Point2D(4,3);
		Point2D[] array = {p11, p12, p13, p14, p15, p16};
		Polygon2D poly = new Polygon2D(array);
		GUIShape Poly = new GUIShape(poly, true, Color.green, 13);
		
        ArrayList<GUI_Shapeable> arr = new ArrayList<>();
        arr.add(Rect2);
        arr.add(Triangle1);
        
        ShapeCollection col = new ShapeCollection(arr);
        
        col.addAt(Poly, 1);
        assertEquals(col.get(1).getTag(), 13);
        
        col.addAt(Triangle2, 3);
        assertEquals(col.get(3).getTag(), 9);

        col.addAt(Rect1, 0);
        assertEquals(col.get(0).getTag(), -5);
	}
	
	@Test
	void copyTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D triangle1 = new Triangle2D(p1, p2, p3);
		GUIShape Triangle1 = new GUIShape(triangle1, true, Color.pink, 40);
		
		Point2D p4 = new Point2D(5,2);
		Point2D p5 = new Point2D(0,1);
		Point2D p6 = new Point2D(-7,8);
		Triangle2D triangle2 = new Triangle2D(p4, p5, p6);
		GUIShape Triangle2 = new GUIShape(triangle2, false, Color.orange, 9);

		
		Point2D p7 = new Point2D(0,1);
		Point2D p8 = new Point2D(3,2);
		Rect2D rect1 = new Rect2D(p7, p8);
		GUIShape Rect1 = new GUIShape(rect1, true, Color.black, -5);
		
		Point2D p9 = new Point2D(-3,1);
		Point2D p10 = new Point2D(3,2);
		Rect2D rect2 = new Rect2D(p7, p8);
		GUIShape Rect2 = new GUIShape(rect2, true, Color.white, 7);

		Point2D p11 = new Point2D(0,1);
		Point2D p12 = new Point2D(3,2);
		Point2D p13 = new Point2D(4,7);
		Point2D p14 = new Point2D(2,1);
		Point2D p15 = new Point2D(8,5);
		Point2D p16 = new Point2D(4,3);
		Point2D[] array = {p11, p12, p13, p14, p15, p16};
		Polygon2D poly = new Polygon2D(array);
		GUIShape Poly = new GUIShape(poly, true, Color.green, 13);
		
        ArrayList<GUI_Shapeable> arr = new ArrayList<>();
        arr.add(Rect2);
        arr.add(Triangle1);
        arr.add(Poly);
        arr.add(Triangle2);
        arr.add(Rect1);
        
        ShapeCollection col0 = new ShapeCollection(arr);
        
        ShapeCollectionable col = col0.copy();
        
        assertEquals(col.get(0).getTag(), 7);
        assertEquals(col.get(1).getTag(), 40);
        assertEquals(col.get(2).getTag(), 13);
        assertEquals(col.get(3).getTag(), 9);
        assertEquals(col.get(4).getTag(), -5);
	}
	
	@Test
	void sortTest() {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(1,1);
		Triangle2D triangle1 = new Triangle2D(p1, p2, p3);
		GUIShape Triangle1 = new GUIShape(triangle1, true, Color.pink, 40);
		
		Point2D p4 = new Point2D(5,2);
		Point2D p5 = new Point2D(0,1);
		Point2D p6 = new Point2D(-7,8);
		Triangle2D triangle2 = new Triangle2D(p4, p5, p6);
		GUIShape Triangle2 = new GUIShape(triangle2, false, Color.orange, 9);

		
		Point2D p7 = new Point2D(10,7);
		Point2D p8 = new Point2D(3,2);
		Rect2D rect1 = new Rect2D(p7, p8);
		GUIShape Rect1 = new GUIShape(rect1, true, Color.black, -5);
		
		Point2D p9 = new Point2D(-3,1);
		Point2D p10 = new Point2D(3,2);
		Rect2D rect2 = new Rect2D(p9,p10);
		GUIShape Rect2 = new GUIShape(rect2, true, Color.white, 7);
		
		Point2D p11 = new Point2D(0,0);
		Circle2D circle = new Circle2D(p11, 3);
		GUIShape Circle = new GUIShape(circle, false, Color.yellow, 0);
		
        ArrayList<GUI_Shapeable> arr = new ArrayList<>();
        arr.add(Rect2);
        arr.add(Triangle1);
        arr.add(Triangle2);
        arr.add(Circle);
        arr.add(Rect1);
        
        ShapeCollection col = new ShapeCollection(arr);
        
        col.sort(ShapeComp.CompByArea);
        assertEquals(col.get(0).getTag(), 40);
        
        col.sort(ShapeComp.CompByAntiArea);
        assertEquals(col.get(4).getTag(), 40);
        
        col.sort(ShapeComp.CompByPerimeter);
        assertEquals(col.get(0).getTag(), 40);
        
        col.sort(ShapeComp.CompByAntiPerimeter);
        assertEquals(col.get(4).getTag(), 40);
        
        col.sort(ShapeComp.CompByTag);
        assertEquals(col.get(0).getTag(), -5);
        
        col.sort(ShapeComp.CompByAntiTag);
        assertEquals(col.get(4).getTag(), -5);
        
        col.sort(ShapeComp.CompByToString);
        assertEquals(col.get(0).getTag(), 0);
        
        col.sort(ShapeComp.CompByAntiToString);
        assertEquals(col.get(4).getTag(), 0);
	}
	
	@Test
	void removeAllTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D triangle1 = new Triangle2D(p1, p2, p3);
		GUIShape Triangle1 = new GUIShape(triangle1, true, Color.pink, 40);
		
		Point2D p4 = new Point2D(5,2);
		Point2D p5 = new Point2D(0,1);
		Point2D p6 = new Point2D(-7,8);
		Triangle2D triangle2 = new Triangle2D(p4, p5, p6);
		GUIShape Triangle2 = new GUIShape(triangle2, false, Color.orange, 9);

		
		Point2D p7 = new Point2D(0,1);
		Point2D p8 = new Point2D(3,2);
		Rect2D rect1 = new Rect2D(p7, p8);
		GUIShape Rect1 = new GUIShape(rect1, true, Color.black, -5);
		
		Point2D p9 = new Point2D(-3,1);
		Point2D p10 = new Point2D(3,2);
		Rect2D rect2 = new Rect2D(p9, p10);
		GUIShape Rect2 = new GUIShape(rect2, true, Color.white, 7);

		Point2D p11 = new Point2D(0,1);
		Point2D p12 = new Point2D(3,2);
		Point2D p13 = new Point2D(4,7);
		Point2D p14 = new Point2D(2,1);
		Point2D p15 = new Point2D(8,5);
		Point2D p16 = new Point2D(4,3);
		Point2D[] array = {p11, p12, p13, p14, p15, p16};
		Polygon2D poly = new Polygon2D(array);
		GUIShape Poly = new GUIShape(poly, true, Color.green, 13);
		
        ArrayList<GUI_Shapeable> arr = new ArrayList<>();
        arr.add(Rect2);
        arr.add(Triangle1);
        arr.add(Poly);
        arr.add(Triangle2);
        arr.add(Rect1);
        
        ShapeCollection col = new ShapeCollection(arr);
        
        col.removeAll();
      
        assertEquals(col.size(), 0);
	}
	
	@Test
	void getBoundingBoxTest() {
		Point2D p1 = new Point2D(4,4);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D triangle1 = new Triangle2D(p1, p2, p3);
		GUIShape Triangle1 = new GUIShape(triangle1, true, Color.pink, 40);
		
		Point2D p4 = new Point2D(5,2);
		Point2D p5 = new Point2D(0,1);
		Point2D p6 = new Point2D(-7,8);
		Triangle2D triangle2 = new Triangle2D(p4, p5, p6);
		GUIShape Triangle2 = new GUIShape(triangle2, false, Color.orange, 9);

		Point2D p7 = new Point2D(0,1);
		Point2D p8 = new Point2D(3,2);
		Rect2D rect1 = new Rect2D(p7, p8);
		GUIShape Rect1 = new GUIShape(rect1, true, Color.black, -5);
		
		Point2D p9 = new Point2D(-3,1);
		Point2D p10 = new Point2D(3,2);
		Rect2D rect2 = new Rect2D(p9, p10);
		GUIShape Rect2 = new GUIShape(rect2, true, Color.white, 7);

		Point2D p11 = new Point2D(0,1);
		Point2D p12 = new Point2D(3,2);
		Point2D p13 = new Point2D(4,7);
		Point2D p14 = new Point2D(2,1);
		Point2D p15 = new Point2D(8,5);
		Point2D p16 = new Point2D(4,3);
		Point2D[] array = {p11, p12, p13, p14, p15, p16};
		Polygon2D poly = new Polygon2D(array);
		GUIShape Poly = new GUIShape(poly, true, Color.green, 13);
		
		Point2D p17 = new Point2D(10,15);
		Circle2D c = new Circle2D(p17, 5);
		GUIShape C = new GUIShape(c, false, Color.BLACK, -8);

		
        ArrayList<GUI_Shapeable> arr = new ArrayList<>();
        arr.add(Rect2);
        arr.add(Triangle1);
        arr.add(Poly);
        arr.add(Triangle2);
        arr.add(Rect1);
        arr.add(C);
        
        ShapeCollection col = new ShapeCollection(arr);
        
        assertEquals(col.getBoundingBox().getPoints()[0].x(), -7);
        assertEquals(col.getBoundingBox().getPoints()[0].y(), 1);
        assertEquals(col.getBoundingBox().getPoints()[1].x(), 15);
        assertEquals(col.getBoundingBox().getPoints()[1].y(), 20);

	}

}
