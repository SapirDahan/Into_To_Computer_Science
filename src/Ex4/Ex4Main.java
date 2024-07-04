package Ex4;
import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;

import Ex4.geo.Circle2D;
import Ex4.geo.Point2D;
import Ex4.geo.Polygon2D;
import Ex4.geo.Rect2D;
import Ex4.gui.Ex4;
import Ex4.GUIShape;
import Ex4.GUI_Shapeable;

/*
 * @author Sapir Dahan ID: 325732972
 *
 */

/**
 * A very simple main class for basic code for Ex4
 * 
 * t2: output:
 * GUIShape,0,true,1,Circle2D,3.0,4.0, 2.0
 * GUIShape,255,false,2,Circle2D,6.0,8.0, 4.0
 *
 */
public class Ex4Main {

	public static void main(String[] args) {
		//t1();
		//t2();
		//t3(); 
		t4(); // Show Hello, World
	}
	
	// Minimal empty frame (no shapes)
	public static void t1() {
		Ex4 ex4 = Ex4.getInstance();
		ex4.show();
	} 
	
	// Two simple circles
	public static void t2() {
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
		ex4.show();
		System.out.print(ex4.getInfo());
	}
	
	// this function works only if a0 is on current working directory
	// Loads a file from file a0.txt.
	public static void t3() {
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable shapes = ex4.getShape_Collection();
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path file = Paths.get(currentPath.toString(), "a0");
		shapes.load(file.toString());
		ex4.init(shapes);
		ex4.show();
	}
	
	// Show hello, World
	public static void t4() {
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable shapes = ex4.getShape_Collection();
		
		Rect2D r1 = new Rect2D(new Point2D(0,0), new Point2D(10,10));
		GUI_Shapeable gr1 = new GUIShape(r1, true, Color.BLACK, 1);
		shapes.add(gr1);
		
		Circle2D c1 = new Circle2D(new Point2D(3.46875,5.359375), 0.39559340316163016);
		GUI_Shapeable gc1 = new GUIShape(c1, true, Color.GREEN, 2);
		shapes.add(gc1);
		
		Circle2D c2 = new Circle2D(new Point2D(6.359375,5.40625), 0.39559340316163016);
		GUI_Shapeable gc2 = new GUIShape(c2, true, Color.GREEN, 3);
		shapes.add(gc2);

		Point2D p1 = new Point2D(1.78125,5.328125);
		Point2D p2 = new Point2D(2.1875,5.328125);
		Point2D p3 = new Point2D(2.203125,5.5);
		Point2D p4 = new Point2D(2.109375,5.609375);
		Point2D p5 = new Point2D(1.96875,5.640625);
		Point2D p6 = new Point2D(1.78125,5.671875);
		Point2D p7 = new Point2D(1.6875,5.625);
		Point2D p8 = new Point2D(1.5625,5.546875);
		Point2D p9 = new Point2D(1.484375,5.40625);
		Point2D p10 = new Point2D(1.484375,5.265625);
		Point2D p11 = new Point2D(1.515625,5.140625);
		Point2D p12 = new Point2D(1.59375,5.0);
		Point2D p13 = new Point2D(1.71875,4.921875);
		Point2D p14 = new Point2D(1.890625,4.890625);
		Point2D p15 = new Point2D(2.03125,4.890625);
		Point2D p16 = new Point2D(2.125,4.953125);
		Point2D p17 = new Point2D(2.15625,5.0625);
		Point2D p18 = new Point2D(2.15625,5.140625);
		Point2D p19 = new Point2D(1.78125,5.171875);
		Point2D[] arr1 = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19};
		Polygon2D po1 = new Polygon2D(arr1);
		GUI_Shapeable gpo1 = new GUIShape(po1, true, Color.GREEN, 4);
		shapes.add(gpo1);
		
