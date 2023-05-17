//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// Related Topics 字符串 
// 👍 483 👎 0

package leetcode.editor.cn;
//Java：替换空格
public class P剑指 Offer 05TiHuanKongGeLcof{
    public static void main(String[] args) {
        Solution solution = new P剑指 Offer 05TiHuanKongGeLcof().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String replaceSpace(String s) {

      StringBuilder sb = new StringBuilder();

      int length = s.length();
      for (int i = 0; i < length; i++) {
        if (s.charAt(i) == ' ') {
          sb.append("%20");
        } else {
          sb.append(s.charAt(i));
        }
      }
      return sb.toString();

      /*//字符数组
      int length = s.length();
      char c = ' ';
      int size = 0;
      char[] arr = new char[length * 3];
      for (int i = 0; i < length; i++) {
        c = s.charAt(i);
        if (c == ' ') {
          arr[size++] = '%';
          arr[size++] = '2';
          arr[size++] = '0';
        } else {
          arr[size++] = c;
        }
      }
      String newStr = new String(arr, 0, size);
      return newStr;
      */
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
