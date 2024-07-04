package Ex3;

import java.awt.Color;

/*
* @author Sapir Dahan ID: 325732972
*/

/**
 * DOCUMENTATION
 * 
 * drawSegment:
 * This function gets two points and a color, and draws a line 
 * connecting between them with the selected color.
 *
 * drawRect:
 * This function get two points and and a color. It draws a rectangle 
 * that is defined by the two points at its opposite vertices with the 
 * selected color.
 * 
 * drawCircle:
 * This function get one point, a radius and a color. It draws a circle 
 * that is defined by the point at its center and the radius and with the 
 * selected color.
 * 
 * fill:
 * There are two functions for fill. The first get a point and a color and
 * the second get two integers which is the x and y of the point and a color.
 * The two functions do the same. They take the point they got and filling
 * the point and the neighbors which had the same color as the point in the
 * new color. The function returns how many points changed there color.
 * 
 * shortestPath:
 * This function get two point and returns an array of points which is 
 * the shortest (no diagonals) path between the two points. If there is no 
 * path between the points the function returns null.
 *  
 * shortestPathDist:
 * This function get two point and returning the length of the shortest
 * path between the two points. If no path exists then it return 0. 
 * 
 * nextGenGol:
 * This function make the next move in game of life according to the 
 * Current map.
 * 
 *
 */

public class Ex3 {
	private static  Map2D _map = null;
	public static Color _color = Color.BLACK;
	private static String _mode = "";
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int BLUE = Color.BLUE.getRGB();
	public static final int RED = Color.RED.getRGB();
	public static final int YELLOW = Color.YELLOW.getRGB();
	public static final int GREEN = Color.GREEN.getRGB();
	public static int LENGHT;

	public static void main(String[] args) {
		int dim = 10;  // init matrix (map) 10*10
		init(dim);
	}
	
	private static void init(int x) {
		LENGHT = x;
		StdDraw_Ex3.clear();
		_map = new MyMap2D(x);
		StdDraw_Ex3.setScale(-0.5, _map.getHeight()-0.5);
		StdDraw_Ex3.enableDoubleBuffering();
		_map.fill(WHITE);
		drawArray(_map);		
	}

	public static void drawGrid(Map2D map) {
		int w = map.getWidth();
		int h = map.getHeight();
		for(int i=0;i<w;i++) {
			StdDraw_Ex3.line(i, 0, i, h);
		}
		for(int i=0;i<h;i++) {
			StdDraw_Ex3.line(0, i, w, i);
		}
	}
	static public void drawArray(Map2D a) {
		StdDraw_Ex3.clear();
		StdDraw_Ex3.setPenColor(Color.gray);
		drawGrid(_map);
		for(int y=0;y<a.getWidth();y++) {
			for(int x=0;x<a.getHeight();x++) {
				int c = a.getPixel(x, y);
				StdDraw_Ex3.setPenColor(new Color(c));
				drawPixel(x,y);
			}
		}		
		StdDraw_Ex3.show();
	}
	public static void actionPerformed(String p) {
		_mode = p;
		
		// Select color from menu
		if(p.equals("Blue")) {_color = Color.BLUE;}
		if(p.equals("White")) {_color = Color.WHITE; }
		if(p.equals("Black")) {_color = Color.BLACK; }
		if(p.equals("Red")) {_color = Color.RED; }
		if(p.equals("Yellow")) {_color = Color.YELLOW; }
		if(p.equals("Green")) {_color = Color.GREEN; }

		// Select map size from menu
		if(p.equals("20x20")) {init(20); LENGHT = 20;}
		if(p.equals("40x40")) {init(40); LENGHT = 40;}
		if(p.equals("80x80")) {init(80); LENGHT = 80;}
		if(p.equals("160x160")) {init(160); LENGHT = 160;}
		
		// Clear the map
		if(p.equals("Clear")) {init(LENGHT);}

		// Draw the map
		drawArray(_map);

	}
	
	// Auxiliary static variables for drawRect function
	public static Point2D pointRect = null;
	public static int counterRect = 0;
	
	// Auxiliary static variables for drawCircle function
	public static Point2D pointCircle = null;
	public static int counterCircle = 0;
	
	// Auxiliary static variables for drawSegment function
	public static Point2D pointSegment = null;
	public static int counterSegment = 0;
	
	// Auxiliary static variables for shortestPath function
	public static Point2D pointShortestPath = null;
	public static int counterShortestPath = 0;
	
	public static void mouseClicked(Point2D p) {
		System.out.println(p);
		int col = _color.getRGB(); // Color mode
		
		// If the mode is Point
		if(_mode.equals("Point")) {
			_map.setPixel(p,col);
		}
		
		// If the mode is Rect
		if(_mode.equals("Rect")) {
			if(pointRect == null) {
				pointRect = p;
				counterRect++;
			}
			else if(counterRect == 1){
				counterRect++;
				_map.drawRect(p,pointRect,col);
				pointRect = null;
				counterRect = 0;
			}
		}
		
		// If the mode is Circle
		if(_mode.equals("Circle")) {
			if(pointCircle == null) {
				pointCircle = p;
				counterCircle++;
			}
			else if(counterCircle == 1){
				counterCircle++;
				double rad = Math.sqrt(Math.pow(p.ix()-pointCircle.ix(), 2)+Math.pow(p.iy()-pointCircle.iy(), 2));
				_map.drawCircle(pointCircle,rad,col);
				pointCircle = null;
				counterCircle = 0;
			}
		}
		
		// If the mode is Segment
		if(_mode.equals("Segment")) {
			if(pointSegment == null) {
				pointSegment = p;
				counterSegment++;
			}
			else if(counterSegment == 1){
				counterSegment++;
				_map.drawSegment(pointSegment, p, col);
				pointSegment = null;
				counterSegment = 0;
			}
		}
		
		// If the mode is ShortestPath
		if(_mode.equals("ShortestPath")) {
			if(pointShortestPath == null) {
				pointShortestPath = p;
				counterShortestPath++;
			}
			else if(counterShortestPath == 1){
				counterShortestPath++;
				Point2D[] arr = _map.shortestPath(pointShortestPath, p);
				
				// If the array is not null this mean that there is a path
				if(arr != null) { 
					for(int i=0; i < arr.length; i++) {
						_map.setPixel(arr[i], col);
					}
					System.out.println("The length of the shortest path is: " + _map.shortestPathDist(pointShortestPath, p));
				}
				
				// Else, there is no path
				else {
					System.out.println("No path exists!");
				}
				
				pointShortestPath = null;
				counterShortestPath = 0;
			}
		}
		
		// If the mode is Fill
		if(_mode.equals("Fill")) {
			_map.fill(p, col);
			_mode = "none";
		}
		
		// If the mode is Gol
		if(_mode.equals("Gol")) {
			_map.nextGenGol();	
		}
		
		// Draw the map
		drawArray(_map);
	}
	
	static private void drawPixel(int x, int y) {
		StdDraw_Ex3.filledCircle(x, y, 0.3);
	}
}
