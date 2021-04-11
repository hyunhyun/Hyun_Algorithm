package April.week1;


public class KeyPad {
	//https://programmers.co.kr/learn/courses/30/lessons/67256
	//[카카오 인턴] 키패드 누르기
	public static void main(String[] args) {
//		int s = Math.abs(-10);
//		System.out.println(s);
//		int[] numbers= {1,2,3,4,5,6,7,8,9,0};
//		solution(numbers, "left");
	}
	
	
	public static int calcDistance(int x, int y, int i, int j) {
		return Math.abs(x-i) + Math.abs(y-j);
	}
	public static String solution(int[] numbers, String hand) {
        String answer = "";
        char[] a = new char[numbers.length];
        
        int leftX = 3;
        int leftY = 0;
        int rightX = 3;
        int rightY = 2;
        
        int leftDist = 0;
        int rightDist = 0;
        
        int mapX, mapY;
        
        int num;
        for(int i=0; i<numbers.length; i++) {
        	num = numbers[i];
        	leftDist = 0;
        	rightDist = 0;
        	
        	//map 만들기
        	if(num == 0) {
        		mapX = 3;
        		mapY = 1;
        	}
        	else if(num % 3 == 0) {
        		mapX = num / 3 -1;
        		mapY = 2;
        	}else {
        		mapX = num / 3;
        		mapY = num % 3 -1;
        	}
        	
        	System.out.print("x : "+mapX);
        	System.out.println("y: "+mapY);
	        leftDist = calcDistance(mapX, mapY, leftX, leftY);
	        rightDist = calcDistance(mapX, mapY, rightX, rightY);
        
	        if(num== 1 || num ==4 || num ==7) {
	        	leftX = mapX;
        		leftY = mapY;
        		a[i] = 'L';
	        }else if(num==3 || num ==6 || num==9) {
	        	rightX = mapX;
        		rightY = mapY;
        		a[i] = 'R';
	        }
	        else if(leftDist == rightDist) {
	        	if("left".equals(hand)) {
	        		leftX = mapX;
	        		leftY = mapY;
	        		a[i] = 'L';
	        	}else {
	        		rightX = mapX;
	        		rightY = mapY;
	        		a[i] = 'R';
	        	}
	        }else if(leftDist < rightDist) {
	        	leftX = mapX;
	    		leftY = mapY;
	    		a[i] = 'L';
	        }else if(rightDist < leftDist ) {
	        	rightX = mapX;
	        	rightY = mapY;
	        	a[i] = 'R';
	        }
        }
        return new String(a);
//        return answer;
    }
}
