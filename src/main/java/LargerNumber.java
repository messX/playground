import com.sun.jdi.IntegerValue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Given a list of non-negative integers,
 * arrange them such that they form the largest number.
 *
 * For example:
 *
 * Given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 *
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */

public class LargerNumber {

    public static String useComparator(List<String> toSort){
        Comparator<String> comparator = (a, b) ->{
            if(parseInt(a+b) > parseInt(b+a)){
                return -1;
            }
            else{
                return 1;
            }
        };
        toSort.sort(comparator);
        return String.join("", toSort);
    }

    public static void main(String[] args) {
        List<String> toSort = Arrays.asList("3","30", "34", "5", "9");
        System.out.println(useComparator(toSort));
    }
}
