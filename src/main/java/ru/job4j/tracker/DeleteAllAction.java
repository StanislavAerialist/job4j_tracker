package ru.job4j.tracker;

import java.util.List;

public class DeleteAllAction implements UserAction {
    private final Output out;

    public DeleteAllAction(Output out) {
            this.out = out;
        }

        @Override
        public String name() {
            return "Delete all items";
        }

        @Override
        public boolean execute(Input input, Store meMTracker) {
            out.println("=== Delete all items ===");
            List<Item> del = meMTracker.findAll();
            if (!del.isEmpty()) {
                for (Item item : del) {
                    out.println(item);
                    meMTracker.delete(item.getId());
                }
            } else {
                out.println("Ошибка удаления заявок.");
            }
            return true;
    }
}
