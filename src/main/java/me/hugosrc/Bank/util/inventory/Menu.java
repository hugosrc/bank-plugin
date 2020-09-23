package me.hugosrc.Bank.util.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Menu implements InventoryHolder {

    private final Inventory menu;
    private Map<Integer, ItemButton> callbacks;

    public Menu(String title, int rows) {
        menu = Bukkit.createInventory(this, rows*9, "" + title);
        callbacks = new HashMap<>();
    }

    public void open(Player player) {
        player.openInventory(menu);
    }

    @Override
    public Inventory getInventory() {
        return menu;
    }

    public void setItem(int slot, ItemStack item, Consumer<InventoryClickEvent> consumer) {
        ItemButton itemButton = new ItemButton(item, consumer);

        menu.setItem(slot, itemButton.getItem());

        callbacks.put(slot, itemButton);
    }

    public void onClick(InventoryClickEvent event) {
        if (!callbacks.containsKey(event.getRawSlot())) {
            return;
        }

        event.setCancelled(true);

        Consumer<InventoryClickEvent> action = callbacks.get(event.getRawSlot()).getAction();

        if (action != null) {
            action.accept(event);
        }
    }
}
