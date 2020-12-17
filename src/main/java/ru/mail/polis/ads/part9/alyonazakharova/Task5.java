package ru.mail.polis.ads.part9.alyonazakharova;

import java.io.*;
import java.util.*;

//Кратчайший путь 2
//https://www.e-olymp.com/ru/problems/4853

public class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt() - 1;
        int b = in.nextInt() - 1;

        boolean[] visited = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();
        visited[a] = true;
        deque.push(a);

        LinkedList<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<Integer>();
        }

        int from, to;
        for (int i = 0; i < m; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            graph[from].add(to);
            graph[to].add(from);
        }

        int[] d = new int[n];
        int[] p = new int[n];
        p[a] = -1;
        while (!deque.isEmpty()) {
            int v = deque.pop();
            for (int i = 0; i < graph[v].size(); i++) {
                int u = graph[v].get(i);
                if (!visited[u]) {
                    visited[u] = true;
                    deque.add(u);
                    d[u] = d[v] + 1;
                    p[u] = v;
                }
            }
        }

        if (!visited[b]) {
            System.out.println(-1);
        } else {
            ArrayList<Integer> path = new ArrayList<>();
            for (int v = b; v != -1; v = p[v]) {
                path.add(v + 1);
            }
            System.out.println(path.size() - 1);
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
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
