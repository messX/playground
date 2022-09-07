import java.util.ArrayList;
import java.util.List;

/**
 * All char combination
 * 0 ->[]
 * 1 -> []
 * 2 -> ['a','b','c']
 * 3 -> ['d','e','f']
 * 4 -> ['g','h','i']
 * 5 -> ['j','k','l']
 * 6 -> ['m','n','o']
 * 7 -> ['p','q','r', 's']
 * 8 -> ['t','u','v']
 * 9 -> ['w','x','y', 'z']
 * 234
 */
public class AllCharsCombination {

    public static Character getChar(int num, int index, char[][]numToChar){
        return index <= numToChar[num].length - 1 ? numToChar[num][index] : null;
    }

    public static List<String> printCombination(char[][] numToChar, int[]inputList, int readIndex, List<String> res){
          if(readIndex < 0){
              return res;
          }
          List<String> tempCopy = new ArrayList<>(res);
        //System.out.println(numToChar[inputList[readIndex]]);
        List<String> localList = new ArrayList<>();
          for(char c: numToChar[inputList[readIndex]]){
              if(tempCopy.size() == 0){
                  localList.add(String.valueOf(c));
                  continue;
              }
              for(String s: tempCopy){
                    String charList = c + s;
                    //System.out.println("charlist: "+charList);
                    localList.add(charList);
              }
          }
          // to print all combinations
          //res.addAll(localList);
          res = localList;
          //System.out.println("res 2:");
         //System.out.println(res);
        readIndex--;
          return printCombination(numToChar, inputList, readIndex, res);
    }

    public static void main(String[] args) {
        char[][] numToChar = new char[10][4];
        numToChar[0] = new char[]{};
        numToChar[1] = new char[]{};
        numToChar[2] = new char[]{'a','b','c'};
        numToChar[3] = new char[]{'d','e','f'};
        numToChar[4] = new char[]{'g','h','i'};
        numToChar[5] = new char[]{'j','k','l'};
        numToChar[6] = new char[]{'m','n','o'};
        numToChar[7] = new char[]{'p','q','r', 's'};
        numToChar[8] = new char[]{'t','u','v'};
        numToChar[9] = new char[]{'w','x','y', 'z'};
        System.out.println(printCombination(numToChar, new int[]{2, 3, 4}, 2, new ArrayList<>()));
    }
}
