package bfsDfs;

import java.util.LinkedList;
import java.util.Queue;

public class Network {
//https://programmers.co.kr/learn/courses/30/lessons/43162
	
	public static void main(String[] args) {
		int n = 3;
		int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
		int[][] computers3 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		int[][] computers4 = {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
//		int answer = solution(n, computers1);
		int answer = solution(4, computers4);
		System.out.println("ans :"+answer);
		
	}
	
	public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<Integer>();	//BFS
        
        int now;
        for(int i=0; i<n; i++) {	
			if( !visited[i]) { //자기 자신 넣음
				visited[i]= true;
				queue.add(i);
				answer++;
			}
	
			while(!queue.isEmpty()) {
				now = queue.poll();
				System.out.println(now);
//				for(int j=now+1; j<n; j++) {  /// 0 -3-1 이런식이면 체크가 안되서
				for(int j=0; j<n; j++) {	
					if(!visited[j] && computers[now][j] == 1) {
						queue.add(j);
						visited[j] = true;
					}
				}
			}	
        }
          
        return answer;
    }
}
