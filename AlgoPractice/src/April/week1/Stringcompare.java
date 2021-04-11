package April.week1;

import java.util.Comparator;

public class Stringcompare {
//https://programmers.co.kr/learn/courses/30/lessons/12917 
	//문자열 내림차순으로 배치하기
	public static void main(String[] args) {
		solution("Zbcdefg");
	}
	
	  public static String solution(String s) {
		  String answer = "";
	         char[] cArray = new char[s.length()];
	         
	         char bfCh, ch;
	        
	         for(int i=0; i<s.length(); i++) {
	        	 cArray[i] = s.charAt(i);
	         }
	         
	         char temp;
	        for(int i=0; i<s.length()-1; i++) {
	        	for(int j=i+1; j<s.length(); j++) {
	        		bfCh= cArray[i];
	        		ch = cArray[j];
	        		
	        		if(Character.isLowerCase(bfCh) && Character.isLowerCase(ch)&& bfCh < ch) {
	        			temp = bfCh;
		        		cArray[i] = ch;
		        		cArray[j] = temp;
	        		}else if(Character.isUpperCase(bfCh) && Character.isLowerCase(ch)) {
	        			temp = bfCh;
		        		cArray[i] = ch;
		        		cArray[j] = temp;
	        		}else if(Character.isUpperCase(bfCh) && Character.isUpperCase(ch) && bfCh < ch) {
	        			temp = bfCh;
		        		cArray[i] = ch;
		        		cArray[j] = temp;
	        		}
	        		
	        	}
	        	System.out.println(new String(cArray));
	        }
	        
	        
	        
	        answer = new String(cArray);
	        return answer;
	        
	    }
}
