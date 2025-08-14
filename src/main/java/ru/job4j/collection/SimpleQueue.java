package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T result;
        try {
            result = output.pop();
        } catch (NoSuchElementException e) {
            while (true) {
                try {
                    output.push(input.pop());
                } catch (NoSuchElementException ex) {
                    break;
                }
            }
            result = output.pop();
        }
        size--;
        return result;
    }

    public void push(T value) {
        input.push(value);
        size++;
    }
}
