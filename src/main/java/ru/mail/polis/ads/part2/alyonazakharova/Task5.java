package ru.mail.polis.ads.part2.alyonazakharova;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/4037

public class Task5 {

    private static class MyPair {
        int first;
        int second;

        private MyPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static void mergeSort(MyPair[] array, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        MyPair[] leftArray = new MyPair[mid];
        MyPair[] rightArray = new MyPair[n - mid];

        System.arraycopy(array, 0, leftArray, 0, mid);
        System.arraycopy(array, mid, rightArray, 0, n - mid);

        mergeSort(leftArray, mid);
        mergeSort(rightArray, n - mid);

        merge(array, leftArray, rightArray, mid, n - mid);
    }

    private static void merge(MyPair[] array, MyPair[] leftArray, MyPair[] rightArray, int left, int right) {
        int i = 0, j = 0, k = 0;
        while ((i < left) && (j < right)) {
            if (leftArray[i].first <= rightArray[j].first) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < left) {
            array[k++] = leftArray[i++];
        }
        while (j < right) {
            array[k++] = rightArray[j++];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        MyPair[] a = new MyPair[n];
        for (int i = 0; i < n; i++){
            a[i] = new MyPair(in.nextInt(), in.nextInt());
        }
        mergeSort(a, a.length);
        for (int i = 0; i < n; i++) {
            out.println(a[i].first + " " + a[i].second);
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
