import java.util.Collections;
import java.util.LinkedList;

/**
 * Given the head pointer of a linked list,
 * sort the linked list in ascending order using insertion sort.
 * Return the new head pointer of the sorted linked list.
 */
public class LinkedListSort {
    /**
     * loop:
     * take 1 val
     * check if it is smallest, else swap
     * continue
     * @param head
     * @return Node
     */
    public static Node sortList(Node head){
        Node nextHeadPointer = head.getNext();
        Node headPointer = head;
        Node prevPointer = head;
        while(headPointer != null){
            while(nextHeadPointer != null){
                if(headPointer.getVal() > nextHeadPointer.getVal()){
                    prevPointer.setNext(headPointer);
                    head.setNext(nextHeadPointer.getNext());
                    nextHeadPointer.setNext(head);
                    headPointer = nextHeadPointer;
                }
                prevPointer = nextHeadPointer;
                nextHeadPointer = nextHeadPointer.getNext();
            }
            headPointer = headPointer.getNext();
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedList<String> ls = new LinkedList<>();
        ls.add("acb");
        ls.add("bca");
        ls.add("abc");
        ls.sort((a, b)-> a.compareTo(b));
        System.out.println(ls);
    }
}

class Node{
    int val;
    Node next;
    Node(){}
    Node(int val, Node next){
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
