package ru.job4j.generic;

import java.util.Objects;

public class User extends Base {

    private final String name;

    private final Role role;

    public User(String id, String name, Role role) {
        super(id);
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{"
                + "id='" + getId() + '\''
                + ", name='" + name + '\''
                + ", role=" + role
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, role);
    }
}
