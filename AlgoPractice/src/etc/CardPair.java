package etc;

import java.util.ArrayList;
import java.util.HashSet;

public class CardPair {
	//BruteForce
	//순열
	static int n; //총 짝 갯수
    static ArrayList<Integer> list;
    static boolean[] check;
    
    public static void main(String[] args) {
    	int[][] board= {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
    	int r = 1;
    	int c = 0;
    	
//    	int[][] board = {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}};
//    	int r =0; 
//    	int c = 1;
    	
    	int ans = solution(board, r, c);
    	System.out.println(ans);
	}
    
	//https://programmers.co.kr/learn/courses/30/lessons/72415
	public static int solution(int[][] board, int r, int c) {
        int answer = 0;
        
        check = new boolean[8];
        
        //brute force??
        //순열 구하고 그 순열 짝 대로 다 확인해보기
        
        HashSet<Integer> set = new HashSet<Integer>();
        //현재 존재하는 카드 짝 다 구하기
        for(int i=0; i<4; i++) {
        	for(int j=0; j<4; j++) {
        		if(board[i][j] != 0) {
        			set.add(board[i][j]);
        		}
        	}
        }
        
        list = new ArrayList<Integer>(set); //1 2 3
        
        n = list.size(); //쌍의 수
        ArrayList<int[]> arrayList = new ArrayList<int[]>();	//순열 리스트 기억할 list
        int[] ans = new int[n];
        permutation(0,arrayList, ans);
        //TODO
        System.out.println("n : "+n);
        for(int i=0; i<list.size(); i++) {
        	System.out.println(list.get(i));
        }
        
        
        //TODO
        for(int i=0; i<arrayList.size(); i++) {
        	int[] perm = arrayList.get(i);
        	for(int j=0; j<perm.length; j++) {
        		System.out.print(perm[j]);
        	}
        	System.out.println();
        }
        	
        int min = Integer.MAX_VALUE;
        //직접 각 순열 순대로 짝 찾기
        for(int i=0; i<arrayList.size(); i++) {
        	int tempAns = 0;
        	int[] perm = arrayList.get(i);
        	
        	int[][] tempBoard = copyArray(board);
        	
        	//한 순열
        	for(int j=0; j<perm.length; j++) {
	        	if(tempBoard[r][c] != perm[j]) { //현재 있는 위치가 찾아야 하는 카드 가 아닐때
	        		//TODO 
	        		System.out.println(perm[j]);
	        		int xCurrent = r;
	        		int yCurrent = c;
	        		
	        		Pair pair = findLocation(perm[j], tempBoard);
	        		
	        		tempAns += calcDiff(xCurrent,yCurrent,pair.x, pair.y);
	        		
	        		xCurrent = pair.x;
	        		yCurrent = pair.y;
	        		
	        		//TODO
//	        		System.out.print("x : "+xCurrent);
//	        		System.out.println("/ y : "+yCurrent);
	        		
	        		tempBoard[xCurrent][yCurrent] = 0;	//한번 간곳 0 처리
	        		
	        		pair = findLocation(perm[j], tempBoard);	//쌍 중 2번째꺼 찾기
	        		
	        		tempAns += calcDiff(xCurrent,yCurrent,pair.x, pair.y);
	        		
	        		xCurrent = pair.x;
	        		yCurrent = pair.y;
	        		
	        		//TODO
//	        		System.out.print("x : "+xCurrent);
//	        		System.out.println("/ y : "+yCurrent);
	        		
	        		tempBoard[xCurrent][yCurrent] = 0;	//한번 간곳 0 처리
	        	}
        	}
        	
        	if(min > tempAns) {
        		min = tempAns;
        	}
        }
        
        answer = min;
     
        return answer;
    }
	public static void permutation(int depth, ArrayList<int[]> arrayList, int[] ans) {		
		if(n == depth) {
			arrayList.add(ans);
//			print(ans);
			return;
		}
		//
		for(int i=0; i<n; i++) {
			if(!check[i]) {
				ans[depth] = list.get(i);
				check[i] = true;
				permutation(depth+1, arrayList, ans);
				check[i] = false; //현재 꺼 선택 안하는 다른 순열 구하기 위해서
				
				int[] temp = new int[n];
				for(int idx = 0; idx<= depth; idx++) {
					temp[idx] = ans[idx];
				}
				ans = temp;
			}
		}
	}
//	private static void print(int[] answer) {
//        for (int i :answer) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//    }
	
	public static int[][] copyArray(int[][] board){
		int[][] tempBoard = new int[4][4];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				tempBoard[i][j] = board[i][j];
			}
		}
		
		return tempBoard;
	}
	
	public static class Pair{
		int x,y;
	}
	
	public static Pair findLocation(int find, int[][] tempBoard) {
		Pair pair = new Pair();
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(tempBoard[i][j] == find) {
					pair.x = i;
					pair.y = j; 
					return pair;		//먼저 찾는거 하나 보냄 두개일 때 하나 찾은건 0 될거라? 근데 1 2 가는 순이랑 2 1 가는 거랑 값이 다를 수 도 있으럭 같은데
				}
			}
		}
		
		return pair;
	}
	
	public static int calcDiff(int r, int c, int x, int y) {
		int ans = 1; //enter 쳐서 뒤집는 거 1 로시작
			if(r == x) {
				if(c == y) {	//위치 완전 같은 경우
					//ans += 0;
				}else if(y==0 || y==3) {
					ans += 1;
				}
			}else if(c == y) {
				if(x==0 || x==3) {
					ans += 1;
				}
			}else {
				if(x==0 || x==3) {
					ans++;
					if(y==0 || y==3) {
						ans++; //2
					}else {
						ans += Math.abs(c-y);
					}
				}else if(y==0 || y==3) {
					ans += 1;
					ans += Math.abs(r-x);
				}else {
					ans += Math.abs(r-x)+Math.abs(c-y);
				}
			}
			
		return ans;
	}
}
