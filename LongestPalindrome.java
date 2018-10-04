import java.lang.*;
import java.io.*;
import java.util.*;
public class LongestPalindrome {
	public String longestPalindrome(String s) {
        if(reverseString(s)==s)
            return s;
        else
        {
            int length=s.length();
            
            for(int i=0;i<length;i++)
            {
            	int a=length-i;
            	int initial=0;
            	int j=0;
                while(j<length)
                {
                	String returnedString = reverseString(s.substring(initial,a));
                if( returnedString.equalsIgnoreCase(s.substring(initial,a)))
                    return  s.substring(initial,a);
                initial++;
                a++;
                if(a>length)
                	break;
                }    
            }
            return "NULL";
        }
    }
    
    public String  reverseString(String s)
    {
        byte [] strAsByteArray = s.getBytes();
 
        byte [] result = 
                   new byte [strAsByteArray.length];
 
        // Store result in reverse order into the
        // result byte[]
        for (int i = 0; i<strAsByteArray.length; i++)
            result[i] = 
             strAsByteArray[strAsByteArray.length-i-1];
 
        return(new String(result));
    }
    public static void main (String args [])
    {
    	LongestPalindrome lPalindrome=new LongestPalindrome();
    	String lp=lPalindrome.longestPalindrome("abcddddscba");
    	System.out.print(lp);
    }
}
