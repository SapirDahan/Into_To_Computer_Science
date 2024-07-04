package Ex3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MyMap2DTest {

	@Test
	void drawSegmenTest() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(2,5);
		Point2D p2 = new Point2D(8,8);
		Point2D p3 = new Point2D(6,7);
		int col = Color.BLACK.getRGB();
		map.drawSegment(p1, p2, col);
		if(map.getPixel(p3) != col) {
			fail("suppose to be black");
		}
	}
	
	@Test
	void drawRectTest() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(2,5);
		Point2D p2 = new Point2D(8,8);
		Point2D p3 = new Point2D(3,8);
		Point2D p4 = new Point2D(4,4);
		int col = Color.BLACK.getRGB();
		map.drawRect(p1, p2, col);
		if(map.getPixel(p3) != col) {
			fail("suppose to be black");
		}
		if(map.getPixel(p4) == col) {
			fail("suppose to be white");
		}
	}
	
	@Test
	void drawCircleTest() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(0,0);
		Point2D p3 = new Point2D(4,4);
		int col = Color.BLACK.getRGB();
		double rad = 2.3454;
		map.drawCircle(p1, rad, col);
		if(map.getPixel(p2) != col) {
			fail("suppose to be black");
		}
		if(map.getPixel(p3) == col) {
			fail("suppose to be white");
		}
	}
	
	@Test
	void fillTest1() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(13,51);
		Point2D p2 = new Point2D(47,16);
		int col1 = Color.BLACK.getRGB();
		int col2 = Color.RED.getRGB();
		int col3 = Color.GREEN.getRGB();
		map.fill(p1, col1);
		if(map.getPixel(p2) != col1) {
			fail("suppose to be black");
		}
		Point2D p3 = new Point2D(0,0);
		Point2D p4 = new Point2D(99,99);
		map.drawSegment(p4, p3, col2);
		Point2D p5 = new Point2D(40,6);
		map.fill(p5, col3);
		Point2D p6 = new Point2D(6,40);
		if(map.getPixel(p6) == col3) {
			fail("suppose to be black");
		}
	}
	
	@Test
	void fillTest2() {
		MyMap2D map = new MyMap2D(100,100);
		int col1 = Color.BLACK.getRGB();
		int col2 = Color.RED.getRGB();
		int col3 = Color.GREEN.getRGB();
		map.fill(13,51, col1);
		if(map.getPixel(47,16) != col1) {
			fail("suppose to be black");
		}
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(99,99);
		map.drawSegment(p1, p2, col2);
		map.fill(40, 6, col3);
		if(map.getPixel(6, 40) == col3) {
			fail("suppose to be black");
		}
	}
	
	@Test
	void fillTest3() {
		MyMap2D map = new MyMap2D(10,10);
		int col = Color.GREEN.getRGB();
		int area = map.fill(5,5,col);
		if(area != 100) {
			fail("the area suppose to be 100");
		}
	}
	
	@Test
	void fillTest4() {
		MyMap2D map = new MyMap2D(10,10);
		int col = Color.GREEN.getRGB();
		Point2D p = new Point2D(5,5);
		int area = map.fill(p,col);
		if(area != 100) {
			fail("the area suppose to be 100");
		}
	}
	
	@Test
	@Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
	void fillTestTime() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p = new Point2D(45,31);
		int col = Color.RED.getRGB();
		map.fill(p, col);
	}
	
	@Test
	void shortestPathTest1() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(99,99);
		Point2D[] arr = map.shortestPath(p1, p2);
		if(arr.length != 199) {
			fail("the lenght suppose to be 199");
		}
	}
	
	@Test
	void shortestPathTest2() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(50,0);
		Point2D p2 = new Point2D(50,99);
		int col = Color.RED.getRGB();
		map.drawSegment(p1, p2, col);
		Point2D p3 = new Point2D(25,25);
		Point2D p4 = new Point2D(75,50);
		Point2D[] arr = map.shortestPath(p3, p4);
		if(arr != null) {
			fail("there is no path exists");
		}
	}
	
	@Test
	void shortestPathTest3() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(50,0);
		Point2D p2 = new Point2D(50,99);
		int col = Color.RED.getRGB();
		map.drawSegment(p1, p2, col);
		Point2D p3 = new Point2D(25,25);
		Point2D p4 = new Point2D(75,50);
		Point2D[] arr = map.shortestPath(p3, p4);
		if(arr != null) {
			fail("there is no path exists");
		}
	}
	
	@Test
	void shortestPathTest4() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(50,0);
		Point2D p2 = new Point2D(50,99);
		int col = Color.RED.getRGB();
		map.drawSegment(p1, p2, col);
		Point2D p3 = new Point2D(25,25);
		Point2D[] arr = map.shortestPath(p3, p2);
		if(arr != null) {
			fail("there is no path exists");
		}
	}
	
	@Test
	void shortestPathTest5() {
		MyMap2D map = new MyMap2D(5,5);
		Point2D p1 = new Point2D(1,4);
		Point2D p2 = new Point2D(3,1);
		int col = Color.RED.getRGB();
		map.drawRect(p1, p2, col);
		Point2D p3 = new Point2D(0,4);
		Point2D p4 = new Point2D(4,4);
		Point2D[] arr = map.shortestPath(p3, p4);
		if(arr.length != 13) {
			fail("the length of the path suppose to be 13");
		}
	}
	
	@Test
	@Timeout(value = 7, unit = TimeUnit.MILLISECONDS)
	void shortestPathTestTime() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(99,99);
		map.shortestPath(p1, p2);
	}
	
	@Test
	void shortestPathDistTest1() {
		MyMap2D map = new MyMap2D(200,200);
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(199,199);
		int arrLenght = map.shortestPathDist(p1, p2);
		if(arrLenght != 399) {
			fail("the lenght suppose to be 399");
		}
	}
	
	@Test
	void shortestPathDistTest2() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(50,0);
		Point2D p2 = new Point2D(50,99);
		int col = Color.RED.getRGB();
		map.drawSegment(p1, p2, col);
		Point2D p3 = new Point2D(25,25);
		Point2D p4 = new Point2D(75,50);
		int length = map.shortestPathDist(p3, p4);
		if(length != 0) {
			fail("there is no path exists");
		}
	}
	
	@Test
	void shortestPathDistTest3() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(50,0);
		Point2D p2 = new Point2D(50,99);
		int col = Color.RED.getRGB();
		map.drawSegment(p1, p2, col);
		Point2D p3 = new Point2D(25,25);
		int length = map.shortestPathDist(p3, p2);
		if(length != 0) {
			fail("there is no path exists");
		}
	}
	
	@Test
	@Timeout(value = 7, unit = TimeUnit.MILLISECONDS)
	void shortestPathDistTestTime() {
		MyMap2D map = new MyMap2D(100,100);
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(99,99);
		map.shortestPath(p1, p2);
	}
	
	@Test 
	void nextGenGolTest1() {
		MyMap2D map = new MyMap2D(3,3);
		int black = Color.BLACK.getRGB();
		int white = Color.WHITE.getRGB();
		map.fill(white);
		map.setPixel(1, 0, black);
		map.setPixel(1, 1, black);
		map.setPixel(1, 2, black);
	
		map.nextGenGol();
		
		if(map.getPixel(0,0) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(0,1) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(0,2) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(1,0) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(1,1) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(1, 2) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(2, 0) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(2,1) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(2, 2) == black) {
			fail("suppose to be white");
		}
	}
	
	@Test 
	void nextGenGolTest2() {
		MyMap2D map = new MyMap2D(2,2);
		int black = Color.BLACK.getRGB();
		int white = Color.WHITE.getRGB();
		map.fill(black);
	
		map.nextGenGol();
		
		if(map.getPixel(0,0) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(0,1) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(1,0) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(1,1) == white) {
			fail("suppose to be black");
		}
	}
	
	@Test 
	void nextGenGolTest3() {
		MyMap2D map = new MyMap2D(3,3);
		int black = Color.BLACK.getRGB();
		int white = Color.WHITE.getRGB();
		map.fill(white);
		map.setPixel(1, 0, black);
		map.setPixel(1, 2, black);
		map.setPixel(0, 1, black);
		map.setPixel(2, 1, black);
	
		map.nextGenGol();
		
		if(map.getPixel(0,0) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(0,1) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(0,2) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(1,0) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(1,1) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(1, 2) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(2, 0) == black) {
			fail("suppose to be white");
		}
		if(map.getPixel(2,1) == white) {
			fail("suppose to be black");
		}
		if(map.getPixel(2, 2) == black) {
			fail("suppose to be white");
		}
	}
	
}
