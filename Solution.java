import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

class Solution {
	static int preIndex = 0;
	// Return the max Element
	 public int maxTree(TreeNode t) {
		 int max=0;
		 Integer maximum = new Integer(0);
		 maxx(t,maximum);
		 //max=max(t);
		 
		 return maximum;
	 }
	 // Return the max element
	int max(TreeNode t) {
		if(t==null)
			return 0;
		int left=max(t.left);
		int right=max(t.right);
		if(t.val>left && t.val>right)
			return t.val;
		else if(left>right) {
			return left;
		}
		else
			return right;
	}
	// Max element different try
	void maxx(TreeNode t,Integer max) {
		if(t!=null) {
			maxx(t.left,(t.val>max)?t.val:max);
			maxx(t.right,(t.val>max)?t.val:max);
		}
	}
	// Searching for an element in the Tree
	boolean searchTree(TreeNode l1,int search) {
		if(l1!=null) {
			if(l1.val==search)
				return true;
			boolean valRight=searchTree(l1.left, search);
			boolean valLeft=searchTree(l1.right, search);
			if(valLeft || valRight)
				return true;
			else
				return false;
		}
		else
			return false;
		
	}
	
	// Searching for an element in the tree iterative
	boolean searchTree1(TreeNode tree,int search) {
		if(tree==null)
			return false;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(tree);
		while(!q.isEmpty()) {
			tree = q.remove();
			if(tree.val==search)
				return true;
			if(tree.left!=null) {
				q.add(tree.left);
			}
			if(tree.right!=null) {
				q.add(tree.right);
			}
		}
		return false;
		
	}
	
	int heightRec(TreeNode t) {
		if(t==null)
			return 0;
		int left=heightRecBranch(t.left);
		int right=heightRecBranch(t.right);
		return left+right+1;
	}
	int heightRecBranch(TreeNode t) {
		if(t==null)
			return 0;
		int left=heightRecBranch(t.left);
		int right=heightRecBranch(t.right);
		if(left>right)
			return left+1;
		else
			return right+1;
	}
	
	int heightIterate(TreeNode t) {
		int level=1;
		if(t==null)
			return 0;
		Queue<TreeNode> q=new LinkedList<>();
		q.add(t);
		q.add(null);
		while(!q.isEmpty()) {
			TreeNode root=q.remove();
			if(root==null) {
				if(!q.isEmpty()) {
					level++;
					q.add(null);
				}
			}
			else {
				if(root.left!=null)
					q.add(root.left);
				if(root.right!=null)
					q.add(root.right);
			}
		}
		return level;
	}
	int sizeRec(TreeNode t) {
		if(t==null)
			return 0;
		return (1+sizeRec(t.left)+sizeRec(t.right));
	}
	int sizeIterate(TreeNode tree) {
		if(tree==null)
			return 0;
		int height=0;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(tree);
		while(!q.isEmpty()) {
			tree = q.remove();
			height++;
			if(tree.left!=null)
				q.add(tree.left);
			if(tree.right!=null)
				q.add(tree.right);			
		}
		return height;
		
	}
	
	TreeNode ancestors(TreeNode root,TreeNode node1,TreeNode node2) {
		if(root==null)
			return root;
		if(root==node1 || root== node2)
			return root;
		TreeNode left=ancestors(root.left, node1, node2);
		TreeNode right=ancestors(root.right, node1, node2);
		if(left!=null && right!=null)
			return root;
		else
			return (left!=null ? left:right);
	}
	
	TreeNode constructTree(int [] preOrder,int [] inOrder,int start,int stop) {
		if(start>stop) {
			return null;
		}
		TreeNode t1=new TreeNode(preOrder[preIndex++]);
		int index=search(preOrder,start,stop,t1.val);
		t1.left=constructTree(preOrder, inOrder,start,index-1 );
		t1.right=constructTree(preOrder, inOrder, index+1,stop);
		return t1;
	}
	
	int binarySearch(int [] preOrder,int searchElement,int min,int max) {
		if(max>=min) {
			int mid=(min+max)/2;
			if(preOrder[mid]==searchElement)
				return mid;
			if(searchElement<preOrder[mid])
				return binarySearch(preOrder,searchElement,min,mid-1);
			else
				return binarySearch(preOrder, searchElement, mid+1, max);
		}
		return 0;
	}
	
	int search(int arr[], int strt, int end, int value) 
    {
        int i;
        for (i = strt; i <= end; i++) 
        {
            if (arr[i] == value)
                return i;
        }
        return i;
    }
  
	
	int diameterLeaves(TreeNode t) {
		if(t==null)
			return 0;
		int tot=diameterLeavesNodes(t.left)+1+diameterLeavesNodes(t.right);
		int lefter=diameterLeaves(t.left);
		int righter=diameterLeaves(t.right);
		if(tot>lefter && tot>righter)
			return tot;
		else if(lefter>righter)
			return lefter;
		else
			return righter;
	}
	
