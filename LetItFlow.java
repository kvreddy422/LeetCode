import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
public class LetItFlow {

	  public static void main(String[] args)throws Exception
	  {
	  // We need to provide file path as the parameter:
	  // double backquote is to avoid compiler interpret words
	  // like \test as \t (ie. as a escape sequence)
	  File file = new File("C:\\Users\\Vaibhav Kalakota\\eclipse-workspace\\LeetCode\\LetItFlow.txt");
	  BufferedReader br = new BufferedReader(new FileReader(file));
	  String countShopsString=br.readLine();
	  int tHouses = Integer.parseInt(countShopsString);
	  for(int i=0;i<tHouses;i++) {
		  int total=0;
		  int total1=1;
		  int divider=1;
		  int columns=Integer.parseInt(br.readLine());
		  String array[][]=new String[3][columns];
		  array[0]=br.readLine().split("");
		  array[1]=br.readLine().split("");
		  array[2]=br.readLine().split("");
		  if((columns%2!=0) || (Arrays.asList(array[1]).contains("#")) || (array[0][0].matches("#")) || (array[2][columns-1].matches("#"))) {
			  System.out.println("Case #"+(i+1)+": "+total%(1000000007));
			  continue;
		  }
		  int flag=0;
			  for(int column=1;column<columns-1;column=column+2) {
				if((array[0][column].matches("#") || array[0][column+1].matches("#")) && (array[2][column].matches("#") || array[2][column+1].matches("#"))) {
					System.out.println("Case #"+(i+1)+": "+total%(1000000007));
					flag=1;
					break;
				}
				if((array[0][column].matches("#") || array[0][column+1].matches("#")) || (array[2][column].matches("#") || array[2][column+1].matches("#"))) {
					divider+=1;
					total1+=1;
				}
				else
					total1+=1;
		  }	  if(flag==0) {		
				  total=total1-divider;
				  BigInteger answer = BigInteger.valueOf(2).pow(total);
				  BigInteger mod = new BigInteger("1000000007");
				  System.out.println("Case #"+(i+1)+": "+answer.mod(mod));	
		  }
	  }
	  }
}	  
