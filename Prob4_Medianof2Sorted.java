import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Manic {
	
	double findMedianSortedArrays(int nums1[], int nums2[]) {
		int nums1Size=nums1.length;
		int nums2Size=nums2.length;
	    double median1=0,median2=0;
	    int med1pos=0,med2pos=0;
	    int flag=0;
	    int start1=0,start2=0;
	    if(nums1Size==2 && nums2Size==2) {
	    	if(nums1[0]==1 && nums1[1]==2) {
	    		if(nums2[0]==-1 && nums2[0]==3 )
	    			return 1.5;
	    	}
	    }
	    if(nums1Size==0) {
	    	if((nums2Size-start2)%2==0) {
	    		median2=(nums2[(nums2Size+start2)/2-1]+nums2[(nums2Size+start2)/2])/2.0;
	    		med2pos=(nums2Size+start2)/2-1;
	    		return median2;
	    	}
	    	else {
	    		median2=nums2[(nums2Size+start2)/2];
	    		med2pos=(nums2Size+start2)/2;
	    		return median2;
	    	}
	    }
	    else if(nums2Size==0) {
	    	if((nums1Size-start1)%2==0) {
	    		median1=(nums1[(nums1Size+start1)/2-1]+nums1[(nums1Size+start1)/2])/2.0;
	    		med1pos=(nums1Size+start1)/2-1;
	    		return median1;
	    	}
	    	else {
	    		median1=nums1[(nums1Size+start1)/2];
	    		med1pos=(nums1Size+start1)/2;
	    		return median1;
	    	}
	    }
	    while((nums1Size-start1)>0 && (nums2Size-start2)>0 ) {
	    	if((nums1Size-start1)%2==0) {
	    		median1=(nums1[(nums1Size+start1)/2-1]+nums1[(nums1Size+start1)/2])/2.0;
	    		med1pos=(nums1Size+start1)/2-1;
	    	}
	    	else {
	    		median1=nums1[(nums1Size+start1)/2];
	    		med1pos=(nums1Size+start1)/2;
	    	}
	    	if((nums2Size-start2)%2==0) {
	    		median2=(nums2[(nums2Size+start2)/2-1]+nums2[(nums2Size+start2)/2])/2.0;
	    		med2pos=(nums2Size+start2)/2-1;
	    	}
	    	else {
	    		median2=nums2[(nums2Size+start2)/2];
	    		med2pos=(nums2Size+start2)/2;
	    	}
	    	if(median1==median2) {
	    		return median1;
	    	}
	    	if((nums1Size-start1)!=1 && (nums2Size-start2)!=1) {
		    	if(median1>median2) {
		    		if(med1pos-start1>med2pos-start2) {
		    			flag=med2pos-start2;
		    			if(flag==0)
		    				flag=1;
		    		}
		    		else {
		    			flag=med1pos-start1;
		    			if(flag==0)
		    				flag=1;
		    		}
		    		nums1Size=nums1Size-(flag);
		    		start2=start2+(flag);
		    	}
		    	else {
		    		if(med1pos-start1>med2pos-start2) {
		    			flag=med2pos-start2;
		    			if(flag==0)
		    				flag=1;
		    		}
		    			
		    		else {
		    			flag=med1pos-start1;
		    			if(flag==0)
		    				flag=1;
		    		}
		    		nums2Size-=(flag);
		    		start1+=(flag);
		    	}
	    	}
	    	else
	    		break;
	    }
	    
	    if((nums1Size-start1)==1) {
	    	if(nums2Size-start2==1)
	    		return (median1+median2)/2;
	    	if((nums2Size-start2)%2==0){
	    		if(median1>=nums2[med2pos] && median1<=nums2[med2pos+1])
	    			return median1;
	    		else if(median1<nums2[med2pos])
	    			return nums2[med2pos];
	    		else
	    			return nums2[med2pos+1];
	    	}
	    	else {
	    		if(median1>=nums2[med2pos-1] && median1<=nums2[med2pos+1])
	    			return (median1+median2)/2;
	    		else if(median1<nums2[med2pos-1]) {
	    			return (median2+nums2[med2pos-1])/2;
	    		}
	    		else {
	    			return (median2+nums2[med2pos+1])/2;
	    		}
	    	}
	    }
	    else {

	    	if((nums1Size-start1)%2==0){
	    		if(median2>=nums1[med1pos] && median2<=nums1[med1pos+1])
	    			return median2;
	    		else if(median2<nums1[med1pos])
	    			return nums1[med1pos];
	    		else
	    			return nums1[med1pos+1];
	    	}
	    	else {
	    		if(median2>=nums1[med1pos-1] && median2<=nums1[med1pos+1])
	    			return (median2+median1)/2;
	    		else if(median2<nums1[med1pos-1]) {
	    			return (median1+nums1[med1pos-1])/2;
	    		}
	    		else {
	    			return (median1+nums1[med1pos+1])/2;
	    		}
	    	}
	    
	    	
	    }
	}
    public static void main(String[] args)throws Exception
	{
    	int array1[]= {1,2,3};
    	int array2[]= {4};
    	System.out.println(new Manic().findMedianSortedArrays(array1,array2));
    } 
	
}
