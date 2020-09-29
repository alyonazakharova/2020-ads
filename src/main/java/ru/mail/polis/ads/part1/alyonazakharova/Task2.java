package ru.mail.polis.ads.part1.alyonazakharova;

import java.io.*;
import java.util.*;

public class Task2 {

    public static class Node {
        private final char value;
        private final Node right;
        private final Node left;

        public Node(char value, Node right, Node left) {
            this.value = value;
            this.right = right;
            this.left = left;
        }
    }

    public static class ParseTree {

        private final Node root;
        private final PrintWriter out;

        public ParseTree(String expression, PrintWriter out) {
            Deque<Node> deque = new ArrayDeque<>();
            Node root = null;
            for (int i = 0; i < expression.length(); i++) {
                char current = expression.charAt(i);
                if (Character.isLowerCase(current)) {
                    deque.push(new Node(current, null, null));
                } else {
                    root = new Node(current, deque.pop(), deque.pop());
                    deque.push(root);
                }
            }
            this.root = root;
            this.out = out;
        }

        public void traverse(Node node) {
            int height = getHeight(node);
            for (int i = height; i >=1; i--) {
                printLevel(node, i);
            }
            out.write("\n");
        }

        private int getHeight(Node node) {
            if (node == null) {
                return 0;
            } else {
                int leftHeight = getHeight(node.left);
                int rightHeight = getHeight(node.right);

                if (leftHeight > rightHeight) {
                    return (leftHeight + 1);
                } else {
                    return (rightHeight + 1);
                }
            }
        }

        private void printLevel(Node node, int level) {
            if (node != null) {
                if (level == 1) {
                    out.write(node.value);
                } else if (level > 1) {
                    printLevel(node.right, level - 1);
                    printLevel(node.left, level - 1);
                }
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        ParseTree tree;
        for (int i = 0; i < n; i++) {
            tree = new ParseTree(in.next(), out);
            tree.traverse(tree.root);
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
