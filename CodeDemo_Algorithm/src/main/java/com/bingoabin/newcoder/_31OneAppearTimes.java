//从1到n整数中1出现的次数
//求出1-13的整数中1出现的次数,并算出100-1300的整数中1出现的次数？
// 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
// ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。

//思路：
//这个写几个例子分析一下就可以知道：
// F（11）= F（10）+ countNumberOf1(11);
// 大问题：求1~n之间的非负整数中1出现的次数
// 小问题：n这个数中有几个1
// 递归方程式如下：
// F(n)= 0，n <= 0;
// F(n)= F(n-1) + countNumberOf1（n）,其他情况
// 可使用递归或者递推解决

//分析n的各个位上出现1的次数，以83221为例。
// 第1位为1：****1，有8322+1种。
// 第2位为1：***1*,有8320+10^1种。
// 第3位为1：**1**，有8300+10^2种。
// 第4位为1：*1***，有8000+10^3种。
// 第5位为1:1****，有0+10^4。
// 为什么是这样子？以第3位为1的情况为例子。
// 83221第3位固定为1则为**1**，此时我们以第3位为分界线分析。
// 低位部分：由于第3位本来为2，所以83100~83199之间有10^2=100种
// 高位部分：00100~82199之间有8300种。
// 需要注意的是若第3位为1（即n=83121）时，低位部分并不能在0~9之间变化。而是在83100~83121之间，有22种。
// http://123.207.138.13/archive/9

package com.bingoabin.newcoder;

public class _31OneAppearTimes {
	public class Solution {
		public int NumberOf1Between1AndN_Solution(int n) {
			int count = 0;
			while (n > 0) {
				String str = String.valueOf(n);
				char[] chars = str.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					if (chars[i] == '1') {
						count++;
					}
				}
				n--;
			}
			return count;
		}
	}

	public class Solution1 {
		//递推版
		public int NumberOf1Between1AndN_Solution(int n) {
			if (n <= 0) {
				return 0;
			}
			int count = 0;
			for (int i = 1; i <= n; i++) {
				count += countNumberOf1(i);
			}
			return count;
		}

		//递归版
//    public int NumberOf1Between1AndN_Solution(int n) {
//        if (n <= 0) {
//            return 0;
//        }
//        return NumberOf1Between1AndN_Solution(n - 1) + countNumberOf1(n);
//    }

		public int countNumberOf1(int num) {
			int count = 0;
			while (num != 0) {
				if (num % 10 == 1) {
					count++;
				}
				num /= 10;
			}
			return count;
		}

	}

	public class Solution2 {
		public int NumberOf1Between1AndN_Solution(int n) {
			int count = 0, previous = 0, coef = 1;
			while (n > 0) {
				int remain = n % 10;
				int over = n / 10;
				if (remain > 1) {
					count += coef;
				} else if (remain == 1) {
					count += previous + 1;
				}
				count += coef * over;
				previous += coef * remain;
				coef *= 10;
				n /= 10;
			}
			return count;
		}
	}
}
