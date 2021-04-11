package April.week1;

public class SecretMap {
//https://programmers.co.kr/learn/courses/30/lessons/17681
//[1차] 비밀지도
	
	public static void main(String[] args) {
//		int a = 9 | 30;
//		System.out.println(a);
		
//		int n	= 5;
//		int[] arr1 =	{9, 20, 28, 18, 11};
//		int[] arr2 =	{30, 1, 21, 17, 28};
		
		int n	= 6;
		int[] arr1 =	{46, 33, 33, 22, 31, 50};
		int[] arr2 =	{27, 56, 19, 14, 14, 10};
		
//		String s = "11010110100";
//		String ans = s.replaceAll("1", "#");
//		ans = ans.replaceAll("0", " ");
//		
//		System.out.println(ans);
		String[] s = solution(n, arr1, arr2);
		
		for(String ss : s) {
			System.out.println(ss);
		}
		
		
	}
	
	public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[] tempArr = new int[n];
        
        for(int i=0; i<n ; i++) {
        	tempArr[i] = arr1[i] | arr2[i];
        }
        
        
        for(int i=0; i<n; i++) {
        	String bin = Integer.toBinaryString(tempArr[i]);
        	
        	answer[i] = bin;
        }
        
        for(int i=0; i<n; i++) {
        	answer[i] = answer[i].replaceAll("1", "#");
        	answer[i] = answer[i].replaceAll("0", " ");
        	
        	if(answer[i].length() < n) {
        		String temp = answer[i];
        		answer[i] = "";
        		for(int j=0; j< n - temp.length(); j++) {
        			answer[i] += " ";
        		}
        		answer[i] = answer[i] +temp;
        		
        	}
        		
        }
        
        return answer;
    }
}
