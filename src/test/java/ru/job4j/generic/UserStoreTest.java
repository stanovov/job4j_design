package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        user1 = new User(
                "1",
                "Petr",
                new Role(
                        "1",
                        "Admin"
                )
        );
        user2 = new User(
                "2",
                "Semyon",
                new Role(
                        "2",
                        "Buyer"
                )
        );
        user3 = new User(
                "3",
                "Ivan",
                new Role(
                        "3",
                        "Seller"
                )
        );
    }

    @Test
    public void whenAddUsers() {
        UserStore userStore = new UserStore();
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        assertThat(userStore.findById(user1.getId()), is(user1));
        assertThat(userStore.findById(user2.getId()), is(user2));
        assertThat(userStore.findById(user3.getId()), is(user3));
    }

    @Test
    public void whenFindByIdNonExistentUser() {
        UserStore userStore = new UserStore();
        User result = userStore.findById("1");
        assertNull(result);
    }

    @Test
    public void whenReplaceUser() {
        UserStore userStore = new UserStore();
        userStore.add(user1);
        User replaceableUser = new User(
                "1",
                "Mary",
                new Role(
                        "2",
                        "Buyer"
                )
        );
        boolean result = userStore.replace(replaceableUser.getId(), replaceableUser);
        assertTrue(result);
        assertThat(userStore.findById("1"), is(replaceableUser));
    }

    @Test
    public void whenReplaceUserAndThereIsNotKey() {
        UserStore userStore = new UserStore();
        userStore.add(user1);
        boolean result = userStore.replace(user2.getId(), user2);
        assertFalse(result);
        assertNull(userStore.findById(user2.getId()));
    }

    @Test
    public void whenDeleteUser() {
        UserStore userStore = new UserStore();
        userStore.add(user1);
        boolean result = userStore.delete(user1.getId());
        assertTrue(result);
        assertNull(userStore.findById(user1.getId()));
    }

    @Test
    public void whenDeleteNonExistentUser() {
        UserStore userStore = new UserStore();
        boolean result = userStore.delete("1");
        assertFalse(result);
    }

}