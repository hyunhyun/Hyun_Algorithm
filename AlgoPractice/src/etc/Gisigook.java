package etc;

public class Gisigook {
	//https://programmers.co.kr/learn/courses/30/lessons/12979
	
    public int solution(int n, int[] stations, int w) {
    	int answer = 0;
    	int startIndex = 1;
    	int num = 2*w +1;
    	
    	//
    	for(int i=0; i<stations.length; i++) {
    		int x  = stations[i];
			   int index = x - w; //1부터 시작해서
			   if(index > startIndex) {
				   int length = index - startIndex;
				   
				   int cnt = length / num;
				   if (length % num != 0) {	//Math.ceil 사용 보다 해당내용으로 하니까 효율성 토ㅇ과함
					  cnt += 1;
				   }
				   answer += cnt;
				}	   	   
			   startIndex = x + w;			   
		   }
		   if(startIndex < n) {		//마지막 기지국까지 했는네 안 닿는 곳 남았을때
			   int length = n - startIndex;   
			   int cnt = length / num;
			   if (length % num != 0) {
				  cnt += 1;
			   }
			   answer += cnt;
		   }

        return answer;
    }
 }
