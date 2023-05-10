package com.xm.datastructure.pojo;


public class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }

  public int getVal() {
    return val;
  }

  public void setVal(int val) {
    this.val = val;
  }

  public ListNode getNext() {
    return next;
  }

  public void setNext(ListNode next) {
    this.next = next;
  }

  // 链表节点的构造函数
  // 使用arr为参数，创建一个链表，当前的ListNode为链表头节点
  public ListNode(int[] arr){
      try {
        if(arr == null || arr.length == 0)
        throw new Exception("arr can not be empty");
      } catch (Exception e) {
        System.out.println("arr can not be empty" + e.getMessage());
      }

    this.val = arr[0];
    ListNode cur = this;
    for(int i = 1; i < arr.length; i ++){
      cur.next = new ListNode(arr[i]);
      cur = cur.next;
    }
  }

  //以当前节点为头节点的链表信息字符串 方便查看
  @Override
  public String toString(){
    StringBuilder res = new StringBuilder();
    ListNode cur = this;
    while(cur != null){
      res.append(cur.val + "->");
      cur = cur.next;
    }
    res.append("NULL");
    return res.toString();
  }
}

