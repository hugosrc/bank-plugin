package me.hugosrc.Bank.util.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ItemButton {

    private ItemStack item;
    private Consumer<InventoryClickEvent> action;

    public ItemButton(ItemStack item, Consumer<InventoryClickEvent> action) {
        this.item = item;
        this.action = action;
    }

    public ItemStack getItem() {
        return item;
    }

    public Consumer<InventoryClickEvent> getAction() {
        return action;
    }
}
