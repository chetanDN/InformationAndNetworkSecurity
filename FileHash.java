package filehash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;

public class FileHash {
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the String");
		String plainText = in.readLine();
		
		String MD5 = hash(plainText,"MD5");
		System.out.println("MD5 hash Code : " + MD5);
		
		String SHA1 = hash(plainText,"SHA-1");
		System.out.println("SHA1 hash Code : " + SHA1);
		
		String SHA256 = hash(plainText,"SHA-256");
		System.out.println("SHA256 hash Code : " + SHA256);
	}

	public static String hash(String plaintext, String hashAlgo) throws Exception
	{
		MessageDigest mDigest = MessageDigest.getInstance(hashAlgo);			//This MessageDigest class provides applications the functionality of a message mDigest algorithm, such as SHA-1 or SHA-256. Message digests are secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value.
		byte[] hashedbytes = mDigest.digest(plaintext.getBytes("UTF-8"));		//byte[] <- algo on plaintext
		return convert(hashedbytes);		//convert bytes[] to hexadecimal string
	}

	public static String convert(byte[] hashedbytes) 
	{
		StringBuffer SB = new StringBuffer();
		for(int i=0;i<hashedbytes.length;i++)
		{
			SB.append(Integer.toString((hashedbytes[i] & 0xff) + 0x100,16).substring(1));	//16 for hexa decimal rep, substring 1 will negate +0x100

		}
		return SB.toString();
	}
	

	
}
