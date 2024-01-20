/*
List data = new ArrayList<Object>();
data.add(new Integer(1));
 data.add("Hello");        List l = new ArrayList<Object>();
  l.add("Go");
   l.add("MMT");        data.add(l);
   Map m = new HashMap<String, Object>();
   m.put("lob", "flight");
   m.put("airlines", new ArrayList<Object>(Arrays.asList("indigo", "spicejet")));
   data.add(m);
json = jsonEncode(data);
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonEncoder {
    public static String jsonEncode(Object data, List<Object> visitedObjects) {
        final StringBuilder result = new StringBuilder();
        // check if list
        // check if string
        // check is map
        if(!(data instanceof String) ){
            if(visitedObjects.contains(data)){
                throw new RuntimeException("Invalid reference!");
            }
            else{
                visitedObjects.add(data);
            }
        }
        if(data instanceof Map){
            Map<Object, Object> internalData = (Map<Object, Object>) data;
            result.append("{");
            internalData.forEach((k, v)->{
                result.append(String.valueOf(k));
                result.append(JsonEncoder.jsonEncode(v, visitedObjects));
            });
            result.append("}");
        } else if (data instanceof List) {
            List<Object> internalListData = (List<Object>) data;
            result.append("[");
            for (int i = 0; i < internalListData.size(); i++) {
                //
                result.append(JsonEncoder.jsonEncode(internalListData.get(i), visitedObjects));
                result.append(", ");
            }
            internalListData.forEach((listData)->{

            });
            result.append("]");
        } else if (data instanceof String) {
            result.append(String.valueOf(data));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        List data = new ArrayList<Object>();
        data.add("1");
        data.add("Hello");
        List l = new ArrayList<Object>();
        l.add("Go");
        l.add("MMT");
        data.add(l);
        l.add(data);
        //List<Object> visitedObjects = new IdentityArrayList<>();
        //System.out.println(jsonEncode(data, visitedObjects));
    }

}
