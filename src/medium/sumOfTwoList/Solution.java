package medium.sumOfTwoList;

/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

注: 下列三种解法在时间复杂度和空间复杂度上没有本质区别.
 */
public class Solution {

    /**
     * 初级解法.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        ListNode node1 = l1;
        ListNode node2 = l2;
        int carry = 0;
        while (node1 != null || node2 != null) {
            int sum = 0;
            if (node1 == null) {
                sum = node2.val + carry;
            } else if (node2 == null) {
                sum = node1.val + carry;
            } else {
                sum = node1.val + node2.val + carry;
            }

            if (sum >= 10) carry = 1;
            else carry = 0;

            if (head != null) {
                current.next = new ListNode(sum % 10);
                current = current.next;
            } else {
                head = new ListNode(sum % 10);
                current = head;
            }


            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }
        if (carry == 1) {
            current.next = new ListNode(1);
        }
        return head;
    }

    /**
     * 解法二, 与其每次判断进位值的大小, 不如直接计算.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersSolveTwo(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        ListNode node1 = l1;
        ListNode node2 = l2;
        int carry = 0;
        while (node1 != null || node2 != null) {
            int sum = 0;
            if (node1 == null) {
                sum = node2.val + carry;
            } else if (node2 == null) {
                sum = node1.val + carry;
            } else {
                sum = node1.val + node2.val + carry;
            }

            carry = sum / 10;

            if (head != null) {
                current.next = new ListNode(sum % 10);
                current = current.next;
            } else {
                head = new ListNode(sum % 10);
                current = head;
            }


            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }
        if (carry == 1) {
            current.next = new ListNode(1);
        }
        return head;
    }

    /**
     * 解法三, 使用条件赋值语法.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersSolveThree(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        ListNode node1 = l1;
        ListNode node2 = l2;
        int carry = 0;
        while (node1 != null || node2 != null) {
            int x = (node1 != null) ? node1.val : 0;
            int y = (node2 != null) ? node2.val : 0;

            int sum = carry + x + y;

            carry = sum / 10;

            if (head != null) {
                current.next = new ListNode(sum % 10);
                current = current.next;
            } else {
                head = new ListNode(sum % 10);
                current = head;
            }

            node1 = (node1 != null) ? node1.next : null;
            node2 = (node2 != null) ? node2.next : null;

        }
        if (carry == 1) {
            current.next = new ListNode(1);
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode (4);

        Solution solution = new Solution();
        ListNode result = solution.addTwoNumbers(l1, l2);
        System.out.println("args = [" + result.val+ "]");
    }
}
