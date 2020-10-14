package ru.mail.polis.ads.part3.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/4039

public class Task2 {

    private static class Heap {

        private final ArrayList<Integer> heap;
        private int size;

        private Heap() {
            this.heap = new ArrayList<>();
            this.heap.add(0);
            this.size = 0;
        }

        private void insert(int x) {
            heap.add(x);
            size++;
            swim(size);
        }

        private int extract() {
            int res = heap.get(1);
            heap.set(1, heap.get(size));
            heap.remove(size);
            size--;
            sink(1);
            return res;
        };

        private void swim(int k) {
            while (k > 1 && heap.get(k) > heap.get(k / 2)) {
                Collections.swap(heap, k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && heap.get(j) < heap.get(j + 1)) {
                    j++;
                }
                if (heap.get(k) >= heap.get(j)) {
                    break;
                }
                Collections.swap(heap,k, j);
                k = j;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        Heap heap = new Heap();

        for (int i = 0; i < n; i++) {
            int command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
            } else if (command == 1) {
                 out.println(heap.extract());
            }
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
