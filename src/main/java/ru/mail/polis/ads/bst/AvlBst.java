package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node root;

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
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node x = get(root, key);
        if (x == null) {
            return null;
        }
        root = remove(root, key);
        return x.value;
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        }
        if (key.compareTo(x.key) == 0) {
            x = innerRemove(x);
        }
        return x;
    }

    private Node innerRemove(Node x) {
        if (x.left == null) {
            return x.right;
        }
        if (x.right == null) {
            return x.left;
        }
        Node t = x;
        x = min(t.right);
        x.right = removeMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        return x;
    }

    @Override
    public Key min() {
        Node x = min(root);
        return x == null? null : x.key;
    }

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

    @Override
    public Key max() {
        Node x = max(root);
        return x == null ? null : x.key;
    }

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

    //возвращает максимальный ключ, меньший либо равный заданному, или null, если такого нет
    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x;
        }
        if (x.key.compareTo(key) > 0) {
            return floor(x.left, key);
        }

        Node node = floor(x.right, key);
        return node == null ? x : node;

    }

    //вовзращает минимальный ключ, больший либо равный заданному, или null, если такого нет
    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        return x == null ? null : x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x;
        }
        if (x.key.compareTo(key) < 0) {
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

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }
}
