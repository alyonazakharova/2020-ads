package ru.mail.polis.ads.part3.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/5149

public class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        int k = in.nextInt();

        int[] coordinates = new int[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = in.nextInt();
        }

        int min = 0;
        int max = coordinates[n-1] - coordinates[0] + 1;

        while (max - min > 1) {
            int mid = (min + max) / 2;
            if (check(coordinates, mid, k)) {
                min = mid;
            } else {
                max = mid;
            }
        }

        out.println(min);
    }

    private static boolean check(int[] coordinates, int distance, int cows) {
        int counter = 1;
        int prev = coordinates[0];
        for (int coordinate : coordinates) {
            if (coordinate - prev >= distance) {
                counter++;
                prev = coordinate;
            }
        }
        return counter >= cows;
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
