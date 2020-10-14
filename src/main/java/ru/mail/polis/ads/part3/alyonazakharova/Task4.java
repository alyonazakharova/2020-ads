package ru.mail.polis.ads.part3.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/9016

public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        int q = in.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++) {
            if (find(array, in.nextInt(), 0, n - 1) == -1) {
                out.println("NO");
            } else {
                out.println("YES");
            }
        }
    }

    private static int find(int[] array, int x, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] < x) {
                left =  mid + 1;
            } else if (array[mid] > x) {
                right = mid - 1;
            } else if (array[mid] == x) {
                return mid;
            }
        }
        return -1;
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
