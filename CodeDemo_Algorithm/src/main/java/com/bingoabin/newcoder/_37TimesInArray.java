//数字在排序数组中出现的次数
//统计一个数字在排序数组中出现的次数。

//直男思路
//看到排序中的查找第一感觉就是二分查找。然后由于数组中的元素是int类型的，那么我们只需找到第一个k的位置和最后一个k的位置即可。可以通过查找k-0.5和k+0.5的位置来确定。

package com.bingoabin.newcoder;

public class _37TimesInArray {
	public static void main(String[] args) {

	}

	public class Solution1 {
		public int GetNumberOfK(int[] array, int k) {
			int count = 0;
			for (int i = 0; i <= array.length - 1; i++) {
				if (array[i] == k) {
					count++;
				}
			}
			return count;
		}
	}

	public class Solution2 {
		public int GetNumberOfK(int[] array, int k) {
			return binarySearch(array, k + 0.5) - binarySearch(array, k - 0.5);
		}

		public int binarySearch(int[] nums, double key) {
			int low = 0;
			int high = nums.length - 1;
			int mid = 0;
			while (low <= high) {
				mid = (low + high) >> 1;
				if (nums[mid] < key) {
					low = mid + 1;
				} else if (nums[mid] > key) {
					high = mid - 1;
				}
			}
			return low;
		}
	}
}
