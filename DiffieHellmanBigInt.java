/*
Program 4
Develop a mechanism to setup a security channel using Diffie-Hellman Key Exchange
between client and server


 */

package diffieHellaman;

import java.math.BigInteger;
import java.util.Scanner;		//instead of io.BufferedReader; io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class DiffieHellmanBigInt {

//	final static BigInteger one = new BigInteger("1");

	public static void main(String args[]) {

		Scanner stdin = new Scanner(System.in);
		BigInteger p;

		// Get a start spot to pick a prime from the user.
		System.out.println("Enter the approximate value of p you want.");
		String ans = stdin.next();
		p = getNextPrime(ans);			//using BigInteger method (string)
		System.out.println("Your prime is " + p + ".");

		// Get the base for exponentiation from the user.
		System.out.println("Now, enter a number in between 2 and p-1.");
		BigInteger g = new BigInteger(stdin.next());

		// Get A's secret number.
		System.out.println("Person A: enter your private key now.");
		BigInteger Xa = new BigInteger(stdin.next());

		// Make A's calculation.
		BigInteger Ya = g.modPow(Xa, p);

		// This is the value that will get sent from A to B.
		// This value does NOT compromise the value of a easily.
		System.out.println("\nPerson A sends public key to person B " + Ya	+ ".");

		// Get B's secret number.
		System.out.println("\nPerson B: enter your secret number now.");
		BigInteger Xb = new BigInteger(stdin.next());

		// Make B's calculation.
		BigInteger Yb = g.modPow(Xb, p);

		// This is the value that will get sent from B to A.
		// This value does NOT compromise the value of Xb easily.
		System.out.println("Person B sends public key to person A " + Yb	+ ".");

		// Once A and B receive their values, they make their new calculations.
		// This involved getting their new numbers and raising them to the
		// same power as before, their secret number.
		BigInteger ACalculatesKey = Yb.modPow(Xa, p);
		BigInteger BCalculatesKey = Ya.modPow(Xb, p);

		try
		{
			TimeUnit.SECONDS.sleep(1);
		}catch(Exception e){
			
		}
		
		// Print out the Key A calculates.
		System.out.println("\nA takes " + Yb + " raises it to the power " 	+ Xa + " mod " + p);
		System.out.println("The Shared Key A calculates is " + ACalculatesKey + ".");

		// Print out the Key B calculates.
		System.out.println("\nB takes " + Ya + " raises it to the power " + Xb + " mod " + p);
		System.out.println("The Shared Key B calculates is " + BCalculatesKey 	+ ".");

	}

	public static BigInteger getNextPrime(String ans) {

		BigInteger test = new BigInteger(ans);
		while (!test.isProbablePrime(99))
	//		test = test.add(one);
			test = test.add(BigInteger.ONE);
		return test;
	}

}

/*
 * Output: Enter the approximate value of p you want. 11 Your prime is 11. Now,
 * enter a number in between 2 and p-1. 2 Person A: enter your secret number
 * now. 6
 * 
 * Person A sends public key to person B 9.
 * 
 * Person B: enter your secret number now. 3 Person B sends public key to person
 * A 8.
 * 
 * A takes 8 raises it to the power 6 mod 11 The Shared Key A calculates is 3.
 * 
 * B takes 9 raises it to the power 3 mod 11 The Shared Key B calculates is 3.
 */
