package me.hugosrc.Bank.listeners;

import me.hugosrc.Bank.BankPlugin;
import me.hugosrc.Bank.entities.objects.Bank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private final Bank bank;

    public PlayerQuit() {
        this.bank = BankPlugin.instance.getBank();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        bank.removeAccount(player);
    }

}
