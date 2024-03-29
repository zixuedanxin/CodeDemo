//平衡二叉树
//输入一棵二叉树，判断该二叉树是否是平衡二叉树。

//解析：
//         定义：若一棵二叉树中每个节点的左右子树的高度至多相差1，则称此二叉树为平衡二叉树。
//         （注意：平衡二叉树并不一定是二叉搜索树）
//     版本1
//         思路：
//             若为空树，返回true。
//             若不为空树，求根节点的左右子树高度并比较，若相差小于等于1，则递归判断左右子树是否满足，若不是，返回false。

//版本2:由于版本1在判断上层节点时，会重复遍历下层节点，所以可以改为从下往上遍历，若不满足定义则返回结果不再判断。
package com.bingoabin.newcoder;

public class _39BalanceBinaryTree {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		public boolean IsBalanced_Solution(TreeNode root) {
			if (root == null) {
				return true;
			}

			if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
				return false;
			}

			return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);

		}

		public int getHeight(TreeNode root) {
			if (root == null) {
				return 0;
			}
			return max(getHeight(root.left), getHeight(root.right)) + 1;
		}

		private int max(int a, int b) {
			return (a > b) ? a : b;
		}
	}

	public class Solution1 {
		public boolean IsBalanced_Solution(TreeNode root) {
			if (root == null) {
				return true;
			}
			int leftHeight = getTreeHeight(root.left);
			int rightHeight = getTreeHeight(root.right);
			return Math.abs(leftHeight - rightHeight) <= 1 ? IsBalanced_Solution(root.left) &&
					IsBalanced_Solution(root.right) : false;
		}

		public int getTreeHeight(TreeNode root) {
			if (root == null) {
				return 0;
			}
			return Math.max(getTreeHeight(root.left), getTreeHeight(root.right)) + 1;
		}
	}

	public class Solution2 {
		public boolean IsBalanced_Solution(TreeNode root) {
			return getTreeHeight(root) != -1;
		}

		//自底向上遍历，若当前二叉树的左子树相差大于1，则返回-1，否则返回左右子树高度的大者+1，并判断当前子树高度是否为-1，
		//为-1则直接返回。
		public int getTreeHeight(TreeNode root) {
			if (root == null) {
				return 0;
			}
			int left = getTreeHeight(root.left);
			if (left == -1) {
				return -1;
			}
			int right = getTreeHeight(root.right);
			if (right == -1) {
				return -1;
			}
			return Math.abs(getTreeHeight(root.left) - getTreeHeight(root.right)) > 1 ? -1 : Math.max(left, right) + 1;
		}
	}
}
