/**
 * Consider a matrix with rows and columns, where each cell contains either a ‘0’ or a ‘1’
 * and any cell containing a 1 is called a filled cell. Two cells are said to be connected
 * if they are adjacent to each other horizontally, vertically, or diagonally.
 * If one or more filled cells are also connected, they form a region. find the length of the largest region.
 */

public class LargestIsland {


    public static int maxIsland(int[][] input, int i, int j, int ROW, int COL){
        int res=0;
        for(int a=i; a<ROW; a++){
            for(int b=j; b<COL; b++){
                res = Math.max(dfs(input, a, b, ROW, COL), res);
            }
        }
        return res;
    }

    private static int dfs(int[][] input, int i, int j, int row, int col) {
        if(!isValid(input, i, j, row, col)){
            return 0;
        }
        int count = 1;
        input[i][j]=0;
        count += dfs(input, i+1, j, row, col);
        count += dfs(input, i-1, j, row, col);
        count += dfs(input, i, j+1, row, col);
        count += dfs(input, i, j-1, row, col);
        return count;
    }

    private static boolean isValid(int[][] input, int i, int j, int row, int col) {
        return i >= 0 && j >=0 && i < row && j < col && input[i][j] != 0;
    }

    public static void main(String[] args) {
        int M[][] = {{0, 0, 1, 1},
                {1, 0, 1, 1},
                {0, 1, 0, 0},
                {0, 0, 0, 0}};
        int ROW = 4;
        int COL = 4;
        System.out.println(maxIsland(M, 0, 0, ROW, COL));
    }
}
