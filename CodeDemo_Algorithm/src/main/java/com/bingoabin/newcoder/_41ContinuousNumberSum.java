//和为S的连续正数序列
//小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
// 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
// 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!

//输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序

//1.若项数n为奇数，则平均值为整数，此时sum可以被n整除。（n&1）==1 && sum% n == 0
// 	若项数n为偶数，则平均值不为整数，且小数部分为0.5，此时余数是除数的一半。(n&1) == 0 &&		       (sum%n)*2==n
// 2.缩小项数范围:
//      从1到n这种可以使得项数最多，公差为1，项数为n的等差数列和sum=(1+n)n/2;可得n^2+n=2sum--	        >n^2<2sum-->n<2sum的算术平方根。注意使用int之后小数部分会被舍弃。最小项目为2
// 3.和为sum的n个数组成的公差为1的等差数列的第一个数为：sum/n - (n-1)/2
//
// 等差数列基本公式:
// 	末项=首项+(项数-1)×公差
// 	和=(首项+末项)×项数÷2

//更简洁版

package com.bingoabin.newcoder;

import java.util.ArrayList;

public class _41ContinuousNumberSum {
	public class Solution {
		public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			if (sum == 1) {
				return list;
			}
			int n = (int) (Math.ceil((Math.sqrt(8 * sum + 1) - 1) / 2));
			int tmp = 0;
			int num = 0;
			for (int i = n; i > 1; i--) {
				tmp = (i + 1) * i / 2;
				if ((sum - tmp) % i == 0) {
					ArrayList<Integer> arrayList = new ArrayList<Integer>();
					num = (sum - tmp) / i;
					for (int a = 1; a <= i; a++) {
						arrayList.add(num + a);
					}
					list.add(arrayList);
				}
			}
			return list;
		}
	}

	public class Solution1 {
		public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
			ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> tempList = null;
			int firstNumber = 0;
			if (sum < 3) {
				return lists;
			}
			int count = (int) Math.sqrt(2 * sum);
			for (int i = count; i >= 2; i--) {
				if ((i & 1) == 1 && sum % i == 0) {
					tempList = new ArrayList<Integer>();
					firstNumber = sum / i - i / 2;
					for (int j = 0; j < i; j++) {
						tempList.add(firstNumber + j);
					}
					lists.add(tempList);
				} else if ((i & 1) == 0 && 2 * (sum % i) == i) {
					tempList = new ArrayList<Integer>();
					firstNumber = sum / i - (i - 1) / 2;
					for (int j = 0; j < i; j++) {
						tempList.add(firstNumber + j);
					}
					lists.add(tempList);
				}
			}
			return lists;
		}
	}

	public class Solution2 {
		//注意在Java中，两个int类型的数字相除，小数部分会被舍弃。
		public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
			ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
			int count = (int) Math.sqrt(2 * sum);
			for (int i = count; i >= 2; i--) {
				//个数为奇数时,中间值便是平均值
				//个数为偶数时，平均值的小数部分是0.5--->即余数为除数的一半(可以省略(i & 1)== 0是因为如果是可以整除的话求膜会等于0，也就不等于i了)
				if ((i & 1) == 1 && sum % i == 0 || (sum % i) * 2 == i) {
					ArrayList<Integer> tempList = new ArrayList<>();
					//第一个数
					int firstNum = sum / i - (i - 1) / 2;//奇数偶数都是这样算
					//一共i个
					for (int j = 0; j < i; j++) {
						tempList.add(firstNum++);
					}
					lists.add(tempList);
				}
			}
			return lists;
		}
	}
}
