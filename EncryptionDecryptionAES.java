/*
Program 2
Consider an alphanumeric data, encrypt and Decrypt the data 
using advanced encryption standards and verify for the correctness. 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;				//methods for obtaining encoders and decoders for the Base64 encoding scheme. 

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionDecryptionAES {
//	static Cipher cipher;

	public static void main(String[] args) throws Exception {
//step 1 - Form a secretKey
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");	//"AES is Required
		keyGenerator.init(128);											//AES block size key size
		SecretKey secretKey = keyGenerator.generateKey();				//genererat secret key per all instances
		
//step 2 - get input and print		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter the String: ");
		String plainText = in.readLine();
		System.out.println("Plain Text Before Encryption: " + plainText);

//step 3 - encrypt plainText+Secretkey
		String encryptedText = encrypt(plainText, secretKey);
		System.out.println("Encrypted Text After Encryption: " + encryptedText);

//step 4 - 	Decrypt enCryptredText+secretKEy	
		String decryptedText = decrypt(encryptedText, secretKey);
		System.out.println("Decrypted Text After Decryption: " + decryptedText);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	public static String encrypt(String plainText, SecretKey secretKey)	throws Exception {
	//1 - cipher
		Cipher cipher = Cipher.getInstance("AES");		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);			//ciper initiate ENCRYPT_MODE for secretKey
	//2 - encryptedByte
		byte[] encryptedByte = cipher.doFinal(plainText.getBytes());	//get encryptedBytt by doFinal on plaintextByte
	
	//3 - Encoder
		Base64.Encoder encoder = Base64.getEncoder();			//ByteEncoder
		String encryptedText = encoder.encodeToString(encryptedByte);	//encripted byte to string

		return encryptedText;											//return string
	}

	public static String decrypt(String encryptedText, SecretKey secretKey)	throws Exception {
	
	// 1 - Decoder
		Base64.Decoder decoder = Base64.getDecoder();

	//2 - Cipher
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
	//3 decrepted byte
		//byte[] decryptedByte = cipher.doFinal(encryptedTextByte);								// byte -> || encoder.encodeToString || -> String 
		byte[] decryptedByte = cipher.doFinal(decoder.decode(encryptedText));					// byte <- || decoder.decode		 || <- String
		
	//4 dcrepted text
		String decryptedText = new String(decryptedByte);										//extrs step to convert to string
		return decryptedText;
	}
}

/*
 * Output: enter the String: India Plain Text Before Encryption: India Encrypted
 * Text After Encryption: h6cYdWegMdZnfIzXgEvs3Q== (always changes) Decrypted
 * Text After Decryption: India
 */
