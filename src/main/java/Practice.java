import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Practice {

    public static List<Integer> getIntersection(int[] a, int [] b){
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(Integer v : a){
            map.put(v, 1);
        }
        for(Integer v1: b){
            if(map.containsKey(v1)){
                res.add(v1);
            }
        }
        return res;
    }

    public static List<Integer> intersection(int[] a, int[] b){
        List<Integer> res = new ArrayList<>();
        return a.length > b.length ? getIntersection(a, b) : getIntersection(b, a);
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,3,4,5,5,6};
        int[] b = {3, 6};
        System.out.println(intersection(a, b));
    }
}
