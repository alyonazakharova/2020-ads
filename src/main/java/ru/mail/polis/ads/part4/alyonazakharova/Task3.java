package ru.mail.polis.ads.part4.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/1618
//наибольшая общая подпоследовательность

public class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        int[] first = new int[n];
        for (int i = 0; i < n; i++) {
            first[i] = in.nextInt();
        }

        int m = in.nextInt();
        int[] second = new int[m];
        for (int i = 0; i < m; i++) {
            second[i] = in.nextInt();
        }

        int[][] d = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (first[i - 1] == second[j - 1]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                }
            }
        }

        out.print(d[n][m]);
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
