import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Manic {
	public int maxArea(int[] height) {
        int size=height.length;
        int finalVolume=0;
        for(int i=0;i<size;i++) {
        	for(int j=i+1;j<size;j++) {
        		int tempVolume=0;
        		if(height[i]>=height[j]) {
        			tempVolume=height[j]*(j-i);
        			if(finalVolume<tempVolume) {
        				finalVolume=tempVolume;
        			}
        			
        		}
        		else {
        			tempVolume=height[i]*(j-i);
        			if(finalVolume<tempVolume) {
        				finalVolume=tempVolume;
        			}
        			
        		}
        	}
        }
        return finalVolume;
	}
    public static void main(String[] args)throws Exception
	{
    	int array[]= {1,8,6,2,5,4,8,3,7};
    	System.out.println(new Manic().maxArea(array));
    	System.out.println("49");
    } 
	
}
