import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @input A : Integer array
 * @input n1 : Integer array's ( A ) length
 * @input B : Integer
 *[2,3], 2
 * [[2]]
 * [2,3,6,7], 7
 * [[7],[2,2,3]]
 * use recursion and backtracking
 */
class AllCombination{
    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> temp = new ArrayList<>();

    public static void getCombination(List<List<Integer>> res, List<Integer> inp,
                                      int index, int sum, List<Integer> tmp){
        if(sum == 0){
            res.add(tmp);
            return;
        }
        for (int i = index; i < inp.size(); i++) {
            if(sum - inp.get(i) >=0){
                tmp.add(inp.get(i));
                getCombination(res, inp, i, sum - inp.get(i), tmp);
                tmp.remove(inp.get(i));
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> inp = Arrays.asList(2,3,4,7);
        getCombination(res, inp, 0, 7, temp);
        System.out.println(res);
    }
}