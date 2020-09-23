package me.hugosrc.Bank.tasks;

import me.hugosrc.Bank.BankPlugin;
import me.hugosrc.Bank.entities.objects.Account;
import me.hugosrc.Bank.entities.objects.Bank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class BankIncomeTask {

    private final Bank bank;

    public BankIncomeTask() {
        this.bank = BankPlugin.getInstance().getBank();
    }

    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(BankPlugin.getInstance(), () -> {

            for (String key : bank.getAccounts().keySet()) {
                Account account = bank.getAccounts().get(key);

                if (account.getTimeToIncome() <= System.currentTimeMillis()) {
                    account.setTimeToIncome(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(36));
                    account.setBalance(account.getBalance() + account.getProjection());

                    Player player = Bukkit.getPlayer(account.getOwner().getName());
                    if (player != null) {
                        player.sendMessage("§aFoi adicionado " + account.getProjection() + " coins de rendimento à sua conta.");
                    }
                }

                account.setTimeToIncome(account.getTimeToIncome() - TimeUnit.SECONDS.toMillis(1));
            }

        }, 0, 20L);
    }

}
