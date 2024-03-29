//重建二叉树
//输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
// 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

//思路：
//      先根遍历序列pre：1,2,4,7,3,5,6,8
// 		中根遍历序列in：4,7,2,1,5,3,8,6
//
// 		采用递归
// 		取pre数组中的第一个元素1，则in数组中以根节点元素1为界，左边即为根节点的左子树元素序列，右边即为根节点的右子树元素序列。
// 		即左子树的中序序列为：4,7,2；右子树的中序序列为：5,3,8,6
// 		注意先根遍历顺序为：根--左孩子--右孩子
// 		所以左子树的前序序列为2,4,7；右子树的中序序列为：3,5,6,8
// 		继续递归pre数组的下一个元素即可，终止条

//注意：
//前序遍历：   根   左   右
//中序遍历：   左   根   右
//后序遍历：   左   右   根

package com.bingoabin.newcoder;

public class _04RebuildBinaryTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public class Solution {
		public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
			TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
			return root;
		}

		private TreeNode reConstructBinaryTree(
				int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

			if (startPre > endPre || startIn > endIn) {
				return null;
			}
			TreeNode root = new TreeNode(pre[startPre]);

			for (int i = startIn; i <= endIn; i++) {
				if (in[i] == pre[startPre]) {
					root.left = reConstructBinaryTree(
							pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
					root.right = reConstructBinaryTree(
							pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
				}
			}
			return root;
		}
	}

	public class Solution1 {
		public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
			return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
		}

		public TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
			//注意条件
			if (preStart > preEnd) {
				return null;
			}
			TreeNode node = new TreeNode(pre[preStart]);
			for (int i = inStart; i <= inEnd; i++) {
				if (pre[preStart] == in[i]) {
					//i-inStart即为左子树的先序序列长度
					node.left = reConstructBinaryTree(pre, preStart + 1, i - inStart + preStart, in, inStart, i - 1);
					node.right = reConstructBinaryTree(pre, i - inStart + preStart + 1, preEnd, in, i + 1, inEnd);
					break;
				}
			}
			return node;
		}
	}
}
