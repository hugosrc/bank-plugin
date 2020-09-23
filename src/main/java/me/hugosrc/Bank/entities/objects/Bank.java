package me.hugosrc.Bank.entities.objects;

import lombok.Getter;
import me.hugosrc.Bank.BankPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Getter
public class Bank {

    private final Map<String, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(Player player) {
        if (accounts.containsKey(player.getName())) {
            return;
        }

        // TODO: Database SELECT

        accounts.put(player.getName(), Account.builder()
                .owner(player)
                .balance(0)
                .timeToIncome(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(36))
                .currentLevel(BankPlugin.getInstance().getLevelManager().getLevels().get(0))
                .lastTransactions(new ArrayList<>())
                .build());
    }

    public void removeAccount(Player player) {
        if (!accounts.containsKey(player.getName())) return;

        // TODO: Database update or create

        accounts.remove(player.getName());
    }

    public void saveAll() {
//        for (String key : accounts.keySet()) {
//             Account account = accounts.get(key);
//
//             TODO: Database update or create
//        }
    }

}
