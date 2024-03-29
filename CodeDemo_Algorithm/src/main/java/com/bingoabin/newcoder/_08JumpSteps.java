//跳台阶
//一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

//思路
// 		暴力枚举（自顶向下递归）：
// 		若台阶数小于等于0，返回0；
// 		若台阶数为1，返回1；(1)
// 		若台阶数为2，返回2；(1,1),(2)
// 		否则，返回F(n-1)+F(n-2);（因为下一步只能是跳1级或者跳2级）
//
// 		备忘录算法（自顶向下递归）：
// 		上面的方法包含大量重复计算，这里利用Map来记录计算过的结果，以减少计算次数。
//
// 		迭代法(自底向上迭代，也许也算动态规划吧):
// 		拿两个变量记录前两个结果和一个临时变量保存当前计算结果（也可不用改临时变量）

package com.bingoabin.newcoder;

import java.util.HashMap;
import java.util.Map;

public class _08JumpSteps {
	public class Solution {
		public int JumpFloor(int target) {
			if (target == 1) {
				return 1;
			}
			if (target == 2) {
				return 2;
			}
			if (target >= 3) {
				return JumpFloor(target - 1) + JumpFloor(target - 2);
			}
			return -1;
		}
	}

	public class Solution1 {
		public int JumpFloor(int target) {
			if (target <= 0) {
				return 0;
			} else if (target == 1 || target == 2) {
				return target;
			} else {
				return JumpFloor(target - 1) + JumpFloor(target - 2);
			}
		}
	}

	public class Solution2 {
		public int JumpFloor(int target) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			return JumpFloor(target, map);
		}

		public int JumpFloor(int n, Map<Integer, Integer> map) {
			if (n <= 0) {
				return 0;
			} else if (n <= 2) {
				return n;
			}
			if (map.containsKey(n)) {
				return map.get(n);
			} else {
				int value = JumpFloor(n - 1, map) + JumpFloor(n - 2, map);
				map.put(n, value);
				return value;
			}
		}
	}

	public class Solution3 {
		public int JumpFloor(int target) {
			if (target <= 0) {
				return 0;
			} else if (target == 1 || target == 2) {
				return target;
			}
			int temp = 0, pre = 1, last = 2;
			for (int i = 3; i <= target; i++) {
				temp = pre + last;
				pre = last;
				last = temp;
			}
			return last;
		}
	}
}
