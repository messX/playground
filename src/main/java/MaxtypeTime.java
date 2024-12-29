/*

You are given a list of tasks, each associated with a memory requirement that must be processed by a processor. The tasks are represented by the following array:

Tasks: [1, 4, 5, 2, 3] (The numbers represent the memory needed for each task.)

The type of each task is given in another array:

Task Type: [1, 2, 1, 3, 4]

You are also given an additional input, which is the maximum memory the processor can handle at a time:

Max Memory: 6

Only two tasks of the same type can run in parallel. Assume that each task takes 1 unit of time to complete. Find out the total time taken to complete all the tasks.

Output:
4 units

pseudo code
input
Tasks: [1, 4, 5, 2, 3, 4, 2]
Task Type: [1, 2, 1, 3, 4, 1, 2]
Max Memory: 6
create hashmap with type as key and memory requirements as list.
1 -> [2,5,4,3], 2 -> [4, 2], 3 -> [2], 4 -> [3]
Join the tasks which can run in parrel while fitting the max memory requirements.
Finally count the size of all hashmaped arrays.
 */

import com.beust.ah.A;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@NoArgsConstructor
public class MaxtypeTime {

    private Map<Integer, List<Integer>> createTypeMap(List<Integer> typeList, List<Integer> taskList) {
        Map<Integer, List<Integer>> finalMap = new HashMap<>();
        int index = 0;
        log.debug(taskList.toString());
        log.debug(typeList.toString());
        for (Integer type: typeList
             ) {
            List<Integer> indexList = new ArrayList<>();
            if(finalMap.containsKey(type)){
                indexList = finalMap.get(type);
            }
            indexList.add(taskList.get(index));
            finalMap.put(type, indexList);
            index++;
        }
        return finalMap;
    }

    private List<Integer> getMergedList(int memory, List<Integer> memoryInputList){
        List<Integer> memoryOutputList = new ArrayList<>();
        List<Integer> skippedIndex = new ArrayList<>();
        for (int i = 0; i < memoryInputList.size(); i++) {
            boolean foundMemsum = false;
            if(skippedIndex.contains(i)){
                continue;
            }
            if(memoryInputList.get(i) < memory){
                for(int k = i + 1; k < memoryInputList.size(); k++){
                    if(skippedIndex.contains(k)){
                        continue;
                    }
                    int memSum = memoryInputList.get(i) + memoryInputList.get(k);
                    if(memSum <= memory){
                        memoryOutputList.add(memSum);
                        foundMemsum = true;
                        skippedIndex.add(i);
                        skippedIndex.add(k);
                        break;
                    }
                }
                if(!foundMemsum){
                   memoryOutputList.add(memoryInputList.get(i));
                }
            }
            else{
                memoryOutputList.add(memoryInputList.get(i));
            }
        }
        return memoryOutputList;
    }

    public static void main(String[] args) {
        List<Integer> taskList = new ArrayList<>(Arrays.asList(1, 4, 5, 2, 3, 4, 2));
        List<Integer> memoryList = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 4, 1, 2));
        int memory = 6;
        MaxtypeTime maxtypeTime = new MaxtypeTime();
        Map<Integer, List<Integer>> arrangedMap =  maxtypeTime.createTypeMap(memoryList, taskList);
        log.debug("arranged Map: " + arrangedMap);
        arrangedMap.forEach((key, value) -> {
            List<Integer> inputList = arrangedMap.get(key);
            log.debug("Key:" + key + "and input list: " + inputList);
            List<Integer> mergedList = maxtypeTime.getMergedList(memory, inputList);
            log.debug("Key:" + key + "and merged list: " + mergedList);
            arrangedMap.replace(key, mergedList);
        });
        AtomicReference<Integer> res = new AtomicReference<>(0);
        arrangedMap.forEach((key, value) -> {
            res.updateAndGet(v -> v + arrangedMap.get(key).size());
        });
        System.out.println("Final result: " + res.get());
    }

}
