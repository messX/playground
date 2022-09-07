import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class FourSum{
    public static List<Integer> temp = new ArrayList<>();
    public static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> foursum(int[] input, int num, int sum, int index, List<Integer> temp){
        if(num == 1){
            if(Arrays.stream(input).anyMatch((i) -> i == sum)){
                List<Integer> toFill = new ArrayList<>(temp);
                toFill.add(sum);
                res.add(toFill);
                return res;
            }
            else{
                return res;
            }
        }
        for (int i = index; i < input.length; i++) {
            int newSum = sum - input[i];
            int newNum = num - 1;
            temp.add(input[i]);
            int finalI = i;
            int[] newInput;
            newInput = Arrays.stream(input).filter((inp -> inp != input[finalI])).toArray();
            foursum(newInput, newNum, newSum, index+1, temp);
            int finalI1 = i;
            temp.removeIf(i1 -> i1 == input[finalI1]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = new int[5];
        input = new int[]{3, 5, 9, 8, 7};
        int num = 4;
        int sum = 23;
        System.out.println(foursum(input, num, sum, 0, temp));
    }
}