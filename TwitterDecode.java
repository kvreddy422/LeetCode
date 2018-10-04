import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwitterDecode {
	
	public static void main(String args[]) {
		List<String> codes = new ArrayList<>();
		codes.add("a 100100");
		codes.add("b 100101");
		codes.add("c 110001");
		codes.add("d 100000");
		codes.add("[newline] 111111");
		codes.add("p 111110");
		codes.add("q 000001");
		String input2="111110000001100100111111100101110001111110";
		int sizeinput=input2.length();
		String result = decode(codes, input2);
		System.out.println(result);
	}
	
	public static String decode(List<String> codes, String encoded) {
		if(encoded == null || encoded.isEmpty() || codes == null)
			return "";
		Set<String> set = new HashSet<String>();
		Map<String,String>  map = new HashMap<String,String>();
		for (String code : codes ) {
			String[] encoding = code.split(" ");
			set.add(encoding[1]);
			map.put(encoding[1],encoding[0]);
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder ans = new StringBuilder();
		for (int i=0; i<encoded.length(); i++) {
			sb.append(encoded.charAt(i));
			if(set.contains(sb.toString()))
			{
				String val = map.get(sb.toString());
				if(val.equals("[newline]"))
				{
					ans.append("\n");
				}
				else
					ans.append(val);
				sb.setLength(0);
			}
		}
		return ans.toString();
    }	

}
