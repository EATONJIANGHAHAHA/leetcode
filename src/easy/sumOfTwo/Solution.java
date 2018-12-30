package easy.sumOfTwo;

/*
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的 两个 整数。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
 */

import java.util.HashMap;
import java.util.Map;

class Solution {

    /**
     * 暴力解法, 时间复杂度O(n^2), 空间复杂度O(n^1)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    /**
     * 两边哈希表解法, 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。
     * 如果存在, 我们需要找出它的索引。
     * 保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     *
     * 通过以空间换取速度的方式，
     * 我们可以将查找时间从 O(n)O(n) 降低到 O(1)O(1)。
     * 哈希表正是为此目的而构建的，
     * 它支持以 近似 恒定的时间进行快速查找。
     * 我用“近似”来描述，是因为一旦出现冲突，
     * 查找用时可能会退化到 O(n)O(n)。但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)O(1)。
     *
     * 一个简单的实现使用了两次迭代。
     * 在第一次迭代中，我们将每个元素的值和它的索引添加到表中。
     * 然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target - nums[i]target−nums[i]）是否存在于表中。
     * 注意，该目标元素不能是 nums[i]nums[i] 本身！
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int j = target - nums[i];
            if (map.containsKey(j) && map.get(j) != i) { //如果表内包含这一个减数, 并且这个减数并不是第一个减数它本身
                return new int[] {i, map.get(j)};
            }
        }
        return null;
    }

    /**
     * 与其执行两边迭代, 不如将检查是否存在元素与增加元素放置在同一边迭代中进行.时间空间复杂度与上一算法相近(常数), 但会快一点.
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumOneIterationMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int j = target - nums[i];
            if (map.containsKey(j) && map.get(j) != i) {
                return new int[] {i, map.get(j)};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
