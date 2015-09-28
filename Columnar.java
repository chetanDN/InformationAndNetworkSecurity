/*
 * Consider a file with composite data substitutes the content
 *  and transpose the ciphers 
 *  
 *  assume "d:\\hello.txt" has content "this is awesome" to be scrambeled by encription
 */

package Columnar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Columnar {
	char arr[][],encrypt[][],decrypt[][],keya[],keytemp[];
	
	public void createMatrixE(String s,String key,int row,int column ){
		arr = new char[row][column];
		int k=0;
		keya=key.toCharArray();
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				if(k<s.length()){
					arr[i][j]=s.charAt(k);
					k++;
				}
				else{
					arr[i][j]=' ';
				}//end of else
			}//end of inner for
		}//end of outer for
	}
	
	public void createKey(String key,int column){
		keytemp=key.toCharArray();
		for(int i=0;i<column-1;i++){
			for(int j=i+1;j<column;j++){
				if(keytemp[i]>keytemp[j]){
					char temp=keytemp[i];
					keytemp[i]=keytemp[j];
					keytemp[j]=temp;
				}
			}
		}
	}
	
	public void creatMatrixD(String s,String key, int row,int column){
		arr=new char[row][column];
		int k=0;
		keya=key.toCharArray();
		for(int i=0;i<column;i++){
			for(int j=0;j<row;j++)
				if(k<s.length()){
					arr[j][i]=s.charAt(k);
					k++;
				}
				else{
					arr[j][i]=' ';
				}
		}
	}
	
	public void encrypt(int row,int column){
		encrypt=new char[row][column];
		for(int i=0;i<column;i++){
			for(int j=0;j<column;j++){
				if(keya[i]==keytemp[j]){
					for(int k=0;k<row;k++){
						encrypt[k][j]=arr[k][i];
					}
					keytemp[j]='?';
					break;
				}
			}
		}
	}
	
	public void decrypt(int row,int column){
		decrypt=new char[row][column];
		
		for(int i=0;i<column;i++){
			for(int j=0;j<column;j++){
				if(keya[j]==keytemp[i]){
					for(int k=0;k<row;k++){
						decrypt[k][j]=arr[k][i];
					}
					keya[j]='?';
					break;
				}
			}
		}
	}
	
	public void resultE(int row, int column,char arr[][]){
		System.out.printf("Result:");
		for(int i=0;i<column;i++){
			for(int j=0;j<row;j++){
				System.out.print(arr[j][i]);
			}
		}
	}
	
	public void resultD(int row,int column,char arr[][]){
		System.out.printf("Result:");
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				System.out.print(arr[i][j]);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		int row,column;
		Columnar obj=new Columnar();
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in)); 
		FileReader fileReader=new FileReader("d:\\hello.txt");
		
		String fileContent=" ";
		int i;
		while( (i=fileReader.read() ) != -1){
			char ch=(char)i;
			fileContent=fileContent+ch;
		}
		
		System.out.printf("Enter the key for encryption and Decryption: ");
		String key=in.readLine();
		row=fileContent.length()/key.length();
		
		if(fileContent.length()%key.length() != 0)
			row++;
		
		column=key.length();

		System.out.printf("* Encryption *\n");
		obj.createMatrixE(fileContent, key, row, column);
		obj.createKey(key, column);
		obj.encrypt(row, column);
		obj.resultE(row, column, obj.encrypt);
		
		System.out.println("\n * Decription * ");
		System.out.println("\n Enter the string for Decription:");
		String s=in.readLine();
		obj.creatMatrixD(s, key, row, column);
		obj.createKey(key, column);
		obj.decrypt(row, column);
		obj.resultD(row, column, obj.decrypt);
		
	
	}
}

/*
 o/p
 Enter the key for encryption and Decryption: prime	//say key used is "prime"
 
* Encryption *
Result:sam hss i o   wetie 


 * Decription * 

 Enter the string for Decription:
sam hss i o   wetie					//copy paste from encripted output

Result: this is awesome  			//plane text after rearranging
 */
