package Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Ex4.ShapeCollection;
import Ex4.ShapeCollectionable;
import Ex4.geo.Circle2D;
import Ex4.geo.Point2D;
import Ex4.geo.Polygon2D;
import Ex4.geo.Rect2D;
import Ex4.geo.Triangle2D;
import Ex4.GUIShape;
import Ex4.GUI_Shapeable;
import Ex4.gui.Ex4;


class Ex4Test {
	
	@Test
	void getShape_CollectionTest() {
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable shapes = ex4.getShape_Collection();
		Point2D p1 = new Point2D(3,4);
		Point2D p2 = new Point2D(6,8);
		Circle2D c1 = new Circle2D(p1,2);
		Circle2D c2 = new Circle2D(p2,4);
		GUI_Shapeable gs1 = new GUIShape(c1, true, Color.black, 1);
		GUI_Shapeable gs2 = new GUIShape(c2, false, Color.blue, 2);
		shapes.add(gs1);
		shapes.add(gs2);
		assertEquals(ex4.getShape_Collection().get(0).getTag(), 1);
		assertEquals(ex4.getShape_Collection().get(1).getTag(), 2);
	}

	@Test
	void getInfoTest() {
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable shapes = ex4.getShape_Collection();
		Point2D p1 = new Point2D(3,4);
		Point2D p2 = new Point2D(6,8);
		Circle2D c1 = new Circle2D(p1,2);
		Circle2D c2 = new Circle2D(p2,4);
		GUI_Shapeable gs1 = new GUIShape(c1, true, Color.black, 1);
		GUI_Shapeable gs2 = new GUIShape(c2, false, Color.blue, 2);
		String str = "GUIShape,0,true,1,Circle2D,3.0,4.0,2.0\nGUIShape,255,false,2,Circle2D,6.0,8.0,4.0\n";
		shapes.add(gs1);
		shapes.add(gs2);
		assertEquals(str,ex4.getInfo());
	}

}
