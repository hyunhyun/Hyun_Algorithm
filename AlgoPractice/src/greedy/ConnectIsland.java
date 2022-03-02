package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class ConnectIsland {
    //https://programmers.co.kr/learn/courses/30/lessons/42861
//    n	costs	return
//    4	[[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]	4
    // Prim's 알고리즘 또는 크루스칼 알고리즘
    public static int[] parent;
    public static void main(String[] args) {
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        int i = solution(4, costs );
        System.out.println("i = "+i);
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];    //i자리의 정점의 부모 저장 union find할 시 부모로 같은 union 여부 확인

        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] == o2[2]){
                    return o1[1] - o2[1];   //o1[1]이나 o1[0]이나 상관 없을듯
                } else{
                    return o1[2] - o2[2];
                }
            }
        } );

        //parent 초기화
        for (int i=0; i<n; i++ ){
            parent[i] = i;
        }
//        for (int[] cost : costs) {
//            for (int i : cost) {
//                System.out.print(i+" ");
//            }
//            System.out.println();
//        }

        for (int[] cost : costs) {
            if(addUnion(cost[0], cost[1])){
                answer += cost[2];
//                printParent();
//                System.out.println("cost[2] = " + cost[2]);
            }
        }

        return answer;
    }

    public static boolean addUnion(int i, int j){
        //cycle 발생하게 되면 pass, 아니면 union 추가(parent 재설정으로 union추가한다는뜻)
        if (parent[i] == parent[j]) {   //이미 같은 union -> 추가시 cycle 발생 pass
            return false;
        }else{
             // union  합치고 부모값 재세팅
           if(i <  j){
               setParent(j, parent[i]);
           }else{
               setParent(i, parent[j]);
           }
           return true;
        } //4 3   3 3
        // 3  1    1 1
    }

    private static void setParent(int idx, int afVal) {
        //idx 정점과 같은 union 정점들 부모값 다시 세팅
        int bfVal = parent[idx]; // 값 바꾸기 전 부모값
        for (int i=0; i<parent.length; i++) {
            if(parent[i] == bfVal) {    //현재 부모객체 다 바꿔야할 같은 union에 있는 것들
                parent[i] = afVal;
            }
        }
    }

    //테스트용
//    private static void printParent() {
//        System.out.println("========parent========");
//        for (int i : parent) {
//            System.out.println(i+" ");
//        }
//        System.out.println("====================");
//    }
}
