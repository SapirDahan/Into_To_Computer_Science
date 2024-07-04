package Ex2;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/*
 * @author Sapir Dahan ID: 325732972
 */

class Ex2Test {
	static double[] po1={2,0,3, -1,0}, po2 = {0.1,0,1, 0.1,3};
	
	@Test
	void testEquals() {
		if(Ex2.equals(po1, po2)) {
			fail("Not equals");
		}
		
		if(!Ex2.equals(po1, po1)) {
			fail("Equals");
		}
	}

	@Test
	void testF() {
		double fx0 = Ex2.f(po1, 0);
		double fx1 = Ex2.f(po1, 1);
		double fx2 = Ex2.f(po1, 2);
		assertEquals(fx0,2);
		assertEquals(fx1,4);
		assertEquals(fx2,6);
			
	}
	
	@Test
	void testPoly() {
		if(!((Ex2.poly(po1)).equals("-1.0x^3 +3.0x^2 +2.0"))) {
			fail();
		}
		
		if(Ex2.poly(po1).equals(Ex2.poly(po2))){
			fail();
		}
		
		double[] po = {0.0, 0.0, -1.2, 0.0, 2.4, 0.0};
		if(!((Ex2.poly(po)).equals("2.4x^4 -1.2x^2"))) {
			fail();
		}
		
		double[] po1 = {0.7, -0.1, -3.2, 0.0, -2.4, 0.0};
		if(!((Ex2.poly(po1)).equals("-2.4x^4 -3.2x^2 -0.1x +0.7"))) {
			fail();
		}
		
		double[] po2 = {2,0,3.1,-1.2};
		if(!((Ex2.poly(po2)).equals("-1.2x^3 +3.1x^2 +2.0"))) {
			fail();
		}
	}

	@Test
	void testRoot() {
		double x12 = Ex2.root(po1, 10, 0, Ex2.EPS);
		assertEquals(x12, 3.1958, Ex2.EPS);		
		
		double [] arr = {1,2,3,4,-5};
		x12 = Ex2.root(arr, 2, 1, Ex2.EPS);
		assertEquals(x12, 1.4617, Ex2.EPS);	
		
		double [] arr1 = {-4, 0, 1};
		x12 = Ex2.root(arr1, -5, 1, Ex2.EPS);
		assertEquals(x12, -2, Ex2.EPS);
	}
	
	@Test
	void testRootRec() {
		double x12 = Ex2.root_rec(po1, 10, 0, Ex2.EPS);
		assertEquals(x12, 3.1958, Ex2.EPS);		
		
		double [] arr = {1,2,3,4,-5};
		x12 = Ex2.root_rec(arr, 2, 1, Ex2.EPS);
		assertEquals(x12, 1.4617, Ex2.EPS);
		
		double [] arr1 = {-4, 0, 1};
		x12 = Ex2.root(arr1, -5, 1, Ex2.EPS);
		assertEquals(x12, -2, Ex2.EPS);
	}
	
	@Test
	void testSameValue() {
		double [] arr = {-5, -4, 0, 3};
		double x12 = Ex2.sameValue(arr, po1, 0, 5, Ex2.EPS);
		assertEquals(x12, 1.8241, Ex2.EPS);	
	}
	
	@Test
	void testArea() {
		double [] arr1 = {0};
		double [] arr2 = {2};
		double s1 = Ex2.area(arr2, arr1, 0, 2, 4);
		assertEquals(s1, 4);
		
		double [] arr3 = {3, 2, 1};
		double [] arr4 = {6, 2, 1};
		double s2 = Ex2.area(arr3, arr4, -2, 5, 100);
		assertEquals(s2, 21, Ex2.EPS);
		
		double s3 = Ex2.area(po1, po2, -1, 3, 100);
		assertEquals(s3, 142.1333, 4);
		
	}
	
	@Test
	void testGetPolynomFromString() {
		String poly = Ex2.poly(po2);
		assertArrayEquals(po2, Ex2.getPolynomFromString(poly));
		String po3 = "-7.6x^4 -8x^2 +34x +9.8";
		double[] pol = {9.8, 34, -8, 0.0, -7.6};
		assertArrayEquals(pol, Ex2.getPolynomFromString(po3));
	}
	
	@Test
	void testAdd() {
		double[] p12 = Ex2.add(po1, po2);
		double[] arr = {2.1, 0.0, 4.0, -0.9, 3.0};
		assertArrayEquals(p12, arr);
	}
	
	@Test
	void testMul() {
		double[] p12 = Ex2.mul(po1, po2);
		double[] arr = {0.2, 0, 2.3, 0.1, 9.0, -0.7, 8.9, -3, 0.0};
		assertArrayEquals(p12, arr);
	}
	
	@Test
	void testAdd2() {
		double[] p12 = Ex2.add(po1, po2);
		double[] minus1 = {-1};
		double[] pp2 = Ex2.mul(po2, minus1);
		double[] p1 = Ex2.add(p12, pp2);
		assertEquals(Ex2.poly(po1), Ex2.poly(p1));
	}

	@Test
	void testMulDoubleArrayDoubleArray() {
		double[] p12 = Ex2.add(po1, po2);
		double dd = Ex2.f(p12, 5);
		assertEquals(dd, 1864.6, Ex2.EPS);
	}
	@Test
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] dp1 = {2,6}; // 6x+2
		double[] dp2 = Ex2.derivative(p);
		assertEquals(dp1[0], dp2[0],Ex2.EPS);
		assertEquals(dp1[1], dp2[1],Ex2.EPS);
		assertEquals(dp1.length, dp2.length);
	}
	
	@Test
	void testDerivative() {
		double[] pol1 = {3, 4, 6, 5};
		double[] pol2 = {4, 12, 15};
		assertArrayEquals(Ex2.derivative(pol1), pol2);
		
		double[] der_po1 = {0,6, -3,0};
		assertArrayEquals(Ex2.derivative(po1), der_po1);
		
		double[] constant = {7.0};
		double[] der_constant = {0.0};
		assertArrayEquals(Ex2.derivative(constant), der_constant);
	}
	
	@Test
	void testPolynomFromPoints() {
		double[] xx1 = {-1, 0, 1};
		double[] yy1 = {1, 0, 1};
		double[] poly1 = {0.0, 0.0, 1.0};
		double[] arr1 = Ex2.PolynomFromPoints(xx1, yy1);
		if(poly1[0]!=arr1[0]||poly1[1]!=arr1[1]||poly1[2]!=arr1[2]) {
			fail();
		}
		
		double[] xx2 = {-2, 3, 7};
		double[] yy2 = {77, 132, 752};
		double[] poly2 = {3.0, -5.0, 16.0};
		double[] arr2 = Ex2.PolynomFromPoints(xx2, yy2);
		if(poly2[0]!=arr2[0]||poly2[1]!=arr2[1]||poly2[2]!=arr2[2]) {
			fail();
		}
	}
}
