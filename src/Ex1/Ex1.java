package Ex1;

import java.util.Scanner;

public class Ex1 {

// ID: 325732972
// Name: Sapir Dahan

/*The algorithm for finding the greatest prime divisor of two numbers 
 * can be divided into two parts. In the first part, I am using the 
 * Euclidian algorithm for finding the greatest divisor of the two numbers. 
 * Since the result is not necessarily prime, I am using factorization in 
 * order to find all the prime factors of the Euclidian algorithm result. 
 * The largest factor is the greatest prime divisor of the two original 
 * numbers. If the Euclidian algorithm result is one, then there is no 
 * common divisor other than one.  */
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number: ");
		long num1 = scanner.nextLong(); // number input
		System.out.print("Enter a number: ");
		long num2 = scanner.nextLong(); // number input
		scanner.close(); // close scanner
		long x = Math.max(num1, num2); // find the max number between x and y
		long y = Math.min(num1, num2); // find the min number between x and y
		long remainder = x % y; // start Euclidian algorithm by finding the remainder of x/y
		while(remainder != 0){ // as long as the remainder is not 0...
			x = y; // set the numerator to be equal to the denominator
			y = remainder; // set the denominator to be equal to the remainder
			remainder = x % y; // repeat the remainder calculation.
		} // close while

		// start the Factorization by searching all possible divisors from two to square root of y. 
		for (long i = 2; i*i<=y; i++) { 
			while(y%i==0){ // as long as the y divides by i without any remainder, continue to divide by i. 
				y = y/i; // divide by i in order to complete the search of multiple instances of the same factor. 
			} // close while
		} // close for loop

		if(y == 1) { // if y is one then there is no prime common divisor. 
			System.out.println(y + ", There is no Prime Common Divisor"); // print the massage. 
		} // close if

		else{ // if y is greater than one then there is a prime common divisor.
			System.out.println("The Greatest Prime Common Divisor is " + y); // print the greatest prime common divisor. 
		} // close else
	} // close main
} // close class
