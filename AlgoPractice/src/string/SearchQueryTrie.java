package string;

import java.util.*;

public class SearchQueryTrie {
//    https://programmers.co.kr/learn/courses/30/lessons/60060
//    trie 구조 사용
//        ["frodo", "front", "frost", "frozen", "frame", "kakao"]
//        ["fro??", "????o", "fr???", "fro???", "pro?"]
//        [3, 2, 4, 1, 0]
    //정확도 :18 , 속도 :15 만점 못받음

    //참고 답안 : https://blog.junghl.ee/ps/programmers/lyrics-search-60060/

    public static void main(String[] args) {
        String[] words ={"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries={"fro??", "????o", "fr???", "fro???", "pro?"};
//        String[] queries={"fro???"};
        int[] answers = solution(words,queries);
        for (int answer : answers) {
            System.out.println(answer);
        }
    }

    static class Trie{
        Node rootNode;
        class Node{
            char key;
            int layer;
            int cnt; //현 위치에서 자식으로 포함되는 단어 개수
            boolean isEnd;   //단어의 끝인지 여부
            Map<Character, Node> children;

            public Node() {
                children = new HashMap<Character, Node>();
            }
        }

        public Trie() {
            this.rootNode = new Node();
        }

        public void insert(String str){
            Node parent = rootNode;

            for(int i=0; i<str.length(); i++){
                char c = str.charAt(i);
                Node node = parent.children.get(c);
                parent.cnt++; //자식단어 개수 추가

                if(node == null) {   //현재 단어로 시작하는 자식없음 추가
                    node = new Node();
                    node.key = c;
                    node.layer = parent.layer+1;
                    if(i == str.length()-1){
                        node.isEnd = true;
                    }
                    parent.children.put(c, node);
                }
                parent = node;
            }
        }

        public int search(String query, Node parent){ //fro??? 접미사로 들어오는걸로 계산
            char c;
            Node node = null;
            int count =0;
//            System.out.println("query = " + query);
            for(int i=0; i<query.length(); i++) {
                c = query.charAt(i);

                node = parent.children.get(c);

                if (node != null) {
//                    System.out.println("not ? node.key = " + node.key);
                    parent = node;
                    continue;
                }
                if (c == '?') {
                    Map<Character, Node> map = parent.children;
                    Iterator<Character> itr = map.keySet().iterator();
                    while (itr.hasNext()) {
                        node = map.get(itr.next());
                        if(i<query.length()-1){
//                            System.out.println("yes ? node.key = " + node.key);
                            count += search(query.substring(i+1), node);
                        }
                    }
                    break;
                }
            }

            if(node != null && node.isEnd && query.length() ==1){
//                System.out.println("end key = " + node.key);
                count++;
            }

            return count;
        }

        public void printStack(){
            Stack<Node> stack = new Stack<Node>();
            stack.add(rootNode);

            while(!stack.isEmpty()){
                Node node = (Node) stack.pop();
                System.out.print("층수 : "+node.layer+" ");
                System.out.print("key : "+node.key+" ");
                System.out.print("cnt : "+node.cnt+" ");
                System.out.print("isEnd : "+node.isEnd+" ");

                Map<Character, Node> map = node.children;
                Iterator<Character> itr = map.keySet().iterator();
                while(itr.hasNext()) {
                    Character key = itr.next();
                    stack.add(map.get(key));
                }
            }
        }
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie trie = new Trie();
        Trie reversTrie = new Trie();

        //접두사 때문에 reverse
        for(int i=0; i<words.length; i++){
            String word = words[i];
            reversTrie.insert(new StringBuilder(word).reverse().toString());
        }

        //접미사 검색
        for (String word : words) {
            trie.insert(word);
        }
//        trie.printStack();


        int tempAns = 0;

        for (int i=0;i< queries.length; i++) {
            String query = queries[i];
            if(query.charAt(0) == '?'){
                String reverseQuery = new StringBuilder(query).reverse().toString();
                tempAns = reversTrie.search(reverseQuery, reversTrie.rootNode);
            }else{
                tempAns = trie.search(query, trie.rootNode);
            }
//            System.out.println("tempAns = " + tempAns);
            answer[i] = tempAns;
        }
        return answer;
    }

}
