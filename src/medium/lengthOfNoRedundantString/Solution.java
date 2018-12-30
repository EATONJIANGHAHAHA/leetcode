package medium.lengthOfNoRedundantString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class Solution {

    /**
     * 暴力解法, it takes O(n^3) of time of complete.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                result = (isUnique(s, i, j)) ? result : j - i;
            }
        }
        return result;
    }
    private boolean isUnique(String s, int i, int j) {
        Set<Character> set = new HashSet<>();
        for (int k = i; k < j; k++) {
            Character c = s.charAt(k);
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }

    /**
     * 滑动窗口解法, 基本思路: 有两个索引i, j, 两个索引之间的空隙就是窗口.
     * 通过滑动着两个索引所在的位置来判断被记录的最长子字符串里是否包含下一个要被判断的字符.
     * 方法内部定义了一个set, 里面包含了当前遍历过的最长的子字符串.
     * 向右侧滑动j, 如果j不在set里面, 添加进去, 继续滑动, 更新最长字符串的长度.
     * 如果set内部包含S[j], 使用i索引从前向后删除set, 直到遇见重复字符并将重复字符删除为止,
     * 才继续向右侧滑动j, 直到j便利完成.
     * @param s
     * @return
     */
    private int lengthOfLongestSubstringSlideWindows(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else set.remove(s.charAt(i++));
        }
        return ans;
    }

    /**
     * 这个方法改善了上一个方法. 再上一个方法中,最坏的情况下, 一个数组中的每个元素都需要被迭代两遍, 因为没发现一个新的不属于当前最长字符的元素的时候,
     * 都要一个一个把这个元素添加进set当中, 而且还是从前向后依次添加,这样实际上是很费时德操作的. 所以与其一个一个添加, 不如使用一个映射.
     * 将字符和字符在数组中所在的索引为止直接添加到映射中, 这样就可以直接跳过前面那些不需要添加的元素了.
     * @param s
     * @return
     */
    private int lenghOfLongestSubstringSw2(String s) {
        int n = s.length(), result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)),i);
            }
            result = Math.max(result, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.lengthOfLongestSubstringSlideWindows("aaaa");
        System.out.println("args = [" + result + "]");
    }
}
