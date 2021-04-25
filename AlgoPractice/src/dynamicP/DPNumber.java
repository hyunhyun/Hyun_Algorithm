package dynamicP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DPNumber {

	static List<ArrayList<Integer>> memList;

	public static void main(String[] args) {
		int answer = solution(2, 11);
		System.out.println("answer : " + answer);

		for (int index = 0; index < memList.size(); index++) {
			ArrayList<Integer> l = memList.get(index);
			System.out.println("index :" + index);
			for (int i : l) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}

	public static void dynamic(int N, int n, int number) {
		ArrayList<Integer> list;
		 if (n == 1) {
				list = new ArrayList<Integer>();
			list.add(N);
			memList.add(list);
		} else {
			Set<Integer> set = new HashSet<Integer>();

			int calc = 0;
			for (int i = 0; i < n; i++) {
				calc += Math.pow(10, i) * N;
			}
			set.add(calc);
			for (int i = 1; i < n / 2 + 1; i++) {
				int j = n - i;

				ArrayList<Integer> l = memList.get(i - 1);
				ArrayList<Integer> r = memList.get(j - 1);

				for (int x = 0; x < l.size(); x++) {
					for (int y = 0; y < r.size(); y++) {
						int xnum = l.get(x);
						int ynum = r.get(y);
						calc = xnum + ynum;
						set.add(calc);
						calc = xnum - ynum;
						set.add(calc);
						calc = xnum * ynum;
						set.add(calc);
						if (ynum != 0) {
							calc = xnum / ynum;
							set.add(calc);
						}
						calc = ynum - xnum;
						set.add(calc);
						if (xnum != 0) {
							calc = ynum / xnum;
							set.add(calc);
						}
					}
				}
			}

			list = new ArrayList<Integer>(set);
			memList.add(list);
		}
		if (n < 8) {
			for (int i : list) {
				if (i == number) {
					return;	//현재 단계에서 값 나오면 멈춤
				}
			}
			dynamic(N, n + 1, number);
		}

	}

	public static int solution(int N, int number) {
		int answer = -1;

		memList = new ArrayList<ArrayList<Integer>>();

		dynamic(N,1, number);

		for (int index = 0; index < memList.size(); index++) {
			ArrayList<Integer> l = memList.get(index);
			for (int i : l) {
				if (i == number) {
					answer = index + 1;
					return answer;
				}
			}
		}
		return answer;
	}
}
