import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

class MissingDuplicate{

    public static List<Integer> find(List<Integer> inputList){
//        int avg = (int) inputList.stream().mapToInt(i -> i).average().orElseThrow(() -> new RuntimeException("Invalid args"));
//        int sum = inputList.stream().mapToInt(i -> i).sum();
//        int max = inputList.stream().mapToInt(i -> i).max().orElseThrow(() -> new RuntimeException("Invalid args"));
//        int overallSum = max * (max + 1) / 2;
        int pairSum = inputList.size() + 1;
        List<Integer> res = new ArrayList<>();
        int f =0, l = pairSum - 2;
        while(f < l){
            int sum = inputList.get(f) + inputList.get(l);
            int diff = pairSum  - sum;
            if(diff < 0){
                res.add(inputList.get(l) - 1);
                res.add(inputList.get(l));
                break;
            }
            else if(diff > 0){
                res.add(inputList.get(f) +
                        1);
                res.add(inputList.get(f));
                break;
            }
            else{
                f++;
                l--;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> inputList = Arrays.asList(1,2,3,3,5);
        System.out.println(find(inputList));
    }
}