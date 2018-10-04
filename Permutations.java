import java.util.ArrayList;
import java.util.List;

// Java program to print all permutations of a 
// given string. 
public class Permutations
{ 
    public static void main(String[] args) 
    { 
        String str = ""; 
        int n = str.length(); 
        Permutations permutation = new Permutations(); 
        List<String> result = permutation.findSchedules(40,10,"a???bc"); 
    } 
    
    public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
    	 // Write your code here
    		 List<String> result=new ArrayList<>();
    		 int total = 0;
    		 int noofQuestionMarks=0;
    		 for(int i=0;i<pattern.length();i++) {
    			 if(pattern.charAt(i)!='?')
    				 total+=Character.getNumericValue(pattern.charAt(i));
    			else
    				noofQuestionMarks++;		 
    		 }
    		 int totLeft = workHours - total;
    		 StringBuilder myName = new StringBuilder(pattern);
//    		 myName.setCharAt(0, 'x');
    		 ArrayList<Integer> arr = new ArrayList<Integer>();
    		 arr.add(1);
    		 arr.add(2);
    		 System.out.println(total);
    		 Solution sol = new Solution();
    		 sol.permute(myName.toString(), 0, myName.toString().length()-1,result);
    		 return result;
    	 }
  
    /** 
     * permutation function 
     * @param str string to calculate permutation for 
     * @param l starting index 
     * @param r end index 
     */
    private void permute(String str, int l, int r) 
    { 
        if (l == r) 
            System.out.println(str); 
        else
        { 
            for (int i = l; i <= r; i++) 
            { 
                str = swap(str,l,i); 
                permute(str, l+1, r); 
                str = swap(str,l,i); 
            } 
        } 
    } 
  
    /** 
     * Swap Characters at position 
     * @param a string value 
     * @param i position 1 
     * @param j position 2 
     * @return swapped string 
     */
    public String swap(String a, int i, int j) 
    { 
        char temp; 
        char[] charArray = a.toCharArray(); 
        temp = charArray[i] ; 
        charArray[i] = charArray[j]; 
        charArray[j] = temp; 
        return String.valueOf(charArray); 
    } 
  
} -