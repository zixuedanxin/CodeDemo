//二叉树中和为某个值的路径
//输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)

//思路：
//深度优先搜索遍历DFS、先根遍历
// 使用path保存当前路径，list保存所有路径。
// 1.首先，将根节点加入path，将将target减去根节点的值
// 2.如果target等于0，并且root没有后继节点（此处即为叶子节点），则将path加入list。
// 3.递归深搜root.left
// 4.递归深搜root.right
// 5.path回退一个节点（即当path无节点时停止递归）
//
// 注意：使用递归时把代码当成叶子节点或空节点考虑

package com.bingoabin.newcoder;

import java.util.ArrayList;

public class _24BinaryTreeSumPath {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		ArrayList<ArrayList<Integer>> result = new ArrayList();
		ArrayList<Integer> subresult = new ArrayList();

		public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
			if (root == null) {
				return result;
			}
			subresult.add(root.val);
			target = target - root.val;
			if (target == 0 && root.left == null && root.right == null) {
				result.add(new ArrayList<Integer>(subresult));
			}
			FindPath(root.left, target);
			FindPath(root.right, target);
			subresult.remove(subresult.size() - 1);
			return result;
		}
	}

	public class Solution1 {
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

		public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
			if (root == null) {
				return list;
			}
			path.add(root.val);
			target -= root.val;
			if (target == 0 && root.left == null && root.right == null) {
				list.add(new ArrayList<Integer>(path)); //注意要重新new一个  否则后面的操作会影响这个结果  所以最后回退到把根节点回退了，path就为空
			}
			FindPath(root.left, target);
			FindPath(root.right, target);
			path.remove(path.size() - 1);//回退一个节点
			return list;
		}
	}
}
