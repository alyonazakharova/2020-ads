package ru.mail.polis.ads.part3.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/3737

public class Task1 {

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        int[] heap = new int[n + 1];
        int i;
        for (i = 1; i <= n; i++) {
            heap[i] = in.nextInt();
        }

        i = 1;
        while (2 * i <= n) {
            if (heap[i] > heap[2 * i]) {
                out.println("NO");
                return;
            }
            if (2 * i + 1 <= n) {
                if (heap[i] > heap[2 * i + 1]) {
                    out.println("NO");
                    return;
                }
            }
            i++;
        }
        out.println("YES");
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
