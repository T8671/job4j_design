package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleIsExplorer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Explorer");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsRole1() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        store.add(new Role("1", "Developer"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenReplaceThenRoleIsDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        store.replace("1", new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Developer");
    }

    @Test
    void whenNoReplaceRoleNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        store.replace("10", new Role("10", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Explorer");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsExplorer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Explorer");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        boolean result = store.replace("1", new Role("1", "Developer"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Explorer"));
        boolean result = store.replace("10", new Role("10", "Developer"));
    }
}