	int diameterLeavesNodes(TreeNode t) {
		if(t==null)
			return 0;
		int left=diameterLeavesNodes(t.left);
		int right=diameterLeavesNodes(t.right);
		if(left>right)
			return left+1;
		else 
			return right+1;
	}
	boolean rootToLeafSum(TreeNode t, int sum) {
		boolean trueSum=rootLeafSum(t,0,sum);
		return trueSum;
	}
	boolean rootLeafSum(TreeNode t,int sum,int total) {
		if(t==null) {
			return false;
		}
		if(t.left==null && t.right==null && sum+t.val==total)
			return true;
		boolean left=rootLeafSum(t.left, sum+t.val, total);
		boolean right=rootLeafSum(t.right, sum+t.val, total);
		if(left==true || right==true)
			return true;
		else 
			return false;
		
	}
	public int [] verticalSum(TreeNode root) {
		int left=-1;
		int right=-1;
		TreeNode rootChange=root;
		while(rootChange!=null) {
			rootChange=rootChange.left;
			left++;
		}
		rootChange=root;
		while(rootChange!=null) {
			rootChange=rootChange.right;
			right++;
		}
		
		int [] array = new int[left+1+right];
		array=varticalSumRec(root,left,array);
		return array;
	}
	public int [] varticalSumRec(TreeNode root,int position,int[] array) {
		if(root==null) {
			return array;
		}
			array[position]+=root.val;
			array=varticalSumRec(root.left,position-1,array);
			array=varticalSumRec(root.right,position+1,array);
			return array;
	}
	
	void printInorder(TreeNode node) 
    {
        if (node == null)
            return;
  
        /* first recur on left child */
        printInorder(node.left);
  
        /* then print the data of node */
        System.out.print(node.val + " ");
  
        /* now recur on right child */
        printInorder(node.right);
    }
	
	//Brute Force Method LeetCode 
	public int lengthOfLongestSubstring(String s) {
		if(s=="")
			return 0;
		if(s==" ")
			return 1;
		int max=0;
		for(int i=0;i<s.length();i++) {
			for(int j=s.length()-1;j>=i;j--) {
				boolean result=findUniqueString(s,i,j);
				if(result==true) {
					if(Math.abs(i-j)>max)
						max=Math.abs(i-j);
				}
			}
		}
        return max;
    }
	
