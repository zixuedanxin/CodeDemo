//孩子们的游戏（约瑟夫环）
//每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
// 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
// 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,
// 继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)

//【约瑟夫环】
// 每次会有一个小朋友死掉，n个小朋友最后只有一个存活，那么只需要死掉n-1个。
// 注意：死亡位置是动态改变的，所以要注意用取模的方法处理。

package com.bingoabin.newcoder;

import java.util.LinkedList;

public class _46ChildrenGame {
	public class Solution {
		public int LastRemaining_Solution(int n, int m) {
			if (n == 0 || m == 0) {
				return -1;
			}
			int s = 0;
			for (int i = 2; i <= n; i++) {
				s = (s + m) % i;
			}
			return s;
		}
	}

	public class Solution1 {
		public int LastRemaining_Solution(int n, int m) {
			if (n <= 0 || m <= 0) {
				return -1;
			}
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (int i = 0; i < n; i++) {
				list.add(i);
			}
			int index = 0;
			for (int i = 0; i < n - 1; i++) {
				//可以直接这样是因为移除了一个元素后size减1，所以当index不变时,相当于自动向后移动了一位
				//另外注意下标不能越界所以要对size取模
				index = (m - 1 + index) % list.size();
				list.remove(index);
			}
			return list.get(0);
		}
	}
}
