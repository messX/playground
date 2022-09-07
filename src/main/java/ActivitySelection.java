import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActivitySelection {
    private int start;
    private int end;
    private String name;

    public ActivitySelection(int start, int end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ActivitySelection{" +
                "start=" + start +
                ", end=" + end +
                ", name='" + name + '\'' +
                '}';
    }

    public static List<ActivitySelection> selectActivities(List<ActivitySelection> activitySelectionList){
        List<ActivitySelection> activitySelectionListRes = new ArrayList<>();
        activitySelectionList.sort(Comparator.comparingInt(ActivitySelection::getEnd));
        activitySelectionListRes.add(activitySelectionList.get(0));
        ActivitySelection toCheck = activitySelectionList.get(0);
        for(int i=1; i<activitySelectionList.size();i++){
            if(toCheck.getEnd() <= activitySelectionList.get(i).getStart()){
                toCheck = activitySelectionList.get(i);
                activitySelectionListRes.add(activitySelectionList.get(i));
            }
        }
        return activitySelectionListRes;
    }


    public static void main(String[] args) {
        List<ActivitySelection> activitySelectionList = new ArrayList<>();
        activitySelectionList.add(new ActivitySelection(5, 9, "a1"));
        activitySelectionList.add(new ActivitySelection(1, 2, "a2"));
        activitySelectionList.add(new ActivitySelection(3, 4, "a3"));
        activitySelectionList.add(new ActivitySelection(0, 6, "a4"));
        activitySelectionList.add(new ActivitySelection(5, 7, "a5"));
        activitySelectionList.add(new ActivitySelection(8, 9, "a6"));
        System.out.println(ActivitySelection.selectActivities(activitySelectionList));
    }

   
}

class ActComp implements Comparator<ActivitySelection>{
    @Override
    public int compare(ActivitySelection t1, ActivitySelection t2) {
        return t2.getEnd() - t2.getEnd();
    }
}