	public boolean findUniqueString(String s,int start,int end) {
		 Set<Character> h = new HashSet<>();
		 for (int i=start;i<end;i++) {
			 Character ch = s.charAt(i);
			 if(h.contains(ch))
				 return false;
			 h.add(ch);
		 }
		 return true;
	}
	int prob26RemoveDup(int [] array) {
		if(array.length==0)
			return 0;
		if(array.length==1)
			return 1;
		int prev=array[0],present;
		int count=1;
		for(int i=1;i<array.length;i++) {
			present=array[i];
			if(present!=prev) {
				count++;
				prev=present;
				continue;
			}
		}
		return count;
	}
	
boolean sumofPath(TreeNode t,int total,int count) {
	if(t==null)
		return false;
	if(t.left==null && t.right== null && count+t.val==total) {
		return true;
	}
	boolean left=sumofPath(t.left, total, count+t.val);
	boolean right=sumofPath(t.right, total, count+t.val);
	if(left ||right) {
		return true;
	}
	else
		return false;
}

boolean isTreeSimilar(TreeNode t1, TreeNode t2) {
	if(t1==null && t2==null)
		return true;
	if(t1==null || t2==null)
		return false;
	return (t1.val==t2.val && isTreeSimilar(t1.left, t2.left) && isTreeSimilar(t1.right, t2.right));
}
	public boolean leetCode20_Valid_Paranthesis(String para) {
		int length=para.length();
		Stack<Character> s= new Stack<>();
		for(int i=0;i<length;i++) {
			if(para.charAt(i)=='{' || para.charAt(i)=='[' || para.charAt(i)=='(' ) {
				s.push(para.charAt(i));
				continue;
			}
			if(para.charAt(i)=='}' || para.charAt(i)==']' || para.charAt(i)==')' ) {
				if(s.isEmpty()) {
					return false;
				}
				if(para.charAt(i)=='}') { 
					if(s.pop().equals('{')) {
						continue;
					}
					else 
						return false;
				}
				if(para.charAt(i)==']') {
					if(s.pop().equals('[')) {
						continue;
					}
					else 
						return false;
				}
				if(para.charAt(i)==')') {
					if(s.pop().equals('(')) {
						continue;
					}
					else 
						return false;
				}
			}
		}
		if(s.isEmpty())
			return true;
		else
			return false;
	}
	public int searchInsert(int[] nums, int target) {
        int i;
        for(i=0;i<nums.length;i++){
            if(nums[i]>target){
                if(i==0)
                    return 0;
                return i-1;
            }
            if(nums[i]==target)
                return i;
        }
        return i;
    }
	
public int leetCode35_searchInsert(int[] nums, int target) {
	int index=binarySearch2(nums, target, 0, nums.length-1);
	if(index==-1) {
		if(target>nums[0])
			return nums.length;
		else
			return 0;
	}
	return index;    
    }

public int binarySearch2(int [] array,int searchVal,int start,int end) {
	if(end>start) {
		int mid=(start+end)/2;
		if(array[mid]==searchVal) {
			return mid;
		}
			
			if(array[mid]<searchVal && array[mid+1]>searchVal) {
				return mid+1;
			}
		if(array[mid]<searchVal)
			return binarySearch2(array, searchVal, mid+1, end);
		if(array[mid]>searchVal)
			return binarySearch2(array, searchVal, start, mid-1);
	}
	
	return -1;
}
public void  mergeSort(int [] array,int start,int end) {
	if(end>start) {
		int mid=(start+end)/2;
		mergeSort(array, start, mid);
		mergeSort(array, mid+1, end);
		merge(array,start,mid,end);
	}
}

public void merge(int [] array, int start, int mid, int end) {
	int left_size=mid-start+1;
	int right_size=end-mid;
	int [] L = new int[left_size];
	int [] R = new int[right_size];
	for (int i=0;i<left_size;i++) {
		L[i]=array[start+i];
	}
	for (int i=0;i<right_size;i++) {
		R[i]=array[mid+1+i];
	}
	int i=0;
	int j=0;
	int k=start;
	while(i<left_size && j<right_size) {
		if(L[i]>R[j]) {
			array[k]=R[j];
			k++;
			j++;
		}
		else {
			array[k]=L[i];
			k++;
			i++;
		}
	}
	while(i<left_size) {
		array[k]=L[i];
		k++;
		i++;
	}
	while(j<right_size) {
		array[k]=R[j];
		k++;
		j++;
	}
}

int levelMaxSum(TreeNode t) {
	if(t==null)
		return 0;
	int sum=getLevelSum(t,0,0);
	return sum;
}
int getLevelSum(TreeNode t,int count,int max) {
	if(t==null)
		return max;
	if(t.left==null && t.right==null) {
		if(max<count+t.val) {
			max=count+t.val;
		}
		return max;
	}
	max=getLevelSum(t.left, count+t.val, max);
	max=getLevelSum(t.right, count+t.val, max);
	return max;
}
public TreeNode mirrorTreeConvert(TreeNode l1){
	if(l1==null)
		return null;
	TreeNode t2=new TreeNode(l1.val);
	t2.right=mirrorTreeConvert(l1.left);
	t2.left=mirrorTreeConvert(l1.right);
	return t2;
}
public void preOrderPrint(TreeNode t) {
	if(t!=null) {
		System.out.println(t.val);
		preOrderPrint(t.left);
		preOrderPrint(t.right);
	}	
}
boolean checkifMirrors(TreeNode t1, TreeNode t2) {
	if(t1== null && t2== null) {
		return true;
	}
	if(t1==null || t2==null)
		return false;
	boolean left=checkifMirrors(t1.right, t2.left);
	boolean right=checkifMirrors(t1.left, t2.right);
	if(t1.val==t2.val && left && right) {
		return true;
	}
	else
		return false;
	
}
public int removeDuplicates(int[] nums) {
	  	
		int length=0;
		if(nums.length==0 || nums.length==1) {
			return nums.length;
		}
		int prev=nums[0];
		int next=nums[1];
		int count=1;
		int dubRemove=0;
		if(prev==next) {
			count++;
		}
		for(int i=1;i<nums.length-1;i++) {
			prev=next;
			next=nums[i+1];
			if(prev!=next)
				count=1;
			else
				count++;
			
			if(count>2) {
				dubRemove++;
			}
			nums[i+1-dubRemove]=nums[i+1];
		}
		return nums.length-dubRemove;
	    
    
}

public boolean canJump(int[] nums) {
	for(int i=0;i<nums.length-1;i++) {
		if(nums[i]!=0) {
			continue;
		}
		int counter=0;
		int j=i-1;
		for(j=i-1;j>=0;j--) {
			if(nums[j]-counter>1) {
				break;
			}
			counter++;
		}
		if(j<0)
			return false;
		
	}
    return true;
}

public int maxProfit(int[] prices) {
	if(prices.length<2)
		return 0;
	int count=0;
	int prev=0,next=0;
	if(prices.length>=2) {
		prev=prices[0];
		next=prices[1];
	}
	for(int i=1;i<prices.length-1;i++) {
		if(next>prev) {
			count+=(next-prev);
		}
		prev=next;
		next=prices[i+1];
	}
	if(next>prev) {
		count+=(next-prev);
	}
    return count;
}

//public List<Integer> spiralOrder(int[][] matrix) {
//	List<Integer> l = new LinkedList<>();
//	int row=matrix.length;
//	if(row==0) {
//		return l;
//	}
//	int columns=matrix[0].length;
//	int ru=0,rd=row-1;
//	int cl=0,cr=columns-1;
//	if(row==1){
//        for(int i=0;i<row;i++){
//            l.add(matrix[i][0]);
//        }
//        return l;
//            
//    }
//	if(columns==1){
//        for(int i=0;i<columns;i++){
//            l.add(matrix[0][i]);
//        }
//        return l;
//            
//    }
//	while(cl<cr || ru<rd) {
//		for(int i=cl;i<=cr;i++) {
//			l.add(matrix[ru][i]);
//		}
//		for(int i=ru+1;i<=rd;i++) {
//			l.add(matrix[i][cr]);
//		}
//		for(int i=cr-1;i>=cl;i--) {
//			l.add(matrix[rd][i]);
//		}
//		ru++;
//		for(int i=rd-1;i>=ru;i--) {
//			l.add(matrix[i][cl]);
//		}
//		rd--;
//		cl++;
//		cr--;
//	}
//	return l;
//}

public List<Integer> spiralOrder(int[][] matrix) {
    
	List<Integer> l = new LinkedList<>();
	int row=matrix.length;
        if(row==0) {
		return l;
	}
	int columns=matrix[0].length;
        
	int ru=0,rd=row;
	int cl=0,cr=columns;
	if(row==1){
        for(int i=0;i<columns;i++){
            l.add(matrix[0][i]);
        }
        return l;
            
    }
	if(columns==1){
        for(int i=0;i<row;i++){
            l.add(matrix[i][0]);
        }
        return l;
            
    }
	while(cl<cr || ru<rd) {
        cr--;
		for(int i=cl;i<=cr;i++) {
			l.add(matrix[ru][i]);
		}
        rd--;
        if(!(cl<=cr && ru<=rd))
        	break;
		for(int i=ru+1;i<=rd;i++) {
			l.add(matrix[i][cr]);
		}
		for(int i=cr-1;i>=cl;i--) {
			l.add(matrix[rd][i]);
		}
		ru++;
		if(!(cl<=cr && ru<=rd))
        	break;
		for(int i=rd-1;i>=ru;i--) {
			l.add(matrix[i][cl]);
		}
		cl++;
	}
	return l;

        
    }

static String findMaxGoalsProbability(List<Integer> teamGoals) {
    if(teamGoals.size()==0)
    return "0.00";
    if(teamGoals.size()==1)
    return "0.00";
    int size=teamGoals.size()*(teamGoals.size()-1)/2;
    Collections.sort(teamGoals);
    int max1=1;
    int max2=0;
    int flag=1;
    int a=0;
    int flag1=1;
    for(int i=teamGoals.size()-2;i>=0;i--) {
    	if(teamGoals.get(i)==teamGoals.get(i+1) && flag==1){
    		max1++;
    	}
    	else {
    		if(flag1==1)
    		{
    			flag=0;
    			max2++;
        		a++;
        		flag1=0;
        		continue;	
    		}
       	}
    	if(teamGoals.get(i)==teamGoals.get(i+1) && a==1){
    		max2++;
    	}
    	else {
    		a++;	
    	}
    }
    double counter=0;
    if(max1>1) {
    	counter=max1*(max1-1)/2;
    }
    else {
    	counter=max2;
    }
double result = counter/size;
String out =Double.toString(result);
if(out.length()==3)
    out=out+"0";
return  out.substring(0, 4);


}

//Complete the worstPerformingStock function below.
static int worstPerformingStock(List<List<Integer>> prices) {
	int size=prices.size();
	if(size==0)
		return 0;
	double WPS=Double.MAX_VALUE;
	int WPS_Stock=0;
	for(int i=0;i<size;i++) {
		List<Integer> price = prices.get(i);
		int Id=price.get(0);
		double CP=price.get(1);
		double SP=price.get(2);
		if(((SP-CP)/CP)<WPS) {
			WPS=((SP-CP)/CP);
			WPS_Stock=Id;
		}
	}
return WPS_Stock;
}



static int differentTeams(String skills) {
	int [] array= {0,0,0,0,0};
	for(int i=0;i<skills.length();i++) {
		if(skills.charAt(i)=='p')
			array[0]++;
		if(skills.charAt(i)=='c')
			array[1]++;
		if(skills.charAt(i)=='m')
			array[2]++;
		if(skills.charAt(i)=='b')
			array[3]++;
		if(skills.charAt(i)=='z')
			array[4]++;
	}
	Arrays.sort(array);
	return array[0];

}
static String winner(String erica,String bob) {
	int ericaScore=0;
	int[] eArray = new int[3];
	int bobScore=0;
	int[] bArray = new int[3];
	for(int i=0;i<erica.length();i++) {
		if(erica.charAt(i)=='E') {
			ericaScore+=1;
			eArray[0]++;
		}
		if(erica.charAt(i)=='M') {
			ericaScore+=3;
			eArray[1]++;
		}
		if(erica.charAt(i)=='H') {
			ericaScore+=5;
			eArray[2]++;
		}
	}
	for(int i=0;i<bob.length();i++) {
		if(bob.charAt(i)=='E') {
			bobScore+=1;
			bArray[0]++;
		}
		if(bob.charAt(i)=='M') {
			bobScore+=3;
			bArray[1]++;
		}
		if(bob.charAt(i)=='H') {
			bobScore+=5;
			bArray[2]++;
		}
	}
	
	if(ericaScore>bobScore) {
		return "Erica";
	}
	else if(bobScore>ericaScore) {
		return "Bob";
	}
	else {
		if(bArray[2]>eArray[2]) {
			return "Bob";
		}
		if(eArray[2]>bArray[2]) {
			return "Erica";
		}
		if(bArray[1]>eArray[1]) {
			return "Bob";
		}
		if(eArray[1]>bArray[1]) {
			return "Erica";
		}
		if(bArray[0]>eArray[0]) {
			return "Bob";
		}
		if(eArray[0]>bArray[0]) {
			return "Erica";
		}
	}
	
	return "Tie";
	
}

static long playlist(List<Integer> songs) {
int size=songs.size();
    Map<Integer, Integer> hash=new HashMap<>();
    long count=0;
    for(int i=0;i<size;i++) { 
        int key=songs.get(i)%60;
        Integer val1=hash.get(key);
        if(val1==null)
            val1=0;
        hash.put(key,val1+1);
        
    }
    for(int i=1;i<=29;i++) {
        if(hash.get(i)==null)
            continue;
        Long count1=(long)hash.get(i);
        if(hash.get(60-i)==null)
            continue;
        Long count2=(long)hash.get(60-i);
        if(count1!=null && count2!=null) {
            count+=count1*count2;
        }
    }
    Long count1=(long)0;
        if(hash.get(0)!=null)
            count1=(long)hash.get(0);
    Long count2=(long)0;
        if(hash.get(30)!=null)
            count2=(long)hash.get(30);
    if(count1!=null) {
        count+=(count1*(count1-1)/2);
    }
    if(count2!=null) {
        count+=(count2*(count2-1)/2);
    }
    
    return count;
}
 static List<String> charity(List<Float> profits){
	 int size=profits.size();
	 List<String> res=new ArrayList<>();
	 float [] arr= {0,0,0};
	 for(int i=0;i<size;i++) {
		 if(arr[0]<=arr[1] && arr[0]<=arr[2]) {
			 arr[0]+=profits.get(i);
			 res.add("A");
			 continue;
		 }
		 if(arr[1]<=arr[2]) {
			 arr[1]+=profits.get(i);
			 res.add("B");
			 continue;
		 }
		 else {
			 arr[2]+=profits.get(i);
			 res.add("C");
		 }
	 }
	 return res;
 }
 
