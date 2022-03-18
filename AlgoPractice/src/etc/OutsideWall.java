package etc;

import org.junit.jupiter.api.Test;

public class OutsideWall {
    //https://programmers.co.kr/learn/courses/30/lessons/60062

    int[][] weakArray;
    public int solution(int n, int[] weak, int[] dist) {
        //https://programmers.co.kr/learn/courses/30/lessons/60062
        int answer = -1;
        makeWeak(n, weak);

        for (int[] array : weakArray) {
            //각 어떤 친구부터 내보낼건지
            int ans = permutation(new boolean[dist.length],0, new int[dist.length], array, dist, -1);  //그 친구들을 어떤 위치부터 시작시킬지

            if(answer == -1 ||(answer > ans && ans != -1)){
                answer = ans;
            }
        }
        return answer;
    }

    public int permutation(boolean[] visit, int depth, int[] permCase, int[] weak, int[] dist, int cnt){  //순열
        if(depth == dist.length){
            int ans =  check(weak, permCase);

            if(cnt == -1 ||(cnt > ans && ans != -1)){
                cnt = ans;
            }
            return cnt;
        }

        for(int i=0; i<dist.length; i++){
            if(!visit[i]){
                permCase[depth] = dist[i];
                visit[i] = true;
                cnt = permutation(visit, depth+1, permCase, weak, dist, cnt);

                visit[i] = false;
            }
        }
        return cnt;
    }

    public int check(int[] weak, int[] permCase){    //사람 순열
        int count = 0;
        int here = -1;

        for(int j=0; j<weak.length; j++){
            if(here >= weak[j]){
                continue;
            }else{
                if(count == permCase.length){    //모든 친구 다 투입한 경우
                    count = -1;
                    break;
                }
                here = weak[j] + permCase[count];       //출발 위치에서 + 갈수 있는 거리
                count++;    //순열 순서++               //친구 더 투입
            }
        }
        return count;
    }

    public void makeWeak(int n, int[] weak){
//        1234
//        2341
//        3412
//        4123
        weakArray = new int[weak.length][weak.length];

        int idx = 0;
        int len = weak.length;
        for(int i=0; i<len; i++){   //1234 2341 3412 4123
            idx = 0;
            boolean rot = false;
            for(int j = i; idx< len ;j++){
                if(j >= len){
                    j = 0;
                    rot = true;
                }
                if(rot){
                    weakArray[i][idx] = weak[j]+n;
                }else
                    weakArray[i][idx] = weak[j];
                idx++;
            }
        }
    }
    

    @Test
    public void test(){
//    n	    weak	            dist	result
//    12	[1, 5, 6, 10]	[1, 2, 3, 4]	2
//    12	[1, 3, 4, 9, 10]	[3, 5, 7]	1

        int n1 =12;
        int[] weak1= {1, 5, 6, 10};
        int[] dist1 = {1,2,3,4};
        int ans1 = solution(n1, weak1, dist1);  //정답 2
        System.out.println("ans1 = " + ans1);

        int n2 =12;
        int[] weak2= {1, 3, 4, 9, 10};
        int[] dist2 = {3, 5, 7};

        int ans2 =solution(n2, weak2, dist2);   //정답 1
        System.out.println("ans2 = " + ans2);

        //6 10 12 14 테스트케이스/
        int n3 = 200;
        int[] weak3 = {0, 10, 50, 80, 120, 160};
        int[] dist3 = {1, 10, 5, 40, 30};
        int ans3 = solution(n3, weak3, dist3);  //정답 3
        System.out.println("ans3 = " + ans3);

        int n4 = 50;
        int[] weak4= {1};
        int[] dist4 ={6};
        int ans4 = solution(n4, weak4, dist4);  //정답은 1
        System.out.println("ans4 = " + ans4);

    }
}
