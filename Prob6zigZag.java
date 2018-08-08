import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Manic {
public String convert(String s, int numRows) {
	String finStr="";
		if(numRows==1)
			return s;
        int sizeMax=(numRows-1)*2;
        int sizeToggle=sizeMax;
        int stringSize=s.length();
        for(int i=0;i<numRows;i++) {
        	sizeToggle=sizeMax;
        	if(i==0 || i==numRows-1) {
        		int j=i;
        		while(j<stringSize) {
        			finStr+=s.charAt(j);
        			j=j+sizeMax;
        		}
        	}
        	else {
        		int j=i;
        		sizeToggle-=i*2;
        		while(j<stringSize) {
        			finStr+=s.charAt(j);
        			j=j+sizeToggle;
        			sizeToggle=sizeMax-sizeToggle;
        		}
        	}
        }
        return finStr;
}
    public static void main(String[] args)throws Exception
	{
    	
    	System.out.println(new Manic().convert("ABC",1));
    	System.out.println("PAHNAPLSIIGYIR");
    } 
	
}
