package April.week1;

public class Dart {
    //https://programmers.co.kr/learn/courses/30/lessons/17682
	//[1차] 다트게임
	public static void main(String[] args) {
		String test[] = {"1S2D*3T", "1D2S#10S", "1D2S0T", "1S*2T*3S", "1D#2S*3S",
				"1T2D3D#", "1D2S3T*"};
		for(String t : test) {
		 int anss = solution(t);
		 System.out.println("answer" +anss);
		}
	}
	
    public static int solution(String dartResult) {
    	int[] answerArray = new int[3];
    	int[] indexs = new int[3];
    	int indexsI = 0;
        int answer = 0;
        int index = 0;
        
        for(int i=0; i< dartResult.length() && indexsI < 3; i++) {
        	char ch = dartResult.charAt(i);
        	if(ch == 'S' || ch == 'D' || ch == 'T') {
        		indexs[indexsI] = i;
        		indexsI++;
        	}
        }
        int bfTemp = 0;
        while(index< 3) {
	        	int temp = indexs[index];
	        	char ch = dartResult.charAt(temp);
	        	int n = 0;
	        	if(ch =='S') {
	        		n = 1;
	        	}else if(ch =='D') {
	        		n = 2;
	        	}else if(ch =='T') {
	        		n = 3;
	        	}

	        	int a = Integer.parseInt(dartResult.substring(bfTemp,temp));
	        	answerArray[index] = (int) Math.pow(a, n);
	        	
	        	
	        	char check = 'a';
	        	if(temp+1 < dartResult.length()) {
	        		check = dartResult.charAt(temp+1);
	        		}
	        	if(check =='*') {
	        		if(index == 0) {
	        			answerArray[0] *= 2;
	        		}else {
	        			answerArray[index-1] *= 2;
	        			answerArray[index] *= 2;
	        		}
	        		bfTemp = temp+2;
	        	}else if(check == '#') {
	        		answerArray[index] *= -1;
	        		bfTemp = temp+2;
	        	}else {
	        		bfTemp = temp+1;
	        	}
	        	
            index++;
	        }
	        
	        for(int i=0; i< 3; i++) {
	        	answer += answerArray[i];
	        }
	        return answer;
	    }
    }
