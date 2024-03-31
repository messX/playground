import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
interface Calc{
   int compute(int a, int b);
}

public class LambdaExamples {

    /**
     * use of lambda sort
     */
    public static void sortedList(){
        List<List<Integer>> toSortList = new ArrayList<>();
        toSortList.add(Arrays.asList(4,8));
        toSortList.add(Arrays.asList(2,4));
        toSortList.add(Arrays.asList(1,4));
        Comparator<List<Integer>> comp= Comparator.comparingInt(a -> a.get(0));
//        Comparator<List<Integer>> comp= (a, b) -> a.get(0) - b.get(0);
        System.out.println(toSortList.stream().sorted(comp).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        //sortedList();
        Calc calc = (x, y) -> {return x+y;};
        System.out.println(calc.compute(1,2));
        Calc calc1 = (x, y) -> {return x%y;};
        System.out.println(calc1.compute(1,2));
    }
}
