package me.hugosrc.Bank.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultEconomyProvider implements EconomyProvider {

    @Override
    public Economy getEconomy() {
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> provider =
                Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);

        if (provider != null) {
            return new VaultEconomy(provider.getProvider());
        }

        return null;
    }
}