 static List<Integer> allocateSchools(List<Integer> schoolSeatsArray, List<Integer> studentScoreArray, List<List<Integer>> studentSchoolPreferencesArray) {
	 HashMap<Integer, Integer> schoolH=new HashMap<>();
	 int schoolsAlloted =0;
	 int studentAlloted =0;
	 int schoolsAllotedTot =0;
	 int studentAllotedTot =0;
	 int sizeSchool = schoolSeatsArray.size();
	 for(int i=0;i<sizeSchool;i++) {
		 schoolsAllotedTot+=schoolSeatsArray.get(i);
		 schoolH.put(i, schoolSeatsArray.get(i));	 
 }
	 HashMap<Integer, Integer> studentH=new HashMap<>();
	 int sizeStudent = studentScoreArray.size();
	 for(int i=0;i<sizeStudent;i++) {
		 studentAllotedTot+=studentScoreArray.get(i);
		 studentH.put(studentScoreArray.get(i),i );	 
 }
	 Collections.sort(studentScoreArray); 
	 Stack<Integer> stack= new Stack<>();
	 for(int i=0;i<sizeStudent;i++) {
		 stack.push(studentScoreArray.get(i));
	 }
	 int sizeAssigned= studentSchoolPreferencesArray.size();
	 for(int i=0;i<sizeAssigned;i++) {
		 List<Integer> teamGoals = new ArrayList<>();
		 Integer scoreofTop = stack.pop();
		 Integer getId=studentH.get(scoreofTop);
		 teamGoals = studentSchoolPreferencesArray.get(getId);
		 Integer tG0=teamGoals.get(0);
		 Integer schoolID = schoolH.get(tG0);
		 Integer schoolValue=schoolSeatsArray.get(schoolID);
		 if(schoolID>0) {
			 schoolsAlloted++;
			 studentAlloted++;
			 schoolSeatsArray.set(tG0, schoolID-1);
			 continue;
		 }
		 Integer tG1=teamGoals.get(1);
		 Integer schoolID1 = schoolH.get(tG1);
		 Integer schoolValue1=schoolSeatsArray.get(schoolID);
		 if(schoolID1>0) {
			 schoolsAlloted++;
			 studentAlloted++;
			 schoolSeatsArray.set(tG1, schoolID1-1);
			 continue;
		 }
		 Integer tG2=teamGoals.get(2);
		 Integer schoolID2 = schoolH.get(tG2);
		 Integer schoolValue2=schoolSeatsArray.get(schoolID);
		 if(schoolID2>0) {
			 schoolsAlloted++;
			 studentAlloted++;
			 schoolSeatsArray.set(tG2, schoolID2-1);
			 continue;
		 }
		 
	 }
	 List<Integer> result = new ArrayList<>();
	 result.add(schoolsAllotedTot-schoolsAlloted);
	 result.add(sizeStudent-studentAlloted);
	 return result;
	 
 }
 
