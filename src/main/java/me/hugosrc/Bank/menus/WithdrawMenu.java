package me.hugosrc.Bank.menus;

import me.hugosrc.Bank.util.inventory.Menu;
import me.hugosrc.Bank.util.builders.ItemBuilder;
import me.hugosrc.Bank.entities.objects.Account;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class WithdrawMenu extends Menu {

    private final Account account;

    public WithdrawMenu(Account account) {
        super("Escolha um valor", 4);

        this.account = account;
    }

    @Override
    public void open(Player player) {
        setItem(
                10,
                new ItemBuilder(Material.DISPENSER, 64)
                        .name("§aTudo")
                        .lore(Arrays.asList(
                                "§8Saque Bancário",
                                " ",
                                "§fSaldo atual: §a" + account.getBalance(),
                                "§fValor a retirar: §a" + account.getBalance(),
                                " ",
                                "§eClique para retirar!"))
                        .build(),
                event -> account.withdraw(account.getBalance()));

        setItem(
                12,
                new ItemBuilder(Material.DISPENSER, 32)
                        .name("§aMetade")
                        .lore(Arrays.asList(
                                "§8Saque Bancário",
                                " ",
                                "§fSaldo atual: §a" + account.getBalance(),
                                "§fValor a retirar: §a" + account.getBalance() / 2,
                                " ",
                                "§eClique para retirar!"))
                        .build(),
                event -> account.withdraw(account.getBalance() / 2));

        setItem(
                14,
                new ItemBuilder(Material.DISPENSER)
                        .name("§a20%")
                        .lore(Arrays.asList(
                                "§8Saque Bancário",
                                " ",
                                "§fSaldo atual: §a" + account.getBalance(),
                                "§fValor a retirar: §a" + (account.getBalance() * 20) / 100,
                                " ",
                                "§eClique para retirar!"))
                        .build(),
                event -> account.withdraw((account.getBalance() * 20) / 100));

        setItem(
                16,
                new ItemBuilder(Material.DISPENSER)
                        .name("§aDefinir valor")
                        .lore(Arrays.asList(
                                "§8Saque Bancário",
                                " ",
                                "§fSaldo atual: §a" + account.getBalance(),
                                " ",
                                "§eClique para retirar!"))
                        .build(),
                event -> account.withdraw(1000)); // TEMP <===== TODO: Sign input to get value)

        setItem(
                31,
                new ItemBuilder(Material.ARROW)
                        .name("§aVoltar")
                        .build(),
                event -> new BankMenu().open(player));

        super.open(player);
    }

}
