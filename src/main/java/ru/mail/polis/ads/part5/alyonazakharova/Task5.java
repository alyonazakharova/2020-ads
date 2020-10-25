package ru.mail.polis.ads.part5.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/2169
//перестановки в лексикографическом порядке

public class Task5 {

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void print(int[] array, int n, final PrintWriter out) {
        for (int i = 0; i < n - 1; i++) {
            out.print(array[i] + " ");
        }
        out.print(array[n - 1] + "\n");
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        print(array, n, out);

        int p = 1;
        for (int i = 1; i <= n; i++) {
            p *= i;
        }

        for (int i = 0; i < p - 1; i++) {

            int tailStartIndex = n - 1;
            while (array[tailStartIndex - 1] > array[tailStartIndex]) {
                tailStartIndex--;
            }

            int k = n - 1;
            while (array[k] < array[tailStartIndex - 1]) {
                k--;
            }

            swap(array, k, tailStartIndex - 1);

            for (int j = tailStartIndex; j < tailStartIndex + (n - tailStartIndex) / 2; j++) {
                swap(array, j, n - 1 - (j - tailStartIndex));
            }

            print(array, n, out);
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
