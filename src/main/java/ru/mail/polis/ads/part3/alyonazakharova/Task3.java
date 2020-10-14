package ru.mail.polis.ads.part3.alyonazakharova;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

//https://www.e-olymp.com/ru/problems/4074

public class Task3 {

    private static class MaxHeap {

        private final ArrayList<Integer> heap;
        private int size;

        private MaxHeap() {
            this.heap = new ArrayList<>();
            this.heap.add(0);
            this.size = 0;
        }

        private int max() {
            return heap.get(1);
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

    private static class MinHeap {

        private ArrayList<Integer> heap;
        private int size;

        private MinHeap() {
            this.heap = new ArrayList<>();
            this.heap.add(0);
            this.size = 0;
        }

        private int min() {
            return heap.get(1);
        }

        private void insert(int x) {
            heap.add(x);
            size++;
            swim(size);
        }

        private int extract() {
            int res = heap.get(1);
            heap.set(1, heap.get(size - 1));
            heap.remove(size - 1);
            size--;
            sink(1);
            return res;
        };

        private void swim(int k) {
            while (k > 1 && heap.get(k) < heap.get(k / 2)) {
                Collections.swap(heap, k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && heap.get(j) > heap.get(j + 1)) {
                    j++;
                }
                if (heap.get(k) <= heap.get(j)) {
                    break;
                }
                Collections.swap(heap,k, j);
                k = j;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {

        MaxHeap leftHeap = new MaxHeap();
        MinHeap rightHeap = new MinHeap();

        try {
            int first = in.nextInt();
            out.println(first);

            int second = in.nextInt();
            out.println((first + second) / 2);

            if (first < second) {
                leftHeap.insert(first);
                rightHeap.insert(second);
            } else {
                leftHeap.insert(second);
                rightHeap.insert(first);
            }

            int x;
            while (true) {
                x = in.nextInt();
                if (x < leftHeap.max()) {
                    leftHeap.insert(x);
                } else {
                    rightHeap.insert(x);
                }

                if (leftHeap.size - rightHeap.size == 1) {
                    out.println(leftHeap.max());
                } else if (rightHeap.size - leftHeap.size == 1) {
                    out.println(rightHeap.min());
                } else if (leftHeap.size - rightHeap.size > 1) {
                    rightHeap.insert(leftHeap.extract());
                } else if (rightHeap.size - leftHeap.size > 1) {
                    leftHeap.insert(rightHeap.extract());
                }

                if (leftHeap.size == rightHeap.size) {
                    out.println((leftHeap.max() + rightHeap.min()) / 2);
                }
            }
        } catch (NullPointerException e) {
            out.close();
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
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        } catch (IOException e) {
            System.out.println("oops");
        }
    }
}
