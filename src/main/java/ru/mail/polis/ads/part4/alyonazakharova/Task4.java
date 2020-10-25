package ru.mail.polis.ads.part4.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/262
//лесенка

public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();

        int[] stairwayToHeaven = new int[n + 2];
        stairwayToHeaven[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            stairwayToHeaven[i] = in.nextInt();
        }
        stairwayToHeaven[n + 1] = 0;

        int[] sum = new int[n + 2];
        sum[0] = 0;

        int k = in.nextInt();

        for (int i = 1; i < n + 2; i++) {
            int max = sum[i - 1];
            int j = 2;
            while (j <= k && i - j >= 0) {
                if (sum[i - j] > max) {
                    max = sum[i - j];
                }
                j++;
            }
            sum[i] = max + stairwayToHeaven[i];
        }

        out.println(sum[n + 1]);
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
