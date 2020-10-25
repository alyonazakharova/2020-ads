package ru.mail.polis.ads.part4.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/4261
//количество инверсий

public class Task5 {

    private static int counter = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        mergeSort(array, n);

        out.println(counter);
    }

    private static void mergeSort(int[] array, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[n - mid];

        System.arraycopy(array, 0, leftArray, 0, mid);
        System.arraycopy(array, mid, rightArray, 0, n - mid);

        mergeSort(leftArray, mid);
        mergeSort(rightArray, n - mid);

        merge(array, leftArray, rightArray, mid, n - mid);
    }

    private static void merge(int[] array, int[] leftArray, int[] rightArray, int left, int right) {
        int i = 0, j = 0, k = 0;
        while ((i < left) && (j < right)) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
                counter += leftArray.length - i;
            }
        }
        while (i < left) {
            array[k++] = leftArray[i++];
        }
        while (j < right) {
            array[k++] = rightArray[j++];
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
