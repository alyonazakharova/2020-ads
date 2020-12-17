package ru.mail.polis.ads.part10.alyonazakharova;

import java.io.*;
import java.util.*;

//Минимальный каркас
//https://www.e-olymp.com/ru/problems/3835

public class Task2 {

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edges = new Edge[m];

        int from, to, weight;
        for (int i = 0; i < m; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            weight = in.nextInt();
            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges, Comparator.comparingInt(Edge::getWeight));

        int[] treeID = new int[n];
        for (int i = 0; i < n; i++) {
            treeID[i] = i;
        }

        int cost = 0;

        for (int i = 0; i < m; i++) {
            int a = edges[i].from;
            int b = edges[i].to;
            int l = edges[i].weight;
            if (treeID[a] != treeID[b]) {
                cost += l;
                int oldID = treeID[b];
                int newID = treeID[a];
                for (int j = 0; j < n; j++) {
                    if (treeID[j] == oldID) {
                        treeID[j] = newID;
                    }
                }
            }
        }

        out.print(cost);
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