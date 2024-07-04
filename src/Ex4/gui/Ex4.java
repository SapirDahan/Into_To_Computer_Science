package Ex4.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Ex4.Ex4_Const;
import Ex4.GUIShape;
import Ex4.GUI_Shapeable;
import Ex4.ShapeCollection;
import Ex4.ShapeCollectionable;
import Ex4.geo.Circle2D;
import Ex4.geo.GeoShapeable;
import Ex4.geo.Point2D;
import Ex4.geo.Polygon2D;
import Ex4.geo.Rect2D;
import Ex4.geo.Segment2D;
import Ex4.geo.ShapeComp;
import Ex4.geo.Triangle2D;

/*
 * @author Sapir Dahan ID: 325732972
 *
 * This project provides a GUI with a white board and allows drawing of different shapes 
 * on that board. The shapes are: circle, polygon, rectangle, segment and a triangle. Each shape 
 * has several properties: coordinates, scale, rotation, color and fill. The user can move the 
 * shapes around the white board, scale the shapes, fill and un-fill, rotate, copy, change color
 * and remove any shape. At any given time, the user can clear the white board. 
 * 
 * Each shape can be selected in various methods: point, all, anti and none. The user can also 
 * view the information about all the shapes.
 * 
 * At any given time, the user can sort the shapes layers. Each shape represents a distinct layer.
 * There are several methods for sorting the shapes: by area, by anti-area, by perimeter,
 * by anti-perimeter, by to-string, by anti-to-string, by tag and by anti-tag.
 * 
 * The user can save the drawing into a text file, and load it at any time. 
 * 
 * The shapes are being represented as objects. The shape objects are being implemented using 
 * GeoShapeable interface. This interface includes the following attributes:
 * - contains
 * - area
 * - perimeter
 * - move
 * - copy
 * - scale
 * - rotate
 * - getPoints
 * 
 * The rectangle shape has somewhat unique treatment. Since the rectangle has no rotation 
 * property, it cannot be saved as a rotated rectangle. Therefore, when the user rotates a
 * rectangle, it is automatically casted into a polygon. In a similar way, when a file is 
 * being loaded, and the rectangle in the file appears to be not parallel to the axes,
 * it is being automatically casted into a polygon as well.
 * 
 * The class ShapeComp represents a Comparator over the shape objects as a linear order.
 * 
 * The class GUI_Shape provides the shape objects with the following additional properties:
 * - fill
 * - tag
 * - color
 * - isSelected
 * 
 * The class ShapeCollection implements the interface ShapeCollectionable which saves the data 
 * structure that represents the shape collection.
 * 
 * The project also implements access to a file system. When the user selects save or load 
 * options from the menu, a pop-up window with the file explorer appears. The user can select
 * a file to load or save. In the case where a file does not exist, the code will create it
 * before saving the data into it.
 * 
 */

