/*
 Program 5
Implementation of Message Authentication Code using cryptography VMAC function.
 */

package simpleMac;

import javax.crypto.*;

//import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;



public class SimpleVMacExample {



	
	public static void main(String[] args) throws Exception {
		
//1 secretkey
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
		SecretKey secretKey = keyGenerator.generateKey();
//2 cipher
	//	Mac theMacCipher = Mac.getInstance("HmacMD5");

//3 get input 
		System.out.println("Enter String to Generate VMAC.");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String plainText = in.readLine();
		System.out.println("Plain Text Before Encryption: " + plainText);
		

//encrypt step 1
//		byte[] plainTextByte = plainText.getBytes();
		
		
//encrypt 2nd step of encrypt same
		Mac theMacCipher = Mac.getInstance("HmacMD5");
		theMacCipher.init(secretKey);
//		byte[] theMacCodeByte = theMacCipher.doFinal(plainTextByte);
		byte[] theMacCodeByte = theMacCipher.doFinal(plainText.getBytes());

//encrypt step 3 no encoder, direct display	
		

		System.out.print("The VMAC for the plaintext \'" + plainText 	+ "\' is ");
		for (int i = 0; i < theMacCodeByte.length; i++) {
			System.out.print(theMacCodeByte[i]);
			if (i < theMacCodeByte.length-1) {
				System.out.print(",");
			} // if
		} // for i
		System.out.println();
	} // main
	
/*	
	public static String getPlainText() {
		System.out.print("Enter plaintext:");
		String plaintext = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			plaintext = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read plaintext!");
			System.exit(1);
		} // catch
		return plaintext;
	} // getPlainText()
*/
}

/*
 * Output : Enter String to Generate VMAC. Enter plaintext:India The VMAC for
 * the plaintext 'India' is
 * -67,-20,-3,-60,48,0,40,89,20,-79,63,-60,10,-89,-104,-66
 */

