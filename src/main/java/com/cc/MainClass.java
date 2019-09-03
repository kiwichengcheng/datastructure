package com.cc;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




class Solution {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode removeElements(ListNode head, int val) {
        /*ListNode pre = new ListNode(0);
        pre.next = head;
        while(pre.next != null){
            if(pre.next.val == val){
                if(pre.next == head){
                    head = head.next;
                }
                pre.next = pre.next.next;
            }else {
                pre = pre.next;
            }
        }

        return head;*/

        if(head == null){
            return head;
        }else {
            if(head.val == val){
                return removeElements(head.next,val);
            }else {
                head.next = removeElements(head.next, val);
                return head;
            }

        }

    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static Solution.ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        Solution.ListNode dummyRoot = new Solution.ListNode(0);
        Solution.ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new Solution.ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(Solution.ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            Solution.ListNode head = stringToListNode(line);
            line = in.readLine();
            int val = Integer.parseInt(line);

            Solution.ListNode ret = new Solution().removeElements(head, val);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}
