package ru.mail.polis.ads.part9.alyonazakharova;

import java.io.*;
import java.util.StringTokenizer;

//Форд-Беллман
//https://www.e-olymp.com/ru/problems/1453

public class Task3 {

    private static class Edge {
        private final int from;
        private final int to;
        private final int weight;

        private Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
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
            edges[i] = new Edge(from, to, weight);
        }

        int[] dist = new int[n];
        for (int i = 1; i < n; i++) {
            dist[i] = 30000;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[edges[j].from] < 30000) {
                    dist[edges[j].to] = Math.min(dist[edges[j].to], dist[edges[j].from] + edges[j].weight);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(dist[i] + " ");
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
