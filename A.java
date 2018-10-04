import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.util.*;

public class A {
	private static final boolean IS_PRACTICE = false;
	private static final boolean IS_CONSOLE_OUTPUT = true;
	private static final String INPUT_FILE_NAME = "tourist";
	
	public static void main(String[] args) throws FileNotFoundException {
		
//		Scanner in = createScanner();
		File file =new File("C:\\\\Users\\\\Vaibhav Kalakota\\\\eclipse-workspace\\\\LeetCode\\\\src\\\\input.txt");
			    Scanner in = new Scanner(file);
		
		int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			long n = in.nextLong();
			long k = in.nextLong();
			BigInteger v = new BigInteger(in.next());
			v = v.subtract(BigInteger.ONE);
			
			v = v.multiply(BigInteger.valueOf(k));
			
			String[] names = new String[(int) n];
			for (long j = 0; j < n; j++) {
				names[(int) j] = in.next();
			}
			
			BigInteger start = v.mod(BigInteger.valueOf(n));
			
			String ans = "";
			if (start.add(BigInteger.valueOf(k)).compareTo(BigInteger.valueOf(n)) <= 0) {
				ans = Arrays.stream(
					Arrays
						.copyOfRange(names, start.intValue(), start.intValue() + (int) k)
					)
					.reduce((prev, curr) -> prev + " " + curr)
					.get();
			} else {
				int lastEnd = BigInteger.valueOf(n).subtract(start).intValue();
				int firstEnd = (int) (k - lastEnd);
				
				ans = Arrays.stream(
						Arrays.copyOfRange(names, 0, firstEnd))
						.reduce((prev, curr) -> prev + " " + curr)
						.get();
				
				ans = ans + " " + Arrays.stream(
									Arrays.copyOfRange(names, start.intValue(), (int) n))
									.reduce((prev, curr) -> prev + " " + curr)
									.get();
			}
			
			System.out.printf("Case #%d: %s%s", i, ans, i != t ? "\n" : "");
		}
		in.close();
	}
	
	private static Scanner createScanner() throws FileNotFoundException {
		if (IS_PRACTICE) {
			if (!IS_CONSOLE_OUTPUT) {
				String outputFileName = "output-" + INPUT_FILE_NAME + ".out"; 
				File outputFile = new File(outputFileName);
				System.setOut(new PrintStream(outputFile));
			}
		
			return new Scanner(new FileReader(INPUT_FILE_NAME + ".in"));
			
		} else {
			return new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		}
	}
}
