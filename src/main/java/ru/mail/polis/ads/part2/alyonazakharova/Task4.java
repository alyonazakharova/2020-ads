package ru.mail.polis.ads.part2.alyonazakharova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

//https://www.e-olymp.com/ru/problems/4827

public class Task4 {

    private static BigInteger find(BigInteger[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        while (true) {
            int elem = partition(array, left, right);
            if (elem == k) {
                return array[elem];
            } else if (k < elem) {
                right = elem;
            } else {
                left = elem + 1;
            }
        }
    }

    private static int partition(BigInteger[] array, int left, int right) {
        BigInteger x = array[left];
        int i = left;
        int j = right;
        while (i <= j) {
            while (array[i].compareTo(x) > 0) {
                i++;
            }
            while (array[j].compareTo(x) < 0) {
                j--;
            }
            if (i >= j) {
                break;
            }
            BigInteger tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        return j;
    }

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int k = Integer.parseInt(in.readLine()) - 1;
        String[] numbers = in.readLine().split(" ");
        BigInteger[] array = new BigInteger[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            array[i] = new BigInteger(numbers[i]);
        }
        out.print(find(array, k));
        in.close();
        out.close();
    }

    public static void main(final String[] arg) {
        try {
            solve();
        } catch (IOException ignored) {}
    }
}
