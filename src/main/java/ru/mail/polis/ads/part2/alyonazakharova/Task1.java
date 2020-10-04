package ru.mail.polis.ads.part2.alyonazakharova;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/3738

public class Task1 {

    private static int[] sort(int[] array, int start, int end) {
        if (start >= end) {
            return array;
        }
        int i = start, j = end;
        int mid = i - (i - j) / 2;
        while (i < j) {
            while ((i < mid) && (array[i] <= array[mid])) {
                i++;
            }
            while ((j > mid) && (array[j] >= array[mid])) {
                j--;
            }
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                if (i == mid) {
                    mid = j;
                } else if (j == mid) {
                    mid = i;
                }
            }
        }
        sort(array, start, mid);
        sort(array, mid + 1, end);
        return array;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = in.nextInt();
        }
        int[] output = sort(input, 0, input.length - 1);
        for (int i = 0; i < n; i++) {
            out.print(output[i] + " ");
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
