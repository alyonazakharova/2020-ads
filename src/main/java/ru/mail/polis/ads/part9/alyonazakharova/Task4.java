package ru.mail.polis.ads.part9.alyonazakharova;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

//Кратчайший путь
//https://www.e-olymp.com/ru/problems/4856

public class Task4 {

    private static class Edge {
        private int vertexTo;
        private int value;

        public Edge(int vertexTo, int value) {
            this.vertexTo = vertexTo;
            this.value = value;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;

        int inf = Integer.MAX_VALUE;

        LinkedList<Edge>[] graph = new LinkedList[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<Edge>();
            dist[i] = inf;
        }

        dist[s] = 0;
        int[] p = new int[n];

        int b, e, weight;
        for (int i = 0; i < m; i++) {
            b = in.nextInt() - 1;
            e = in.nextInt() - 1;
            weight = in.nextInt();
            graph[b].add(new Edge(e, weight));
            graph[e].add(new Edge(b, weight));
        }

        boolean[] u = new boolean[n];

        for (int i = 0; i < n; i++) {
            int v = -1;
            for (int j = 0; j < n; j++) {
                if (!u[j] && (v == -1 || dist[j] < dist[v])) {
                    v = j;
                }
            }
            if (dist[v] == inf) {
                break;
            }
            u[v] = true;

            for (int j = 0; j < graph[v].size(); j++) {
                int to = graph[v].get(j).vertexTo;
                int len = graph[v].get(j).value;
                if (dist[v] + len < dist[to]) {
                    dist[to] = dist[v] + len;
                    p[to] = v;
                }
            }
        }

        if (dist[f] == inf) {
            System.out.println(-1);
            return;
        }

        System.out.println(dist[f]);

        Deque<Integer> path = new ArrayDeque<>();
        for (int v = f; v != s; v = p[v]) {
            path.push(v);
        }
        path.push(s);

        while (!path.isEmpty()) {
            System.out.print((path.pop() + 1) + " ");
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
