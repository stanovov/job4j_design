package ru.job4j.generic;

import java.util.Objects;

public class Role extends Base {

    private String name;

    public Role(String id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{"
                + "id='" + getId() + '\''
                + ", name='" + name + '\''
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
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
