package etc;

public class ArrayRotation {
	//https://programmers.co.kr/learn/courses/30/lessons/77485
	//이중 배열 사용
	static int[][] map;
	public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
     
        answer = new int[queries.length];
        map = new int[rows][columns];
        
        for(int r=1; r<rows+1; r++) {
        	for(int c=1; c<columns+1; c++) {
        		map[r-1][c-1] = (r-1) * columns + c;
        	}
        }
        
        for(int i=0; i<queries.length; i++) {
        	//함수 호출
        	int min = rotation(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        	
        	//TODO
        	for(int x=0; x<rows; x++) {
        		for(int j=0; j<columns; j++) {
        			System.out.print(map[x][j]+" ");
        		}
        		System.out.println();
        	}
        	
        	answer[i] = min;
        }
        
        return answer;
    }
	
	public static int rotation(int x1, int y1, int x2, int y2) {
		int min = Integer.MAX_VALUE;
		int now;
		int temp1, temp2, temp3, temp4;  //모서리 순서대로 1234
		//1 - 2
		//|   |
		//3 - 4
		
		//맨위  --처리
		temp2 = map[x1-1][y2-1];

		for(int i= y2-1; i>= y1; i--) {
			now = map[x1-1][i-1];
			if(now <min) {
				min = now;
			}
			map[x1-1][i] = now;
		}
		
		//오른쪽 |처리
		temp4 = map[x2-1][y2-1];	
		
		for(int i=x2-1; i>x1; i--) {
			now = map[i-1][y2-1];
			if(now <min) {
				min = now;
			}
			map[i][y2-1] = now;
		}
		if(temp2 < min) {
			min = temp2;
		}
		map[x1][y2-1] = temp2;
		
		//아래 --처리
		temp3 = map[x2-1][y1-1];
		
		for(int i=y1+1; i<y2; i++) {
			now = map[x2-1][i-1];
			if(now <min) {
				min = now;
			}
			map[x2-1][i-2] = now;
		}
		if(temp4 < min) {
			min = temp4;
		}
		map[x2-1][y2-2] = temp4;
		
		//왼쪽 |
		for(int i=x1+1; i<x2; i++) {
			now = map[i-1][y1-1];
			if(now <min) {
				min = now;
			}
			map[i-2][y1-1] = now;
		}
		map[x2-2][y1-1] = temp3;	
		if(temp3 < min) {
			min = temp3;
		}
		return min;
	}
	
	public static void main(String[] args) {
		int rows = 6;
		int cols = 6;
		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
		
		solution(rows, cols, queries);
	}
}
