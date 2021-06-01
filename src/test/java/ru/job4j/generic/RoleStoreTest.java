package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    private Role role1;
    private Role role2;
    private Role role3;

    @Before
    public void setUp() {
        role1 = new Role(
                "1",
                "Admin"
        );
        role2 = new Role(
                "2",
                "Buyer"
        );
        role3 = new Role(
                "3",
                "Seller"
        );
    }

    @Test
    public void whenAddRoles() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        assertThat(roleStore.findById(role1.getId()), is(role1));
        assertThat(roleStore.findById(role2.getId()), is(role2));
        assertThat(roleStore.findById(role3.getId()), is(role3));
    }

    @Test
    public void whenFindByIdNonExistentRole() {
        RoleStore roleStore = new RoleStore();
        Role result = roleStore.findById("1");
        assertNull(result);
    }

    @Test
    public void whenReplaceRole() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(role1);
        Role replaceableRole = new Role(
                "1",
                "Buyer"
        );
        boolean result = roleStore.replace(replaceableRole.getId(), replaceableRole);
        assertTrue(result);
        assertThat(roleStore.findById("1"), is(replaceableRole));
    }

    @Test
    public void whenReplaceUserAndThereIsNotKey() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(role1);
        boolean result = roleStore.replace(role2.getId(), role2);
        assertFalse(result);
        assertNull(roleStore.findById(role2.getId()));
    }

    @Test
    public void whenDeleteUser() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(role1);
        boolean result = roleStore.delete(role1.getId());
        assertTrue(result);
        assertNull(roleStore.findById(role1.getId()));
    }

    @Test
    public void whenDeleteNonExistentUser() {
        RoleStore roleStore = new RoleStore();
        boolean result = roleStore.delete("1");
        assertFalse(result);
    }

}