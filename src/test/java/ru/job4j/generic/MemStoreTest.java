package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemStoreTest {

    private Role role;

    private User user;

    @Before
    public void setUp() {
        role = new Role(
                "1",
                "Admin"
        );
        user = new User(
                "2",
                "Petr",
                role
        );
    }

    @Test
    public void whenAddElements() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(role);
        memStore.add(user);
        assertThat(memStore.findById(role.getId()), is(role));
        assertThat(memStore.findById(user.getId()), is(user));
    }

    @Test
    public void whenFindByIdNonExistentElement() {
        MemStore<Base> memStore = new MemStore<>();
        Base result = memStore.findById("1");
        assertNull(result);
    }

    @Test
    public void whenReplaceElement() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(role);
        User replaceableUser = new User(
                "1",
                "Semyon",
                new Role(
                        "2",
                        "Buyer"
                )
        );
        boolean result = memStore.replace(replaceableUser.getId(), replaceableUser);
        assertTrue(result);
        assertThat(memStore.findById("1"), is(replaceableUser));
    }

    @Test
    public void whenReplaceElementAndThereIsNotKey() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(user);
        boolean result = memStore.replace(role.getId(), role);
        assertFalse(result);
        assertNull(memStore.findById(role.getId()));
    }

    @Test
    public void whenDeleteElement() {
        MemStore<Base> memStore = new MemStore<>();
        memStore.add(user);
        boolean result = memStore.delete(user.getId());
        assertTrue(result);
        assertNull(memStore.findById(user.getId()));
    }

    @Test
    public void whenDeleteNonExistentElement() {
        MemStore<Base> memStore = new MemStore<>();
        boolean result = memStore.delete("1");
        assertFalse(result);
    }
}