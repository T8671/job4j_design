package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void verifyGeneralAssertionToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .isNotEmpty()
                .hasSize(5)
                .containsOnly("four", "first", "second", "three", "five")
                .containsExactly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("second", "first", "three", "five", "four")
                .containsAnyOf("first")
                .doesNotContain("six")
                .startsWith("first")
                .endsWith("five")
                .containsSequence("first", "second");
    }

    @Test
    void verifySatisfactionConditionToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .isNotNull()
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(0);
                })
                .anySatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(0);
                })
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() > 0)
                .noneMatch(e -> e.length() < 1);
    }

    @Test
    void verifyNavigationListContentToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(0).isNotNull().isEqualTo("first");
        assertThat(list).last().isNotNull().isEqualTo("five");
    }

    @Test
    void verifyFilteredListContentToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .filteredOn(e -> e.length() > 5)
                .first()
                .isEqualTo("second");
        assertThat(list)
                .filteredOnAssertions(e -> assertThat(e.length()).isLessThan(6))
                .hasSize(4)
                .first()
                .isEqualTo("first");
    }

    @Test
    void verifyGeneralAssertionToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set)
                .isNotEmpty()
                .hasSize(5)
                .containsOnly("four", "first", "second", "three", "five")
                .containsExactlyInAnyOrder("second", "first", "three", "five", "four")
                .containsAnyOf("first")
                .doesNotContain("six");
    }

    @Test
    void verifySatisfactionConditionToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set)
                .isNotNull()
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(0);
                })
                .anySatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(0);
                })
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() > 0)
                .noneMatch(e -> e.length() < 1);
    }

    @Test
    void verifyFilteredSetContentToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set)
                .filteredOn(e -> e.length() > 5)
                .containsOnly("second");
        assertThat(set)
                .filteredOnAssertions(e -> assertThat(e.length()).isLessThan(6))
                .hasSize(4)
                .containsOnly("first", "three", "four", "five");
    }

    @Test
    void verifyGeneralAssertionToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("1", "2", "3");
        assertThat(map).hasSize(3)
                .containsKeys("1", "2", "3")
                .containsValues(0, 1, 2)
                .doesNotContainKey("0")
                .containsEntry("2", 1);
    }
}
