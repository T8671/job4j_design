package ru.job4j.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenParent99NoBecauseNo99node() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(99, 2)).isFalse();
    }

    @Test
    void whenAddRootAsChildThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 1)).isFalse();
    }

    @Test
    void whenAddNullThenFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(null, 2)).isFalse();
    }

    @Test
    void whenAddNewChildThenTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2)).isTrue();
        assertThat(tree.findBy(2)).isPresent();

    }

    @Test
    void whenAddChildThenParentContainsIt() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        Tree.Node<Integer> parent = tree.findBy(1).orElseThrow();
        boolean found = parent.children.stream()
                .anyMatch(node -> node.value.equals(2));
        assertThat(found).isTrue();
    }
}