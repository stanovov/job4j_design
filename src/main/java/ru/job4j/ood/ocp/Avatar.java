package ru.job4j.ood.ocp;

public class Avatar {

    private final String clazz;

    public Avatar(String clazz) {
        this.clazz = clazz;
    }

    public int attack(Avatar enemy) {
        int damage = 0;
        if (clazz.equals("warrior")) {
            damage = 8;
        } else if (clazz.equals("archer")) {
            damage = 10;
        } else if (clazz.equals("mage")) {
            damage = 12;
        }
        return damage;
    }

    public int defend(Avatar enemy) {
        int defPoint = 0;
        if (clazz.equals("warrior")) {
            defPoint = 6;
        } else if (clazz.equals("archer")) {
            defPoint = 4;
        } else if (clazz.equals("mage")) {
            defPoint = 2;
        }
        return defPoint;
    }
}
