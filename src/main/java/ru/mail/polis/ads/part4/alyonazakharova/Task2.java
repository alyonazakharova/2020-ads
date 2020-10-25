package ru.mail.polis.ads.part4.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/15
//мышка и зернышки

public class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {

        int m = in.nextInt();
        int n = in.nextInt();

        int[][] floor = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floor[i][j] = in.nextInt();
            }
        }

        int[][] d = new int[m][n];
        d[m - 1][0] = floor[m - 1][0];
        for (int i = m - 2; i >= 0; i--) {
            d[i][0] = floor[i][0] + d[i + 1][0];
        }
        for (int j = 1; j < n; j++) {
            d[m - 1][j] = floor[m - 1][j] + d[m - 1][j - 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                d[i][j] = Math.max(d[i + 1][j], d[i][j - 1]) + floor[i][j];
            }
        }

        StringBuilder path = new StringBuilder();
        int i = 0;
        int j = n - 1;
        while (i < m - 1 && j > 0) {
            if (d[i + 1][j] > d[i][j - 1]) {
                path.append('F');
                i++;
            } else {
                path.append('R');
                j--;
            }
        }
        while (j > 0) {
            path.append('R');
            j--;
        }
        while (i < m - 1) {
            path.append('F');
            i++;
        }

        out.print(path.reverse());
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
