package dynamicP;

import java.util.HashMap;

public class DPTriangle {
	//https://programmers.co.kr/learn/courses/30/lessons/43105
	static HashMap<Key, Integer> map;
	
	public static void main(String[] args) {
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		int answer = solution(triangle);
		System.out.println(answer);
	}
	public static class Key {
		int level;
		int index;
		
		public Key(int level,  int index) {
			this.level = level;
			this.index = index;
		}


		@Override
		public int hashCode() {
			int result = (level<<16)+ index;
			return result;
		}
		
		@Override
		  public boolean equals (final Object O) {
		    if (!(O instanceof Key)) return false;
		    if (((Key) O).level != level) return false;
		    if (((Key) O).index != index) return false;
		    return true;
		  }


		@Override
		public String toString() {
			return "Key [level=" + level + ", index=" + index + "]";
		}

		
	}
	
	public static void DP(int level, int x, int y, int[][] triangle) {
		if(level == 1) {
			map.put(new Key(level, y), triangle[x][y]);
			return;
		}else {
			Key leftKey = new Key(level-1, y);
			Key rightKey = new Key(level-1, y+1);
			
			if(!map.containsKey(leftKey)) {
				DP(level-1, x+1, y, triangle);
			}
			if(!map.containsKey(rightKey)) {
				DP(level-1, x+1, y+1, triangle);
			}

			int left = map.get(leftKey);
			int right = map.get(rightKey);
			
			int result = triangle[x][y] + Math.max(left, right);
			
			map.put(new Key(level, y), result);
		}
	}
	
	public static int solution(int[][] triangle) {
        int answer = 0;
        map = new HashMap<Key, Integer>();
        
        int n = triangle.length;
        
       DP(n, 0, 0, triangle);
       
       answer = map.get(new Key(n, 0));
        
        return answer;
    }
}
