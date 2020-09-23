package me.hugosrc.Bank.menus;

import me.hugosrc.Bank.BankPlugin;
import me.hugosrc.Bank.entities.objects.Account;
import me.hugosrc.Bank.entities.objects.Bank;
import me.hugosrc.Bank.entities.objects.Transaction;
import me.hugosrc.Bank.entities.objects.TransactionType;
import me.hugosrc.Bank.util.builders.ItemBuilder;
import me.hugosrc.Bank.util.inventory.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BankMenu extends Menu {

    private final Bank bank;
    private Account account;

    public BankMenu() {
        super("Banqueiro", 4);
        bank = BankPlugin.getInstance().getBank();
    }

    @Override
    public void open(Player player) {
        this.account = bank.getAccounts().get(player.getName());

        setItem(
                11,
                new ItemBuilder(Material.CHEST)
                        .name("§aDepositar")
                        .lore(Arrays.asList(
                                "§fSaldo atual: §a" + account.getBalance() + " Coins",
                                " ",
                                "§7Guarde seu dinheiro no banco",
                                "§7enquanto se aventura por aí!",
                                " ",
                                "§7Você ganha §b"+ account.getCurrentLevel().getPercentageIncome() +"% em rendimentos §7a",
                                "§7cada 36 horas.",
                                " ",
                                "§fPróx. Rendimento: §b" + account.getProjection(),
                                " ",
                                "§eClique para depositar!"))
                        .build(),
                event -> new DepositMenu(account).open(player));

        setItem(
                13,
                new ItemBuilder(Material.DISPENSER)
                        .name("§cSacar")
                        .lore(Arrays.asList(
                                "§fSaldo atual: §a" + account.getBalance() + " Coins",
                                " ",
                                "§7Retire seu dinheiro do banco",
                                "§7para gastar como quiser.",
                                " ",
                                "§eClique para sacar!"))
                        .build(),
                event -> new WithdrawMenu(account).open(player));

        List<String> lastTransactions = new ArrayList<>();
        for (Transaction transaction : account.getLastTransactions()) {
            lastTransactions.add(transaction.serialize());
        }

        setItem(
                15,
                new ItemBuilder(Material.MAP)
                        .name("§eHistórico")
                        .lore(lastTransactions)
                        .build(),
                event -> {});

        setItem(
                31,
                new ItemBuilder(Material.NETHER_STAR)
                        .name("§6Melhorias de Conta")
                        .lore(Arrays.asList(
                                "§7Melhore seu tipo de conta para ter",
                                "§7rendimentos maiores e também para",
                                "§7poder ter mais dinheiro guardado.",
                                " ",
                                "§fConta Atual: §6" + account.getCurrentLevel().getName(),
                                "§fLimite de moedas: §6" + account.getCurrentLevel().getMaxMoney(),
                                " ",
                                "§eClique para evoluir!"))
                        .build(),
                event -> new UpgradeMenu(account).open(player));

        setItem(
                35,
                new ItemBuilder(Material.REDSTONE_TORCH_ON)
                        .name("§eInformações")
                        .lore(Arrays.asList(
                                "§7Mantenha suas moedas seguras no",
                                "§7banco! Você perde metade do saldo",
                                "§7da sua carteira ao morrer em combate.",
                                " ",
                                "§fLimite máximo: §6" + account.getCurrentLevel().getMaxMoney(),
                                " ",
                                "§7O banqueiro recompensa você a cada 36",
                                "§7horas com um §brendimento §7 de acordo com",
                                "§7suas moedas depositadas e o seu tipo de conta.",
                                " ",
                                "§fPróx. Rendimento: §b" + formatTime(account.getTimeToIncome()),
                                "§fProjeção: §a" + account.getProjection()))
                        .build(),
                event -> {});

        super.open(player);
    }

    private String formatTime(long time) {
        long currentSeconds = TimeUnit.MILLISECONDS
                .toSeconds(time - System.currentTimeMillis());

        int day = (int)TimeUnit.SECONDS.toDays(currentSeconds);
        long hours = TimeUnit.SECONDS.toHours(currentSeconds) - (day *24);
        long minutes = TimeUnit.SECONDS.toMinutes(currentSeconds) - (TimeUnit.SECONDS.toHours(currentSeconds)* 60);
        long seconds = TimeUnit.SECONDS.toSeconds(currentSeconds) - (TimeUnit.SECONDS.toMinutes(currentSeconds) *60);

        return day + "d, " + hours + "h, " + minutes + "m, " + seconds + "s.";
    }

}
