package ksm.template;
import java.util.*;
import java.io.*;


public class Main {
    public static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        Node() {
        }

        public Node(int value) {
            super();
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Integer> q = new ArrayDeque<>();
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            q.offerLast(Integer.parseInt(line));
        }

        Node start = new Node(q.pollFirst());
        while (!q.isEmpty()) {
            int tmp = q.pollFirst();
            Node now = start;
            while (true) {
                if (tmp < now.value) {
                    if (now.left == null) {
                        now.left = new Node(tmp);
                        now.left.parent = now;
                        break;
                    } else {
                        now = now.left;
                    }
                } else {
                    if (now.right == null) {
                        now.right = new Node(tmp);
                        now.right.parent = now;
                        break;
                    } else {
                        now = now.right;
                    }
                }
            }
        }
        printNode(start);
    }

    public static void printNode(Node start) {
        if (start.left != null) {
            printNode(start.left);
        }
        if (start.right != null) {
            printNode(start.right);
        }
        System.out.println(start.value);
    }
}