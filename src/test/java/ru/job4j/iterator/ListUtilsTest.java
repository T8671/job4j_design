package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf3() {
        Predicate<Integer> predicate = (n) -> n == 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input).containsSequence(1);
    }

    @Test
    void whenRemoveEvenNumber() {
        Predicate<Integer> predicate = (a) -> a % 2 == 0;
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 2, 4);
        ListUtils.removeIf(input, predicate);
        assertThat(input).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIf3With2() {
        Predicate<Integer> predicate = (n) -> n == 3;
        ListUtils.replaceIf(input, predicate, 2);
        assertThat(input).containsSequence(1, 2);
    }

    @Test
    void whenReplaceIfIsNoReplacementValue() {
        Predicate<Integer> predicate = (n) -> n == 2;
        ListUtils.replaceIf(input, predicate, 2);
        assertThat(input).containsSequence(1, 3);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> toRemove = List.of(1, 3);
        ListUtils.removeAll(input, toRemove);
        assertThat(input).containsSequence();
    }

    @Test
    void whenRemoveAllNothingChanged() {
        List<Integer> toRemove = List.of();
        ListUtils.removeAll(input, toRemove);
        assertThat(input).containsSequence(1, 3);
    }

    @Test
    void whenRemoveAllNoCommonElements() {
        List<Integer> toRemove = List.of(2);
        ListUtils.removeAll(input, toRemove);
        assertThat(input).containsSequence(1, 3);
    }
}