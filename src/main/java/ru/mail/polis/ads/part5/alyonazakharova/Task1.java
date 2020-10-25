package ru.mail.polis.ads.part5.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/3968
//квадратный корень

public class Task1 {

    private static void solve(final FastScanner in, final PrintWriter out) {

        double c = Double.parseDouble(in.next());
        double l = 0.0;
        double r = Math.sqrt(c);
        double m = 0;
        while (l < r) {
            m = (l + r) / 2;
            double f = m * m + Math.sqrt(m);
            if (Math.abs(f - c) < 0.0000001) {
                break;
            } else if (f > c) {
                r = m;
            } else {
                l = m;
            }
        }
        out.print((double)Math.round(m * 1000000000d)/1000000000d);
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

