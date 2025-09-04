package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        if (parent == null && child == null) {
            return false;
        }
        return findBy(child).isEmpty()
                && findBy(parent).map(p -> {
            p.children.add(new Node<>(child));
            return true;
        }).orElse(false);

    }

    public boolean isBinary() {
        return findByPredicate(p -> p.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!(queue.isEmpty())) {
            Node<E> current = queue.poll();
            if (condition.test(current)) {
                return Optional.of(current);
            } else {
                queue.addAll(current.children);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(p -> Objects.equals(p.value, value));
    }
}