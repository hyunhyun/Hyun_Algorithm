package etc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GidungBo {
    //https://programmers.co.kr/learn/courses/30/lessons/60061
    //기둥은 해당 좌표로부터 위
    //보는 해당 좌표로 부터 오른쪽
    // [x, y, a, b] - (x,y)좌표, a(0:기둥, 1:보), b(0:삭제, 1:설치)
    boolean[][] gidung;
    boolean[][] bo;

    @Test
    public void test(){
        int n1 = 5;
        int[][] build_frame1 = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        int[][] answer1 = {{1,0,0},{1,1,1},{2,1,0},{2,2,1},{3,2,1},{4,2,1},{5,0,0},{5,1,0}};
        int[][] actual1 = solution(n1,build_frame1);

        int n2 = 5;
        int[][] build_frame2 = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
        int[][] answer2 = {{0,0,0},{0,1,1},{1,1,1},{2,1,1},{3,1,1},{4,0,0}};
        int[][] actual2 = solution(n2,build_frame2);


        for(int i=0; i< answer1.length; i++){
            for(int j=0; j<3; j++){
                Assertions.assertEquals(answer1[i][j], actual1[i][j]);
            }
        }

        for(int i=0; i< answer2.length; i++){
            for(int j=0; j<3; j++){
                Assertions.assertEquals(answer2[i][j], actual2[i][j]);
            }
        }
    }


    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        gidung = new boolean[n+1][n+1];   //0 ~ n
        bo = new boolean[n+1][n+1];
        int count = 0;  //건축물 수

        for(int[] build : build_frame){
            int x = build[0];
            int y = build[1];
            int st = build[2];  // structure 0 기둥 1 보
            int action = build[3];  //0 삭제 1 설치

            if(action == 0){    //삭제
                if(st == 0){    //기둥
                    if(!checkGidungDelete(x,y,n)){  //일단 지우고 체크함 - false 지우면 안됨 다시 추가
                        gidung[x][y]= true;
                    }else { //삭제
                        count--;
                    }
                }else{
                    if(!checkBoDelete(x,y,n)){
                        bo[x][y] = true;
                    }else {
                        count--;    //삭제
                    }
                }
            }else{      // 설치
                if(st == 0){    //기둥
                    if(checkGidungAdd(x,y,n)){  //true면 add
                        gidung[x][y] = true;
                        count++;
                    }
                }else{
                    if(checkBoAdd(x,y,n)){
                        bo[x][y] = true;
                        count++;
                    }
                }
            }
        }

        answer = new int[count][3];
        //x좌표 오름차순, y좌표 오름차순
        //같을시 보(1)보다 기둥(0) 앞으로   [x,y, a] 123 순서대로 오름차순

        int temp = 0;
        for(int i=0; i<n+1; i++){
            for(int j=0; j<n+1; j++){
                if(gidung[i][j]){
                    answer[temp][0] = i;
                    answer[temp][1] = j;
                    answer[temp][2] = 0;
                    temp++;
                }
                if(bo[i][j]){
                    answer[temp][0] = i;
                    answer[temp][1] = j;
                    answer[temp][2] = 1;
                    temp++;
                }
            }
        }

        //print
        for(int[] a : answer){
            System.out.print(a[0]+" ");
            System.out.print(a[1]+" ");
            System.out.println(a[2]+" ");
        }
        return answer;
    }

    public boolean checkGidungAdd(int x, int y, int n){
        if(y > n-1){    //y <= n-1
            return false;
        }
        if(y == 0){ //바닥에 설치
            return true;
        }
        if (gidung[x][y-1]){    //다른 기둥 위에 기둥 설치
            return true;
        }
        if(bo[x][y]){ //  보 왼쪽에
            return true;
        }if(x-1 >= 0 && bo[x-1][y]){    // 보 오른쪽에
           return true;
        }
        return false;
    }

    public boolean checkBoAdd(int x, int y, int n){
        if(x > n-1 ){  //x <= n-1
            return false;
        }
        if(y-1>=0 && gidung[x][y-1]){   //보 왼쪽에 기둥 보[n-1][n]까지
            return true;
        }
        if(x+1<=n && y-1>=0 && gidung[x+1][y-1]){   //보 오른쪽에 기둥
            return true;
        }
        if(x-1 >= 0 && x+1<= n && bo[x-1][y] && bo[x+1][y]){   //보 사이에  0~n까지 가능
            return true;
        }
        return false;
    }

    public boolean checkBoDelete(int x,int y, int n){
        boolean check = true;
        bo[x][y] = false;   //일단 삭제 하고 테스트
        if(x-1 >= 0 && bo[x-1][y]){   // 해당 보가 빠졋을때 왼쪽 보가 있으면
            check = checkBoAdd(x-1,y,n);
            if(!check){
                return check;
            }
        }
        if(x+1 <= n && bo[x+1][y]){ //오른쪽 보 존재
            check = checkBoAdd(x+1,y,n);
            if(!check){
                return check;
            }
        }
        if(gidung[x][y]){ //왼쪽끝 기둥
            check = checkGidungAdd(x,y,n);
            if(!check){
                return check;
            }
        }
        if(x+1 <= n && gidung[x+1][y]){ //오른쪽 끝 기둥
            check = checkGidungAdd(x+1,y,n);
            if(!check){
                return check;
            }
        }

        return check;
    }

    public boolean checkGidungDelete(int x,int y, int n){
        boolean check = true;
        gidung[x][y] = false;   //일단 삭제 하고 테스트
        if(x-1 >= 0 && y+1 <= n && bo[x-1][y+1]){   // 해당 기둥 위에 왼쪽방향으로 얹어진 보
            check = checkBoAdd(x-1,y+1,n);
            if(!check){
                return check;
            }
        }
        if( y+1 <= n && bo[x][y+1]){   // 해당 기둥 위에 오른쪽방향으로 얹어진 보
            check = checkBoAdd(x,y+1,n);
            if(!check){
                return check;
            }
        }
        if (y+1 <= n && gidung[x][y+1]) {
            check = checkGidungAdd(x,y+1,n);
            if(!check){
                return check;
            }
        }
        return check;
    }
}
