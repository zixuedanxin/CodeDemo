//二叉搜索树的后续遍历序列
//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。

//思路：
//BST的后序序列的合法序列是，对于一个序列S，最后一个元素是x （也就是根），如果去掉最后一个元素的序列为T，那么T满足：T可以分成两段，前一段（左子树）小于x，后一段（右子树）大于x。

//        6
//     4     8
//  3    5  7   9      3547986
package com.bingoabin.newcoder;

public class _23BinarySearchTree {
	public class Solution {
		public boolean VerifySquenceOfBST(int[] sequence) {
			if (sequence == null || sequence.length == 0) {
				return false;
			}
			return isTree(sequence, 0, sequence.length - 1);
		}

		public boolean isTree(int[] arr, int L, int R) {
			if (L >= R) {
				return true;
			}
			int index = R - 1;
			while ((index >= L) && (arr[index] > arr[R])) {
				index--;
			}
			for (int i = L; i < index; i++) {
				if (arr[i] > arr[R]) {
					return false;
				}
			}
			return isTree(arr, L, index) && isTree(arr, index + 1, R - 1);

		}
	}

	public class Solution1 {
		public boolean VerifySquenceOfBST(int[] sequence) {
			if (sequence == null || sequence.length == 0) {
				return false;
			}
			if (sequence.length == 1) {
				return true;
			}
			return isBSTLastOrder(sequence, 0, sequence.length - 1);
		}

		public boolean isBSTLastOrder(int[] nums, int start, int end) {
			//递归结束条件
			//当执行到start==end的下一步则应该停止
			//由judge(array,start,flag-1)可知终止条件为start-end == 1  或直接写为start > end
			if (start > end) {
				return true;
			}
			int flag = start;
			//寻找第一个比根节点大的元素
			while (nums[flag] < nums[end]) {
				flag++;
			}
			//若在右半部分找到比根节点小的则返回false
			for (int i = flag; i < end; i++) {
				if (nums[i] < nums[end]) {
					return false;
				}
			}
			//当前成立，则进入左右子递归
			return isBSTLastOrder(nums, start, flag - 1) && isBSTLastOrder(nums, flag, end - 1);
		}
	}
}
