import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Balanced parenthesis
 * '{}[]()' true. (]] false
 */
public class Paranthesis {

    public static boolean checkParenthesis(String input){
        Map<Character, Character> map=new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        boolean result = true;
        Stack<Character> checkList = new Stack<>();
        for (char c : input.toCharArray()) {
            if(map.containsValue(c)){
                checkList.push(c);
            }
            else{
                Character toCheck;
                if(checkList.size() > 0){
                    toCheck = checkList.pop();
                    if(!toCheck.equals(map.get(c))){
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String test = "{}[]()]";
        System.out.println("Check: " + checkParenthesis(test));
    }
}