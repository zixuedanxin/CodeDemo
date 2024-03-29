//数组中出现次数超过一半的数字
//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

//思路：
//数组中超过一半了，中位数肯定就是这个数了，先排序，然后取中位数就行了

//使用辅助数组计数：
// 	1.使用map
// 	2.先找出最后的数max,再创建长度为max的数组。

//阵地攻守思想
// 	 第一个数字作为第一个士兵，守阵地；count = 1； 遇到相同元素，count++; 遇到不相同元素，即为敌人，同归于尽,count–；
// 	 当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是所求元素。 最近再遍历一次数组，判断这个士兵的个数看是否大于数组长度的一半即可。

package com.bingoabin.newcoder;

import java.util.Arrays;
import java.util.HashMap;

public class _28InArrayAppearHalf {
	public class Solution {
		public int MoreThanHalfNum_Solution(int[] array) {
			int len = array.length;
			if (len < 1) {
				return 0;
			}
			int count = 0;
			Arrays.sort(array);
			int num = array[len / 2];
			for (int i = 0; i < len; i++) {
				if (num == array[i]) {
					count++;
				}
			}
			if (count <= (len / 2)) {
				num = 0;
			}
			return num;
		}
	}

	public class Solution1 {
		public int MoreThanHalfNum_Solution(int[] array) {
			Arrays.sort(array);
			int middle = array[array.length / 2];
			int count = 0;
			for (int i = 0; i < array.length; ++i) {
				if (array[i] == middle) {
					++count;
				}
			}
			return (count > array.length / 2) ? middle : 0;
		}
	}

	public class Solution2 {
		public int MoreThanHalfNum_Solution(int[] array) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < array.length; i++) {
				Integer count = map.get(array[i]);
				if (count == null) {
					count = 1;
				} else {
					count++;
				}
				if (2 * count > array.length) {
					return array[i];
				}
				map.put(array[i], count);
			}
			return 0;
		}
	}

	public class Solution3 {
		public int MoreThanHalfNum_Solution(int[] array) {
			int index = 0;
			int count = 1;
			for (int i = 1; i < array.length; i++) {
				if (count == 0) {
					index = i;
					count = 1;
				}
				if (array[index] != array[i]) {
					count--;
				} else {
					count++;
				}
			}
			count = 0;
			for (int i = 0; i < array.length; i++) {
				if (array[i] == array[index]) {
					count++;
				}
			}
			if (2 * count > array.length) {
				return array[index];
			}
			return 0;
		}
	}
}
