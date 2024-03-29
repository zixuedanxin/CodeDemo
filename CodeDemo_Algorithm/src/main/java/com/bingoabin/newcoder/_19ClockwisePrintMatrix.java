//顺时针打印矩阵
//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

//思路：
//空间复杂度为O（1）的一种做法：计数器count初始值为二维数组的元素个数，当计数器大于0的时候执行四个循环，每个循环代表一个方向，注意如果进入了某个循环（也就是往某个方向走了）需要做一些处理。并且在每个循环里也判断计数器是否为0。

package com.bingoabin.newcoder;

import java.util.ArrayList;

public class _19ClockwisePrintMatrix {
	public class Solution {
		public ArrayList<Integer> printMatrix(int[][] matrix) {
			ArrayList result = new ArrayList();
			if (matrix.length == 0 || matrix == null) {
				return result;
			}
			int up = 0;
			int down = matrix.length - 1;
			int left = 0;
			int right = matrix[0].length - 1;
			while (true) {
				//向右
				for (int i = left; i <= right; i++) {
					result.add(matrix[up][i]);
				}
				if (++up > down) {
					break;
				}
				//向下
				for (int i = up; i <= down; i++) {
					result.add(matrix[i][right]);
				}
				if (--right < left) {
					break;
				}
				//向左
				for (int i = right; i >= left; i--) {
					result.add(matrix[down][i]);
				}
				if (--down < up) {
					break;
				}
				//向上
				for (int i = down; i >= up; i--) {
					result.add(matrix[i][left]);
				}
				if (++left > right) {
					break;
				}
			}
			return result;
			// 1 2  3  4
			// 5 6  7  8
			// 9 10 11 12
			//13 14 15 16
		}
	}

	public class Solution1 {
		public ArrayList<Integer> printMatrix(int[][] matrix) {
			ArrayList<Integer> lists = new ArrayList<Integer>();
			int len = matrix[0].length;
			int i = 0, j = 0, count = 0;
			int column = 0;
			column = matrix.length;
			int flag[][] = new int[column][len];
			for (int k = 0; k < column; k++) {
				for (int l = 0; l < len; l++) {
					flag[k][l] = 0;
				}
			}
			int countAllData = 0;
			while (true) {
				while (i < len - count) { //右

					if (i < len && j < column && flag[j][i] == 0) {
						lists.add(matrix[j][i]);
						flag[j][i] = 1;
						countAllData++;
					}
					i++;
				}
				i--;
				while (j < column - count) { //下
					if (i < len && j < column && flag[j][i] == 0) {
						lists.add(matrix[j][i]);
						flag[j][i] = 1;
						countAllData++;
					}
					j++;
				}
				j--;
				while (i >= count) { //左
					if (i < len && j < column && flag[j][i] == 0) {
						lists.add(matrix[j][i]);
						flag[j][i] = 1;
						countAllData++;
					}
					i--;
				}
				i++;
				while (j > count) { //上
					if (i < len && j < column && flag[j][i] == 0) {
						lists.add(matrix[j][i]);
						flag[j][i] = 1;
						countAllData++;
					}
					j--;
				}
				j++;
				count++;
				if (countAllData == len * column) {
					break;
				}
			}
			return lists;
		}
	}

	public class Solution2 {
		public ArrayList<Integer> printMatrix(int[][] matrix) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int chang = matrix[0].length;
			int kuang = matrix.length;
			int count = chang * kuang, i = 0, j = 0, layer = 0;
			boolean flag = false;
			while (count > 0) {
				while (j < chang - layer && count > 0) {    //右
					list.add(matrix[i][j++]);
					count--;
					flag = true;
				}
				if (flag) {
					j--;
					i++;
					flag = false;
				}
				while (i < kuang - layer && count > 0) {    //下
					list.add(matrix[i++][j]);
					count--;
					flag = true;
				}
				if (flag) {
					i--;
					j--;
					flag = false;
				}
				while (j >= layer && count > 0) {    //左
					list.add(matrix[i][j--]);
					count--;
					flag = true;
				}
				if (flag) {
					j++;
					i--;
					flag = false;
				}
				while (i > layer && count > 0) {    //上
					list.add(matrix[i--][j]);
					count--;
					flag = true;
				}
				if (flag) {
					i++;
					j++;
					flag = false;
				}
				if (count == chang * kuang) {
					break;
				}
				layer++;
			}
			return list;
		}
	}
}
