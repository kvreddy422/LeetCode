import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.logging.LoggingMXBean;

import javax.lang.model.type.IntersectionType;
import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.ResolutionSyntax;
import javax.swing.event.AncestorEvent;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.xml.bind.ValidationEvent;
import javax.xml.parsers.DocumentBuilder;

import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.math.*;
import java.text.Bidi;
import java.awt.Point;
import java.awt.BufferCapabilities.FlipContents;
import java.io.*;
import java.lang.reflect.Constructor;

public class Platfrom {
	public static void main(String[] args) throws FileNotFoundException {
		//InputReader in = new InputReader(System.in);
		//PrintWriter out = new PrintWriter(System.out);

		InputReader in = new InputReader(new File("ethan.txt"));
		PrintWriter out = new PrintWriter(new File("ethan_out.txt"));

		int pi = in.nextInt();
		for (int qi = 1; qi <= pi; qi++) {
			int n = in.nextInt();
			int k = in.nextInt();
			int[][] binaryTree = new int[n + 1][2];
			
			for(int i = 1; i <= n; i++){
				binaryTree[i][0] = in.nextInt();
				binaryTree[i][1] = in.nextInt();
			}
			
			List<Integer> preorderList = new ArrayList<Integer>();
			List<Integer> postorderList = new ArrayList<Integer>();
			
			//preorderList.add(0);
			//postorderList.add(0);
			
			preorder(binaryTree, 1, preorderList);
			postorder(binaryTree, 1, postorderList);
			
			Map<Integer, Integer> preorderMap = genMap(preorderList);
			Map<Integer, Integer> postorderMap = genMap(postorderList);
			
			/*for (Map.Entry<Integer, Integer> entry : preorderMap.entrySet()) {
				System.out.printf("key = %d, value = %d\n", entry.getKey(), entry.getValue());
			}
			for (Map.Entry<Integer, Integer> entry : postorderMap.entrySet()) {
				System.out.printf("key = %d, value = %d\n", entry.getKey(), entry.getValue());
			}*/
			
			int[] preorderResult = new int[n];
			int[] postorderResult = new int[n];
			
			int resultK = 0;
			for(int i = 0; i < n; i++){
				if(preorderResult[i] == 0){
					resultK++;
					slove(preorderList, postorderList, preorderMap, postorderMap, preorderResult, postorderResult, i, resultK);
				}else if(postorderResult[i] == 0){
					resultK++;
					slove(postorderList, preorderList, postorderMap, preorderMap, postorderResult, preorderResult, i, resultK);
				}
			}
			
			/*for(int i = 0; i < n; i++){
				out.printf("%d ", preorderList.get(i));
			}
			out.println();
			for(int i = 0; i < n; i++){
				out.printf("%d ", postorderList.get(i));
			}
			out.println();
			out.printf("k: %s\n", resultK);*/
			if(resultK < k) {
				out.printf("Case #%d: Impossible\n", qi);
			} else {
				out.printf("Case #%d:", qi);
				for(int i = 1; i <= n; i++){
					int v = preorderResult[preorderMap.get(i)];
					if(v > k){
						v = k;
					}
					out.printf(" %d", v);
				}
				out.println();
			}
		}

		out.close();
	}
	
	static void preorder(int[][] binaryTree, int nowIndex, List<Integer> result){
		result.add(nowIndex);
		if(binaryTree[nowIndex][0] != 0){
			preorder(binaryTree, binaryTree[nowIndex][0], result);
		}
		if(binaryTree[nowIndex][1] != 0){
			preorder(binaryTree, binaryTree[nowIndex][1], result);
		}
	}
	
	static void postorder(int[][] binaryTree, int nowIndex, List<Integer> result){
		if(binaryTree[nowIndex][0] != 0){
			postorder(binaryTree, binaryTree[nowIndex][0], result);
		}
		if(binaryTree[nowIndex][1] != 0){
			postorder(binaryTree, binaryTree[nowIndex][1], result);
		}
		result.add(nowIndex);
	}
	
	static Map<Integer, Integer> genMap(List<Integer> list){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < list.size(); i++){
			map.put(list.get(i), i);
		}
		
		return map;
	}
	
	static void slove(List<Integer> list1, List<Integer> list2, 
			Map<Integer, Integer> map1, Map<Integer, Integer> map2, 
			int[] result1, int[] result2, 
			int index1, int k){
		int value = list1.get(index1);
		int index2 = map2.get(value);
		
		result1[index1] = k;
		
		if(result2[index1] == 0){
			slove(list2, list1, map2, map1, result2, result1, index1, k);
		}
		if(result2[index2] == 0){
			slove(list2, list1, map2, map1, result2, result1, index2, k);
		}
		
	}

	static class InputReader {
		BufferedReader br;
		StringTokenizer st;

		public InputReader(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		public InputReader(InputStream in) {
			br = new BufferedReader(new InputStreamReader(in));
		}

		public String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public boolean hasNext() {
			while (st == null || !st.hasMoreTokens()) {
				String s = null;
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (s == null)
					return false;
				st = new StringTokenizer(s);
			}
			return true;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}
}