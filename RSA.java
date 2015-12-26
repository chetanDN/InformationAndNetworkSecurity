/*
 Program 3
 Apply the RSA algorithm on a text file to produce cipher text file.
 
 Consider inputfile "D:\\hello.txt" has content <India>
 output file is D:\\output.txt"
 */

package rsa;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

	private BigInteger n, d, e;
//	private int bitlen = 1024;

////////////////////////////////////////////////////////////////////////////////////////
	/** Create an instance that can both encrypt and decrypt. */
	public RSA(int bitlen) {
		//bitlen = bits;
		SecureRandom r = new SecureRandom();
		BigInteger p = new BigInteger(bitlen / 2, 100, r);
		BigInteger q = new BigInteger(bitlen / 2, 100, r);
		n = p.multiply(q);
		BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); //phi(n)
		e = new BigInteger("3");										//chosen 3
		while (m.gcd(e).intValue() > 1) {								//check gcd(phi(n),e) ==1 ; i.e (1>1) condition false to get 1
			e = e.add(new BigInteger("2"));								//checking for all odd numbers 3,5,7,
		}
		d = e.modInverse(m);											//d=( e-inverse) mod phi(n)
	}
//////////////////////////////////////////////////////////////////////////////////////
	/** Encrypt the given plainTextBytes message. */
	public synchronized BigInteger encrypt(BigInteger message) {
		return message.modPow(e, n);
	}

	/** Decrypt the given cipherTextBytes message. */
	public synchronized BigInteger decrypt(BigInteger message) {
		return message.modPow(d, n);
	}
//////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws IOException {
		RSA rsa = new RSA(1024);	//initialise bitlen to what ever value passed here. prev value overided

		FileReader fileReader = new FileReader("D:\\hello.txt");

		String fileContents = "";

		int i;

		while ((i = fileReader.read()) != -1) {
			char ch = (char) i;

			fileContents = fileContents + ch;
		}

		System.out.println("Plaintext: " + fileContents);

//////////////////////////////////////////////////////////////////////////////////////
//1 plainTextBytes
		BigInteger plainTextBytes = new BigInteger(fileContents.getBytes());
//2 cipherTextBytes
		BigInteger cipherTextBytes = rsa.encrypt(plainTextBytes);
		System.out.println("Ciphertext: " + cipherTextBytes);

		// Writing the Encrypted text to file
		String ciper1 = cipherTextBytes.toString();
		File file = new File("D:\\output.txt");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(ciper1);
		fileWriter.flush();
		fileWriter.close();
//3 cipherTextBytes to back plainTextBytes
		BigInteger decryptedBytes = rsa.decrypt(cipherTextBytes);

	//	String text2 = new String(plainTextBytes.toByteArray());
		String text2 = new String(decryptedBytes.toByteArray());
		System.out.println("Plaintext: " + text2);

	}
}

/*
 * Output: Plaintext: India Ciphertext: 31370526588203547925368304149358625
 * (cipher text will change for each run/session) Plaintext: India
 */