		p1 = new Point2D(4.90625,5.828125);
		p2 = new Point2D(5.171875,5.0);
		p3 = new Point2D(5.359375,5.359375);
		p4 = new Point2D(5.59375,5.046875);
		p5 = new Point2D(5.875,5.84375);
		p6 = new Point2D(5.671875,5.859375);
		p7 = new Point2D(5.5625,5.4375);
		p8 = new Point2D(5.375,5.65625);
		p9 = new Point2D(5.21875,5.390625);
		p10 = new Point2D(5.09375,5.875);
		Point2D[] arr2 = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10};
		Polygon2D po2 = new Polygon2D(arr2);
		GUI_Shapeable gpo2 = new GUIShape(po2, true, Color.GREEN, 5);
		shapes.add(gpo2);
		
		p1 = new Point2D(8.34375,5.671875);
		p2 = new Point2D(8.28125,5.703125);
		p3 = new Point2D(8.171875,5.703125);
		p4 = new Point2D(8.078125,5.640625);
		p5 = new Point2D(8.03125,5.546875);
		p6 = new Point2D(8.03125,5.390625);
		p7 = new Point2D(8.0625,5.28125);
		p8 = new Point2D(8.140625,5.1875);
		p9 = new Point2D(8.28125,5.171875);
		p10 = new Point2D(8.375,5.21875);
		p11 = new Point2D(8.421875,5.296875);
		p12 = new Point2D(8.4375,5.34375);
		p13 = new Point2D(8.421875,5.234375);
		p14 = new Point2D(8.59375,5.234375);
		p15 = new Point2D(8.609375,6.171875);
		p16 = new Point2D(8.5,6.1875);
		p17 = new Point2D(8.421875,5.625);
		Point2D[] arr3 = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17};
		Polygon2D po3 = new Polygon2D(arr3);
		GUI_Shapeable gpo3 = new GUIShape(po3, true, Color.GREEN, 6);
		shapes.add(gpo3);
		
		p1 = new Point2D(7.609375,6.140625);
		p2 = new Point2D(7.640625,5.171875);
		p3 = new Point2D(7.8125,5.171875);
		p4 = new Point2D(7.765625,6.15625);
		Point2D[] arr4 = {p1,p2,p3,p4};
		Polygon2D po4 = new Polygon2D(arr4);
		GUI_Shapeable gpo4 = new GUIShape(po4, true, Color.GREEN, 7);
		shapes.add(gpo4);
		
		Circle2D c3 = new Circle2D(new Point2D(3.453125,5.3525), 0.22371595411369327);
		GUI_Shapeable gc3 = new GUIShape(c3, true, Color.BLACK, 8);
		shapes.add(gc3);
		
		p1 = new Point2D(2.765625,5.84375);
		p2 = new Point2D(2.78125,4.9375);
		p3 = new Point2D(2.9375,4.9375);
		p4 = new Point2D(2.890625,5.84375);
		Point2D[] arr5 = {p1,p2,p3,p4};
		Polygon2D po5 = new Polygon2D(arr5);
		GUI_Shapeable gpo5 = new GUIShape(po5, true, Color.GREEN, 9);
		shapes.add(gpo5);
		
		p1 = new Point2D(2.484375,5.875);
		p2 = new Point2D(2.484375,4.9375);
		p3 = new Point2D(2.609375,4.9375);
		p4 = new Point2D(2.609375,5.875);
		Point2D[] arr6 = {p1,p2,p3,p4};
		Polygon2D po6 = new Polygon2D(arr6);
		GUI_Shapeable gpo6 = new GUIShape(po6, true, Color.GREEN, 10);
		shapes.add(gpo6);
		
		p1 = new Point2D(7.0,5.578125);
		p2 = new Point2D(7.015625,5.109375);
		p3 = new Point2D(7.125,5.109375);
		p4 = new Point2D(7.109375,5.40625);
		p5 = new Point2D(7.15625,5.453125);
		p6 = new Point2D(7.265625,5.484375);
		p7 = new Point2D(7.296875,5.515625);
		p8 = new Point2D(7.359375,5.53125);
		p9 = new Point2D(7.390625,5.53125);
		p10 = new Point2D(7.390625,5.65625);
		p11 = new Point2D(7.3125,5.671875);
		p12 = new Point2D(7.25,5.640625);
		p13 = new Point2D(7.15625,5.5625);
		p14 = new Point2D(7.140625,5.75);
		p15 = new Point2D(7.015625,5.78125);
		Point2D[] arr7 = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15};
		Polygon2D po7 = new Polygon2D(arr7);
		GUI_Shapeable gpo7 = new GUIShape(po7, true, Color.GREEN, 11);
		shapes.add(gpo7);
		
		Circle2D c4 = new Circle2D(new Point2D(3.46875,5.328125), 0.1875);
		GUI_Shapeable gc4 = new GUIShape(c4, true, Color.GREEN, 12);
		shapes.add(gc4);
		
		Circle2D c5 = new Circle2D(new Point2D(6.359375,5.375), 0.1875);
		GUI_Shapeable gc5 = new GUIShape(c5, true, Color.BLACK, 13);
		shapes.add(gc5);
		
		p1 = new Point2D(1.796875,5.140625);
		p2 = new Point2D(1.828125,5.078125);
		p3 = new Point2D(1.9375,5.03125);
		p4 = new Point2D(2.03125,5.0625);
		p5 = new Point2D(2.0,5.203125);
		p6 = new Point2D(1.796875,5.265625);
		Point2D[] arr8 = {p1,p2,p3,p4,p5,p6};
		Polygon2D po8 = new Polygon2D(arr8);
		GUI_Shapeable gpo8 = new GUIShape(po8, true, Color.BLACK, 14);
		shapes.add(gpo8);
		
		Circle2D c6 = new Circle2D(new Point2D(8.265625,5.4375), 0.09375);
		GUI_Shapeable gc6 = new GUIShape(c6, true, Color.BLACK, 15);
		shapes.add(gc6);
		
		p1 = new Point2D(1.765625,5.4375);
		p2 = new Point2D(2.0625,5.4375);
		p3 = new Point2D(1.984375,5.55);
		p4 = new Point2D(1.859375,5.555625);
		p5 = new Point2D(1.796875,5.53125);
		Point2D[] arr9 = {p1,p2,p3,p4,p5};
		Polygon2D po9 = new Polygon2D(arr9);
		GUI_Shapeable gpo9 = new GUIShape(po9, true, Color.BLACK, 16);
		shapes.add(gpo9);
		
		p1 = new Point2D(4.140625,5.203125);
		p2 = new Point2D(4.296875,5.203125);
		p3 = new Point2D(4.296875,5.046875);
		p4 = new Point2D(4.25,4.953125);
		p5 = new Point2D(4.15625,4.859375);
		p6 = new Point2D(4.015625,4.796875);
		p7 = new Point2D(3.9375,4.765625);
		p8 = new Point2D(3.984375,4.859375);
		p9 = new Point2D(4.109375,4.953125);
		p10 = new Point2D(4.140625,5.046875);
		Point2D[] arr10 = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10};
		Polygon2D po10 = new Polygon2D(arr10);
		GUI_Shapeable gpo10 = new GUIShape(po10, true, Color.GREEN, 17);
		shapes.add(gpo10);
		
		p1 = new Point2D(0.65625,5.78125);
		p2 = new Point2D(0.828125,5.78125);
		p3 = new Point2D(0.828125,5.40625);
		p4 = new Point2D(1.015625,5.40625);
		p5 = new Point2D(1.015625,5.765625);
		p6 = new Point2D(1.171875,5.765625);
		p7 = new Point2D(1.171875,4.875);
		p8 = new Point2D(1.0,4.890625);
		p9 = new Point2D(0.984375,5.1875);
		p10 = new Point2D(0.828125,5.1875);
		p11 = new Point2D(0.8125,4.890625);
		p12 = new Point2D(0.640625,4.875);
		Point2D[] arr11 = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12};
		Polygon2D po11 = new Polygon2D(arr11);
		GUI_Shapeable gpo11 = new GUIShape(po11, true, Color.GREEN, 18);
		shapes.add(gpo11);
		
		Circle2D c7 = new Circle2D(new Point2D(3.453125,5.3125), 0.21875);
		GUI_Shapeable gc7 = new GUIShape(c7, true, Color.BLACK, 15);
		shapes.add(gc7);
		
		Rect2D r2 = new Rect2D(new Point2D(8.96875,5.32375), new Point2D(9.4375,5.195625));
		GUI_Shapeable gr2 = new GUIShape(r2, true, Color.GREEN, 16);
		shapes.add(gr2);

		ex4.init(shapes);
		ex4.show();
	}
}
