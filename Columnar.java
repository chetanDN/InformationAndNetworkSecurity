package columnar;

/*
 * Consider a file with composite data substitutes the content
 *  and transpose the ciphers 
 *  
 *  assume "d:\\hello.txt" has content "India" to be scrambeled by encription
 */



import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Columnar {

	char arr[][],encryptMatrix[][],decryptMatrix[][],bsKey[];
	static char uKey[];

	
	public static void main(String[] args) throws IOException {
		int row,column;
		Columnar obj=new Columnar();
//Step 1 get filecontents		
	
		FileReader fileReader=new FileReader("d:\\hello.txt");					//read message from file content
		String fileContent="";
		int i;
		while( (i=fileReader.read() ) != -1){									//get file content to string
			char ch=(char)i;
			fileContent=fileContent+ch;
		}
		fileReader.close();
		
//Step 2 -	Get userKey
		System.out.print("Enter the key for encryption and Decryption: ");
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in)); //scan key from System.in
		String key=in.readLine();												//get the key
		uKey=key.toCharArray();	
		
		
//Step 3 - cal row and col
		row=fileContent.length()/key.length();									//calculate reqired no of rows 5/3 = 1
		if(fileContent.length()%key.length() != 0)								//5%3 = 2 != 0, row=2;
			row++;	
		column=key.length();													//col = 3(to form upper matrix

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		obj.createKey(key, column);												//bsKey=sorted keys in ascending order
		
		System.out.print("* Encryption *\n");

		obj.buildMatrixEH(fileContent, row, column);						//createMatrixEncription with arr[horizontal] uKey in user order
		obj.encryptMatrix(row, column);												//encryptMatrix[][] will sorted columnwise matrix
		obj.printEncryptMatrixV(row, column);									//print encript[vertically
		
		System.out.println("\n * Decription * ");
		System.out.println("\n Enter the string for Decription:");
		String s=in.readLine();													//"d naIi"
	
		obj.buildMatrixDV(s, row, column);									//arr[][] store vertically
		obj.decryptMatrix(row, column);												//sort collumneise
		obj.printDecryptMatrixH(row, column);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	
	}
	
	public void createKey(String key,int column){			//key "321" column 3
		bsKey=key.toCharArray();
		for(int i=0;i<column-1;i++){						//bsKey has sorted key [1],[2,[3]
			for(int j=i+1;j<column;j++){
				if(bsKey[i]>bsKey[j]){
					char temp=bsKey[i];
					bsKey[i]=bsKey[j];
					bsKey[j]=temp;
				}
			}
		}
	}
	
	public void buildMatrixEH(String s,int row,int column ){
//general arr[][] to store before sorting in encryptMatrix[][]
		arr = new char[row][column];										//create new array(base addr) as per calc. row=2 col=3
		int k=0;

		for(int i=0;i<row;i++){												//i<2
			for(int j=0;j<column;j++){		//more frequent								//j<3
				if(k<s.length()){											//like while loop till the end of string
					arr[i][j]=s.charAt(k);									//fill char horizontally using k index for string
					k++;													// i , j index for matrix						
				}
			}																// [I][n][d]
		}																	// [i][a][\0]
	}

	public void buildMatrixDV(String s, int row,int column){
		arr=new char[row][column];
		int k=0;
		for(int i=0;i<column;i++){
			for(int j=0;j<row;j++)		//row more frequent
				if(k<s.length()){
					arr[j][i]=s.charAt(k);			//[j][i] save string vertically then right
					k++;
				}
		}
	}
	
	public void encryptMatrix(int row,int column){
		encryptMatrix=new char[row][column];
		for(int i=0;i<column;i++){
			for(int j=0;j<column;j++){
				if(uKey[i]==bsKey[j]){			//uKey= 3 2 1   bsKey(sorted) = 1 2 3, mapping helps to get realtive collum
					for(int k=0;k<row;k++){	
						encryptMatrix[k][j]=arr[k][i];	//matrix columnwise sort, 3rd col get 1st col elements
					}
				}									
			}
		}
	}
	
	public void decryptMatrix(int row,int column){
		decryptMatrix=new char[row][column];
		
		for(int i=0;i<column;i++){				//columnwise arrangement
			for(int j=0;j<column;j++){
				if(uKey[j]==bsKey[i]){			//sort matrix columnwise
					for(int k=0;k<row;k++){
						decryptMatrix[k][j]=arr[k][i];	//[0][3] <- [0][0]  (put 1st col to key position)  (filling from last)
					}

				}
			}
		}
	}
	
	public void printEncryptMatrixV(int row, int column){		//arr[][] mapped to encript[][]
		System.out.print("Result:");
		for(int i=0;i<column;i++){			//from		[d][n][I]
			for(int j=0;j<row;j++){			//			[\0][a][i]
				//System.out.print(arr[j][i]);		//print vertically arr[][](mapped encript[][])
				System.out.print(encryptMatrix[j][i]);
			}
		}
	}
	
	public void printDecryptMatrixH(int row,int column){
		System.out.print("Result:");
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				System.out.print(decryptMatrix[i][j]);			//print result row wise
			}
		}
	}
	
	
}

/*
Enter the key for encryption and Decryption: 213
* Encryption *
Result:naIid
 * Decription * 

 Enter the string for Decription:
naIid 
Result:India 
 */
