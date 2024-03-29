//丑数
//把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

//丑数：1
// n2: 2
// n3: 3
// n5: 5
// 选择n2,n3,n5中最小的加入丑数数组（或集合），则：
// 丑数：1 2
// n2: 2 4
// n3: 3 6
// n5: 5 10
// 选择n2,n3,n5中最小的加入丑数数组（或集合），则：
// 丑数：1 2   3
// n2: 2 4   6
// n3: 3 6   9
// n5: 5 10 15
//
// 先将1加入到丑数数组list中，每次取出最后一个元素，并分别将该丑数的2/3/5倍分别加入到队列queue2/queue3/queue5中，取三个队列的对头元素之中的最小值min，若三个队列的对头元素等于这个min，则将其出队。然后将这个min加入到丑数数组list中。

//分析可知：其实每次都是从n2、n3、n5数组中各选择一个未加入丑数数组的数来比较，再拿出最小值，再将等于这个最小值的下标向前移动，因此只需要三个指针即可。

package com.bingoabin.newcoder;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author xumaosheng
 * @date 2019/9/10 0:36
 */
public class _33UglyNumber {
	public class Solution {
		public int GetUglyNumber_Solution(int index) {

			if (index <= 0) {
				return 0;
			}
			int[] result = new int[index];
			int count = 0;
			int i2 = 0;
			int i3 = 0;
			int i5 = 0;

			result[0] = 1;
			int tmp = 0;
			while (count < index - 1) {
				tmp = min(result[i2] * 2, min(result[i3] * 3, result[i5] * 5));
				if (tmp == result[i2] * 2) {
					i2++;//三条if防止值是一样的，不要改成else的
				}
				if (tmp == result[i3] * 3) {
					i3++;
				}
				if (tmp == result[i5] * 5) {
					i5++;
				}
				result[++count] = tmp;
			}
			return result[index - 1];
		}

		private int min(int a, int b) {
			return (a > b) ? b : a;
		}
	}

	public class Solution1 {
		public int GetUglyNumber_Solution(int index) {
			if (index <= 0) {
				return 0;
			} else if (index == 1) {
				return 1;
			}
			LinkedList<Integer> queue2 = new LinkedList<>();
			LinkedList<Integer> queue3 = new LinkedList<>();
			LinkedList<Integer> queue5 = new LinkedList<>();
			ArrayList<Integer> list = new ArrayList<>();
			list.add(1);
			int min = 0;
			while (list.size() < index) {
				Integer num = list.get(list.size() - 1);
				queue2.addLast(num * 2);
				queue3.addLast(num * 3);
				queue5.addLast(num * 5);
				min = Math.min(Math.min(queue2.getFirst(), queue3.getFirst()), queue5.getFirst());
				if (queue2.getFirst() == min) {
					queue2.removeFirst();
				}
				if (queue3.getFirst() == min) {
					queue3.removeFirst();
				}
				if (queue5.getFirst() == min) {
					queue5.removeFirst();
				}
				list.add(min);
			}
			return min;
		}
	}

	public class Solution2 {
		public int GetUglyNumber_Solution(int index) {
			if (index <= 0) {
				return 0;
			}
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(1);
			int i2 = 0, i3 = 0, i5 = 0;
			int n2 = 0, n3 = 0, n5 = 0;
			int min;
			while (list.size() < index) {
				n2 = list.get(i2) * 2;
				n3 = list.get(i3) * 3;
				n5 = list.get(i5) * 5;
				min = Math.min(Math.min(n2, n3), n5);
				list.add(min);
				//注意下面不能用else if,当n2、n3、n5有相等值时都应该向前移动，如n2=6,n3=6时。
				if (min == n2) {
					i2++;
				}
				if (min == n3) {
					i3++;
				}
				if (min == n5) {
					i5++;
				}
			}
			return list.get(list.size() - 1);
		}
	}
}
