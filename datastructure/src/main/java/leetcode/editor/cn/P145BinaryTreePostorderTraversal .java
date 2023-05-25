//给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 1042 👎 0

package leetcode.editor.cn;


import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//Java：二叉树的后序遍历
public class P145BinaryTreePostorderTraversal {
  public static void main(String[] args) {
    Solution solution = new P145BinaryTreePostorderTraversal().new Solution();
    // TO TEST
  }
  //leetcode submit region begin(Prohibit modification and deletion)

  /**
   * Definition for a binary tree node.
   * public class TreeNode {
   * int val;
   * TreeNode left;
   * TreeNode right;
   * TreeNode() {}
   * TreeNode(int val) { this.val = val; }
   * TreeNode(int val, TreeNode left, TreeNode right) {
   * this.val = val;
   * this.left = left;
   * this.right = right;
   * }
   * }
   */
  class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> res = new ArrayList<>();
      if (root == null) return res;
      Deque<TreeNode> stack = new LinkedList<>();
      TreeNode prev = null;
      while (root != null || !stack.isEmpty()) {
        while (root != null) {
          stack.push(root);
          root = root.left;
        }
        root = stack.pop();
        if (root.right == null || root.right == prev) {
          res.add(root.val);
          prev = root;
          root = null;
        } else {
          stack.push(root);
          root = root.right;
        }
      }
      return res;
    }

  }
//leetcode submit region end(Prohibit modification and deletion)

}
