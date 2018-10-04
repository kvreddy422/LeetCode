import java.util.ArrayList;
import java.util.List;

class GFG_zeros {
	static int[] entries ;
	static  int totalHours ;
	static  List<String> ans ;
	static  String givenPattern ;
	static int maxHours ;
	public static void findEntries(int i, int prevSum)
	{
		if(prevSum > totalHours)
			return;
		if(i == entries.length )
		{
			int sum = 0;
			for (int j=0; j<entries.length; j++) {
				sum += entries[j];
			}
			if(sum == totalHours)
			{
				StringBuilder sb = new StringBuilder();
				int index  = 0;
				for (int k=0; k<givenPattern.length();k++ ) {
					if (givenPattern.charAt(k)  != '?') {
						sb.append(givenPattern.charAt(k));
					}
					else
						sb.append(entries[index++]);
				}
				ans.add(sb.toString());
			}
			return;
		}

		int iterateFrom = maxHours - prevSum;
		for (int p = 0; p<=maxHours; p++) {
			entries[i] = p;
			findEntries(i+1,prevSum+p); 
		}
	}
	public static List<String> findSchedules(int workHours, int dayHours, String pattern)
	{
		int noOfDays = 0;
		int totalHoursWorked = 0;
		for (int i=0; i<7;i++ ) {
			if (pattern.charAt(i) == '?') {
				noOfDays++;
			}
			else
			{
				totalHoursWorked += pattern.charAt(i) - '0';
			}
		}
		entries = new int[noOfDays];
		totalHours = workHours - totalHoursWorked;
		maxHours = dayHours;
		ans = new ArrayList<String>();
		givenPattern = pattern;
		findEntries(0,0);
		return ans;
	}
    public static void main (String[] args)  
    { 
        int n = 5; 
        List<String> result = findSchedules(40,8,"??13???"); 
    }
}