//树的子结构
//输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

//思路：
//      双递归：外层HasSubtree是一个先根遍历递归，内层isDirectSubTree是一个判断root2是不是root1的直接子结构的递归。步骤如下：
// 		1.如果当前root1和root2的节点值相同，则可能为子结构，进入子递归判断。
// 		2.若上一步找到子结构则返回true，否则递归判断root2是否为root1的左子树的子结构。
// 		3.若上一步找到子结构则返回true，否则递归判断root2是否为root1的右子树的子结构。

package com.bingoabin.newcoder;

public class _17TreeSubstructure {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		public boolean HasSubtree(TreeNode root1, TreeNode root2) {
			if (root1 == null || root2 == null) {
				return false;
			}
			return isSubTree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
		}

		public boolean isSubTree(TreeNode root1, TreeNode root2) {
			if (root2 == null) {
				return true;
			}
			if (root1 == null) {
				return false;
			}

			if (root1.val != root2.val) {
				return false;
			}
			return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
		}
	}

	public class Solution1 {
		public boolean HasSubtree(TreeNode root1, TreeNode root2) {
			if (root2 == null) {
				return false;
			}
			boolean flag = false;
			if (root1 != null && root2 != null) {
				if (root1.val == root2.val) {
					flag = isDirectSubTree(root1, root2);
				}
				if (!flag) {
					flag = HasSubtree(root1.left, root2);
				}
				if (!flag) {
					flag = HasSubtree(root1.right, root2);
				}
			}
			return flag;
		}

		public boolean isDirectSubTree(TreeNode root1, TreeNode root2) {
			if (root2 == null) {
				return true;
			}
			if (root1 == null && root2 != null) {
				return false;
			}
			if (root1.val != root2.val) {
				return false;
			}
			return isDirectSubTree(root1.left, root2.left) && isDirectSubTree(root1.right, root2.right);
		}
	}
}
