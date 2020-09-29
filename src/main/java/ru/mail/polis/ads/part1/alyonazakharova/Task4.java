package ru.mail.polis.ads.part1.alyonazakharova;

import java.io.*;
import java.util.StringTokenizer;

public class Task4 {

    public static class MyStack {

        private static class Element {
            private final int value;
            private final Element prev;

            public Element(int value, Element prev) {
                this.value = value;
                this.prev = prev;
            }
        }

        private int size;
        private Element last;

        public MyStack() {
            this.size = 0;
            this.last = null;
        }

        public void push(int n) {
            Element prev = last;
            last = new Element(n, prev);
            size++;
        }

        public int pop() {
            if (size != 0) {
                int lastValue = last.value;
                last = last.prev;
                size--;
                return lastValue;
            } else {
                throw new RuntimeException();
            }
        }

        public int back() {
            if (this.size != 0) {
                return last.value;
            } else {
                throw new RuntimeException();
            }
        }

        public int size() {
            return size;
        }

        public void clear() {
            last = null;
            size = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        MyStack stack = new MyStack();

        String command = in.next();
        while (true) {
            switch (command) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(stack.pop());
                    } catch (RuntimeException e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(stack.back());
                    } catch (RuntimeException e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
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
