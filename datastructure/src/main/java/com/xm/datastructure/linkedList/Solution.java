package com.xm.datastructure.linkedList;

import com.xm.datastructure.pojo.ListNode;

class Solution {


  public ListNode reverseList(ListNode head) {
    System.out.println("初始链表：" + head);
    if (head == null || head.getNext() == null) {
      return head;
    }
    ListNode newHead = reverseList(head.getNext());
    head.getNext().setNext(head);
    head.setNext(null);
    System.out.println("反转链表：" + newHead);
    return newHead;
  }

  public ListNode removeDuplicateNodes(ListNode head) {
    if (head == null || head.getNext() == null) {
      return head;
    }
    int pre,cur,next;
    cur = head.getVal();
    ListNode newHead = removeDuplicateNodes(head.getNext());
    next = newHead.getVal();
    if (cur == next) {
      head.setNext(newHead.getNext());
      newHead.setNext(null);
    }

    return head;
  }

  public static void main(String[] args) {
    int[] arr = {1, 3, 3, 6, 8, 9};
    ListNode listNode = new ListNode(arr);
    System.out.println("初始链表：" + listNode);
    //ListNode listNode1 = new Solution().reverseList(listNode);
    ListNode listNode1 = new Solution().removeDuplicateNodes(listNode);
    System.out.println("去重后链表：" + listNode1);
  }
}
