package ru.mail.polis.ads.part2.alyonazakharova;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/4741

public class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        int counter = 0;
        int current;
        for (int i = 0; i < n; i++) {
            current = in.nextInt();
            for (int j = 0; j < i; j++) {
                if (current < array[j]) {
                    counter++;
                }
            }
            array[i] = current;
        }
        out.print(counter);
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
