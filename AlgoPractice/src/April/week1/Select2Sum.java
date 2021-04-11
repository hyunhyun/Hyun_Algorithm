package April.week1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Select2Sum {
	//https://programmers.co.kr/learn/courses/30/lessons/68644
	//두개 뽑아서 더하기
	public int[] solution(int[] numbers) {
        int[] answer = {};
        HashSet<Integer> hs = new HashSet<Integer>();
        
        for(int i=0;i<numbers.length-1; i++) {
        	for(int j=i+1; j<numbers.length; j++) {
        		hs.add(numbers[i]+numbers[j]);
        	}
        }
        
        ArrayList<Integer> ls = new ArrayList<Integer>(hs);
        
        Collections.sort(ls);
        
        answer = new int[ls.size()];
        
        for(int i=0 ; i<ls.size(); i++) {
        	answer[i] = ls.get(i);
        }
        
        return answer;
    }
}
