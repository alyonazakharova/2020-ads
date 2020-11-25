package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyHashTable<Key, Value> implements HashTable<Key, Value> {

    static class Node<Key, Value> {
        Key key;
        Value value;
        Node<Key, Value> next;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private final double loadFactor;
    private int size;
    private Node<Key, Value>[] array;

    public MyHashTable(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.array = new Node[capacity];
    }

    private int getIndex(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize() {
        Node<Key, Value>[] oldArray = array;
        capacity *= 2;
        size = 0;
        array = new Node[capacity];
        for (Node<Key, Value> node : oldArray) {
            Node<Key, Value> temp = node;
            while (temp != null) {
                put(temp.key, temp.value);
                temp = temp.next;
            }
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int index = getIndex(key);
        Node<Key, Value> node = array[index];
        while (node != null && !key.equals(node.key)) {
            node = node.next;
        }
        return node == null ? null : node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = getIndex(key);
        Node<Key, Value> node = array[index];
        if (node == null) {
            array[index] = new Node<Key, Value>(key, value);
            size++;
            return;
        }

        while (node.next != null && !key.equals(node.key)) {
            node = node.next;
        }

        if (key.equals(node.key)) {
            node.value = value;
        } else {
            node.next = new Node<>(key, value);
            size++;
        }

        if (size / capacity > loadFactor) {
            resize();
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int index = getIndex(key);
        Node<Key, Value> node = array[index];

        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            Value value = node.value;
            array[index] = node.next;
            size--;
            return value;
        }

        while (node.next != null && !key.equals(node.next.key)) {
            node = node.next;
        }

        if (node.next != null) {
            Value value = node.next.value;
            node.next = node.next.next;
            size--;
            return value;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
