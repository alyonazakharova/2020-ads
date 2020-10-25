package ru.mail.polis.ads.part5.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/3969
//дипломы

public class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {

        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();

        long l = Math.max(w, h);
        long r = l * n;

        while (l < r) {
            long m = (l + r) / 2;
            long count = (m / w) * (m / h);
            if (n <= count) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        out.print(l);
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


