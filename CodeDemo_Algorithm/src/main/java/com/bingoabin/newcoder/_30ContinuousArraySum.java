//连续子数组的最大和
//HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
// 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
// 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)

//思路：
//暴力枚举：直接两层循环，遍历所有情况并记录最大值。
//优化版：
// temp表示和为正数的最长的连续子数组的和（如果该连续子数组和为负数，则开始新的序列；
//     如果和为正数，则继续拼上新的元素）
//     greatestSum表示连续子数组的最大和（每遍历一个元素，我们就取temp和greatestSum的最大值）

package com.bingoabin.newcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _30ContinuousArraySum {
	public class Solution {
		public int FindGreatestSumOfSubArray(int[] array) {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				int sum = 0;
				for (int j = i; j < array.length; j++) {
					sum += array[j];
					list.add(sum);
				}
			}
			if (list.size() <= 0) {
				return 0;
			}
			Collections.sort(list);
			return list.get(list.size() - 1);
		}
	}

	public class Solution1 {
		//暴力枚举
		public int FindGreatestSumOfSubArray(int[] array) {
			int greatestSum = array[0];
			int temp;
			for (int i = 0; i < array.length; i++) {
				temp = array[i];
				if (greatestSum < temp) {
					greatestSum = temp;
				}
				for (int j = i + 1; j < array.length; j++) {
					if (greatestSum < temp + array[j]) {
						greatestSum = temp + array[j];
					}
					temp += array[j];
				}
			}
			return greatestSum;
		}

	}

	public class Solution2 {
		public int FindGreatestSumOfSubArray(int[] array) {
			int sum = array[0];
			int tmp = sum;
			for (int i = 1; i < array.length; i++) {
				tmp = tmp < 0 ? array[i] : tmp + array[i];
				sum = Math.max(sum, tmp);
			}
			return sum;
		}
	}
}
