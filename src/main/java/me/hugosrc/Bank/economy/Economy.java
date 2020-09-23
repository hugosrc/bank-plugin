package me.hugosrc.Bank.economy;

import org.bukkit.entity.Player;

public interface Economy {
    double getBalance(Player player);
    void withdrawPlayer(Player player, double amount);
    void depositPlayer(Player player, double amount);
}
