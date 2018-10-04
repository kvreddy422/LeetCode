import java.util.ArrayList;
import java.util.List;

class GFG {
   public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        int[] num = new int[9];
        for(int i = 0; i < 9; i++){
            num[i] = i ;
        }
        helperComSum3(res, new ArrayList<Integer>(), k, n, num, 0);
        return res;
    }
    public static void helperComSum3(List<List<Integer>> res, List<Integer> cur, int k, int n, int[] num, int index){
        if(k == 0 && n == 0){
            res.add(new ArrayList<Integer>(cur));
            return;
        }else if(n > 0 && k > 0){
            for(int i = index; i<num.length; i++){
                if(num[i] > n) break;
                cur.add(num[i]);
                helperComSum3(res, cur, k -1 , n - num[i], num, i+1);
                cur.remove(cur.size() -1);
            }
        }

    }
    public static void main (String[] args)  
    { 
        int n = 5; 
        combinationSum3(3,6); 
    }
}