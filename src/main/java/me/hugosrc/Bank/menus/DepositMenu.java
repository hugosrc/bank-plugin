package me.hugosrc.Bank.menus;

import me.hugosrc.Bank.BankPlugin;
import me.hugosrc.Bank.util.inventory.Menu;
import me.hugosrc.Bank.util.builders.ItemBuilder;
import me.hugosrc.Bank.economy.Economy;
import me.hugosrc.Bank.entities.objects.Account;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DepositMenu extends Menu {

    private final Economy economy;
    private final Account account;

    public DepositMenu(Account account) {
        super("Escolha um valor", 4);

        this.economy = BankPlugin.getInstance().getEconomy();
        this.account = account;
    }

    @Override
    public void open(Player player) {
        setItem(
                10,
                new ItemBuilder(Material.CHEST, 64)
                        .name("§aTudo")
                        .lore(Arrays.asList(
                                "§8Depósito Bancário",
                                " ",
                                "§fSaldo atual: §a" + account.getBalance(),
                                "§fValor a depositar: §a" + economy.getBalance(player),
                                " ",
                                "§eClique para depositar!"))
                        .build(),
                event -> account.deposit(economy.getBalance(player)));

        setItem(
                12,
                new ItemBuilder(Material.CHEST, 32)
                        .name("§aMetade")
                        .lore(Arrays.asList(
                                "§8Depósito Bancário",
                                " ",
                                "§fSaldo atual: §a" + account.getBalance(),
                                "§fValor a depositar: §a" + economy.getBalance(player) / 2,
                                " ",
                                "§eClique para depositar!"))
                        .build(),
                event -> account.deposit(economy.getBalance(player) / 2));

        setItem(
                14,
                new ItemBuilder(Material.CHEST)
                        .name("§a20%")
                        .lore(Arrays.asList(
                                "§8Depósito Bancário",
                                " ",
                                "§fSaldo atual: §a" + account.getBalance(),
                                "§fValor a depositar: §a" + (economy.getBalance(player) * 20) / 100,
                                " ",
                                "§eClique para depositar!"))
                        .build(),
                event -> account.deposit((economy.getBalance(player) * 20) / 100));

        setItem(
                16,
                new ItemBuilder(Material.CHEST)
                        .name("§aDefinir valor")
                        .lore(Arrays.asList(
                                "§8Depósito Bancário",
                                " ",
                                "§fSaldo atual: §a" + account.getBalance(),
                                " ",
                                "§eClique para depositar!"))
                        .build(),
                event -> {

                });

        setItem(
                31,
                new ItemBuilder(Material.ARROW)
                        .name("§aVoltar")
                        .build(),
                event -> new BankMenu().open(player));

        super.open(player);
    }

}
