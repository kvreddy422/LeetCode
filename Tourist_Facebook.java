import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class Tourist_Facebook {

	  public static void main(String[] args)throws Exception
	  {
	  // We need to provide file path as the parameter:
	  // double backquote is to avoid compiler interpret words
	  // like \test as \t (ie. as a escape sequence)
	  File file = new File("C:\\Users\\Vaibhav Kalakota\\eclipse-workspace\\LeetCode\\src\\input.txt");
	  BufferedReader br = new BufferedReader(new FileReader(file));
		  String countShopsString=br.readLine();
	  int countShops = Integer.parseInt(countShopsString);
	  for(int i=0;i<countShops;i++) {
		  String getN_K_V=br.readLine();
		  String array[]=getN_K_V.split(" ");
		  int N = Integer.parseInt(array[0]);
		  int K = Integer.parseInt(array[1]);
		  long V = Long.parseLong(array[2]);
		  long V_N=((V-1)*K)%(N);
		  String arrayPlaces[]=new String[N];
		  for(int j=0;j<N;j++) {
			  arrayPlaces[j]=br.readLine();
		  }
		  long arrayItems[]=new long[K];
		  for(int z=0;z<K;z++) {
			  arrayItems[z]= (V_N+z)%N;
		  }
		  Arrays.sort(arrayItems);
		  System.out.print("Case #"+(i+1)+": ");
		  for(int j=0;j<arrayItems.length;j++) {
			  if(j==arrayItems.length-1)
				  System.out.print(arrayPlaces[(int)arrayItems[j]]);
			  else
				  System.out.print(arrayPlaces[(int)arrayItems[j]]+" ");
		  }
		  if(i!=countShops-1)
		  System.out.println("");
		  /*int countShops = Integer.parseInt(countShopsString);*/
	  }
	  
	  /*while ((st = br.readLine()) != null)
	    System.out.println(st);*/
	  }
	
}
