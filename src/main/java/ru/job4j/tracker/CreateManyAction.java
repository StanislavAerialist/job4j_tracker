package ru.job4j.tracker;

public class CreateManyAction implements UserAction {
    private final Output out;

    public CreateManyAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add 1000000 Items";
    }

    @Override
    public boolean execute(Input input, Store meMTracker) {
        out.println("=== Create 1000000 Item ===");
        String name = input.askStr("Enter name: ");
        for (int i = 1; i < 1000000; i++) {
            Item item = new Item(name + i);
            meMTracker.add(item);
            out.println("Добавленная заявка: " + item);
        }
        return true;
    }
}