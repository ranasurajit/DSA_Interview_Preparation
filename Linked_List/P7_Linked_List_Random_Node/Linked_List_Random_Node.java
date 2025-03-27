package Linked_List.P7_Linked_List_Random_Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Linked_List_Random_Node {

    static List<Integer> list = new ArrayList<Integer>();
    Random random = new Random();
    static ListNode node;

    public static void main(String[] args) {
        Linked_List_Random_Node solution = new Linked_List_Random_Node();

        String[] operations = {
                "Linked_List_Random_Node", "getRandom", "getRandom",
                "getRandom", "getRandom", "getRandom"
        };
        int[][] input = { { 1, 2, 3 }, {}, {}, {}, {}, {} };
        ListNode head = LinkedListUtils.createSinglyLinkedList(input[0]);
        LinkedListUtils.printSinglyLinkedList(head);

        List<Object> resultBrute = new ArrayList<>();
        for (String operation : operations) {
            if (operation.equals("Linked_List_Random_Node")) {
                new Linked_List_Random_Node(head);
                resultBrute.add(null);
            } else {
                resultBrute.add(solution.getRandomBrute());
            }
        }
        System.out.println(resultBrute);

        List<Object> resultOptimal = new ArrayList<>();
        for (String operation : operations) {
            if (operation.equals("Linked_List_Random_Node")) {
                new Linked_List_Random_Node(head);
                resultOptimal.add(null);
            } else {
                resultOptimal.add(solution.getRandomOptimal());
            }
        }
        System.out.println(resultOptimal);
    }

    public Linked_List_Random_Node() {

    }

    public Linked_List_Random_Node(ListNode head) {
        ListNode current = head;
        Linked_List_Random_Node.node = head;
        while (current != null) {
            Linked_List_Random_Node.list.add(current.val);
            current = current.next;
        }
    }

    /**
     * Using Brute-Force Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public int getRandomBrute() {
        int n = Linked_List_Random_Node.list.size();
        int randomIndex = this.random.nextInt(n);
        return list.get(randomIndex);
    }

    /**
     * Using Reservoir Sampling
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int getRandomOptimal() {
        ListNode current = Linked_List_Random_Node.node;
        int size = 1;
        int result = 0;
        while (current != null) {
            if (random.nextInt(size) == 0) {
                result = current.val;
            }
            size++;
            current = current.next;
        }
        return result;
    }
}
