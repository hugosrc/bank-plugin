package me.hugosrc.Bank.economy;

import org.bukkit.entity.Player;

public class VaultEconomy implements Economy {

    private net.milkbowl.vault.economy.Economy economy;

    public VaultEconomy(net.milkbowl.vault.economy.Economy economy) {
        this.economy = economy;
    }

    @Override
    public double getBalance(Player player) {
        return economy.getBalance(player);
    }

    @Override
    public void withdrawPlayer(Player player, double amount) {
        economy.withdrawPlayer(player, amount);
    }

    @Override
    public void depositPlayer(Player player, double amount) {
        economy.depositPlayer(player, amount);
    }
}
