package ru.mail.polis.ads.part1.alyonazakharova;

import java.io.*;
import java.util.*;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        if (input.length() % 2 == 1) {
            out.println("NO");
            return;
        }
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                deque.push('(');
            } else {
                if (deque.pollLast() ==  null) {
                    out.println("NO");
                    return;
                }
            }
        }
        if (deque.isEmpty()) {
            out.println("YES");
        } else {
            out.println("NO");
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
