package 완전탐색;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCompress {
    //https://programmers.co.kr/learn/courses/30/lessons/60057
    @Test
    public void run(){
        String[] s = {
                        "aabbaccc"
                        ,"ababcdcdababcdcd"
                        ,"abcabcdede"
                        ,"abcabcabcabcdededededede"
                        ,"xababcdcdababcdcd"
        };

        int[] answer = {
                7
                , 9
                , 8
                , 14
                , 17
        };

        for (int i=0; i<s.length; i++) {
            int ans = solution(s[i]);
            Assertions.assertEquals(answer[i], ans); //expected, actual
        }
    }

    public int solution(String s) {
        int answer = 0;

        answer = s.length(); //압축안된 길이 값으로 세팅

        if(s.length() == 1){
            return answer;
        }

        for(int gap = 1; gap < s.length()/2 + 1; gap++){    //gap 자르는 단위 값
            int startIdx = 0;
            String tempString = ""; //자른 문자열이 동일한지 비교하는 변수
            int tempCount = 0;  //자른 문자열 반복되는 횟수
            StringBuffer compress = new StringBuffer();

            while(startIdx < s.length()){
                String temp ="";    //현재 gap 만큼 자른 변수
                if(startIdx + gap <= s.length()) {
                    temp = s.substring(startIdx, startIdx + gap);
                }else{
                    temp = s.substring(startIdx);
                }

                if(!tempString.equals(temp)){
                    if(tempCount > 1){
                        compress.append(String.valueOf(tempCount));
                    }
                    compress.append(tempString);
                    tempString = temp;
                    tempCount = 1;

                }else if(tempString.equals(temp)){
                    tempCount++;
                }
                startIdx += gap;

                if(startIdx>=s.length()){
                    if(tempCount > 1){
                        compress.append(tempCount)
                                .append(tempString);
                    }else{
                        compress.append(tempString);
                    }
                }
            }

            if(compress.toString().length() <answer){
                answer = compress.toString().length();
            }
        }
        return answer;
    }

}
