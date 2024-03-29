//二进制中的1的个数
//输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。

//思路：
// 		当n不等于0时执行以下循环：
// 		1.判断n的最低位是否为1，若为1，则计数器加1
// 		2.将n无符号右移1位(若使用带符号移位，如果n为负数，左边会一直补1而进入死循环。)
//
// 		利用n & (n-1):
// 		n-1的作用是啥？
// 		例如
// 		正数 7:0...0111  减1后即为：0...0110（省略的全是0） 相与结果：0...0110
// 		正数12:0...1100  减1后即为：0...1011（省略的全是0） 相与结果：0...1000
// 		负数-7:1...1001  减1后即为：1...1000(省略的全是1)  相与结果：1...1000
// 		n&(n-1)会使得从右边开始第1个为1的位置被变为0，由此可以从低位到高位直接检查那些为1的位置。

package com.bingoabin.newcoder;

public class _11NumberInBinary {
	public class Solution {
		public int NumberOf1(int n) {
			//1010  1001        1000  0111
			int count = 0;
			while (n != 0) {
				count++;
				n = n & (n - 1);
			}
			return count;
		}
	}

	public class Solution1 {
		public int NumberOf1(int n) {
			int count = 0;
			while (n != 0) {
				if ((n & 1) == 1) {
					count++;
				}
				n = n >>> 1;//无符号右移
			}
			return count;
		}
	}

	public class Solution2 {
		public int NumberOf1(int n) {
			int count = 0;
			while (n != 0) {
				count++;
				n = n & (n - 1);
			}
			return count;
		}
	}

}