 /*static int maxDifferenceOddEven(List<Integer> a) {
	 int posE=0;
		int posO=0;
		for(int i=0;i<a.size();i++) {
			if(a.get(i)%2!=0) {
				posO=i;
				break;
			}
		}
		int z=posO+1;
		for(z=posO+1;z<a.size();z++) {
			if(a.get(z)%2!=0) {
				continue;
			}
			else
				break;
		}
		if(z==a.size())
			return -1;
		int even=0;
		int odd =0;
		HashMap<Integer, Integer> h=new HashMap<>();
		int oArr[]=new int[a.size()];
		int eArr[]=new int[a.size()];
		for(int i=0;i<a.size();i++) {
			if(a.get(i)%2==0) {
				eArr[even]=a.get(i);
				even++;
				if(h.get(a.get(i))!=null) {
					continue;
				}
				h.put(a.get(i), i);
			}
			else {
				oArr[odd]=a.get(i);
				odd++;
				if(h.get(a.get(i))!=null) {
					continue;
				}
				h.put(a.get(i), i);
			}
		}
		
		Arrays.sort(eArr);
		Arrays.sort(oArr);
		int maxCount=0;
		for(int i=even;i>=0;i--) {
			int counter=0;
			for(int j=0;j<=odd;j++) {
				int eO=h.get(eArr[i]);
				int oO=h.get(oArr[j]);
				if(eO>oO) {
						counter=eArr[i]-oArr[j];
						if(maxCount>counter) {
							maxCount=counter;
						}
				}
			}
		}
		return maxCount;

 }
 
*/
// static int maxDifferenceOddEven(List<List<Integer>> prices) {
//	 int count=1;
//		
//		for(count=1;count<prices.size();count++)
//		{
//			if(prices.get(count)%2==0)
//				continue;
//			else
//				break;
//		}
//
//  int max=Integer.MIN_VALUE;
//  
// // if(count==a.size())
// 	 
//	int min=prices.get(count);
//	 
//	  int i=count;
//	  
//	  if(i==prices.size()-1)
//		  return -1;
//	  else
//		  i=count+1;
//	  
//	  
//	 while(i<prices.size())
//	 {
//	 
//		 	if(prices.get(i)%2!=0)
//		  {
//			 if( prices.get(i)<min)
//			 {
//				 min=prices.get(i);
//				 continue;
//			 }
//		  }
//		  else
//		  {
//			  if(prices.get(i)>max)
//			  {
//				  
//				 
//				 if(prices.get(i)-min>max)
//				 {
//					 max=prices.get(i)-min;
//				 }
//					 
////				 
//			  }
//				 
//			
//		  }
//		  
//		 	i++;
//	 }
//	 
//		return max;-
//
// }
 
