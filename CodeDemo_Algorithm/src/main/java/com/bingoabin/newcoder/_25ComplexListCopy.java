//复杂链表的复制
//输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

//思路：
//1.复制每个节点，并将复制的节点插在原节点之后。
// 2.根据旧节点的random属性给新节点的random属性赋值（新节点的random属性是旧节点的下一个，此处注意判断是否为空）
// 3.拆分新旧链表（奇数为旧链表，偶数为新链表）

package com.bingoabin.newcoder;

import java.util.HashMap;

public class _25ComplexListCopy {
	public class RandomListNode {
		int label;
		RandomListNode next = null;
		RandomListNode random = null;

		RandomListNode(int label) {
			this.label = label;
		}
	}

	public class Solution {
		public RandomListNode Clone(RandomListNode pHead) {
			if (pHead == null) {
				return null;
			}
			HashMap<RandomListNode, RandomListNode> result = new HashMap();
			RandomListNode head = pHead;
			while (head != null) {
				result.put(head, new RandomListNode(head.label));
				head = head.next;
			}
			head = pHead;
			while (head != null) {
				result.get(head).next = result.get(head.next);
				result.get(head).random = result.get(head.random);
				head = head.next;
			}
			return result.get(pHead);
		}
	}

	public class Solution1 {
		public RandomListNode Clone(RandomListNode pHead) {
			if (pHead == null) {
				return null;
			}
			RandomListNode currentNode = pHead;
			RandomListNode temp;
			//1.复制每个节点，并将复制的节点插在原节点之后。
			while (currentNode != null) {
				temp = new RandomListNode(currentNode.label);
				temp.next = currentNode.next;
				currentNode.next = temp;
				currentNode = currentNode.next.next;
			}
			currentNode = pHead;
			//2.根据旧节点的random属性给新节点的random属性赋值（新节点的random属性是旧节点的下一个，此处注意判断是否为空）
			while (currentNode != null) {
				if (currentNode.random == null) {
					currentNode.next.random = null;
				} else {
					currentNode.next.random = currentNode.random.next;
				}
				currentNode = currentNode.next.next;
			}
			currentNode = pHead;
			RandomListNode root = pHead.next;
			//拆分新旧链表（奇数为旧链表，偶数为新链表）
			while (currentNode != null) {
				temp = currentNode.next;
				currentNode.next = currentNode.next.next;
				if (temp.next == null) {
					temp.next = null;
				} else {
					temp.next = temp.next.next;
				}
				currentNode = currentNode.next;
			}
			return root;
		}
	}
}
