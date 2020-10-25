package ru.mail.polis.ads.part4.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/problems/1087
//скобочная последовательность

public class Task1 {

    private static char requiredBracket(char bracket) {
        if (bracket == '(') {
            return ')';
        } else if (bracket == ')') {
            return '(';
        } else if (bracket == '[') {
            return ']';
        } else {
            return '[';
        }
    }

    private static boolean isOpening(char bracket) {
        return bracket == '(' || bracket == '[';
    }

    private static int bestPartition;

    private static void solve(final FastScanner in, final PrintWriter out) {

        String input = in.next();
        char[] chars = input.toCharArray();
        int n = input.length();
        int[][] array = new int[n][n];
        String[][] brackets = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                brackets[i][j] = "+";
            }
        }

        for (int i = 0; i < n; i++) {
            array[i][i] = 1;
            brackets[i][i] = String.valueOf(requiredBracket(chars[i]));
        }

        for (int k = 1; k < n; k++) {
            int j = k;
            for (int i = 0; i < n - k; i++) {
                if ((chars[i] == '(' && chars[j] == ')')
                        || (chars[i] == '[' && chars[j] == ']')) {

                    array[i][j] = array[i + 1][j - 1];
                    brackets[i][j] = brackets[i + 1][j - 1];

                } else {
                    int min = j - i + 1;
                    bestPartition = 0;
                    int t = 0;
                    while (t < j && i + t + 1 <= j) {
                        if (i + t + 1 < n) {
                            if (array[i][i + t] + array[i + t + 1][j] <= min) {
                                min = array[i][i + t] + array[i + t + 1][j];
                                bestPartition = t;
                            }
                        }
                        t++;
                    }
                    array[i][j] = min;
                    brackets[i][j] = brackets[i][i + bestPartition] + brackets[i + bestPartition + 1][j];
                }
                j++;
            }
        }
        System.out.println(bestPartition);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                System.out.print(brackets[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder sb = new StringBuilder(input);
        int i = 0;
        int j = n - 1;
        while (requiredBracket(sb.charAt(i)) == sb.charAt(j) && i < bestPartition) {
            i++;
            j--;
        }

        System.out.println(i);
        System.out.println(j);
        Deque<Character> deque = new ArrayDeque<>();
        for (int k = 0; k < array[0][n - 1]; k++) {
            deque.push(brackets[0][n - 1].charAt(k));
        }
        while (!deque.isEmpty()) {
            System.out.println(deque.pollLast());
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
