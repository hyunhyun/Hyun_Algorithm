package bfsDfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class StringConvert {
//https://programmers.co.kr/learn/courses/30/lessons/43163
	//BFS > 단어 변환
	public static void main(String[] args) {
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		String begin = "hit";
		String target = "cog";
		
		int answer = solution(begin, target, words);
		System.out.println(answer);
	}
	public static boolean checkDiff(String s1, String s2) {
		boolean check = false;
		int cnt = 0;
		
		for(int i=0; i<s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				cnt++;
			}
			if(cnt > 1) {
				return false;
			}
		}
		if(cnt == 1) {
			check = true;
		}	
		return check;
	}
	
	public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        int n = words.length;
        boolean[] visited = new boolean[n];
        Queue<String> queue = new LinkedList<String>();
        Queue<Integer> depths = new LinkedList<Integer>();
        
        //words array에 target 값 없을 시
       if(!Arrays.asList(words).contains(target)) {
    	   return 0;
       }
       
       queue.add(begin);     
       depths.add(answer);
       
       while(!queue.isEmpty()) {
    	   String check = queue.poll();
    	   int depth = depths.poll();

	    	depth++;
	    	
	    	for(int i=0; i<words.length; i++) {
	    		//한글자 차이나는지 검사
	    		if(!visited[i]) { //확인안한데고
	    			if(checkDiff(check, words[i])) {	//한글자만 차이나면
		    			if(words[i].equals(target)) {
		    				answer = depth;
		    				return answer;
		    			}else {
		    				queue.add(words[i]);
		    				depths.add(depth);
		    				visited[i] = true;
		    			}
	    			}
	    		}
	       }
       }
	return answer;
	}
}
