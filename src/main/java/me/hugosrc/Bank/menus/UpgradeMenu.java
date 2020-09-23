package me.hugosrc.Bank.menus;

import me.hugosrc.Bank.BankPlugin;
import me.hugosrc.Bank.entities.managers.LevelManager;
import me.hugosrc.Bank.entities.objects.Account;
import me.hugosrc.Bank.entities.objects.Level;
import me.hugosrc.Bank.util.builders.ItemBuilder;
import me.hugosrc.Bank.util.inventory.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class UpgradeMenu extends Menu {

    private final Account account;
    private final LevelManager levelManager;

    public UpgradeMenu(Account account) {
        super("Melhorias de Conta", 4);

        this.account = account;
        this.levelManager = BankPlugin.getInstance().getLevelManager();
    }

    @Override
    public void open(Player player) {

        int slot = 11;
        for (Level level : levelManager.getLevels()) {
            setItem(
                    slot,
                    new ItemBuilder(getStatusIcon(account.getCurrentLevel(), level))
                            .name("§aConta " + level.getName())
                            .lore(Arrays.asList(
                                    "§7Muda o tipo de conta para §aConta " + level.getName(),
                                    " ",
                                    "§fRendimento máximo: §6" + level.getMaxIncome(),
                                    "§fSaldo máximo: §6" + level.getMaxMoney(),
                                    " ",
                                    "§6Custo:",
                                    "§7" + level.getPrice(),
                                    " ",
                                    getStatusMessage(account.getCurrentLevel(), level)))
                            .build(),
                    event -> {
                        account.upLevel(level);
                        new UpgradeMenu(account).open(player);
                    });

            slot++;
        }

        setItem(31, new ItemBuilder(Material.ARROW).name("§aVoltar").build(), event -> new BankMenu().open(player));

        super.open(player);
    }

    private String getStatusMessage(Level currentLevel, Level hoverLevel) {
        int currentLevelIndex = levelManager.getLevels().indexOf(currentLevel);
        int hoverLevelIndex = levelManager.getLevels().indexOf(hoverLevel);

        if (currentLevelIndex == hoverLevelIndex) {
            return "§eEste é seu nível atual.";
        }

        if (currentLevelIndex > hoverLevelIndex) {
            return "§cVocê já desbloqueou esse nível!";
        }

        return "§aClique para desbloquear!";
    }

    private ItemStack getStatusIcon(Level currentLevel, Level hoverLevel) {
        int currentLevelIndex = levelManager.getLevels().indexOf(currentLevel);
        int hoverLevelIndex = levelManager.getLevels().indexOf(hoverLevel);

        if (currentLevelIndex == hoverLevelIndex) {
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 4).build();
        }

        if (currentLevelIndex > hoverLevelIndex) {
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 5).build();
        }

        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).build();
    }
}
