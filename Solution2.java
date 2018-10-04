import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Result {
    public List<List<Integer>> combinationSum(int[] candidates, int target, int nq) {
        List<List<Integer>> result=new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        List<Integer> tempResult=new ArrayList<Integer>();
        createCombinationSum(candidates,target,result,tempResult,0,nq);
        return result;
    }
    public void createCombinationSum(int[] candidates, int target,List<List<Integer>> result, List<Integer> tempResult, int start,int nq){
        if(target<0)    return;
        if(tempResult.size()==nq){
        	if(target==0){
        		Iterator it=tempResult.iterator();
        		int[] newArr=new int[tempResult.size()];
        		int index=0;
        		while(it.hasNext()){
        			newArr[index++]=(int)it.next();
        		}
        		ArrayList<ArrayList<Integer>> temp=pm.permute(newArr);
        		Iterator<ArrayList<Integer>> it1=temp.iterator();
        		while(it1.hasNext()){
        			result.add(new ArrayList<>(it1.next()));
        		}
        		
        	}
        	else{
        		return;
        	}
        }
        else if(target==0 && tempResult.size()==nq){
        	Iterator it=tempResult.iterator();
    		int[] newArr=new int[tempResult.size()];
    		int index=0;
    		while(it.hasNext()){
    			newArr[index++]=(int)it.next();
    		}
    		ArrayList<ArrayList<Integer>> temp=pm.permute(newArr);
    		Iterator<ArrayList<Integer>> it1=temp.iterator();
    		while(it1.hasNext()){
                result.add(new ArrayList<>(tempResult));
    		}
        }
        for(int i=start;i<candidates.length;i++){
            tempResult.add(candidates[i]);
            createCombinationSum(candidates,target-candidates[i],result,tempResult,i,nq);
            tempResult.remove(tempResult.size()-1);
        }
    }
}
public class Sample {

	private static final int[] DATA = {0,1,2,3,4,5,6,7,8};
	public static void main(String[] args) {
		int nq=0;
		String input="???????";
		int max_hours=4;
		int[] candidates=new int[max_hours+1];
		for(int i=0;i<=max_hours;i++){
			candidates[i]=i;
		}
		int wh=24;
		int remainingSum=wh;
		for(int i=0;i<input.length();i++){
			if(input.charAt(i)=='?'){
				nq++;
			}
			else{
				remainingSum = remainingSum-(input.charAt(i)-'0');
			}
		}
		Result s=new Result();
		List<String> finalOutput=new ArrayList<String>();
		List<List<Integer>> result=s.combinationSum(candidates, remainingSum, nq);
		HashSet<String> output=new HashSet<String>();
		Iterator it=result.iterator();
		while(it.hasNext()){
			List<Integer> tempResult=(List<Integer>) it.next();
			int index=0;
			String tempStr="";
			for(int i=0;i<input.length();i++){
				if(input.charAt(i)=='?'){
					tempStr+=tempResult.get(index++);
				}
				else{
					tempStr+=input.charAt(i);
				}
			}
			output.add(tempStr);
		}
		System.out.println(output);
	}

}

class pm{
	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permute(num, 0, result);
		return result;
	}
	 
	static void permute(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
	 
		if (start >= num.length) {
			ArrayList<Integer> item = convertArrayToList(num);
			result.add(item);
		}
	 
		for (int j = start; j <= num.length - 1; j++) {
			swap(num, start, j);
			permute(num, start + 1, result);
			swap(num, start, j);
		}
	}
	 
	private static ArrayList<Integer> convertArrayToList(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}
	 
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}