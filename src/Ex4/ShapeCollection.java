package Ex4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Ex4.geo.Circle2D;
import Ex4.geo.Point2D;
import Ex4.geo.Rect2D;
import Ex4.gui.Ex4;
import Ex4.GUIShape;
import Ex4.GUI_Shapeable;

/*
 * @author Sapir Dahan ID: 325732972
 *
 */

/*
 * This class represents a collection of GUI_Shape.
 */

public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}

	public ShapeCollection(ArrayList<GUI_Shapeable> arr) {
		_shapes = arr;
	}

	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {

		// If i is  a place at the array
		if(0 <= i && i< size()) {
			GUI_Shapeable sh = _shapes.get(i);
			_shapes.remove(i); // Remove the i place at the array
			return sh;
		}

		return null; // If i in not a place at the array return null
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		// If it possible to place an element at the i place
		if(s != null && s.getShape()!=null && 0 <= i && i <= size()) {
			_shapes.add(i, s); // Add the shape at the i place
		}
	}

	@Override
	public void add(GUI_Shapeable s) {

		// If the shape not null
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s); // add the shape at the end of the
		}
	}

	@Override
	public ShapeCollectionable copy() {

		// Deep copy
		return new ShapeCollection(_shapes);
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {

		// Sort according to the comparator
		Collections.sort(_shapes, comp);
	}

	@Override
	public void removeAll() {

		// Remove all shapes
		_shapes.removeAll(_shapes);
	}

	@Override
	public void save(String file) {

		// Create file
		try {
			File myObj = new File(file);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}	
	}

	@Override
	public void load(String file) {
		removeAll(); // Remove all shapes 

		try {
			File myObj = new File(file);
			Scanner myReader = new Scanner(myObj); // Read file
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] data_arr = data.split(","); // Array with the data of the line
				GUIShape g = new GUIShape();
				g.init(data_arr);
				_shapes.add(g); // Add the shape
			}
			myReader.close(); // close file
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}	
	}

	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;

		if(size() != 0) {

			// Init x, y max and min
			double x_min = get(0).getShape().getPoints()[0].x();
			double y_min = get(0).getShape().getPoints()[0].y();
			double x_max = get(0).getShape().getPoints()[0].x();
			double y_max = get(0).getShape().getPoints()[0].y();

			// Go over shapes
			for(int i=0; i < size(); i++) {

				// Go over the points of the shapes
				for(int j=0; j < get(i).getShape().getPoints().length; j++) {

					// If the shape is circle
					if (get(i).getShape() instanceof Circle2D) {
						Circle2D c = (Circle2D) get(i).getShape();
						double rad = c.getRadius();
						if(get(i).getShape().getPoints()[0].x() + rad > x_max) {
							x_max  = get(i).getShape().getPoints()[0].x() + rad;
						}
						if(get(i).getShape().getPoints()[0].y() + rad > y_max) {
							y_max  = get(i).getShape().getPoints()[0].y() + rad;
						}
						if(get(i).getShape().getPoints()[0].x() - rad < x_min) {
							x_min  = get(i).getShape().getPoints()[0].x() + rad;
						}
						if(get(i).getShape().getPoints()[0].y() - rad < y_min) {
							y_min  = get(i).getShape().getPoints()[0].y() + rad;
						}
					}

					else {
						if(get(i).getShape().getPoints()[j].x() > x_max) {
							x_max  = get(i).getShape().getPoints()[j].x();
						}
						if(get(i).getShape().getPoints()[j].y() > y_max) {
							y_max  = get(i).getShape().getPoints()[j].y();
						}
						if(get(i).getShape().getPoints()[j].x() < x_min) {
							x_min  = get(i).getShape().getPoints()[j].x();
						}
						if(get(i).getShape().getPoints()[j].y() < y_min) {
							y_min  = get(i).getShape().getPoints()[j].y();
						}
					}
				}
			}

			// Create the rectangle
			ans = new Rect2D(new Point2D(x_min, y_min), new Point2D(x_max, y_max));
		}

		return ans; // Return the rectangle
	}

	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
}