public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs = null;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1 = null;
	private  Point2D _p2 = null;
	private static  int tag = 0;
	private  ArrayList<Point2D> arrPoints = new ArrayList<Point2D>();

	private  static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";

	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}

	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();

		// Create Circle
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}

		// Create rectangle
		if(gs instanceof Rect2D) {
			Rect2D r = (Rect2D)gs;
			double cen_x = (r.getPoint1().x() + r.getPoint2().x())/2;
			double cen_y = (r.getPoint1().y() + r.getPoint2().y())/2;
			double half_width = Math.abs(r.getPoint1().x() - r.getPoint2().x())/2;
			double half_height = Math.abs(r.getPoint1().y() - r.getPoint2().y())/2;
			if(isFill) {
				StdDraw_Ex4.filledRectangle(cen_x, cen_y, half_width, half_height);
			}
			else { 
				StdDraw_Ex4.rectangle(cen_x, cen_y, half_width, half_height);
			}
		}

		// Create triangle
		if(gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D)gs;
			double[] x = {t.getPoint1().x(), t.getPoint2().x(), t.getPoint3().x()};
			double[] y = {t.getPoint1().y(), t.getPoint2().y(), t.getPoint3().y()};

			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else { 
				StdDraw_Ex4.polygon(x, y);
			}
		}

		// Create segment
		if(gs instanceof Segment2D) {
			Segment2D s = (Segment2D)gs;
			double[] x = {s.getPoint1().x(), s.getPoint2().x()};
			double[] y = {s.getPoint1().y(), s.getPoint2().y()};

			StdDraw_Ex4.polygon(x, y);
		}

		// Create Polygon
		if(gs instanceof Polygon2D) {
			Polygon2D poly = (Polygon2D)gs;

			double[] x = new double[poly.getPoints().length];
			double[] y = new double[poly.getPoints().length];

			for(int i = 0; i < poly.getPoints().length; i++) {
				x[i] = poly.getPoints()[i].x();
				y[i] = poly.getPoints()[i].y();
			}
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else { 
				StdDraw_Ex4.polygon(x, y);
			}
		}
	}

	// Set color
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	// Set fill
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	// Check the mode and call the functions accordingly
	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}

		if(p.equals("ByArea")) {_shapes.sort(ShapeComp.CompByArea);}
		if(p.equals("ByAntiArea")) {_shapes.sort(ShapeComp.CompByAntiArea);}
		if(p.equals("ByPerimeter")) {_shapes.sort(ShapeComp.CompByPerimeter);}
		if(p.equals("ByAntiPerimeter")) {_shapes.sort(ShapeComp.CompByAntiPerimeter);}
		if(p.equals("ByTag")) {_shapes.sort(ShapeComp.CompByTag);}
		if(p.equals("ByAntiTag")) {_shapes.sort(ShapeComp.CompByAntiTag);}
		if(p.equals("ByToString")) {_shapes.sort(ShapeComp.CompByToString);}
		if(p.equals("ByAntiToString")) {_shapes.sort(ShapeComp.CompByAntiToString);}

		if(p.equals("All")) {selectAll();}
		if(p.equals("Anti")) {selectAnti();}
		if(p.equals("None")) {selectNone();}

		if(p.equals("Remove")) {Remove();}
		if(p.equals("Info")) {System.out.println(getInfo());} // Print info
		if(p.equals("Save")) {saveDirectory();}
		if(p.equals("Load")) {openDirectory();}

		// Draw the shapes
		drawShapes();

	}

	// This function been called when there is a click on the mouse
	public void mouseClicked(Point2D p) {
		//System.out.println("Mode: "+_mode+"  "+p); // Print the mode plus the click

		// Mode is circle
		if(_mode.equals("Circle")) {

			// Get the first point
			if(_gs==null) {
				_p1 = new Point2D(p);
			}

			// When second click
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				tag++;
			}
		}

		// Mode is rectangle
		if(_mode.equals("Rect")) {

			// Get first click
			if(_gs==null) {
				_p1 = new Point2D(p);
			}

			// when second click
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				tag++;
			}
		}

		// Mode is triangle
		if(_mode.equals("Triangle")) {

			// Third click
			if(_p2 != null) {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;
				tag++;
			}

			// Second click
			else if(_p1 != null && _p2 == null) {
				_p2 = new Point2D(p);
			}

			// First click
			else if(_gs==null) {
				_p1 = new Point2D(p);
			}
		}

		// Mode is segment
		if(_mode.equals("Segment")) {

			// First click
			if(_gs==null) {
				_p1 = new Point2D(p);
			}

			// Second click
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				tag++;
			}
		}

		// Mode is polygon
		if(_mode.equals("Polygon")) {

			System.out.println("is _gs null: " + (_gs==null));

			// First click
			if(_gs==null) {
				_p1 = new Point2D(p);
				arrPoints.add(_p1);
				mouseMoved(null);
			}

			// Other click
			else {
				arrPoints.add(p);
				mouseMoved(null);
			}
		}

		// Mode is move
		if(_mode.equals("Move")) {

			// first click
			if(_p1==null) {
				_p1 = new Point2D(p);
			}

			// Second point
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}

		// Mode is copy
		if(_mode.equals("Copy")) {

			// First click
			if(_p1==null) {
				_p1 = new Point2D(p);
			}

			// Second point
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}

		// Mode is rotate
		if(_mode.equals("Rotate")) {

			// First point
			if(_p1==null) {
				_p1 = new Point2D(p);
			}

			// Second point
			else {
				rotate(_p1, p);
				_p1 = null;
			}
		}

		// Mode is point
		if(_mode.equals("Point")) {
			select(p);
		}

		// Mode is scale_90%
		if(_mode.equals("Scale_90%")) {
			scale(p, 0.9);			
		}

		// Mode is scale_110%
		if(_mode.equals("Scale_110%")) {
			scale(p, 1.1);			
		}

		// Draw the shapes
		drawShapes();
	}

	// Selected point
	private void select(Point2D p) {

		// Go throw shapes in the collection
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// If the shape contain the point then change select mode
			if(g != null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	// Select all the shapes at the collection
	private void selectAll() {

		// Go throw shapes in the collection
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// Set shape to selected
			if(g!=null) {
				s.setSelected(true);
			}
		}
	}

	// Anti-Select all the shapes at the collection
	private void selectAnti() {

		// Go throw shapes in the collection
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// Set shape to the opposite of there selected mode
			if(g!=null) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	// Select none of the shapes at the collection
	private void selectNone() {

		// Go throw shapes in the collection
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// Set shape to un-selected
			if(g!=null) {
				s.setSelected(false);
			}
		}
	}

	// Move shape
	private void move() {

		// Go throw shapes in the collection
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// If selected move the shape
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}

		_gs = null; // set _gs to null
	}

	// Copy selected shapes
	private void copy() {
		int size = _shapes.size(); // Size of the array of the shapes

		// Go throw shapes in the collection
		for(int i = 0; i < size;i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// If selected
			if(s.isSelected() && g!=null) {
				GUI_Shapeable s_copy = s.copy(); // Copy shape
				s_copy.setSelected(false); // Un-select the copied shape
				GeoShapeable g_copy = s_copy.getShape(); // Copy GeoShape
				g_copy.move(_p1); // Move the copied shape
				s_copy.setShape(g_copy);
				_shapes.add(s_copy); // Add the copied shape to shapes
			}
		}

		_gs = null; // set _gs to null
	}

	// remove selected shapes
	private void Remove() {

		// Go throw shapes in the collection
		for(int i=_shapes.size() - 1;i >= 0; i--) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// if selected then remove the shape
			if(s.isSelected() && g!=null) {
				_shapes.removeElementAt(i);
			}
		}

		_gs = null; // set _gs to null
	}

	// Scale selected shapes
	private void scale(Point2D p, double scale) {

		// Go throw shapes in the collection
		for(int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// if selected scale the shape
			if(s.isSelected() && g!=null) {
				g.scale(p, scale);
			}
		}

		_gs = null; // set _gs to null
	}

	// Right click
	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!"); // Print right click

		// If the mode is polygon then create it and add it to shapes
		if(_mode.equals("Polygon")) {
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			((GUIShape) _gs).removeLastPoint(); // Remove the right click
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
			tag++;
			arrPoints.removeAll(arrPoints);
			drawShapes(); // draw shapes
		}
	}

	// when the mouse moved
	public void mouseMoved(MouseEvent e) {

		// After one click start drawing
		if(_p1 != null) {

			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();

			// Temporarily shape
			GeoShapeable gs = null;

			// The mouse is on the point
			Point2D p = new Point2D(x1,y1);

			// Mode is circle
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}

			// Mode is rectangle
			if(_mode.equals("Rect")) {
				gs = new Rect2D(p,_p1);
			}

			// Mode is triangle
			if(_mode.equals("Triangle")) {
				Point2D[] arr = new Point2D[3];

				// After one click
				if(_p2 == null) {
					arr[0] = p;
					arr[1] = _p1;
					arr[2] = _p1;
				}

				// After two clicks
				else {
					arr[0] = p;
					arr[1] = _p1;
					arr[2] = _p2;
				}

				gs = new Triangle2D(arr[0], arr[1], arr[2]);
			}

			// Mode is segment
			if(_mode.equals("Segment")) {
				gs = new Segment2D(p,_p1);
			}

			// Mode is polygon
			if(_mode.equals("Polygon")) {

				// Create an array on the length of the points of the polygon
				Point2D[] arr = arrPoints.toArray(new Point2D[arrPoints.size()+1]);
				arr[arrPoints.size()] = p;
				gs = new Polygon2D(arr);
			}

			// Create GUIShape
			_gs = new GUIShape(gs,false, Color.pink, tag);

			// draw the shapes
			drawShapes();
		}
	}

	// Rotate selected shapes
	public void rotate(Point2D cen, Point2D sec_point) {

		// Go throw shapes in the collection
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();

			// Calculate degree
			double degree = Math.toDegrees(Math.atan2(sec_point.y() - cen.y(), sec_point.x() - cen.x()));

			// If selected
			if(s.isSelected() && g!=null) {

				// If rectangle rotate 4 points and change to polygon
				if(g instanceof Rect2D) {
					Rect2D r = (Rect2D)g;
					Point2D p3 = new Point2D(r.getPoint1().x(), r.getPoint2().y());
					Point2D p4 = new Point2D(r.getPoint2().x(), r.getPoint1().y());
					r.rotate(cen, degree);
					p3.rotate(cen, degree);
					p4.rotate(cen, degree);

					Point2D[] arr = {r.getPoint1(), p3, r.getPoint2(), p4};
					Polygon2D poly = new Polygon2D(arr);
					_shapes.removeElementAt(i);
					s.setShape(poly);
					GUIShape Poly = new GUIShape(poly, s.isFilled(), s.getColor(), s.getTag());
					_shapes.addAt(Poly, i);
					Poly.setSelected(true);
				}

				// Other shapes - rotate
				else {
					g.rotate(cen, degree);
				}
			}
		}

		_gs = null; // set _gs to null
	}

	// Open directory
	public void openDirectory(){
		JFileChooser fc = new JFileChooser();
		int i = fc.showOpenDialog(StdDraw_Ex4.getFrame()); 

		// If selected approve
		if(i == JFileChooser.APPROVE_OPTION){    
			File f = fc.getSelectedFile();    
			String filepath = f.getPath();
			_shapes.load(filepath); // Load
		}
		show();
	}

	// save directory
	public void saveDirectory(){
		JFileChooser fc = new JFileChooser(); 
		int i = fc.showSaveDialog(StdDraw_Ex4.getFrame());

		// If selected approve
		if(i == JFileChooser.APPROVE_OPTION){    
			File f = fc.getSelectedFile(); 

			// If file don't end with txt then add it
			if(!f.getAbsolutePath().endsWith("txt")){
				f = new File(f + ".txt");
			}

			String filepath=f.getPath();
			_shapes.save(filepath); // Creating the file
			write_to_file(filepath); // writing to the file
		}
	}

	// write to file
	public void write_to_file (String path) {
		try {
			FileWriter myWriter = new FileWriter(path);
			myWriter.write(getInfo()); // Write info
			myWriter.close(); // close file
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	@Override
	public ShapeCollectionable getShape_Collection() {
		return this._shapes; // Return shapes
	}

	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); } // Show

	@Override
	public String getInfo() {

		// Return all shapes toString
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}

		return ans;
	}
}
