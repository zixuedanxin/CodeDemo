//合并两个排序的列表
//输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

//思路：
//链表1的头结点的值小于链表2的头结点的值，因此链表1的头结点是合并后链表的头结点。在剩余的结点中，
// 链表2的头结点的值小于链表1的头结点的值，因此链表2的头结点是剩余结点的头结点，
// 把这个结点和之前已经合并好的链表的尾结点链接起来。(其实就是归并排序算法中的“合并”操作)

//另一题
// ## 题目:[合并K个排序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)
//
//
//
// 		## 题目描述
//
// 		> 合并 *k* 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
// 		>
// 		> 示例:
// 		>
// 		> 输入:
// 		> [
// 		>   1->4->5,
// 		>   1->3->4,
// 		>   2->6
// 		> ]
// 		> 输出: 1->1->2->3->4->4->5->6

//思路：
//想办法转成合并两个排序链表再做

package com.bingoabin.newcoder;

public class _16JoinSortList {
	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {

			this.val = val;
		}
	}

	public class Solution {
		public ListNode Merge(ListNode list1, ListNode list2) {
			if (list1 == null) {
				return list2;
			}
			if (list2 == null) {
				return list1;
			}
			if (list1.val < list2.val) {
				list1.next = Merge(list1.next, list2);
				return list1;
			} else {
				list2.next = Merge(list1, list2.next);
				return list2;
			}
		}
	}

	public class Solution1 {
		public ListNode Merge(ListNode list1, ListNode list2) {
			if (list1 == null) {
				return list2;
			}
			if (list2 == null) {
				return list1;
			}
			if (list1.val >= list2.val) {
				list2.next = Merge(list1, list2.next);
				return list2;
			} else {
				list1.next = Merge(list1.next, list2);
				return list1;
			}
		}
	}

	public class Solution2 {
		public ListNode Merge(ListNode list1, ListNode list2) {
			ListNode head = new ListNode(-1);
			head.next = null;
			ListNode root = head;
			//注意这个条件：如果两个链表等长可以使其中一个扫描完
			while (list1 != null && list2 != null) {
				if (list1.val < list2.val) {
					head.next = list1;
					head = list1;
					list1 = list1.next;
				} else {
					head.next = list2;
					head = list2;
					list2 = list2.next;
				}
			}
			if (list1 != null) {
				head.next = list1;
			}
			if (list2 != null) {
				head.next = list2;
			}
			return root.next;
		}
	}

	public class Solution3 {
		public ListNode mergeKLists(ListNode[] lists) {
			if (lists.length == 0) {
				return null;
			} else if (lists.length == 1) {
				return lists[0];
			} else if (lists.length == 2) {
				return mergeTwoList(lists[0], lists[1]);

			} else {
				ListNode[] l1 = new ListNode[lists.length / 2];
				ListNode[] l2 = new ListNode[lists.length - lists.length / 2];
				for (int i = 0; i < lists.length; i++) {
					if (i < lists.length / 2) {
						l1[i] = lists[i];
					} else {
						l2[i - lists.length / 2] = lists[i];
					}
				}
				return mergeTwoList(mergeKLists(l1), mergeKLists(l2));
			}
		}

		public ListNode mergeTwoList(ListNode l1, ListNode l2) {
			if (l1 == null) {
				return l2;
			}
			if (l2 == null) {
				return l1;
			}
			if (l1.val >= l2.val) {
				l2.next = mergeTwoList(l1, l2.next);
				return l2;
			} else {
				l1.next = mergeTwoList(l1.next, l2);
				return l1;
			}

		}
	}
}
