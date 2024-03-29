//二叉搜索树与双向列表
//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

//思路：
//    中根遍历
//     1.转换左子树。
//     2.处理根节点：
//         在方法外部定义一个root用于保存链表的表头元素，定义一个lastNode用于保存链表表尾元素。
//         如果root为空，则将根节点pRootOfTree赋值给root和lastNode，此时root将成为链表表头（树的最左叶子节点），
//         lastNode成为链表表尾元素。
//         否则，将根节点pRootOfTree加入链表表尾，并让lastNode指向新的表尾元素。
//     3.转化右子树。
//     4.返回root

package com.bingoabin.newcoder;

public class _26SearchTreeToBothwayList {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		TreeNode root = null;
		TreeNode lastNode = null;

		public TreeNode Convert(TreeNode pRootOfTree) {
			if (pRootOfTree == null) {
				return null;
			}
			Convert(pRootOfTree.left);
			if (root == null) {
				root = pRootOfTree;
				lastNode = pRootOfTree;
			} else {
				pRootOfTree.left = lastNode;
				lastNode.right = pRootOfTree;
				lastNode = pRootOfTree;
			}
			Convert(pRootOfTree.right);
			return root;
		}
	}
}
