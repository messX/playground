

// problem link: https://app.clickup.com/9002206935/v/dc/8c95hpq-816/8c95hpq-836
/*
Input and output:
[1,1,2,1], n=2 -> Min time between Two tasks of sametype
Output: 7
1__12_1
Brute force:
[1,2,1,1,3,3], n=4
Get all the common kind of tasks:
1:3
2:1
3:2
1____1____1
132__13___1
In case n=1
1_1_1
13131
= Min time:
1 Make higher repeated list
2 Fill second highest in the empty position
3 If there is no empty position
4 Make another list of empty position and merge the two list.
repeat
*/


import java.util.*;

public class MinTimeToTask {

    public static List<Integer> fillVacant(List<Integer> inputList, int emptySpace){
        for(int i=0;i<emptySpace;i++){
            inputList.add(-1);
        }
        return inputList;
    }

    public static HashMap<Integer, Integer> getFrequencyMap(List<Integer> inputList){
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer i: inputList
             ) {
            if(frequencyMap.containsKey(i)){
                frequencyMap.put(i, frequencyMap.get(i) + 1);
            }
            else{
                frequencyMap.put(i, 1);
            }
        }
        return frequencyMap;
    }
    
    public static List<Integer> getKeyWithMaxFreq(HashMap<Integer, Integer> inputMap){
        List<Integer> maxKeyVal = new ArrayList<>();
        final Integer[] maxVal = {-1};
        inputMap.forEach((k, v) -> {
            if(v > maxVal[0]){
                maxVal[0] = v;
                maxKeyVal.add(k);
                maxKeyVal.add(v);
            }
        });
        return maxKeyVal;
    }

    public static int getMaxSize(List<Integer> inputList, int n){
        HashMap<Integer, Integer> frequencyMap = getFrequencyMap(inputList);
        do
        {
            List<Integer> maxKeyVal = getKeyWithMaxFreq(frequencyMap);
            frequencyMap.remove(maxKeyVal.get(0));
            /* pseudo algo
                input: 1,1,1,2
                {1:3, 2:1}
                finalList=[]
                lastEmptyIndex=0
                n = 2
                iter 1:
                    finalist = [1, -1, -1, 1, -1, -1, 1]
                    lastEmptyIndex = 1
                iter 2:
                    finalList = [1,2,-1,1,-1,-1,1]
                    lastEmptyIndex=2
                freq = maxKeyVal.get(1);
                val = maxKeyVal.get(0);
                loop till freq > 0
                    finalList.push(val)
                    for i -> n times:
                        if finalList(index+i) != -1:
                            finalList.push(-1)
             */
        }while (!frequencyMap.isEmpty());

        return 0;
    }

    public static void main(String[] args) {
        List<Integer> inputArr = new ArrayList<>(Arrays.asList(1,1,2,1));
        int n = 2;
        System.out.println("Res :" + String.valueOf(getMaxSize(inputArr, n)));
    }

}
