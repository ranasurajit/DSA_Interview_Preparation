package Linked_List.P18_Design_Linked_List;

import java.util.ArrayList;
import java.util.List;

public class Design_Linked_List {
    public static void main(String[] args) {
        String[] operations = {
                "MyLinkedList", "addAtHead", "addAtHead",
                "addAtHead", "addAtIndex", "deleteAtIndex",
                "addAtHead", "addAtTail", "get", "addAtHead",
                "addAtIndex", "addAtHead"
        };
        int[][] params = {
                {}, { 7 }, { 2 },
                { 1 }, { 3, 0 },
                { 2 }, { 6 }, { 4 },
                { 4 }, { 4 },
                { 5, 0 }, { 6 }
        };

        MyLinkedList list = null;
        List<Object> result = new ArrayList<Object>();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("MyLinkedList")) {
                list = new MyLinkedList();
                result.add(null);
            } else if (operation.equals("addAtHead")) {
                list.addAtHead(params[i][0]);
                result.add(null);
            } else if (operation.equals("addAtTail")) {
                list.addAtTail(params[i][0]);
                result.add(null);
            } else if (operation.equals("get")) {
                result.add(list.get(params[i][0]));
            } else if (operation.equals("addAtIndex")) {
                list.addAtIndex(params[i][0], params[i][1]);
                result.add(null);
            } else if (operation.equals("deleteAtIndex")) {
                list.deleteAtIndex(params[i][0]);
                result.add(null);
            }
        }

        System.out.println(result);
    }

    static class MyLinkedList {

        ListNode head = null;
        int size = -1;

        public MyLinkedList() {
            head = null;
            size = 0;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.val;
        }

        public void addAtHead(int val) {
            ListNode newNode = new ListNode(val);
            newNode.next = head;
            head = newNode;
            size++;
        }

        public void addAtTail(int val) {
            ListNode node = new ListNode(val);
            if (head == null) {
                head = node;
            } else {
                ListNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = node;
            }
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size)
                return;
            if (index == 0) {
                addAtHead(val);
                return;
            }
            ListNode node = new ListNode(val);
            ListNode prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size)
                return;
            if (index == 0) {
                head = head.next;
            } else {
                ListNode prev = head;
                for (int i = 0; i < index - 1; i++) {
                    prev = prev.next;
                }
                prev.next = prev.next.next;
            }
            size--;
        }

        static class ListNode {
            int val;
            ListNode next;

            public ListNode(int val) {
                this.val = val;
            }

            public ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }
    }

    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */
}
