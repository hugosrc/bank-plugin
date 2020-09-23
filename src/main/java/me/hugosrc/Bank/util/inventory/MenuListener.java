package me.hugosrc.Bank.util.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player) || event.getCurrentItem() == null) {
            return;
        }

        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof Menu) {
            Menu menu = (Menu) event.getInventory().getHolder();

            menu.onClick(event);
        }
    }

}
