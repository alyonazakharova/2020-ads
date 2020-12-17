package ru.mail.polis.ads.part9.alyonazakharova;

//Топологическая сортировка
//https://github.com/polis-mail-ru/2020-ads

import java.io.*;
import java.util.*;

public class Task1 {

    private static LinkedList<Integer>[] graph;
    private static int[] colors;
    private static boolean hasCycle;

    private static void dfs(int v) {
        colors[v] = 1;
        for (int i = 0; i < graph[v].size(); i++) {
            if (colors[graph[v].get(i)] == 0) {
                dfs(graph[v].get(i));
            } else if ((colors[graph[v].get(i)] == 1)) {
                hasCycle = true;
            }
        }
        colors[v] = 2;
    }

    private static void sort(int v, boolean[] visited, Deque<Integer> deque) {
        visited[v] = true;
        Integer i;
        Iterator<Integer> iter = graph[v].iterator();
        while (iter.hasNext()) {
            i = iter.next();
            if (!visited[i]) {
                sort(i, visited, deque);
            }
        }
        deque.push(v);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            graph[from - 1].add(in.nextInt() - 1);
        }

        colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                dfs(i);
            }
        }

        if (hasCycle) {
            System.out.print(-1);
            return;
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                sort(i, visited, deque);
            }
        }

        while (!deque.isEmpty()) {
            System.out.print((deque.pop() + 1) + " ");
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
