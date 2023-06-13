//定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。 
//
// 
//
// 示例: 
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.min();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.min();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// 各函数的调用总次数不超过 20000 次 
// 
//
// 
//
// 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/ 
// Related Topics 栈 设计 
// 👍 499 👎 0

package leetcode.editor.cn;

import java.util.Stack;

//Java：包含min函数的栈
public class P剑指 Offer 30BaoHanMinhanShuDeZhanLcof{
    public static void main(String[] args) {
        Solution solution = new P剑指 Offer 30BaoHanMinhanShuDeZhanLcof().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> A,B;
    public MinStack() {
      A = new Stack<>();
      B = new Stack<>();
    }
    
    public void push(int x) {
      A.add(x);
      if (B.empty() || B.peek() >= x) B.add(x);
    }
    
    public void pop() {
      if (A.pop().equals(B.peek())) B.pop();
    }
    
    public int top() {
      return A.peek();
    }
    
    public int min() {
      return B.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
