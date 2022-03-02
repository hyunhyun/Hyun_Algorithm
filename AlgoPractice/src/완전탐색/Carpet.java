package 완전탐색;

public class Carpet {

//  https://programmers.co.kr/learn/courses/30/lessons/42842?language=java
//    10	2	[4, 3]
//    8	1	[3, 3]
//    24	24	[8, 6]
// 가로>= 세로
// brown은 8 이상 5,000 이하
// yellow는 1 이상 2,000,000 이하

    public static void main(String[] args) {
        int[] answer = Solution.solution(24, 24);

        System.out.println("answer[0] = " + answer[0]);
        System.out.println("answer[1] = " + answer[1]);
    }
    
    static class Solution {
        public static int[] solution(int brown, int yellow) {
            int[] answer = new int[2];
//            brown = 2*garo + 2*(sero-2); = 2*(garo+sero)-4
//            yellow = (garo-2)*(sero-2) ;

            for(int s = 3 ; s < brown/2 + 2 ; s++){ //g :garo
                int g= (brown + 4)/2 - s; //s: 세로
                int yellowTemp = (g- 2)*(s-2);
                if(yellowTemp == yellow){
                    answer[0] = g;
                    answer[1] = s;
                    break;
                }
            }
            return answer;
        }
    }

}