 /*
  * Complete the 'findSchedules' function below.
  *
  * The function is expected to return a STRING_ARRAY.
  * The function accepts following parameters:
  *  1. INTEGER workHours
  *  2. INTEGER dayHours
  *  3. STRING pattern
  */

 public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
 // Write your code here
//	 List<String> result=new ArrayList<>();
	 int total = 0;
	 int noofQuestionMarks=0;
	 for(int i=0;i<pattern.length();i++) {
		 if(pattern.charAt(i)!='?')
			 total+=Character.getNumericValue(pattern.charAt(i));
		else
			noofQuestionMarks++;		 
	 }
	 List<Integer> array = new ArrayList<>();
//	 int array[] = new int[noofQuestionMarks];
	 String array_char[] = new String[pattern.length()-noofQuestionMarks];
	 int z=0,z1=0;;
	 for(int i=0;i<pattern.length();i++) {
		 if(pattern.charAt(i)=='?') {
			 array.add(i);
			 z++;
		 }
		 else {
			 array_char[z1]=Character.toString(pattern.charAt(i));
			 z1++; 
		 }
	 }
	 int totLeft = workHours - total;
	 StringBuilder myName = new StringBuilder(pattern);
//	 myName.setCharAt(0, 'x');
	 ArrayList<Integer> arr = new ArrayList<Integer>();
	 arr.add(1);
	 arr.add(2);
	 System.out.println(total);
	 Solution sol = new Solution(); 
	    List<List<Integer>> list = new ArrayList<>();
	    sol.findCombinations(totLeft,list);
	    List<List<Integer>> new_list = new ArrayList<>();
	    for(int i=0;i<list.size();i++) {
	    	if(list.get(i).size()==noofQuestionMarks) {
	    		if(list.get(i).get(noofQuestionMarks-1) <=dayHours)
	    		new_list.add(list.get(i));
	    	}
	    }
	    List<String> result_final=new ArrayList<>();
	    for(int i=0;i<new_list.size();i++) {
	    	List<String> result=new ArrayList<>();
	    	List<Integer> a =new_list.get(i);
	    	StringBuilder str = new StringBuilder();
	    	for(int j=0;j<a.size();j++) {
	    		str.append(a.get(j).toString());
	    	}
	    	sol.permute(str.toString(), 0, str.toString().length()-1,result);
	    	result_final.addAll(result);
	    }
	    Set<String> foo = new TreeSet<String>(result_final);
	    List<String> foo_final = new ArrayList<>(foo);
	    List<String> foo_final_final = new ArrayList<>();
	    for(int i=0;i<foo_final.size();i++) {
	    	StringBuffer sbs= new StringBuffer();
	    	String innerResults=foo_final.get(i);
	    	int z_local=0,z_local2=0;
	    	for(int j=0;j<pattern.length();j++) {
	    		if( (array).contains(j)) {
	    			sbs.append(innerResults.charAt(z_local));
	    			z_local++;	
	    		}
	    		else {
	    			sbs.append(array_char[z_local2]);
	    			z_local2++;
	    		}
	    		
	    	}
	    	foo_final_final.add(sbs.toString());
	    }
	 return foo_final_final;
 }
 
 private void permute(String str, int l, int r,List<String> result) 
 { 
     if (l == r) {
         System.out.println(str); 
     	 result.add(str);
     }

     else
     { 
         for (int i = l; i <= r; i++) 
         { 
             str = swap(str,l,i); 
             permute(str, l+1, r,result); 
             str = swap(str,l,i); 
         } 
     } 
 }  
 
 public String swap(String a, int i, int j) 
 { 
     char temp; 
     char[] charArray = a.toCharArray(); 
     temp = charArray[i] ; 
     charArray[i] = charArray[j]; 
     charArray[j] = temp; 
     return String.valueOf(charArray); 
 } 
 
 static void findCombinationsUtil(int arr[], int index, 
         int num, int reducedNum, List<List<Integer>> list) 
{ 
// Base condition 
if (reducedNum < 0) 
return; 

// If combination is  
// found, print it 
if (reducedNum == 0) 
{ 
List<Integer> innerList = new ArrayList<>();
for (int i = 0; i < index; i++) {
innerList.add(arr[i]);
}

list.add(innerList); 
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
for (int k = prev; k <= num ; k++) 
{ 
// next element of 
// array is k 
arr[index] = k; 

// call recursively with 
// reduced number 
findCombinationsUtil(arr, index + 1, num, 
         reducedNum - k,list); 
} 
} 

/* Function to find out all  
combinations of positive  
numbers that add upto given 
number. It uses findCombinationsUtil() */
static void findCombinations(int n, List<List<Integer>> list) 
{ 
// array to store the combinations 
// It can contain max n elements 
int arr[] = new int [n]; 

// find all combinations 
findCombinationsUtil(arr, 0, n, n,list); 
}

///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////

static void textQueries(List<String> sentences, List<String> queries) {
int len_Sen = sentences.size();
int len_Que = queries.size();

for(int i=0;i<len_Que;i++) {
    int count=0;
    String input_Query=queries.get(i);
    List<String> list_Query1 = Arrays.asList(input_Query.split(" "));
    Set<String> list_Query2 = new HashSet<String>(list_Query1);
    List list_Query = new ArrayList(list_Query2);
    for(int j=0;j<len_Sen;j++) {
        int flag=0;
        String input_Sen=sentences.get(j);
        List<String> list_Sen1 = Arrays.asList(input_Sen.split(" "));
        Set<String> list_Sen2 = new HashSet<String>(list_Sen1);
        List list_Sen = new ArrayList(list_Sen2);
        if(list_Sen2.containsAll(list_Query2))
        	flag=0;
        else
        	flag=1;
        if(flag==0) {
            System.out.print(j+" ");
            count++;
        }
            
    }
    if(count==0)
        System.out.print("-1");
    System.out.println();
}



}


static void textQueries_better(List<String> sentences, List<String> queries) {
int len_Sen = sentences.size();
int len_Que = queries.size();
Map<String,List<Integer>> senMap= new HashMap<>();

for(int i=0;i<len_Sen;i++) {
	String input_Sen=sentences.get(i);
	List<String> list_Sen1 = Arrays.asList(input_Sen.split(" "));
	Set<String> list_Sen2 = new HashSet<String>(list_Sen1);
	List<String> list_Sen = new ArrayList(list_Sen2);
	for(int j=0;j<list_Sen1.size();j++) {
		List<Integer> l1=senMap.get(list_Sen1.get(j));
		if(l1==null)
			l1=new ArrayList<>();
		l1.add(i);
		senMap.put(list_Sen1.get(j).toLowerCase(), l1);
	}
}

for(int i=0;i<len_Que;i++) {
    int count=0;
    String input_Query=queries.get(i);
    List<String> list_Query1 = Arrays.asList(input_Query.split(" "));
    Set<String> list_Query2 = new HashSet<String>(list_Query1);
    List<String> list_Query = new ArrayList(list_Query2);
    Map<Integer, Integer> innerMap = new HashMap<>();
    for(int j=0;j<list_Query1.size();j++) {
    	List<Integer> out=senMap.get(list_Query1.get(j).toLowerCase());
    	if(out!=null) {
    		for(int z=0;z<out.size();z++) {
    			Integer newCount =innerMap.get(out.get(z));
    			if(newCount==null) {
    				innerMap.put(out.get(z), 1);
    			}
    			else {
    				innerMap.put(out.get(z),innerMap.get(out.get(z))+1 );
    			}
    		}
    		int a=0;
    		
    	}
    	else {
    		break;
    	}
    }
    Iterator it = innerMap.entrySet().iterator();
    int countforNeg=0;
    while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        if(pair.getValue().equals(list_Query1.size())) {
        	countforNeg++;
        	System.out.print(pair.getKey()+" ");
        }
        it.remove(); // avoids a ConcurrentModificationException
    }
    if(countforNeg!=0)
    	System.out.println();
    else
    	System.out.println("-1");

}

}


 	public static void main(String[] args)throws Exception
 		{
    	TreeNode l1=new TreeNode(1);
    	l1.left=new TreeNode(8);
    	l1.right=new TreeNode(5);
    	l1.left.left=new TreeNode(34); 
//     	l1.left.right=new TreeNode(0);
//     	l1.right.left=new TreeNode(9);
//     	l1.right.right=new TreeNode(2);
//     	l1.left.left.left=new TreeNode(32);
//     	l1.left.left.right=new TreeNode(64);
//     	l1.left.right.left=new TreeNode(1);
//     	l1.left.right.right=new TreeNode(1);
//     	l1.right.left.left=new TreeNode(66);
//     	l1.right.left.right=new TreeNode(55);
//     	l1.right.right.left=new TreeNode(44);
//     	l1.right.right.right=new TreeNode(31);
     	
     	TreeNode l2=new TreeNode(1);
    	l2.left=new TreeNode(8);
    	l2.right=new TreeNode(5);
    	
     	ListNode ll =new ListNode(1);
     	ll.next = new ListNode(2);
     	ll.next.next = new ListNode(3);
     	ll.next.next.next = new ListNode(4);
     	ll.next.next.next.next = new ListNode(3);
     	ll.next.next.next.next.next = new ListNode(2);
     	ll.next.next.next.next.next.next = new ListNode(1);
//     	int[] array = {-2,10,-5,-1,-1,-1,-1,20,-4};
//     	int [] preOrder = {4,2,5,1,3,7,9,1,2,12,4,5,9,12,5,8,1,3,9};
//     	int [] inOrder = {1,3};
//     	int [] arrayDup = {2,0,0};
//    	TreeNode result=new Solution().mirrorTreeConvert(l1);
     	/*new Solution().mergeSort(array,0,array.length-1);*/
//     	for(int i=0;i<array.length;i++) {
//     		System.out.print(array[i]+" ");
//     	}
//    	boolean length=new Solution().canJump(arrayDup);
//    	int result1=new Solution().maxProfit(preOrder);
//    	int [][] matrix = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
//    	int [][] matrix1= {{1,2,3,4,5,6,7,8,9,10}};
    	List<Integer> teamGoals = new ArrayList<>();
    	teamGoals.add(4);
    	teamGoals.add(4);
    	teamGoals.add(3);
    	teamGoals.add(3);
//    	teamGoals.add(4);
//    	teamGoals.add(8);
//    	teamGoals.add(1);
//    	teamGoals.add(8);
//    	teamGoals.add(4);
//    	teamGoals.add(18);
//    	teamGoals.add(1);
//    	teamGoals.add(7);
//    	teamGoals.add(2);
//    	teamGoals.add(5);
//    	teamGoals.add(10);
//    	teamGoals.add(5);*
//    	teamGoals.add(7);
//    	teamGoals.add(19);
//    	teamGoals.add(6);
//    	teamGoals.add(14);
//    	teamGoals.add(17);
//    	teamGoals.add(11);
//    	teamGoals.add(3);
//    	teamGoals.add(14);
//    	teamGoals.add(9);
//    	teamGoals.add(20);
    	/*List<List<Integer>> prices = new ArrayList<>();
    			List<Integer> p1= new ArrayList<>();
    			p1.add(1200);
    			p1.add(100);
    			p1.add(110);
    			List<Integer> p2= new ArrayList<>();
    			p2.add(1400);
    			p2.add(100);
    			p2.add(105);
    			prices.add(p1);
    			prices.add(p2);*/

//		List<Integer> schoolSeatsArray = Arrays.asList(1, 3, 1, 2);
//		List<Integer> studentScoreArray = Arrays.asList(990, 780, 830, 860, 920);
//		List<List<Integer>> studentSchoolPreferencesArray = new ArrayList<List<Integer>>();
//		studentSchoolPreferencesArray.add(Arrays.asList(3, 2, 1));
//		studentSchoolPreferencesArray.add(Arrays.asList(3, 0, 0));
//		studentSchoolPreferencesArray.add(Arrays.asList(2, 0, 1));
//		studentSchoolPreferencesArray.add(Arrays.asList(0, 1, 3));
//		studentSchoolPreferencesArray.add(Arrays.asList(0, 2, 3));
//		List<Integer> output = allocateSchools(schoolSeatsArray, studentScoreArray, studentSchoolPreferencesArray);
//		System.out.println(" "+output.get(0)+" "+output.get(1));
		List<String> result = findSchedules(4,3,"??13?00");
//		List<String> sentences = new ArrayList<>();
//		List<String> queries = new ArrayList<>();
//		sentences.add("it go will away it go will away it go will awayit go will away go do art go");
//		sentences.add("hai what to will east what to will east what to will east what to will east");
//		sentences.add("what to will east what to will east what to will east what to will east");
//		queries.add("go do art go");
//		queries.add("go");
//		queries.add("do artgodo ");
//		queries.add("east what to");
//		queries.add("123");
//		queries.add("");
		
		
//		textQueries_better( sentences, queries);
		
    }
    
}