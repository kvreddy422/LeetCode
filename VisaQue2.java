import java.util.ArrayList;
import java.util.List;

public class VisaQue2 {
	
	static List<String> resultsTemp = new ArrayList<>();
	static StringBuffer stringinResult = new StringBuffer();
	public static void main (String[] args)  
    { 
        int n = 5; 
        List<String> result = findSchedules(40,8,"??13???"); 
    }
	
	public static void findCountPatterns(int questionCount, int dayHours, int workHours){
		if(stringinResult.length()==questionCount) {
			if(workHours==0) {
				if(stringinResult.length()==questionCount)
					resultsTemp.add(stringinResult.toString());
				else {
					for(int stringCountInitial=stringinResult.length()-1;stringCountInitial<questionCount;stringCountInitial++) {
						stringinResult.append('0');
					}
					resultsTemp.add(stringinResult.toString());
				}
			}
		}
		else {
			for (int i=0;i<=dayHours;i++) {
				stringinResult.append(i);
				findCountPatterns(questionCount, dayHours, workHours-i);
				stringinResult.deleteCharAt(stringinResult.length()-1);
			}
		}
	}
	public static List<String> findSchedules(int workHours, int dayHours, String pattern)
	{
		List<String> results = new ArrayList<>();
		int questionCount=0;
		List<Integer> indexOfNonQues = new ArrayList<>();
		int hoursCompleted =0;
		for(int i=0;i<pattern.length();i++) {
			if(pattern.charAt(i)=='?') {
				questionCount++;
			}
			else {
				indexOfNonQues.add(i);
				hoursCompleted+=Character.getNumericValue(pattern.charAt(i));
			}
		}
		List<String> resultsFinal = new ArrayList<>();
		findCountPatterns(questionCount,dayHours,workHours-hoursCompleted);
		
		for(int resultCount=0;resultCount<resultsTemp.size();resultCount++) {
			StringBuilder eachString = new StringBuilder(resultsTemp.get(resultCount));
			for(int eachStringindex =0; eachStringindex<eachString.length();eachStringindex++) {
				if(indexOfNonQues.contains(eachStringindex)) {
					eachString.insert(eachStringindex, pattern.charAt(eachStringindex) );
				}
			}
			resultsFinal.add(eachString.toString());
		}
		return resultsFinal;
	}
}
