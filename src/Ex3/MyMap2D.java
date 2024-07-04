package Ex3;

import java.util.ArrayList;

/*
 * 
 * @author Sapir Dahan ID: 325732972
 *
 */

/**
 * This class implements the Map2D interface. **/

public class MyMap2D implements Map2D{
	private int[][] _map;

	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}

	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}

	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) { 
		setPixel(p.ix(), p.iy(), v);
	}


	/*
	 * This function gets two points and a color, and draws a line 
	 * connecting between them with the selected color.
	 */
	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) {

		int dx = p2.ix() - p1.ix(); // Calculate the difference of x
		int dy = p2.iy() - p1.iy(); // Calculate the difference of y

		// The length of the segment is the maximum between the difference of x and y
		int totalPoints = Math.max(Math.abs(dx), Math.abs(dy));

		float x_move = (float)dx/totalPoints; // Calculate how much x will move
		float y_move = (float)dy/totalPoints; // Calculate how much y will move

		// Set the first point of the segment (avoid a hole in the starting point)
		float x = p1.ix();
		float y = p1.iy();
		setPixel((int)x, (int)y, v);

		// Draw the segment by running through the points of the segment
		for(int i=1; i<totalPoints; i++) {
			x = x + x_move;
			y = y + y_move;
			setPixel(Math.round(x), Math.round(y), v);
		}

		// Set the last point of the segment (avoid a hole in the end point)
		setPixel(p2, v);

		return;
	}



	/*
	 * This function get two points and and a color. It draws a rectangle 
	 * that is defined by the two points at its opposite vertices with the 
	 * selected color.
	 */
	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {

		// Find the minimum of x and y between the two points
		int x_min = Math.min(p1.ix(),p2.ix());
		int y_min = Math.min(p1.iy(),p2.iy());

		// Find the maximum of x and y between the two points
		int x_max = Math.max(p1.ix(),p2.ix());
		int y_max = Math.max(p1.iy(),p2.iy());

		// Run through the rectangle points and draw
		for(int i = x_min; i <= x_max; i++) {
			for(int j = y_min; j <= y_max; j++) {
				setPixel(i, j, col);
			}
		}		
		return;
	}


	/*
	 * This function get one point, a radius and a color. It draws a circle 
	 * that is defined by the point at its center and the radius and with the 
	 * selected color.
	 */
	@Override
	public void drawCircle(Point2D p, double rad, int col) {

		// Go over all the points in the map
		for(int i=0; i < getWidth(); i++) {
			for(int j=0; j < getHeight(); j++) {
				Point2D point = new Point2D(i, j); // Create the point 

				// If the distance between from the center is the radios or smaller ...
				if(rad >= p.distance(point)) {
					setPixel(i, j, col); // then set the point
				}
			}
		}		
		return;
	}


	/*
	 * There are two functions for fill. The first get a point and a color and
	 * the second get two integers which is the x and y of the point and a color.
	 * The two functions do the same. They take the point they got and filling
	 * the point and the neighbors which had the same color as the point in the
	 * new color. The function returns how many points changed there color.
	 */
	@Override
	public int fill(Point2D p, int new_v) {
		// Call the second function
		return fill(p.ix(),p.iy(),new_v); // return how many points were changed
	}

	@Override
	public int fill(int x, int y, int new_v) {
		int length = getWidth(); // The length of the map
		int internalCol = getPixel(x,y); // Start from this point

		// Create a map of possible points that can be changed
		int[][] map = new int[length][length];
		for(int i=0; i < length; i++) {
			for(int j=0; j < length; j++) {

				// If the point is in the same color of the internal point ...
				if(getPixel(i, j) == internalCol) {
					map[i][j] = 0; // then set the place in the array to 0
				}

				// If the point is not in the same color of the internal point ...
				else {
					map[i][j] = -1; // then set the place in the array to -1
				}
			}
		}

		map[x][y] = 1; // Set the internal point to 1
		int counter = 1;

		// Create ArrayList to possible points to check
		ArrayList<Point2D> arr = new ArrayList<Point2D>();
		Point2D p_start = new Point2D(x,y);
		arr.add(p_start);

		boolean flag = true;

		// While points were changed in map
		while(flag) {
			flag = false; // Set flag to false at the beginning
			int index = arr.size()-1; // last index of the ArrayList
			int i = arr.get(index).ix();
			int j = arr.get(index).iy();

			// If the value of the point is as counter and index is in possible in the array
			while(map[i][j] == counter && index > -1) {

				// Checking if the neighbor of the point is in the same color of the internal point

				if(i > 0 && map[i-1][j] == 0) {
					map[i-1][j] = counter + 1;
					Point2D p = new Point2D(i-1,j);
					arr.add(p);
					flag = true;
				}
				if(i < length - 1 && map[i+1][j] == 0) {
					map[i+1][j] = counter + 1;
					Point2D p = new Point2D(i+1,j);
					arr.add(p);
					flag = true;
				}
				if(j < length - 1 && map[i][j+1] == 0) {
					map[i][j+1] = counter + 1;
					Point2D p = new Point2D(i,j+1);
					arr.add(p);
					flag = true;
				}
				if(j > 0 && map[i][j-1] == 0) {
					map[i][j-1] = counter + 1;
					Point2D p = new Point2D(i,j-1);
					arr.add(p);
					flag = true;
				}
				index = index - 1;
				if(index>-1) {
					i = arr.get(index).ix();
					j = arr.get(index).iy();
				}

			}

			counter++; // Increasing the counter
		}

		counter = 0; // Set counter to 0

		// Go over all the map
		for(int i=0; i < length; i++) {
			for(int j=0; j < length; j++) {

				// Checking if the point is greater then 0 in map
				if(map[i][j]>0) {
					counter++; // Increasing the counter
					setPixel(i, j, new_v); // Setting the point
				}
			}
		}

		// Return the number of points that had been changed
		return counter;
	}


	/*
	 * This function get two point and returns an array of points which is 
	 * the shortest (no diagonals) path between the two points. If there is no 
	 * path between the points the function returns null.
	 */
	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {

		int length = getWidth(); // The length of the map
		int initCol = getPixel(p1); // Color of the first point
		int finalCol = getPixel(p2); // Color of the second point
		int col = getPixel(p1); // The color that the path can go though

		// If the colors are not equal then it is impossible to make a path
		if(initCol != finalCol) {
			return null; // Return null because no path exist
		}

		// Create a map of possible points that the path can go though
		int[][] map = new int[length][length];
		for(int i=0; i < length; i++) {
			for(int j=0; j < length; j++) {

				// If the point is in the same color of the internal point ...
				if(getPixel(i, j) == col) {
					map[i][j] = 0; // then set the place in the array to 0
				}

				// If the point is not in the same color of the internal point ...
				else {
					map[i][j] = -1; // then set the place in the array to -1
				}
			}
		}


		map[p1.ix()][p1.iy()] = 1; // Set the internal point to 1
		int counter = 1;

		// Create ArrayList to possible points to check
		ArrayList<Point2D> arr = new ArrayList<Point2D>();
		arr.add(p1); // Add the first point

		boolean flag = true;

		// While points were changed in map and the point in map is 0
		while(flag && map[p2.ix()][p2.iy()]==0) {
			flag = false;
			int index = arr.size()-1;
			int i = arr.get(index).ix();
			int j = arr.get(index).iy();

			// If the value of the point is as counter and index is in possible in the array
			while(map[i][j] == counter && index > -1) {

				// Checking if the neighbor of the point is in the same color of the internal point

				if(i > 0 && map[i-1][j] == 0) {
					map[i-1][j] = counter + 1;
					Point2D p = new Point2D(i-1,j);
					arr.add(p);
					flag = true;
				}
				if(i < length - 1 && map[i+1][j] == 0) {
					map[i+1][j] = counter + 1;
					Point2D p = new Point2D(i+1,j);
					arr.add(p);
					flag = true;
				}
				if(j < length - 1 && map[i][j+1] == 0) {
					map[i][j+1] = counter + 1;
					Point2D p = new Point2D(i,j+1);
					arr.add(p);
					flag = true;
				}
				if(j > 0 && map[i][j-1] == 0) {
					map[i][j-1] = counter + 1;
					Point2D p = new Point2D(i,j-1);
					arr.add(p);
					flag = true;
				}
				index = index - 1;
				if(index>-1) {
					i = arr.get(index).ix();
					j = arr.get(index).iy();
				}

			}
			counter++; // Increasing the counter
		}

		// If the second point is still 0 then there no path exist
		if(map[p2.ix()][p2.iy()] == 0) {
			return null; // Return null because no path exist
		}

		// Create an array for the points in the path
		// The length is counter
		Point2D[] path = new Point2D[counter];
		Point2D p = new Point2D(p2);
		path[counter-1] = p;

		// Go over the array
		for(int i=counter-2; i>=0; i--) {

			// Find a neighbor who is equal to i and set this point to the array

			if(p.ix() > 0 && map[p.ix()-1][p.iy()] == i+1) {
				path[i] = new Point2D(p.ix()-1,p.iy());
				p = path[i];
			}
			else if(p.ix() < length - 1 && map[p.ix()+1][p.iy()] == i+1) {
				path[i] = new Point2D(p.ix()+1,p.iy());
				p = path[i];
			}
			else if(p.iy() < length - 1 && map[p.ix()][p.iy()+1] == i+1) {
				path[i] = new Point2D(p.ix(),p.iy()+1);
				p = path[i];
			}
			else if(p.iy() > 0 && map[p.ix()][p.iy()-1] == i+1) {
				path[i] = new Point2D(p.ix(),p.iy()-1);
				p =path[i];
			}
		}

		return path; // Return the array with the points of the path

	}



	/*
	 * This function get two point and returning the length of the shortest
	 * path between the two points. If no path exists then it return 0. 
	 */
	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		// shortestPath returns an array of points of the path
		// The length of the array is the distance of the path
		// If no path exists then return 0.

		if (shortestPath(p1, p2) == null) {return 0;}
		else {return shortestPath(p1, p2).length;}
	}



	/*
	 * This function make the next move in game of life according to the 
	 * Current map.
	 */
	@Override
	public void nextGenGol() {

		// If the color of the pixel is not white set to black
		for(int i=0; i < getWidth(); i++) {
			for(int j=0; j < getHeight(); j++) {
				if(getPixel(i, j) != WHITE) {
					setPixel(i, j, BLACK);
				}
			}
		}

		//Make a copy of the map
		Map2D copyMap = new MyMap2D(getWidth());
		for(int i=0; i < getWidth(); i++) {
			for(int j=0; j < getHeight(); j++) {
				copyMap.setPixel(i, j, getPixel(i, j));
			}
		}

		// Go over the copy of the map
		for(int i=0; i < getWidth(); i++) {
			for(int j=0; j < getHeight(); j++) {
				int counter =0; 

				// count how many live neighbor a pixel have
				if (copyMap.getWidth() > i + 1 && copyMap.getPixel(i+1, j) == BLACK) {
					counter++;
				}
				if (copyMap.getWidth() > i + 1 && copyMap.getHeight() > j +1 && copyMap.getPixel(i+1, j+1) == BLACK ) {
					counter++;
				}
				if (copyMap.getWidth() > i + 1 && j - 1 >= 0 && copyMap.getPixel(i+1, j-1) == BLACK) {
					counter++;
				}
				if (copyMap.getHeight() > j + 1 && copyMap.getPixel(i, j+1) == BLACK) {
					counter++;
				}
				if (i - 1 >= 0 && j - 1 >= 0 && copyMap.getPixel(i-1, j-1) == BLACK) {
					counter++;
				}
				if (j - 1 >= 0 && copyMap.getPixel(i, j-1) == BLACK) {
					counter++;
				}
				if (i - 1 >= 0 && copyMap.getHeight() > j + 1 && copyMap.getPixel(i-1, j+1) == BLACK) {
					counter++;
				}
				if (i - 1 >= 0 && copyMap.getPixel(i-1, j) == BLACK) {
					counter++;
				}

				// If the pixel lived 
				if(getPixel(i, j) == BLACK) {

					// If the pixel have 2 or 3 live neighbor then continue to live
					if(counter == 2 || counter == 3) {
						setPixel(i, j, BLACK);
					}

					// Else the pixel will die
					else {
						setPixel(i, j, WHITE);
					}
				}

				// If the pixel were dead
				else {

					// if the pixel have 3 live neighbor the live
					if(counter == 3) {
						setPixel(i, j, BLACK);
					}
				}

			}
		}
	}

	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}

	}

}
