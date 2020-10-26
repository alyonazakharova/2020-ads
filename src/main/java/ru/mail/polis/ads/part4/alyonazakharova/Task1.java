package ru.mail.polis.ads.part4.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    private static void solve(final FastScanner in, final PrintWriter out) {

        String input;

        try {
            input = in.next();
        } catch (NullPointerException e) {
            return;
        }

        char[] chars = input.toCharArray();
        int n = input.length();
        int[][] array = new int[n][n];
        String[][] brackets = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                brackets[i][j] = "";
            }
        }

        for (int i = 0; i < n; i++) {
            array[i][i] = 1;
            if (chars[i] == '(' || chars[i] == ')') {
                brackets[i][i] = "()";
            } else {
                brackets[i][i] = "[]";
            }
        }

        for (int k = 1; k < n; k++) {
            int j = k;
            for (int i = 0; i < n - k; i++) {
                if ((chars[i] == '(' && chars[j] == ')')
                        || (chars[i] == '[' && chars[j] == ']')) {

                    int min = j - i + 1;
                    int bestPartition = 0;
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
                    if (min < array[i + 1][j - 1]) {
                        array[i][j] = min;
                        brackets[i][j] = brackets[i][i + bestPartition] + brackets[i + bestPartition + 1][j];
                    } else {
                        array[i][j] = array[i + 1][j - 1];
                        brackets[i][j] = "" + chars[i] + brackets[i + 1][j - 1] + chars[j];
                    }

                } else {
                    int min = j - i + 1;
                    int bestPartition = 0;
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

        out.println(brackets[0][n - 1]);
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
