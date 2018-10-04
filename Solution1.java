import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

public class Solution1{
    public static void main(String [] args){
        solution(56,8,"???8???");
    }
    public static void solution(int work_hours, int day_hours, String pattern){
        int sum = 0;
        int count = 0;
        for(Character c : pattern.toCharArray()){
            if(!c.equals('?'))
                sum += Character.getNumericValue(c);
            else
                count += 1;
        }
        System.out.println(sum);
        System.out.println(count);
        int hours_remaining = work_hours - sum;
    
        ArrayList<String> list = findCombinations(hours_remaining,count,day_hours);
        System.out.println(list.size());
        Set<String> set = new HashSet<String>(list);

        List<String> mainList = new ArrayList<String>();
        mainList.addAll(set);

        Collections.sort(mainList);

        for(String s : mainList){
            System.out.println(s);
        }


    }
       public static ArrayList<String> findCombinations(int sumTo, int n, int maxhoursinaday) 
{ 
    // array to store the combinations 
    // It can contain max n elements 
    int arr[] = new int [n]; 
    ArrayList<String> s = new ArrayList<String>();
    // find all combinations 
    findCombinationsUtil(arr, 0, sumTo, sumTo, maxhoursinaday, s);
    return s; 
} 
    public static void findCombinationsUtil(int arr[], int index, 
                                 int num, int reducedNum, int maxhoursinaday, ArrayList<String> s) 
    {    
    // Base condition 
    if (reducedNum < 0) 
        return; 
  
    // If combination is  
    // found, print it 
    if (reducedNum == 0) 
    { 
        String str = "";
        for (int i = 0; i < index; i++) 
                str += arr[i]; 
            s.add(str); 
        return; 
    } 
  
    // Find the previous number  
    // stored in arr[]. It helps  
    // in maintaining increasing  
    // order 
    int prev = (index == 0) ?  
                          1 : arr[index - 1]; 
  
    // note loop starts from  
    // previous number i.e. at 
    // array location index - 1 
    for (int k = prev; k <= num && k<=maxhoursinaday ; k++) 
    { 
        // next element of 
        // array is k 
        arr[index] = k; 
  
        // call recursively with 
        // reduced number 
        if(index <= arr.length - 2 )
            findCombinationsUtil(arr, index + 1, num, 
                                 reducedNum - k,maxhoursinaday, s); 
    } 
    } 
 
}