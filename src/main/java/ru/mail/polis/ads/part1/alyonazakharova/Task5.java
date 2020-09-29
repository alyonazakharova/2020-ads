package ru.mail.polis.ads.part1.alyonazakharova;

import java.io.*;
import java.util.StringTokenizer;

public class Task5 {

    public static class MyQueue {

        private static class Element {
            private final int value;
            private Element next;

            public Element(int value) {
                this.value = value;
            }
        }

        private int size;
        private Element first;
        private Element last;

        public MyQueue() {
            this.size = 0;
            this.first = null;
            this.last = null;
        }

        public void push(int n) {
            if (size == 0) {
                first = new Element(n);
                last = first;
            } else {
                last.next = new Element(n);
                last = last.next;
            }
            size++;
        }

        public int pop() {
            if (size != 0) {
                int firstValue = first.value;
                first = first.next;
                size--;
                return firstValue;
            } else {
                throw new RuntimeException();
            }
        }

        public int front() {
            if (size != 0) {
                return first.value;
            } else {
                throw new RuntimeException();
            }
        }

        public int size() {
            return size;
        }

        public void clear() {
            first = null;
            last = null;
            size = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        MyQueue queue = new MyQueue();

        String command = in.next();
        while (true) {
            switch (command) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(queue.pop());
                    } catch (RuntimeException e) {
                        out.println("error");
                    }
                    break;
                case "front":
                    try {
                        out.println(queue.front());
                    } catch (RuntimeException e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
                default:
            }
            command = in.next();
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
