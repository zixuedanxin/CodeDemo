//数组中只出现一次的数字
//一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。

//0 ^ A = A     A ^ A = 0   ^满足交换律（可用例子4^5^8和4^8^5）
// 1.将所有数异或则得到两个单身数字的异或结果。
// 2.找出这个结果的二进制序列中的一个1（不同的数，异或不可能为0），记录其位置index。
// 3.遍历数组，以各个数字的二进制序列的index位是否为1，分别异或得出结果。

package com.bingoabin.newcoder;

public class _40OnceAppearInArray {
	//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
	public class Solution {
		public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
			if (array.length < 2) {
				return;
			}
			int myxor = 0;
			int flag = 1;
			for (int i = 0; i < array.length; ++i) {
				myxor ^= array[i];
			}
			while ((myxor & flag) == 0) {
				flag <<= 1;
			}
			// num1[0] = myxor;
			//num2[0] = myxor;
			for (int i = 0; i < array.length; ++i) {
				if ((flag & array[i]) == 0) {
					num2[0] ^= array[i];
				} else {
					num1[0] ^= array[i];
				}
			}

		}
	}

	//num1,num2分别为长度为1的数组。传出参数
	//将num1[0],num2[0]设置为返回结果
	public class Solution1 {
		public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
			//方法1：基于：两个相同的数异或等于0
			int temp = 0;
			int i;
			//求出两个目标数异或的结果
			for (i = 0; i < array.length; i++) {
				temp ^= array[i];
			}
			//求出结果的二进制序列中的一个1
			int index = 0;
			while ((temp & 1) == 0) {
				temp = temp >> 1;
				index++;
			}
			num1[0] = 0;
			num2[0] = 0;
			//按第index位是否为1分组，则目标数在不同的组，每组其他数又都有相同的数在同一组，所以全部异或即是结果
			for (i = 0; i < array.length; i++) {
				if ((array[i] >> index & 1) == 0) {//注意：&运算符的优先级比==还要低
					num1[0] ^= array[i];
				} else {
					num2[0] ^= array[i];
				}
			}
		}
	}
}
