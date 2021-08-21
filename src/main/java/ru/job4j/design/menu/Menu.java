package ru.job4j.design.menu;

import java.util.*;

public class Menu implements Toolbox {

    private final Scanner scanner;

    private final List<UserAction> actions;

    private final Map<String, Item> itemsMap = new LinkedHashMap<>();

    private String currentNumber;

    public Menu(List<UserAction> actions, List<Item> items) {
        this(actions, items, new Scanner(System.in));
    }

    public Menu(List<UserAction> actions, List<Item> items, Scanner scanner) {
        validate(actions, items);
        this.actions = new ArrayList<>(actions);
        this.scanner = scanner;
        initItemsMap("", items);
        this.currentNumber = itemsMap.keySet().iterator().next();
    }

    @Override
    public Map<String, Item> getMapItems() {
        return itemsMap;
    }

    @Override
    public String getCurrentNumber() {
        return currentNumber;
    }

    @Override
    public void setCurrentNumber(String number) {
        this.currentNumber = number;
    }

    public void use() {
        boolean run = true;
        do {
            showItems();
            showActions();
            int select = Integer.parseInt(scanner.nextLine());
            if (select < 0 || select >= actions.size()) {
                System.out.println("Wrong input");
                continue;
            }
            UserAction action = actions.get(select);
            run = action.action(this, scanner);
        } while (run);
    }

    private void showItems() {
        for (Map.Entry<String, Item> entry : itemsMap.entrySet()) {
            String number = entry.getKey();
            Item item = entry.getValue();
            boolean selected = currentNumber.equals(number);
            System.out.printf(
                    "%s%s%s %s%n",
                    selected ? ">" : " ",
                    getPrefix(number),
                    item.getName(),
                    number
            );
        }
    }

    private void showActions() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < actions.size(); i++) {
            sb.append(actions.get(i).getName())
                    .append(" - ")
                    .append(i)
                    .append(" | ");
        }
        sb.delete(sb.length() - 3, sb.length());
        System.out.println("-".repeat(sb.length()));
        System.out.println(sb.toString());
    }

    private void validate(List<UserAction> actions, List<Item> items) {
        if (actions == null || actions.size() == 0) {
            throw new IllegalArgumentException("Invalid actions list");
        }
        if (items == null || items.size() == 0) {
            throw new IllegalArgumentException("Invalid items list");
        }
    }

    private void initItemsMap(String prefix, List<Item> childItems) {
        int count = 1;
        for (Item item : childItems) {
            String currPrefix = prefix + count + ".";
            itemsMap.put(currPrefix, item);
            initItemsMap(currPrefix, item.childItems());
            count++;
        }
    }

    private String getPrefix(String number) {
        int count = countDot(number) - 1;
        return count == 0 ? "" : "----".repeat(count) + " ";
    }

    private int countDot(String str) {
        return str.length() - str.replace(".", "").length();
    }

    public static void main(String[] args) {
        List<UserAction> actions = List.of(
                new ActionSelect(),
                new ActionDown(),
                new ActionUp(),
                new ActionExit()
        );
        String desc = "some task";
        String answer = "some answer";
        List<Item> items = List.of(
                new Task("", desc, answer, List.of(
                        new Task("", desc, answer, List.of(
                                new Task("", desc, answer),
                                new Task("", desc, answer)
                        )),
                        new Task("", desc, answer)
                )),
                new Prompt("", desc)
        );
        new Menu(actions, items).use();
    }
}
