package me.hugosrc.Bank.listeners;

import me.hugosrc.Bank.menus.BankMenu;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCClick implements Listener {

    @EventHandler
    public void onClick(NPCRightClickEvent event) {
        if (!event.getNPC().getName().equals("§6§lBanqueiro")) return;

        new BankMenu().open(event.getClicker());
    }

}
