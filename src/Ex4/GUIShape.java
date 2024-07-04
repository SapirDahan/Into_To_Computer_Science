package Ex4;
/**
 * This class implements the GUI_shape.
 */
import java.awt.Color;

import Ex4.geo.Circle2D;
import Ex4.geo.GeoShapeable;
import Ex4.geo.Point2D;
import Ex4.geo.Polygon2D;
import Ex4.geo.Rect2D;
import Ex4.geo.Segment2D;
import Ex4.geo.Triangle2D;

/*
 * @author Sapir Dahan ID: 325732972
 *
 */


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill = false;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	// Constructor
	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}

	// Copy constructor
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
		_isSelected = false;
	}

	// Empty constructor
	public GUIShape() {
		_isSelected = false;
	}

	@Override
	public GeoShapeable getShape() {
		return _g; // Return the shape
	}

	@Override
	public boolean isFilled() {
		return _fill; // Return if filled
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled; // Change filled
	}

	@Override
	public Color getColor() {
		return _color; // Get the color
	}

	@Override
	public void setColor(Color cl) {
		_color = cl; // Change the color
	}

	@Override
	public int getTag() {
		return _tag; // Return the tag
	}

	@Override
	public void setTag(int tag) {
		_tag = tag; // Change the tag	
	}

	@Override
	public GUI_Shapeable copy() {

		// Deep copy
		return new GUIShape(this);
	}

	@Override
	public String toString() {

		// Create each object his describing string
		String str = "GUIShape,";

		// The color is the RGB of the color minus the RGB of black
		str = str + Integer.toString(_color.getRGB()-Color.BLACK.getRGB()) + ",";
		str = str + Boolean.toString(_fill) + ","; // Is filled
		str = str + Integer.toString(_tag) + ","; // The tag

		// Getting the object name
		String s = _g.getClass().getCanonicalName();
		str = str + s.substring(s.lastIndexOf('.') + 1) + ",";

		str = str + _g.toString();

		// Remove all "-"
		str = str.replace("-", "");

		return str; // Return the string
	}

	public void init(String[] ww) {

		// The color is the integer at ww[1] plus the RGB of black
		int color = Integer. parseInt(ww[1]) + Color.BLACK.getRGB();

		// White
		if(color == Color.WHITE.getRGB()) {
			_color = Color.WHITE;
		}

		// Red
		if(color == Color.RED.getRGB()) {
			_color = Color.RED;
		}

		// Green
		if(color == Color.GREEN.getRGB()) {
			_color = Color.GREEN;
		}

		// Yellow
		if(color == Color.YELLOW.getRGB()) {
			_color = Color.YELLOW;
		}

		// Black
		if(color == Color.BLACK.getRGB()) {
			_color = Color.BLACK;
		}

		// Blue
		if(color == Color.BLUE.getRGB()) {
			_color = Color.BLUE;
		}


		// If filled is true
		if(ww[2].equals("true")) {
			setFilled(true);
		}

		// If filled is false
		else {
			setFilled(false);
		}

		// The tag
		_tag = Integer.parseInt(ww[3]);


		// If Circle
		if(ww[4].equals("Circle2D")) {
			Point2D cen = new Point2D(Double.parseDouble(ww[5]), Double.parseDouble(ww[6])); // center
			double rad = Double.parseDouble(ww[7]); // Radius
			Circle2D c = new Circle2D(cen, rad);
			setShape(c);
		}

		// If segment
		if(ww[4].equals("Segment2D")) {
			Point2D p1 = new Point2D(Double.parseDouble(ww[5]), Double.parseDouble(ww[6]));
			Point2D p2 = new Point2D(Double.parseDouble(ww[7]), Double.parseDouble(ww[8]));
			Segment2D s = new Segment2D(p1, p2);
			setShape(s);
		}

		// If rectangle
		if(ww[4].equals("Rect2D")) {

			// This is a rotated rectangle. Then we will change it to polygon
			if(Double.parseDouble(ww[5]) != Double.parseDouble(ww[7]) && Double.parseDouble(ww[7]) != Double.parseDouble(ww[9])) {

				Point2D[] arr = new Point2D[4];
				int counter = 0;
				for(int i = 5; i < 13; i = i + 2) {
					arr[counter] = new Point2D(Double.parseDouble(ww[i]), Double.parseDouble(ww[i+1]));
					counter++;
				}
				Polygon2D p = new Polygon2D(arr);
				setShape(p);
			}

			// Regular rectangle
			else {
				Point2D p1 = new Point2D(Math.min(Double.parseDouble(ww[5]), Double.parseDouble(ww[9])), Math.min(Double.parseDouble(ww[6]), Double.parseDouble(ww[10])));
				Point2D p2 = new Point2D(Math.max(Double.parseDouble(ww[5]), Double.parseDouble(ww[9])), Math.max(Double.parseDouble(ww[6]), Double.parseDouble(ww[10])));
				Rect2D r = new Rect2D(p1, p2);
				setShape(r);
			}
		}

		// If triangle
		if(ww[4].equals("Triangle2D")) {
			Point2D p1 = new Point2D(Double.parseDouble(ww[5]), Double.parseDouble(ww[6]));
			Point2D p2 = new Point2D(Double.parseDouble(ww[7]), Double.parseDouble(ww[8]));
			Point2D p3 = new Point2D(Double.parseDouble(ww[9]), Double.parseDouble(ww[10]));
			Triangle2D t = new Triangle2D(p1, p2, p3);
			setShape(t);
		}

		// If polygon
		if(ww[4].equals("Polygon2D")) {
			int length = ww.length;

			Point2D[] arr = new Point2D[(length - 5)/2];
			int counter = 0;
			for(int i = 5; i < length; i = i + 2) {
				arr[counter] = new Point2D(Double.parseDouble(ww[i]), Double.parseDouble(ww[i+1]));
				counter++;
			}

			Polygon2D p = new Polygon2D(arr);
			setShape(p);
		}
	}

	@Override
	public boolean isSelected() {
		return this._isSelected; // Return if selected
	}

	@Override
	public void setSelected(boolean s) {
		this._isSelected = s; // Change if selected
	}

	@Override
	public void setShape(GeoShapeable g) {
		this._g = g; // Change shape
	}

	// Remove last point of a polygon
	public void removeLastPoint() {
		int size = _g.getPoints().length;
		Point2D[] arr = new Point2D[size -1];
		for(int i = 0; i < size - 1; i++) {
			arr[i] = _g.getPoints()[i];
		}

		this._g = new Polygon2D(arr);
	}
}
