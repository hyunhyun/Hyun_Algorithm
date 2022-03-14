package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BoxingGame {
    //https://programmers.co.kr/learn/courses/30/lessons/49191
    // n = 5;
    // int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
//    https://ddb8036631.github.io/programmers/49191_순위/ - 참고
    public int solution(int n, int[][] results) {
       int answer =0;
       boolean[][] adj = new boolean[n][n];

        //인접행렬 초기화
       for(int[] res : results){
           int win = res[0]-1;
           int lose = res[1]-1;

           adj[win][lose] = true;   //win-> lose 접근 가능
       }


       for(int k=0; k<n; k++){  //중간지점
           for (int i=0; i<n; i++){
               for (int j=0;j<n; j++){
                   if(adj[i][k] && adj[k][j])   //흐름이 승자->패자
                       adj[i][j] = true;    //중간 어떤 지점 거쳐서 접근 가능하면 i-> k도 접근 가능 i 승자 k 패자
               }
           }
       }

       for(int i=0; i<n; i++){
           int count = 0;
           for (int j=0; j<n; j++){
               if(adj[i][j]){
                   count++; //i가 이길수 있는 선수 수
               }
           }
           for (int j=0; j<n; j++){
               if(adj[j][i]){
                   count++; //i가 지는 선수 수
               }
           }
           if(count == n-1){
               answer++;
           }
       }
       return answer;
    }
}
