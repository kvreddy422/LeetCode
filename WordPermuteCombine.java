import java.util.ArrayList;
import java.util.List;

public class WordPermuteCombine {
	
	static List<String> resultList = new ArrayList<>();
	public static void main(String args []) {
		String actualWord = "sugar";
		StringBuilder word = new StringBuilder();
		List<Character> word_new = new ArrayList<>();
		 findPermCombs(actualWord,word_new,0,actualWord.length()); 
		 System.out.println(resultList);
	}
	
	public static void findPermCombs(String actualWord,List<Character> inputString,int start,int end) {
		if(inputString.size()==actualWord.length()) {
			StringBuilder stringConcated =new StringBuilder();
			for(Character c : inputString) {
				stringConcated.append(c);
			}
			resultList.add(stringConcated.toString());
		}
		else {
			for(int i=0;i<end;i++) {
				if(inputString.contains(actualWord.charAt(i)))
					continue;
				inputString.add((actualWord.charAt(i)));
				findPermCombs(actualWord,inputString, i+1, end);
				inputString.remove(inputString.size()-1);
			}
		}
	}

}
