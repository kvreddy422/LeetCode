
// Java program to count islands in boolean 2D matrix 
import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
class Twitter3 
{ 
	List<String> l1= new ArrayList<>();
	List<String> l2= new ArrayList<>();
    //No of rows and columns 
    static final int ROW = 3, COL = 3; 
  
    // A function to check if a given cell (row, col) can 
    // be included in DFS 
    boolean isSafe(int M[][], int row, int col, 
                   boolean visited[][]) 
    { 
        // row number is in range, column number is in range 
        // and value is 1 and not yet visited 
        return (row >= 0) && (row < ROW) && 
               (col >= 0) && (col < COL) && 
               (M[row][col]==1 && !visited[row][col]); 
    } 
  
    // A utility function to do DFS for a 2D boolean matrix. 
    // It only considers the 8 neighbors as adjacent vertices 
    void DFS(int M[][], int row, int col, boolean visited[][],StringBuffer b1) 
    { 
        // These arrays are used to get row and column numbers 
        // of 8 neighbors of a given cell 
        int rowNbr[] = new int[] {-1, -1, -1,  0, 0,  1, 1, 1}; 
        int colNbr[] = new int[] {-1,  0,  1, -1, 1, -1, 0, 1}; 
  
        // Mark this cell as visited 
        visited[row][col] = true; 
        b1.append(Integer.toString(row));
        b1.append(Integer.toString(col));
  
        // Recur for all connected neighbours 
        for (int k = 0; k < 8; ++k) 
            if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited) ) {
            	int row1=row + rowNbr[k];
            	int col1=col + colNbr[k];
            	DFS(M, row1, col1, visited,b1);
            }
    } 
  
    // The main function that returns count of islands in a given 
    //  boolean 2D matrix 
    int countIslands(int M[][],List<String> bS2) 
    { 
        // Make a bool array to mark visited cells. 
        // Initially all cells are unvisited 
        boolean visited[][] = new boolean[M.length][M[0].length]; 
  
  
        // Initialize count as 0 and travese through the all cells 
        // of given matrix 
        int count = 0; 
        for (int i = 0; i < ROW; ++i) 
            for (int j = 0; j < COL; ++j) 
                if (M[i][j]==1 && !visited[i][j]) // If a cell with 
                {                                 // value 1 is not 
                    // visited yet, then new island found, Visit all 
                    // cells in this island and increment island count 
                	StringBuffer b1=new StringBuffer();
                    DFS(M, i, j, visited,b1);
                    bS2.add(b1.toString());
                    ++count; 
                } 
  
        return count; 
    } 
  
    // Driver method 
      
    static int countMatches(List<String> grid1, List<String> grid2) {
    	int lengthG1 = grid1.size();
        int lengthG11 = grid1.get(0).length();
        int arrayG1[][] = new int[lengthG1][lengthG11];
        for (int i=0;i<grid1.size();i++) {
        	String gridString = grid1.get(i);
        	for(int j=0;j<gridString.length();j++) {
        		arrayG1[i][j]=Character.getNumericValue(gridString.charAt(j));
        	}
        }
        
        int lengthG2 = grid1.size();
        int lengthG21 = grid1.get(0).length();
        int arrayG2[][] = new int[lengthG2][lengthG21];
        for (int i=0;i<grid2.size();i++) {
        	String gridString = grid2.get(i);
        	for(int j=0;j<gridString.length();j++) {
        		arrayG2[i][j]=Character.getNumericValue(gridString.charAt(j));
        	}
        }
        List<String> bS2 = new ArrayList<>();
        List<String> bS1 = new ArrayList<>();
        Twitter3 T1=new Twitter3();
        T1.countIslands(arrayG2,bS2); 
        T1.countIslands(arrayG1,bS1);
        int count =0;
        for(int i=0;i<bS1.size();i++) {
        	for(int j=0;j<bS2.size();j++) {
        		if(bS1.get(i).equals(bS2.get(j))) {
        			count++;
        		}
        	}
        }
        
        return count;

    }
    public static void main (String[] args) throws java.lang.Exception 
    { 
    	List<String> grid1=new ArrayList<>();
    	List<String> grid2=new ArrayList<>();
    	grid1.add("001");
    	grid1.add("011");
    	grid1.add("100");
    	grid2.add("001");
    	grid2.add("011");
    	grid2.add("101");
        int M[][]=  new int[][] {{1, 1, 0, 0, 0}, 
                                 {0, 1, 0, 0, 1}, 
                                 {1, 0, 0, 1, 1}, 
                                 {0, 0, 0, 0, 0}, 
                                 {1, 0, 1, 0, 1} 
                                }; 
        Twitter3 I = new Twitter3(); 
        
        System.out.println(I.countMatches(grid1,grid2));
    }
  
}