//旋转数组的最小数字
//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
// NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

//思路：
//第一个下降的元素即为目标元素

package com.bingoabin.newcoder;

public class _06RotateArrayMin {
	public class Solution {
		public int minNumberInRotateArray(int[] array) {
			if (array.length == 0) {
				return 0;
			}
			int i = 0;
			while (true) {
				if (array[0] > array[i++]) {
					return array[i - 1];
				}
			}
		}
	}

	public class Solution1 {
		public int minNumberInRotateArray(int[] array) {
			if (array.length == 0 || array == null) {
				return 0;
			}
			int minNumber = 0;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i + 1] < array[i]) {
					minNumber = array[i + 1];
					break;
				}
			}
			return minNumber;
		}
	}
}
