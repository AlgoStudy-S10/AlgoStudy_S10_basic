package week1;

import java.io.*;
import java.util.*;

public class BOJ_5639_trial2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node{ // 트리 노드 정의
        int value;
        Node left, right;

        Node(int value){
            this.value = value;
        }

        void insert(int num){
            if(num< this.value){
                if(this.left == null){
                    this.left = new Node(num);
                }else{
                    this.left.insert(num);
                }
            }else{
                if(this.right == null){
                    this.right = new Node(num);
                }else{
                    this.right.insert(num);
                }
            }
        }
    }
    static Node root;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null && !line.isEmpty()){ // 입력으로 각 줄에 나오는 숫자 처리
            int num = Integer.parseInt(line.trim());
            if(root == null){ // root 기입
                root = new Node(num); // 가상의 최대값을 넣어서 right 체크에서 문제가 없도록 함
                continue;
            }
            
            root.insert(num);
        }

        dfs(root);
        System.out.println(sb);
    }

    static public void dfs(Node now){
        if(now == null) return;
        dfs(now.left);
        dfs(now.right);
        sb.append(now.value).append("\n");
    }
}
