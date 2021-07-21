package etc;

import java.util.Stack;

public class PairRemove {
	//코딩테스트 연습 - 짝지어 제거하기 | 프로그래머스
	// https://programmers.co.kr/learn/courses/30/lessons/12973
	 public static int solution(String s)
	    {
	        int answer = -1;

	        Stack<Character> stack = new Stack<Character>();
	        
	        int idx = 0;
	        	        
	        while( idx < s.length()) {	 	        	
	        	if(stack.isEmpty()) {
	        		stack.add(s.charAt(idx));
	    			idx++; //다음 확인할 순서
	        	} else if(stack.peek() == s.charAt(idx)) {
	    			stack.pop();
	    			idx++;
	    		} else {
	    			stack.add(s.charAt(idx));
	    			idx++; //다음 확인할 순서
	    		}
	        }
	        
	        if(stack.isEmpty()) {
	        	answer = 1;
	        }else {
	        	answer = 0;
	        }
	        
	        return answer;
	    }
	 public static void main(String[] args) {
		 String s1 = "baabaa";  //	1
		 String s2 = "cdcd"; //	0
		 
		 int answer = solution(s2);
		 System.out.println(answer);	 
	}
}
