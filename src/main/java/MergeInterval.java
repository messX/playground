import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Merges overlapping list eg.
 * Inp: [1,4],[2,5],[6,7]
 * Res: [1,5],[6,7]
 * Pseudo code
 * sort by first val
 * if a[i][0] <= a[i+1][0]
 *  if a[i+1][1] <= a[i][1]: overlap
 *      if a[i][1] <= a[i+1][1]: overlap
 *      else: overlap
 */
public class MergeInterval {

    public static List<List<Integer>> merge(List<List<Integer>> input){
        List<List<Integer>> res = new ArrayList<>();
        int lookupVal = input.get(0).get(0);
        boolean checkedLast = false;
        for(int i=0;i<input.size();i++){
            System.out.println("lookup 1: "+ lookupVal +"," + input.get(i).get(0));
            if(input.get(i).get(0) >= lookupVal && i+1 < input.size()){
                lookupVal = input.get(i).get(0);
                System.out.println("lookup 2: "+ lookupVal +"," + input.get(i+1).get(0));
                if(lookupVal <= input.get(i+1).get(0)){
                    System.out.println(i);
                    if(i+1 == input.size()-1){
                        checkedLast = true;
                    }
                    ArrayList<Integer> res1 = new ArrayList<>();
                    // push first
                    res1.add(input.get(i).get(0));
                    lookupVal = input.get(i+1).get(1) > input.get(i).get(1) ? input.get(i+1).get(1) : input.get(i).get(1);
                    System.out.println("lookup 3: "+ lookupVal);
                    res1.add(lookupVal);
                    res.add(res1);
                }
            }
            if(!checkedLast && i+1 == input.size()){
                res.add(input.get(input.size() - 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1,4));
        input.add(Arrays.asList(2,5));
        input.add(Arrays.asList(5,6));
        input.add(Arrays.asList(6,7));
        System.out.print("Output:");
        System.out.println(merge(input));
    }
}
