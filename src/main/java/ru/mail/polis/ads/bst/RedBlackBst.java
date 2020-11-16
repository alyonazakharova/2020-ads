package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x.right);
        return fixUp(x);
    }

    private void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node x = get(root, key);
        return x == null ? null : x.value;
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        } else if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, RED);
        }
        int comparison = key.compareTo(x.key);
        if (comparison < 0) {
            x.left = put(x.left, key, value);
        } else if (comparison > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Value x = get(key);
        if (x != null) {
            root = remove(root, key);
        }
        return x;
    }

    private Node remove(Node x, Key key) {

        if (x == null) {
            return null;
        }

        int comparison = key.compareTo(x.key);
        if (comparison < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = remove(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if (comparison == 0 && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right)
                        && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key == key) {
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }

        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Key min() {
        Node x = min(root);
        return x == null? null : x.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node x = min(root);
        return x == null ? null : x.value;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        return x.left == null ? x : min(x.left);
    }

    @Nullable
    @Override
    public Key max() {
        Node x = max(root);
        return x == null ? null : x.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node x = max(root);
        return x == null ? null : x.value;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        return x.right == null ? x : max(x.right);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int comparison = key.compareTo(x.key);
        if (comparison == 0) {
            return x;
        }
        if (comparison < 0) {
            return floor(x.left, key);
        }
        Node node = floor(x.right, key);
        return node == null ? x : node;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        return x == null ? null : x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        
        int comparison = key.compareTo(x.key);
        if (comparison == 0) {
            return x;
        }
        if (comparison > 0) {
            return ceil(x.right, key);
        }

        Node node = ceil(x.left, key);
        return node == null ? x : node;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return size(x.left) + size(x.right) + 1;
    }
}
